package compiler.compileLists;

import compiler.CompileList;
import compiler.tokenizer.Node;
import compiler.nodes.Action;

public abstract class AbstractCompiler {
	
	private CompileList compileList;
	private Action first;
	private Action last;

	public AbstractCompiler() {
		compileList = new CompileList();
	}

	public AbstractCompiler(Action first, Action last) {
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
	
	public abstract CompileList compile(Node currentToken, compiler.Compiler compiler);
}
