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
        for i in range(low + 1, (high - len + 1)):
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

arr = [3, -1, 4, -4, 10, 15, -8, 9, 10, 23, -10, -10, 50, -30, -20, 22, 10, -20, 15, 11, -9, -10, 2, -22]
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
