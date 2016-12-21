import java.util.Stack;

public class InfixToPostfix {
  private Stack<Character> stack;

  public InfixToPostfix() {
    stack = new Stack<Character>();
  }

  private int precedence(char c) {
    switch(c) {
      case '+':
      case '-':
        return 0;
      case '*':
      case '/':
        return 1;
      case '^':
        return 2;
      default:
        return -1;
    }
  }

  private boolean isOperand(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  public String convert(String expr) {
    StringBuilder str = new StringBuilder();

    for(int i=0; i<expr.length(); i++) {
      char c = expr.charAt(i);

      if(isOperand(c)){
        str.append(c);
      } else {
        if(c == '(') {
          stack.push(c);
        } else if(c == ')') {
          char tmp;
          while(!stack.empty() && ((tmp = stack.pop()) != '('))
            str.append(tmp);
        } else if(stack.empty() || (precedence(c) > precedence(stack.peek()))) {
          stack.push(c);
        } else {
          char tmp;
          do {
            tmp = stack.pop();
            str.append(tmp);
          } while(!stack.empty() && precedence(stack.peek()) >= precedence(c));
          stack.push(c);
        }
      }
    }

    while(!stack.empty())
      str.append(stack.pop());
    return str.toString();
  }

  public static void main(String[] args) {
    String expr = "a+b*(c^d-e)^(f+g*h)-i";
    InfixToPostfix converter = new InfixToPostfix();
    System.out.println(converter.convert(expr));
  }
}
