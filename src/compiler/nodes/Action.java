package compiler.nodes;
import compiler.virtualMachine.Visitor.*;

public abstract class Action {
	private Action next;
	private Action previous;
	
	public abstract void Accept( NodeVisitor visitor);
	
	public Action(){
		
	}
	
	public Action(Action next, Action previous){
		this.next = next;
		this.previous = previous;
	}
	
	public Action getNext() {
		return next;
	}

	public void setNext(Action next) {
		this.next = next;
	}

	public Action getPrevious() {
		return previous;
	}

	public void setPrevious(Action previous) {
		this.previous = previous;
	}
}
