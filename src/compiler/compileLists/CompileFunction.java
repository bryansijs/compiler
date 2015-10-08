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
        condition = new CompileList(true);
        statement = new CompileList(true);
        statement2 = new CompileList(true);
        
        Jump jumpNode = new Jump();
        ConditionalJump conditionalJumpNode = new ConditionalJump();
        DoNothing elseNothing = new DoNothing();
        
        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode);
        compiledStatement.add(new DoNothing());
        compiledStatement.add(statement);
        compiledStatement.add(jumpNode);
        compiledStatement.add(elseNothing);
        compiledStatement.add(statement2);
        compiledStatement.add(new DoNothing());

        jumpNode.setJumpToNode(compiledStatement.getHead());
        conditionalJumpNode.setNextTrue(conditionalJumpNode.getNext());
        conditionalJumpNode.setNextFalse(elseNothing);
	}
    
	@Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
		// TODO Auto-generated method stub
		return compiledStatement;
	}

}
