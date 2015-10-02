package compiler.compileLists;

import compiler.CompileList;
import compiler.Node;
import compiler.nodes.Action;

public abstract class Compiler {
	
	private CompileList compileList;
	private Action first;
	private Action last;

	public Compiler() {
		compileList = new CompileList();
	}

	public Compiler(Action first, Action last) {
		this.first = first;
		this.last = last;
	}

	public Action getFirst() {
		return first;
	}

	public void setFirst(Action first) {
		this.first = first;
	}
	
	public Action getLast() {
		return last;
	}

	public void setLast(Action last) {
		this.last = last;
	}
	
	public abstract CompileList compile(Node currentToken, Compiler compiler);
}
