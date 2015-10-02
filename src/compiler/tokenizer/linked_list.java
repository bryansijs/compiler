package compiler.tokenizer;



public class linked_list {

	private Node head;
	private Node first;
	private int listCount;

	// LinkedList constructor
	public linked_list() {
		head = null;
		listCount = 0;
	}

	public void add(NodeType token, String value,
			int ruleNumber, int positionInRule, int level, int partner) {
		if (head == null) {
			//First item in List
			Node node = new Node(null,null,token,value,ruleNumber,positionInRule,level,partner,listCount);
			head = node;
			first = node;
			listCount++;
		} else {
			//Not the first.
			Node node = new Node(null, head, token, value, ruleNumber,positionInRule, level, partner,listCount);//Prev
			head.setNext(node);//Next
			head = node;
			listCount++;
		}
	}
	
	public Node getHead(){
		return head;
	}
	
	public Node getFirst(){
		return first;
	}
	
	public int getListCount(){
		return this.listCount;
	}
}
