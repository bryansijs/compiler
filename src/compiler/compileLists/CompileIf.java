package compiler.compileLists;

import compiler.CompileList;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;

public class CompileIf extends CompileIfGeneral{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
	
    // LinkedList constructor
    public CompileIf() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList();
        statement = new CompileList();

        ConditionalJump conditionalJumpNode = new ConditionalJump();
        
        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode);
        compiledStatement.add(new DoNothing());
        compiledStatement.add(statement);
        compiledStatement.add(new DoNothing());

        conditionalJumpNode.setNextTrue(conditionalJumpNode.getNext());
        conditionalJumpNode.setNextFalse(compiledStatement.getHead());
	}
}
