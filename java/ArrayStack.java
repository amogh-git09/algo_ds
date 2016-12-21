import java.lang.reflect.Array;

public class ArrayStack {
  int capacity;
  int top;
  int[] stack;

  public ArrayStack(int capacity) {
    if(capacity < 0) capacity = 0;
    this.capacity = capacity;
    top = -1;
    stack = new int[capacity];
  }

  public boolean push(int val) {
    if((top+1) >= capacity) {
      stack = makeCopy();
    }

    this.stack[++top] = val;
    return true;
  }

  private int[] makeCopy() {
    int[] newArr = new int[capacity*2];
    for(int i=0; i<=top; i++) {
      newArr[i] = stack[i];
    }

    this.capacity *= 2;
    return newArr;
  }

  public void printStack() {
    if(top == -1) return;

    for(int i=0; i<=top; i++) {
      System.out.print(stack[i] + "  ");
    }
    System.out.println("<-- top");
  }

  public Integer pop() {
    if(top < 0) return null;

    int val = stack[top--];
    return val;
  }

  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack(1);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    stack.printStack();
    System.out.println(stack.pop());
    stack.printStack();
    System.out.println(stack.pop());
    stack.printStack();
    System.out.println(stack.pop());
    stack.printStack();
    System.out.println(stack.pop());
    stack.printStack();
    System.out.println(stack.pop());
    stack.printStack();
    System.out.println(stack.pop());

  }
}
