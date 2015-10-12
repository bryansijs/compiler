package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.nodes.FunctionCall;
import compiler.nodes.Jump;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileCondition extends AbstractCompiler {
	
	private CompileList compiledStatement;
	
    public CompileCondition() {
		compiledStatement = new CompileList();
	}

	@Override
	public CompileList compile(Node currentToken, compiler.Compiler compiler) {
		CompileList list = new CompileRValue().compile(currentToken, compiler);
		compiledStatement.add(list);
		return compiledStatement;
	}

}
