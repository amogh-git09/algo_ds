import sys
from datetime import datetime

def find_max_subarray_brute(arr, low, high):
    maxsum = - sys.maxsize
    start = -1
    end = -1
    for len in range(1, (high - low + 2)):
        sum = find_sum(arr, low, low + len - 1)
        if sum > maxsum:
            maxsum = sum
            start = low
            end = low + len - 1
        for i in range(low + 1, (high - len + 2)):
            sum += arr[i + len - 1] - arr[i - 1]
            if sum > maxsum:
                maxsum = sum
                start = i
                end = i + len - 1
    return (start, end, maxsum)

def find_sum(arr, start, end):
    sum = 0
    for i in range(start, end + 1):
        sum += arr[i]
    return sum

def find_max_crossing_sub_array(arr, low, mid, high):
    leftsum = -sys.maxsize
    leftindex = mid
    sum = 0
    for i in range(mid, low-1, -1):
        sum += arr[i]
        if sum > leftsum:
            leftsum = sum
            leftindex = i

    rightsum = -sys.maxsize
    rightindex = mid + 1
    sum = 0
    for j in range(mid+1, high+1):
        sum += arr[j]
        if sum > rightsum:
            rightsum = sum
            rightindex = j

    return (leftindex, rightindex, leftsum + rightsum)

def find_max_subarray(arr, low, high):
    if high < low:
        return -1
    if low == high:
        return (low, high, arr[low])

    mid = (low + high) // 2
    ansleft = find_max_subarray(arr, low, mid)
    ansright = find_max_subarray(arr, mid+1, high)
    ansmid = find_max_crossing_sub_array(arr, low, mid, high)
    if ansleft[2] >= ansright[2] and ansleft[2] >= ansmid[2]:
        return ansleft
    elif ansright[2] >= ansleft[2] and ansright[2] >= ansmid[2]:
        return ansright
    else:
        return ansmid

def max_subarray_linear(arr, low, high):
    ans = [0, 0, arr[low]]
    helper = [0, 0, arr[low]]
    for i in range(low, high + 1):
        if helper[2] >= 0:
            helper[1] = i
            helper[2] += arr[i]
        else:
            helper[0] = helper[1] = i
            helper[2] = arr[i]
        if helper[2] > ans[2]:
            ans = helper[:]
    return ans

arr = [-3, 4, -8, -9, 10, 15, -15, 7, 10, 11, 12, -20, 10, 33, 12, -20, -40, 60, 50]
print(arr)

start = datetime.now()
ans = find_max_subarray_brute(arr, 0, len(arr) - 1)
delta = datetime.now() - start
print(ans)
print("brute time taken:", delta.microseconds)

start = datetime.now()
ans = find_max_subarray(arr, 0, len(arr) - 1)
delta = datetime.now() - start
print(ans)
print("recursive time taken:", delta.microseconds)

start = datetime.now()
ans = max_subarray_linear(arr, 0, len(arr) - 1)
delta = datetime.now() - start
print(ans)
print("linear time taken:", delta.microseconds)
