import java.util.Stack;

/**
 * Esta clase convierte un string de notación infija a postfija.
 */
public class InfixToPostfix {

    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence

    /**
     * Este método se encarga de definir un nivel de importancia dependiendo del operador, a mayor importancia mayor el valor retornado.
     * @param ch char del operador
     * @return nivel de importancia
     */
    static int Prec(char ch) {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '%':
                return 3;
        }
        return -1;
    }

    // The main method that converts
    // given infix expression
    // to postfix expression.

    /**
     * Este método se encarga de retornar un string en notación postfija a partir de un string en notación infija.
     * @param exp string en notación infija
     * @return string en notación postfija
     */
    public static String toPostfix(String exp){
        // initializing empty String for result
        StringBuilder result = new StringBuilder(new String(""));

        // initializing empty stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result.append(c);

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);

                //  If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() &&
                        stack.peek() != '(')
                    result.append(stack.pop());

                stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c)
                        <= Prec(stack.peek())){

                    result.append(stack.pop());
                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result.append(stack.pop());
        }
        return result.toString();
    }
}