class DNode<T> {
  T data;
  DNode<T> next;
  DNode<T> prev;

  public DNode(T data) {
    this.data = data;
    next = prev = null;
  }

  public String toString() {
    return data.toString();
  }
}

public class DoublyLinkedList<T> {
  DNode<T> head;

  public DoublyLinkedList() {
    head = null;
  }

  public boolean insert(T data) {
    DNode<T> node = new DNode<T>(data);
    return insert(node);
  }

  public boolean insert(DNode<T> node) {
    if(node == null) return false;
    if(head == null) {
      head = node;
      return true;
    }

    node.next = head;
    head.prev = node;
    head = node;
    return true;
  }

  public boolean insertAfter(DNode<T> node, T data) {
    if(head == null) return false;

    //find data
    DNode<T> n = head;
    while(n != null && !n.data.equals(data)) {
      n = n.next;
    }

    //data not found
    if(n == null) return false;

    //insert after n
    return insertAfter(node, n);
  }

  public boolean insertAfter(T data, T before) {
    DNode<T> node = new DNode<T>(data);
    return insertAfter(node, before);
  }

  //insert node after 'before' node
  public boolean insertAfter(DNode<T> n, DNode<T> before) {
    if(n == null) return false;
    if(before == null) return false;

    DNode<T> nn = before.next;

    n.next = nn;
    n.prev = before;
    before.next = n;

    if(nn != null) {
      nn.prev = n;
    }

    return true;
  }

  public boolean append(T data) {
    DNode<T> node = new DNode<T>(data);
    return append(node);
  }

  public boolean append(DNode<T> node) {
    if(head == null) return insert(node);

    DNode<T> n = head;
    while(n.next != null) {
      n = n.next;
    }

    n.next = node;
    node.prev = n;
    return true;
  }

  public boolean delete(T data) {
    if(head == null) return false;

    DNode<T> n = head;
    while(n != null && !n.data.equals(data)) {
      n = n.next;
    }

    return delete(n);
  }

  public boolean delete(DNode<T> n) {
    if(n == null) return false;

    DNode<T> next = n.next;
    DNode<T> prev = n.prev;
    n.next = null;
    n.prev = null;
    if(n == head)
      head = next;
    else
      prev.next = next;

    if(next != null)
      next.prev = prev;

    return true;
  }

  public void reverse() {
    if(head == null) return;

    DNode<T> n = head;
    while(n != null) {
      if(n.next == null)
        head = n;

      DNode<T> tmp = n.next;
      n.next = n.prev;
      n.prev = tmp;
      n = tmp;
    }
  }

  public void printList() {
    DNode<T> n = head;
    while(n != null) {
      System.out.print(n + " -> ");
      n = n.next;
    }
    System.out.println("null");
  }

  public static <T> void swap(DoublyLinkedList<T> list, T a, T b) {
    if(list.head == null) return;

    DNode<T> aNode = list.head;
    DNode<T> bNode = list.head;

    while(aNode != null && !aNode.data.equals(a)) {
      aNode = aNode.next;
    }

    while(bNode != null && !bNode.data.equals(b)) {
      bNode = bNode.next;
    }

    if(aNode == null || bNode == null) return;

    swap(list, aNode, bNode);
  }

  private static <T> void swap(DoublyLinkedList<T> list, DNode<T> a, DNode<T> b) {
    if(a == null || b == null || a == b) return;

    if(b.next == a) {
      DNode<T> tmp = a;
      a = b;
      b = tmp;
    }

    DNode<T> aPrev = a.prev;
    DNode<T> aNext = a.next;
    DNode<T> bPrev = b.prev;
    DNode<T> bNext = b.next;

    if(a.next == b) {
      if(aPrev == null)
        list.head = b;
      else
        aPrev.next = b;

      if(bNext != null)
        bNext.prev = a;

      a.next = bNext;
      a.prev = b;
      b.next = a;
      b.prev = aPrev;
      return;
    }

    if(aPrev == null)
      list.head = b;
    else
      aPrev.next = b;

    if(aNext != null)
      aNext.prev = b;

    if(bPrev == null)
      list.head = a;
    else
      bPrev.next = a;

    if(bNext != null)
      bNext.prev = a;

    a.next = bNext;
    a.prev = bPrev;
    b.next = aNext;
    b.prev = aPrev;
  }

