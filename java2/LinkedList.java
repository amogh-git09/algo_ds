class LinkedList<T extends Comparable<T>> {
  Node<T> head;

  public boolean addFront(Node<T> node) {
    node.next = head;
    head = node;
    return true;
  }

  public boolean addFront(T data) {
    Node<T> n = new Node<T>(data);
    return addFront(n);
  }

  public boolean addEnd(Node<T> node) {
    Node<T> n = head;

    if(n == null) {
      head = node;
      return true;
    }

    while(n.next != null) {
      n = n.next;
    }

    n.next = node;
    node.next = null;
    return true;
  }

  public boolean addEnd(T data) {
    Node<T> n = new Node<T>(data);
    return addEnd(n);
  }

  public boolean delete(Node<T> x) {
    if(x == head){
      head = head.next;
      x.next = null;
      return true;
    }

    Node<T> n = head;
    while(n != null && n.next != x) {
      n = n.next;
    }

    if(n == null)
      return false;

    n.next = x.next;
    x.next = null;
    return true;
  }

  public boolean delete(T data) {
    if(head == null)
      return false;

    if(head.data.equals(data)) {
      Node<T> n = head;
      head = head.next;
      n.next = null;
      return true;
    }

    Node<T> n = head;
    while(n != null && n.next != null && !n.next.data.equals(data)) {
      n = n.next;
    }

    if(n == null || n.next == null)
      return false;

    Node<T> x = n.next;
    n.next = x.next;
    x.next = null;
    return true;
  }

  public Node<T> getNode(T d) {
    if(head == null) return null;

    Node<T> n = head;
    while(n != null) {
      if(n.data.equals(d))
        return n;

      n = n.next;
    }
    return null;
  }

  private boolean deleteNext(Node<T> n){
    if(n == null || n.next == null)  return false;

    Node<T> x = n.next;
    n.next = x.next;
    x.next = null;
    return true;
  }

  public T deleteAtPos(int pos) {
    if(pos < 0 || head == null) return null;

    if(pos == 0){
      T val = head.data;
      delete(head);
      return val;
    }

    Node<T> n = head;
    for(int i=0; i<pos-1; i++) {
      if(n.next == null)
        return null;
      n = n.next;
    }

    T val = n.next.data;
    deleteNext(n);
    return val;
  }

  public int sizeRec(Node<T> n) {
    return n==null?0:(size(n.next)+1);
  }

  public int size(Node<T> n) {
    if(n == null) return 0;

    int size = 0;
    while(n != null){
      n = n.next;
      size++;
    }

    return size;
  }

  public int size() {
    return size(head);
  }

  //swaps the first occurrences of x and y
  public boolean swap(T x, T y) {
    if(x.equals(y)) return true;

    Node<T> a = head;
    Node<T> ap = null;
    while(a != null && !a.data.equals(x)) {
      ap = a;
      a = a.next;
    }

    Node<T> b = head;
    Node<T> bp = null;
    while(b != null && !b.data.equals(y)) {
      bp = b;
      b = b.next;
    }

    // x or y not present
    if(a == null || b == null) return false;

    //x is not head
    if(ap != null)
      ap.next = b;
    else
      head = b;

    //y is not head
    if(bp != null)
      bp.next = a;
    else
      head = a;

    //swap next pointers
    Node<T> tmp = a.next;
    a.next = b.next;
    b.next = tmp;
    return true;
  }

  public void reverseList() {
    if(head == null) return;

    Node<T> n = head;
    Node<T> nn = n.next;
    Node<T> np = null;

    while(nn != null) {
      n.next = np;
      np = n;
      n = nn;
      nn = nn.next;
    }

    n.next = np;
    head = n;
  }

  public void printList() {
    Node<T> n = head;
    while(n != null) {
      System.out.print(n + " -> ");
      n = n.next;
    }
    System.out.println("null");
  }

  public static <E extends Comparable<E>> void Rotate(LinkedList<E> list, int k) {
    list.head = Rotate(list.head, k);
  }

  public static <E extends Comparable<E>> Node<E> Rotate(Node<E> head, int k) {
    if(head == null || k <= 0) return head;

    Node<E> n = head;
    Node<E> prev = null;

    for(int i=0; i<k; i++) {
      if(n == null) return head;
      prev = n;
      n = n.next;
    }

    if(n == null) return head;

    prev.next = null;
    Node<E> last = n;
    while(last.next != null)
      last = last.next;

    last.next = head;
    return n;
  }

  public static <E extends Comparable<E>> LinkedList<E>
    Merge(LinkedList<E> l1, LinkedList<E> l2) {
    if(l1 == null || l1.head == null) return l2;
    if(l2 == null || l2.head == null) return l1;

    LinkedList<E> mergedList = new LinkedList<E>();
    Node<E> n = null;
    Node<E> n1 = l1.head;
    Node<E> n2 = l2.head;

    if(n1.data.compareTo(n2.data) < 0){
      n = n1;
      n1 = n1.next;
    }
    else {
      n = n2;
      n2 = n2.next;
    }

    mergedList.head = n;

    while(n1 != null || n2 != null){
      if(n1 == null) {
        n.next = n2;
        n = n2;
        n2 = n2.next;
      } else if(n2 == null) {
        n.next = n1;
        n = n1;
        n1 = n1.next;
      } else if(n1.data.compareTo(n2.data) <= 0) {
        n.next = n1;
        n = n1;
        n1 = n1.next;
      } else {
        n.next = n2;
        n = n2;
        n2 = n2.next;
      }
    }

    return mergedList;
  }

  public static <E extends Comparable<E>> LinkedList<E> MergeSort(LinkedList<E> list) {
    Node<E> n1 = list.head;
    Node<E> n2 = list.head;

    if(n1 == null || n1.next == null) {
      return list;
    }

    n1 = n1.next;

    while(n1 != null && n1.next != null) {
      n2 = n2.next;
      n1 = n1.next.next;
    }

    Node<E> tmp = n2.next;
    n2.next = null;
    n2 = tmp;
    n1 = list.head;

    LinkedList<E> l1 = new LinkedList<E>();
    LinkedList<E> l2 = new LinkedList<E>();
    l1.head = n1;
    l2.head = n2;

    l1 = MergeSort(l1);
    l2 = MergeSort(l2);
    return Merge(l1, l2);
  }

  public static <E extends Comparable<E>> LinkedList<E> reverse (LinkedList<E> list, int k) {
    list.head = reverse(list.head, k);
    return list;
  }

  public static <E extends Comparable<E>> Node<E> reverse (Node<E> head, int k) {
    if(k <= 0 || head == null) return head;

    Node<E> n = head;
    Node<E> next = head.next;
    Node<E> prev = null;

    for (int i=0; i<k; i++) {
      n.next = prev;
      prev = n;
      n = next;
      if(n == null) break;
      next = next.next;
    }

    head.next = reverse(n, k);
    return prev;
  }

  public boolean removeLoop () {
    if(head == null) return false;

    Node<T> fast = head;
    Node<T> slow = head;

    do {
      fast = fast.next;
      if(fast == null) return false;
      fast = fast.next;
      slow = slow.next;
    } while (fast != slow);

    int l = 0;
    do {
      slow = slow.next;
      l++;
    } while(slow != fast);

    slow = head;
    fast = head;

    for(int i=0; i<l; i++)
      fast = fast.next;

    while(fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }

    while(fast.next != slow) {
      fast = fast.next;
    }

    fast.next = null;
    return true;
  }

  public static LinkedList<Integer> Add(LinkedList<Integer> l1, LinkedList<Integer> l2) {
    Node<Integer> resultNode = Add(l1.head, l2.head, 0, null, null);
    LinkedList<Integer> result = new LinkedList<Integer>();
    result.head = resultNode;
    return result;
  }

  public static Node<Integer> Add(Node<Integer> n1, Node<Integer> n2,
    int carry, Node<Integer> digit, Node<Integer> head) {

    if(n1 == null && n2 == null) {
      if(carry == 0) {
        return head;
      } else {
        digit.next = new Node<Integer>(1);
        return head;
      }
    }

    int sum = carry;
    if(n1 != null) {
      sum += n1.data.intValue();
      n1 = n1.next;
    }
    if(n2 != null) {
      sum += n2.data.intValue();
      n2 = n2.next;
    }

    if(sum >= 10) {
      carry = 1;
      sum = sum%10;
    } else {
      carry = 0;
    }

    if(digit == null) {
      digit = new Node<Integer>(sum);
      return Add(n1, n2, carry, digit, digit);
    } else {
      digit.next = new Node<Integer>(sum);
      return Add(n1, n2, carry, digit.next, head);
    }
  }

  public static void main(String[] args) {
    LinkedList<Integer> list1 = new LinkedList<Integer>();
    LinkedList<Integer> list2 = new LinkedList<Integer>();
    list1.addEnd(7);
    list1.addEnd(8);
    list1.addEnd(9);
    list1.addEnd(1);
    list1.addEnd(3);
    list2.addEnd(5);
    list2.addEnd(4);
    list2.addEnd(2);
    list2.addEnd(6);
    list2.addEnd(9);
    list2.addEnd(0);
    list1.printList();
    list2.printList();
    LinkedList<Integer> list = LinkedList.Add(list1, list2);
    list.printList();
  }
}
