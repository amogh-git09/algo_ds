import java.util.Stack;

public class EvaluatePostfix {
  private Stack<Integer> stack = new Stack<Integer>();

  private boolean isOperand(String ch) {
    if(ch.length() != 1)
      return true;

    switch(ch.charAt(0)) {
      case '+':
      case '-':
      case '*':
      case '/':
        return false;
      default:
        return true;
    }
  }

  public Integer evaluate(String expr) {
    String[] tokens = expr.split(" ");

    for(int i=0; i<tokens.length; i++) {
      String elem = tokens[i];

      if(isOperand(elem)) {
        stack.push(Integer.parseInt(elem));
      } else {
        int right = stack.pop();
        int left = stack.pop();
        int result = -1;

        switch(elem.charAt(0)) {
          case '+':
            result = left + right;
            break;
          case '-':
            result = left - right;
            break;
          case '*':
            result = left * right;
            break;
          case '/':
            result = left / right;
            break;
        }

        stack.push(result);
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    EvaluatePostfix evaluator = new EvaluatePostfix();
    System.out.println(evaluator.evaluate("10 2 101 33 4 * + * +"));
  }
}