  public static <T> void swapData(DNode<T> a, DNode<T> b) {
    T tmp = a.data;
    a.data = b.data;
    b.data = tmp;
  }

  public static <E extends Comparable<E>> DNode<E> QuickSort(DoublyLinkedList<E> list) {
    if(list.head == null || list.head.next == null) return list.head;

    DNode<E> last = list.head.next;
    while(last.next != null)
      last = last.next;

    return QuickSort(list, list.head, last);
  }

  public static <E extends Comparable<E>> DNode<E> QuickSort(
    DoublyLinkedList<E> list, DNode<E> l, DNode<E> r) {

    if(l == null || r == null || l == r || r.next == l) return l;

    DNode<E> wall = l;
    DNode<E> n = l;
    DNode<E> pivot = r;

    while(n != r) {
      if(n.data.compareTo(pivot.data) <= 0) {
        DoublyLinkedList.swapData(wall, n);
        wall = wall.next;
      }

      n = n.next;
    }

    DoublyLinkedList.swapData(wall, n);
    QuickSort(list, l, wall.prev);
    QuickSort(list, wall.next, r);

    return l;
  }

  public static <E extends Comparable<E>> DoublyLinkedList<E>
    Merge(DoublyLinkedList<E> l1, DoublyLinkedList<E> l2) {
    if(l1 == null || l1.head == null) return l2;
    if(l2 == null || l2.head == null) return l1;

    DNode<E> n1 = l1.head;
    DNode<E> n2 = l2.head;
    DNode<E> head = DoublyLinkedList.Merge(n1, n2);
    DoublyLinkedList<E> list = new DoublyLinkedList<E>();
    list.head = head;
    return list;
  }

  public static <E extends Comparable<E>> DNode<E> Merge(DNode<E> n1, DNode<E> n2) {
    DNode<E> nHead = null;
    DNode<E> next = null;
    DNode<E> prev = null;

    while(n1 != null || n2 != null) {
      if(n2 == null) {
        next = n1;
        n1 = n1.next;
        if(n1 != null)
          n1.prev = null;
      } else if(n1 == null) {
        next = n2;
        n2 = n2.next;
        if(n2 != null)
          n2.prev = null;
      } else if(n1.data.compareTo(n2.data) <= 0) {
        next = n1;
        n1 = n1.next;
        if(n1 != null)
          n1.prev = null;
      } else {
        next = n2;
        n2 = n2.next;
        if(n2 != null)
          n2.prev = null;
      }

      if(nHead == null) nHead = next;
      if(prev != null) {
        prev.next = next;
        next.prev = prev;
      }
      prev = next;
    }

    return nHead;
  }

  public static <E extends Comparable<E>> DoublyLinkedList<E> MergeSort(DoublyLinkedList<E> list) {
    if(list == null || list.head == null) return list;
    list.head = DoublyLinkedList.MergeSort(list.head);
    return list;
  }

  public static <E extends Comparable<E>> DNode<E> MergeSort (DNode<E> head) {
    if(head == null) return null;
    if(head.next == null) return head;

    DNode<E> fast = head;
    DNode<E> slow = head;

    while(fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    DNode<E> prev = slow.prev;
    prev.next = null;
    slow.prev = null;

    DoublyLinkedList.MergeSort(head);
    DoublyLinkedList.MergeSort(slow);

    head = DoublyLinkedList.Merge(head, slow);
    return head;
  }

  public static void main(String[] args) {
    DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
    list1.append(1);
    list1.append(2);
    list1.append(3);
    list1.append(40);
    list1.append(41);
    list1.append(42);
    list1.append(4);
    list1.append(5);
    list1.append(6);
    list1.append(7);
    list1.append(20);
    list1.append(22);
    list1.printList();

    list1 = DoublyLinkedList.MergeSort(list1);
    list1.printList();
  }
}
