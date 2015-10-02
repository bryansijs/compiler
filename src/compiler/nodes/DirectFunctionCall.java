package compiler.nodes;



public class DirectFunctionCall extends AbstractFunctionCall{
	ActionType actionType;
	int input;
	
	public DirectFunctionCall() {
		// TODO Auto-generated constructor stub
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
}
