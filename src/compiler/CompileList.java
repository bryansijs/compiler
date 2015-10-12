package compiler;

import compiler.nodes.Action;
import compiler.nodes.DoNothing;

public class CompileList {
	private Action head;
	private Action first;
	private int listCount;

	// LinkedList constructor
	public CompileList() {
	}
	
	public CompileList(boolean vullen)
	{
		if(vullen){
			this.head = new DoNothing(null,null);
			this.first = new DoNothing(head, null);
			head.setPrevious(first);
			listCount = 2;
		}
	}
	
	public void insertBeforeLast(Action action){
		if(first == null)
		{
			// eerste item in list
			first = action;
			head = action;
			listCount++;
		}
		else if(listCount < 2)
		{
			// tweede item in list
			head = action;
			first.setNext(action);
			head.setPrevious(first);
			listCount++;
		}
		else
		{
			// Al meerdere items in list
			Action oldPrev = head.getPrevious();
			head.setPrevious(action);
			action.setNext(head);
			action.setPrevious(oldPrev);
			oldPrev.setNext(action);
			listCount++;
		}
	}

	public void add(Action token) {
		if(first == null){
			first = token;
			head = token;
			listCount++;
		}
		else
		{
			//Not the first.
			head.setNext(token);//Next
			token.setPrevious(head);
			head = token;
			listCount++;
		}
	}
	
	public void add(CompileList list){
		if(first == null)
		{
			first = list.first;
			head = list.head;
			listCount = list.listCount;
		}
		else
		{
			head.setNext(list.getFirst());
			list.first.setPrevious(this.head);
			this.head.setNext(list.first);
			listCount =  listCount + list.getListCount();
			head = list.head;
		}
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
