package compiler.nodes;

import compiler.virtualMachine.Visitor.NodeVisitor;


public class Jump extends Action{
	public Action JumpToNode;
	public Jump(Action next, Action previous, Action JumpToNode) {
		super(next, previous);
		this.JumpToNode = JumpToNode;
		// TODO Auto-generated constructor stub
	}
	
	public Jump() {
		// TODO Auto-generated constructor stub
	}
	
	public void setJumpToNode( Action action){
		this.JumpToNode = action;
	}

	@Override
	public void Accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}
