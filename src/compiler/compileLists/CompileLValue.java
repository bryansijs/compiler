package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.Compiler;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileLValue extends AbstractCompiler {
	
	private CompileList compiledStatement;
    private String identifier;
	
    // LinkedList constructor
    public CompileLValue() {
    	
		compiledStatement = new CompileList();
		compiledStatement.add(new DoNothing());
	}
	
	@Override
	public CompileList compile(Node currentToken, compiler.Compiler compiler) {
        
        ArrayList<NodeType> expected = new ArrayList<NodeType>();
        
        expected.add(NodeType.VARIABELE);
        expected.add(NodeType.IDENTIFIER);
        expected.add(NodeType.ASSIGN); // Condition
        
        if(currentToken.getNext().getToken() == NodeType.IDENTIFIER){
        	identifier = currentToken.getNext().getValue().toString();
        }
        
        for(NodeType type: expected)
        {
        	if(type == currentToken.getToken()){
        		currentToken = currentToken.getNext();
        	}
        	else
        	{
        		throw new RuntimeException("Token niet verwacht: " + type.toString());
        	}
        }
        
        CompileRValue rValue = new CompileRValue();
        CompileList rValueCompiledList = rValue.compile(currentToken, compiler);
        
        compiledStatement.add(rValueCompiledList);
        
        DirectFunctionCall df = new DirectFunctionCall();
        df.parameters.add(0, "ReturnToVariable");
        df.parameters.add(1, this.identifier);
        
        compiledStatement.add(df);
        
        //currentToken = currentToken.getNext(); // after assign
        NodeType currentType = currentToken.getToken();
        if(NodeType.NUMBER ==  currentType || NodeType.IDENTIFIER ==  currentType || NodeType.FUNCTION ==  currentType || NodeType.OPERATOR ==  currentType){
    		currentToken = currentToken.getNext();
    	}
    	else
    	{
    		throw new RuntimeException("Token niet verwacht: " + currentType.toString());
    	}
        compiledStatement.add(new DoNothing());
        return compiledStatement;
	}

}
