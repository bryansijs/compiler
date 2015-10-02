package compiler.nodes;


public class DoNothing extends Action{

	public DoNothing(Action next, Action previous) {
		super(next, previous);
	}
	public DoNothing() {
		super();
	}
}
