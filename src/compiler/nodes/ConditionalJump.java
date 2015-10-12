package compiler.nodes;

import compiler.virtualMachine.Visitor.NodeVisitor;

public class ConditionalJump extends Action {
	
	private Action nextTrue;
	private Action nextFalse;
	
	public ConditionalJump() {
		// TODO Auto-generated constructor stub
	}
	
	public ConditionalJump(Action next, Action previous) {
		super(next, previous);
		// TODO Auto-generated constructor stub
	}
	
	public Action getNextTrue() {
		return nextTrue;
	}

	public void setNextTrue(Action nextTrue) {
		this.nextTrue = nextTrue;
	}

	public Action getNextFalse() {
		return nextFalse;
	}

	public void setNextFalse(Action nextFalse) {
		this.nextFalse = nextFalse;
	}

	@Override
	public void Accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}
