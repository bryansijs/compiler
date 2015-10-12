package compiler.nodes;

import java.util.ArrayList;

import compiler.virtualMachine.Visitor.NodeVisitor;



public class DirectFunctionCall extends AbstractFunctionCall{
	ActionType actionType;
	int input;
	
	public DirectFunctionCall() {
		// TODO Auto-generated constructor stub
		parameters = new ArrayList<String>();
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}

	@Override
	public void Accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}
