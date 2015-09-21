package compiler;

public class Node {
	private Node next;
	private Node previous;
	private NodeType Token;
	private Object value;
	private int ruleNumber;
	private int positionInRule;
	private int Level;
	private int partner;

	public Node(Node next, Node previous, NodeType token, String value,
			int ruleNumber, int positionInRule, int level, int partner) {
		super();
		this.next = next;
		this.previous = previous;
		Token = token;
		this.value = value;
		this.ruleNumber = ruleNumber;
		this.positionInRule = positionInRule;
		Level = level;
		this.partner = partner;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node node) {
		this.next = node;
	}

	public Node getPrevious() {
		return previous;
	}

	public NodeType getToken() {
		return Token;
	}

	public Object getValue() {
		return value;
	}

	public int getRuleNumber() {
		return ruleNumber;
	}

	public int getPositionInRule() {
		return positionInRule;
	}

	public int getLevel() {
		return Level;
	}

	public int getPartner() {
		return partner;
	}
}
