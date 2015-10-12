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
	public CompileList compile(Node currentToken, compiler.Compiler compiler) {
        CompileList list = new CompileList();
		
		String leftVariableName = (String)currentToken.getValue();
        String rightVariableName = (String)currentToken.getNext().getNext().getValue();
        
        if(currentToken.getNext().getToken() == NodeType.ELLIPSISCLOSED){
        	// only get var to return
        	DirectFunctionCall df = new DirectFunctionCall();
        	df.parameters.add(0, "ConstToReturn");
 			df.parameters.add(1, currentToken.getValue().toString()); // 3
 			list.add(df);
 			return list;
        }

 		if(currentToken.getToken() == NodeType.NUMBER)
 		{
 			leftVariableName = compiler.getNextVariableName();
 			DirectFunctionCall df = new DirectFunctionCall();

 			df.parameters.add(0, "ConstToReturn");
 			df.parameters.add(1, currentToken.getValue().toString()); // 3
 			list.add(df);
 			
 			if(currentToken.getNext().getToken() != NodeType.SEMICOLON)
 			{
 				// lade x = 4;
     			DirectFunctionCall df2 = new DirectFunctionCall();
     			df2.parameters.add(0,"ReturnToVariable");
     			df2.parameters.add(1,leftVariableName);
     			list.add(df2);
 			}
 			
 		}
 		if(currentToken.getNext().getNext().getToken() == NodeType.NUMBER)
 		{
 			rightVariableName = compiler.getNextVariableName();
 			
 			DirectFunctionCall df3 = new DirectFunctionCall();

 			df3.parameters.add(0, "ConstToReturn");
 			df3.parameters.add(1, currentToken.getNext().getNext().getValue().toString()); // 6
 			list.add(df3);
 			
 			DirectFunctionCall df4 = new DirectFunctionCall();
 			df4.parameters.add(0,"ReturnToVariable");
 			df4.parameters.add(1,rightVariableName);
 			list.add(df4);
 		}
 		
		
		if(currentToken.getNext().getToken() == NodeType.OPERATOR && currentToken.getNext().getValue().toString().equals("+"))
		{	// Plus
			FunctionCall fc = new FunctionCall();
			fc.parameters.add(0, "Add");
			fc.parameters.add(1, leftVariableName);
			fc.parameters.add(2, rightVariableName);
			list.add(fc);
		}
		
		if(currentToken.getNext().getToken() == NodeType.OPERATOR && currentToken.getNext().getValue().toString().equals("-"))
		{	// min
			FunctionCall fc = new FunctionCall();
			fc.parameters.add(0, "Min");
			fc.parameters.add(1, leftVariableName);
			fc.parameters.add(2, rightVariableName);
			list.add(fc);
		}
		
		if(currentToken.getNext().getToken() == NodeType.NOTEQUALS)
		{	// !=
			FunctionCall fc = new FunctionCall();
			fc.parameters.add(0, "NotEqual");
			fc.parameters.add(1, leftVariableName);
			fc.parameters.add(2, rightVariableName);
			list.add(fc);
		}
		if(currentToken.getNext().getToken() == NodeType.EQUALS)
		{	// !=
			FunctionCall fc = new FunctionCall();
			fc.parameters.add(0, "Equal");
			fc.parameters.add(1, leftVariableName);
			fc.parameters.add(2, rightVariableName);
			list.add(fc);
		}
		
		
		
		return list;
	}

}
