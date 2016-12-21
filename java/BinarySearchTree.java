import java.util.List;
import java.util.LinkedList;

class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
  T data;
  TreeNode<T> left;
  TreeNode<T> right;

  public TreeNode(T data) {
    this.data = data;
    left = right = null;
  }

  public int compareTo(TreeNode<T> other) {
    return data.compareTo(other.data);
  }

  public String toString() {
    return data.toString();
  }
}

public class BinarySearchTree<T extends Comparable<T>> {
  TreeNode<T> root;

  public BinarySearchTree() {
    root = null;
  }

  public boolean insert(T data) {
    TreeNode<T> t = new TreeNode<T>(data);
    return insert(root, t);
  }

  public boolean insert(TreeNode<T> t) {
    return insert(root, t);
  }

  public boolean insert(TreeNode<T> root, TreeNode<T> t) {
    if(this.root == null) {
      this.root = t;
      this.root.left = this.root.right = null;
      return true;
    }

    if(t.compareTo(root) <= 0) {
      if(root.left == null)
        root.left = t;
      else
        return insert(root.left, t);
    } else {
      if(root.right == null)
        root.right = t;
      else
        return insert(root.right, t);
    }
    return true;
  }

  public void printTreeBfs() {
    if(root == null) return;
    LinkedList<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
    queue.add(root);

    while(!queue.isEmpty()) {
      TreeNode<T> t = queue.removeFirst();
      if(t.left != null)
        queue.add(t.left);
      if(t.right != null)
        queue.add(t.right);

      System.out.print(t + "  ");
    }
    System.out.println("");
  }

  public static void join(TreeNode a, TreeNode b) {
    a.right = b;
    b.left = a;
  }

  public static TreeNode append(TreeNode a, TreeNode b) {
    if(a == null) return b;
    if(b == null) return a;

    TreeNode aLast = a.left;
    TreeNode bLast = b.left;

    join(aLast, b);
    join(bLast, a);

    return a;
  }

  public static TreeNode treeToList(TreeNode root) {
    if(root == null) return null;

    TreeNode left  = treeToList(root.left);
    TreeNode right = treeToList(root.right);
    root.left = root;
    root.right = root;

    left = append(left, root);
    left = append(left, right);

    return left;
  }

  public static void printCList(TreeNode head) {
    if(head == null) return;

    TreeNode n = head;
    do {
      System.out.print(n + "  ");
      n = n.right;
    } while(n != head);

    System.out.println("");
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(1);
    tree.insert(6);
    tree.printTreeBfs();
    TreeNode list = BinarySearchTree.treeToList(tree.root);
    BinarySearchTree.printCList(list);
  }
}
