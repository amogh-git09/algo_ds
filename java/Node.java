public class Node<T> {
  T data;
  Node<T> next;

  Node(T data) {
    this.data = data;
    next = null;
  }

  public String toString(){
    return data.toString();
  }
}
