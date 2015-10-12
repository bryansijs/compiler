package compiler.compileLists;

import com.sun.org.apache.xpath.internal.functions.FuncId;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.FunctionCall;
import compiler.nodes.Jump;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileFunction extends AbstractCompiler{

	private CompileList compiledStatement;

	
    // LinkedList constructor
    public CompileFunction() {
		compiledStatement = new CompileList();
	}
    
	@Override
	public CompileList compile(Node currentToken, compiler.Compiler compiler) {
		FunctionCall function = new FunctionCall();
		String functionName = currentToken.getValue().toString();
		
		while(currentToken.getToken() != NodeType.ELLIPSISOPEN){
			currentToken = currentToken.getNext();
		}
		currentToken = currentToken.getNext();
		
		
		CompileList param = new CompileRValue().compile(currentToken, compiler);
		function.parameters.add(0, functionName);
		compiledStatement.add(new DoNothing());
		compiledStatement.add(param);
		compiledStatement.add(function);
		compiledStatement.add(new DoNothing());
		return compiledStatement;
	}

}
