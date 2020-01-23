package insulinadjustmentcalc;

/**
 * This class uses a stack based on a linked list
 * @author trevor
 */
public class linkedStack {
    /**
     * The Node class implements the linked list
     */
    private class Node {
        String val;
        Node next;
        
        /**
         * Constructor
         */
        Node(String v, Node n) {
            val = v;
            next = n;
        }
    }
    
     private Node top = null; // The top of the stack
        
    /**
     * The empty method checks for an empty 
     */
     public boolean empty() {
         return top == null;
     }
     
     /**
      * The push method adds a new item to the stack
      * @param s The item being pushed onto the stack
      */
     public void push(String s) {
         top = new Node (s, top);
     }
     
     /**
      * The pop method removes the value at the top of the stack.
      * @return The value at the top of the stack.
      * @exception EmptyStackException when the stack is empty
      */
    public String pop() {
      if (empty())
          throw new EmptyStackException();
      else {
          String retVal = top.val;
          top = top.next;
          return retVal;
        }
    }
    
    /**
     * The peek method returns the top value on the stack.
     * @return The value at the top of the stack
     * @exception EmptyStackException when the stack is empty
     */
    public String peek() {
        if (empty())
                throw new EmptyStackException();
        else
            return top.val;
    }
    
    /**
     * The toString method computes a string a string representation of the 
     * contents of the stack.
     * @return The String that is computed
     */
    public String toString() {
        StringBuilder strBld = new StringBuilder();
        Node p = top;
        while (p != null) {
            strBld.append(p.val);
            p = p.next;
            if(p != null)
                strBld.append("\n");
        }
        return strBld.toString();
    }
    
    /**
     * The isNextEmpty method
     * @return true if node.next is equal to null
     */
    public boolean isNextEmpty(){
        Node nNode = top;
        if(nNode.next == null){
            return true;
        }
        else
            return false;
    }
    class EmptyStackException extends RuntimeException {
        public EmptyStackException(){
            System.out.println("Error: The stack is empty. There aren't any nodes "
                + "to be peeked at or popped of the stack.");
        }
    }
}

