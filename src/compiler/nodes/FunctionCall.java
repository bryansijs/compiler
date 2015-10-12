package compiler.nodes;

import java.util.ArrayList;

import compiler.virtualMachine.Visitor.NodeVisitor;

public class FunctionCall extends AbstractFunctionCall{

	public FunctionCall(){
		parameters = new ArrayList<String>();
	}

	@Override
	public void Accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}
