package compiler;

public class Node {
    // reference to the next node in the chain,
    // or null if there isn't one.
    Node next;
    
    //reference to the previous node in the chain
    //or null if there isn't one.
    Node previous;
    
    // data carried by this node.
    // could be of any type you need.
    Object data;

    // Node constructor
    // Makes and empty node. No next or previous.
    public Node(Object dataValue) {
        next = null;
        previous = null;
        data = dataValue;
    }

    // another Node constructor if we want to
    // specify the node to point to or points from.
    // you can send null on this;
    public Node(Object dataValue, Node nextValue, Node previousValue) {
        next = nextValue;
        previous = previousValue;
        data = dataValue;
    }

    // these methods should be self-explanatory
    public Object getData() {
        return data;
    }

    public void setData(Object dataValue) {
        data = dataValue;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious()
    {
    	return previous;
    }
    
    public void setNext(Node nextValue) {
        next = nextValue;
    }
    
    public void setPrevious(Node previousValue)
    {
    	previous = previousValue;
    }

}
