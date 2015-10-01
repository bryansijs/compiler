package compiler;

import compiler.nodes.Action;

public class compiler_list {

	private Action head;
	private Action first;
	private int listCount;

	// LinkedList constructor
	public compiler_list() {
		head = null;
		first = null;
		listCount = 0;
	}
	
	//TODO insert method for specify node
	//TODO getLastNode for sublist

	public void add(NodeType token, String value, int ruleNumber, int positionInRule, int level, int partner) {
		
	}

	public Action getHead() {
		return head;
	}

	public Action getFirst() {
		return first;
	}

	public int getListCount() {
		return this.listCount;
	}
}
