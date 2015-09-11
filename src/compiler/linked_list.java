package compiler;

public class linked_list {
	  
	private Node head;
	private int listCount;
	 
	// LinkedList constructor
	public linked_list() {
	// this is an empty list, so the reference to the head node
	// is set to a new node with no data
		head = new Node(null);
		listCount = 0;
	}
	 
	
	 public void add(Object data)
	    // appends the specified element to the end of this list.
	    {
	        Node node = new Node(data);
	        Node currentNode = head;
	        // starting at the head node, crawl to the end of the list
	        while (currentNode.getNext() != null) {
	        	currentNode = currentNode.getNext();
	        }
	        // the last node's "next" reference set to our new node
	        currentNode.setNext(node);
	        listCount++;// increment the number of elements variable
	    }
	 
}




