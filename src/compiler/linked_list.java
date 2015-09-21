package compiler;

public class linked_list {

	private Node head;
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
			head = new Node(null,null,token,value,ruleNumber,positionInRule,level,partner);
			listCount++;
		} else {
			//Not the first.
			Node node = new Node(null, head, token, value, ruleNumber,positionInRule, level, partner);//Prev
			head.setNext(node);//Next
			head = node;
			listCount++;
		}
	}
}
