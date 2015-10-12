package compiler.nodes;

import compiler.virtualMachine.Visitor.NodeVisitor;


public class DoNothing extends Action{

	public DoNothing(Action next, Action previous) {
		super(next, previous);
	}
	public DoNothing() {
		super();
	}
	@Override
	public void Accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}
