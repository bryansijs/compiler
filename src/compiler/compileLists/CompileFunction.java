package compiler.compileLists;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;
import compiler.tokenizer.Node;

public class CompileFunction extends AbstractCompiler{

	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
    private CompileList statement2;
	
    // LinkedList constructor
    public CompileFunction() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList();
        statement = new CompileList();
        statement2 = new CompileList();
        
        Action jumpNode = new Jump();
        ConditionalJump conditionalJumpNode = new ConditionalJump();
        DoNothing elseNothing = new DoNothing();
        
        compiledStatement.add(condition);
        compiledStatement.insertBeforeLast(conditionalJumpNode);
        compiledStatement.add(new DoNothing());
        compiledStatement.add(statement);
        compiledStatement.insertBeforeLast(jumpNode);
        compiledStatement.insertBeforeLast(elseNothing);
        compiledStatement.add(statement2);

        jumpNode.setNext(compiledStatement.getHead());
        conditionalJumpNode.setNextTrue(conditionalJumpNode.getNext());
        conditionalJumpNode.setNextFalse(elseNothing);
	}
    
	@Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
		// TODO Auto-generated method stub
		return compiledStatement;
	}

}
