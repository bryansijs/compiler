package compiler.nodes;

public class ConditionalJump extends Action {
	
	private static boolean result;
	private static Action nextTrue;
	private static Action nextFalse;
	
	public ConditionalJump() {
		// TODO Auto-generated constructor stub
	}
	public static Action getNextTrue() {
		return nextTrue;
	}
	
	public Action getNextStep(){
		if(result)
			return getNextTrue();
		else
			return getNextFalse();
	}

	public static void setNextTrue(Action nextTrue) {
		ConditionalJump.nextTrue = nextTrue;
	}

	public static Action getNextFalse() {
		return nextFalse;
	}

	public static void setNextFalse(Action nextFalse) {
		ConditionalJump.nextFalse = nextFalse;
	}

	public ConditionalJump(Action next, Action previous) {
		super(next, previous);
		// TODO Auto-generated constructor stub
	}
	

	public void setConditionalResult(Boolean bool) {
		this.result = bool;
	}
}
