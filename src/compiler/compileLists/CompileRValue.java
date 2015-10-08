package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.nodes.FunctionCall;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileRValue extends AbstractCompiler {
	
	private CompileList compiledStatement;
    private CompileList assign;
	
    // LinkedList constructor
    public CompileRValue() {
    	
		compiledStatement = new CompileList();
        assign = new CompileList();

        compiledStatement.add(new DoNothing());
        compiledStatement.add(new DirectFunctionCall());
        compiledStatement.add(new DoNothing());
	}
	
	@Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
        CompileList list = new CompileList();
		
		String leftVariableName = (String)currentToken.getValue();
        String rightVariableName = (String)currentToken.getNext().getNext().getValue();
     // iets + ietsAnders;
     		if(currentToken.getToken() == NodeType.NUMBER)
     		{
     			leftVariableName = "$001";
     			DirectFunctionCall df = new DirectFunctionCall();
     			df.parameters.set(0, "ConstToReturn");
     			df.parameters.set(1, currentToken.getValue().toString()); // 3
     			list.add(df);
     			
     			DirectFunctionCall df2 = new DirectFunctionCall();
     			df2.parameters.set(0,"ReturnToVariable");
     			df2.parameters.set(1,leftVariableName);
     			list.add(df2);
     		}
     		if(currentToken.getNext().getNext().getToken() == NodeType.NUMBER)
     		{
     			rightVariableName = "$002";
     			
     		}
     		
		
		if(currentToken.getNext().getToken() == NodeType.OPERATOR && currentToken.getNext().getValue().toString() == "+")
		{// identifier
			FunctionCall fc = new FunctionCall();
			fc.parameters.set(0, "Add");
			fc.parameters.set(1, leftVariableName);
			fc.parameters.set(2, rightVariableName);
			list.add(fc);
		}
		
		return list;
	}

}
