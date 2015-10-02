package compiler.compileLists;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;

public class CompileIfElse extends CompileIfGeneral{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
    private CompileList statement2;
	
    // LinkedList constructor
    public CompileIfElse() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList();
        statement = new CompileList();
        statement2 = new CompileList();
        
        Action jumpNode = new Jump();
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

        jumpNode.setNext(compiledStatement.getHead());
        conditionalJumpNode.setNextTrue(conditionalJumpNode.getNext());
        conditionalJumpNode.setNextFalse(elseNothing);
	}
}
