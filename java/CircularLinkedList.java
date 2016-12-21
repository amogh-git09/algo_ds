class TwoNodeWrapper<E> {
  Node<E> node1;
  Node<E> node2;

  public TwoNodeWrapper(Node<E> node1, Node<E> node2) {
    this.node1 = node1;
    this.node2 = node2;
  }
}

class CircularLinkedList<T> {
  Node<T> head;

  public boolean insert(T data) {
    Node<T> n = new Node<T>(data);
    return insert(n);
  }

  public boolean insert(Node<T> node) {
    if(head == null) {
      head = node;
      head.next = head;
      return true;
    }

    node.next = head;
    Node<T> last = head;

    while(last.next != head)
      last = last.next;

    last.next = node;
    head = node;

    return true;
  }

  public static <E extends Number> void SortedInsert(CircularLinkedList<E> list, Node<E> node) {
    if(list.head == null) {
      list.insert(node);
      return;
    }

    if(node.data.intValue() <= list.head.data.intValue()) {
      list.insert(node);
      return;
    }

    Node<E> n = list.head;
    while(n.next.data.intValue() < node.data.intValue() && n.next != list.head) {
      n = n.next;
    }

    Node<E> tmp = n.next;
    n.next = node;
    node.next = tmp;
  }

  public static <E extends Number> void SortedInsert(CircularLinkedList<E> list, E data) {
    Node<E> node = new Node<E>(data);
    CircularLinkedList.SortedInsert(list, node);
  }

  public static <E> TwoNodeWrapper<E> SplitInTwoHalves(CircularLinkedList<E> list) {
    if(list == null) return null;

    return SplitInTwoHalves(list.head);
  }

  public static <E> TwoNodeWrapper<E> SplitInTwoHalves(Node<E> head) {
    if(head == null) return null;

    if(head.next == head) {
      return new TwoNodeWrapper<E>(head, null);
    }

    Node<E> fast = head;
    Node<E> slow = head;

    do {
      slow = slow.next;
      fast = fast.next.next;
    } while(fast.next != head && fast != head);

    Node<E> fastLast = fast;
    Node<E> slowLast = slow;

    while(fastLast.next != slow)
      fastLast = fastLast.next;

    while(slowLast.next != fast)
      slowLast = slowLast.next;

    fastLast.next = fast;
    slowLast.next = slow;

    return new TwoNodeWrapper<E>(fast, slow);
  }

  public void printList() {
    if(head == null) return;
    Node<T> n = head;

    while(n.next != head) {
      System.out.print(n + " --> ");
      n = n.next;
    }

    System.out.println(n);
  }

  public static void main(String[] args) {
    CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
    CircularLinkedList.SortedInsert(list, 5);
    CircularLinkedList.SortedInsert(list, 3);
    CircularLinkedList.SortedInsert(list, 1);
    CircularLinkedList.SortedInsert(list, 2);
    CircularLinkedList.SortedInsert(list, 10);
    CircularLinkedList.SortedInsert(list, 9);
    list.printList();
  }
}
