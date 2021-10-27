import java.util.Stack;

/**
 * Clase del nodo del árbol de expresión binario.
 */
class Node {
    /**
     * Almacena el value del nodo.
     */
    char value;
    /**
     * Almacena un puntero al hijo izquierdo y derecho del nodo.
     */
    Node left, right;

    /**
     * Construye el nodo.
     * @param item value del nodo
     */
    Node(char item) {
        value = item;
        left = right = null;
    }
}

/**
 * Clase del árbol de expresión binario.
 */
class ExpressionTree {

    /**
     * Verifica si el char es un operador.
     * @param c value del nodo
     * @return true si es un operador
     */
    boolean isOperator(char c) {
        return c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '%';
    }

    /**
     * Lee el arbol en notación infija.
     * @param t nodo root
     */
    void inorder(Node t) {
        if (t != null) {
            inorder(t.left);
            System.out.print(t.value + " ");
            inorder(t.right);
        }
    }

    /**
     * Construye el árbol de expresión apartir de un array con los caracteres en notación postfija.
     * @param postfix array con los caracteres en notación postfija
     * @return nodo root del árbol
     */
    Node constructTree(char[] postfix) {
        Stack<Node> st = new Stack<>();
        Node t, t1, t2;

        // Traverse through every character of
        // input expression
        for (int i = 0; i < postfix.length; i++) {

            // If operand, simply push into stack
            if (!isOperator(postfix[i])) {
                t = new Node(postfix[i]);
                st.push(t);
            } else // operator
            {
                t = new Node(postfix[i]);

                // Pop two top nodes
                // Store top
                t1 = st.pop();      // Remove top
                t2 = st.pop();

                //  make them children
                t.right = t1;
                t.left = t2;

                // System.out.println(t1 + "" + t2);
                // Add this subexpression to stack
                st.push(t);
            }
        }

        //  only element will be root of expression
        // tree
        t = st.peek();
        st.pop();

        return t;
    }

    /**
     * Evalua el árbol de expresión binaria.
     * @param root nodo root del árbol
     * @return resultado del árbol de expresión
     */
    public int evalTree(Node root)
    {
        // Empty tree
        if (root == null)
            return 0;

        // Leaf node i.e, an integer
        if (root.left == null && root.right == null)
            return Character.getNumericValue(root.value);

        // Evaluate left subtree
        int leftEval = evalTree(root.left);

        // Evaluate right subtree
        int rightEval = evalTree(root.right);

        // Check which operator to apply
        if (String.valueOf(root.value).equals("+"))
            return leftEval + rightEval;

        if (String.valueOf(root.value).equals("-"))
            return leftEval - rightEval;

        if (String.valueOf(root.value).equals("*"))
            return leftEval * rightEval;

        if (String.valueOf(root.value).equals("/"))
            return leftEval / rightEval;

        if (String.valueOf(root.value).equals("%"))
            return leftEval % rightEval;

        return 0;
    }

    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        String postfix = "23+34*4*-";
        char[] charArray = postfix.toCharArray();
        Node root = et.constructTree(charArray);
        System.out.println("infix expression is");
        et.inorder(root);
        System.out.println("= " + et.evalTree(root));

    }
}