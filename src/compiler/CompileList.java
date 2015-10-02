package compiler;

import compiler.nodes.Action;
import compiler.nodes.DoNothing;

public class CompileList {
	private Action head;
	private Action first;
	private int listCount;

	// LinkedList constructor
	public CompileList() {
		first = new DoNothing(null, null);
		head = new DoNothing(null, first);
		first.setNext(head);
		
		listCount += 2;
	}
	
	public void insertBefore(Action action, Action before ){
		
	}

	public void add(Action token) {
		//Not the first.
		head.setNext(token);//Next
		head = token;
		listCount++;	
	}
	
	public void add(CompileList list){
		list.first.setPrevious(this.head);
		this.head.setNext(list.first);
	}
	
	public Action getHead(){
		return head;
	}
	
	public Action getFirst(){
		return first;
	}
	
	public int getListCount(){
		return this.listCount;
	}
}
