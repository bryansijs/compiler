package compiler.compileLists;

import compiler.CompileList;
import compiler.tokenizer.Node;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;

public class CompileDoWhile extends AbstractCompiler {

	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
	
    // LinkedList constructor
    public CompileDoWhile() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList();
        statement = new CompileList();

        ConditionalJump conditionalJumpNode = new ConditionalJump();

        compiledStatement.add(new DoNothing());
        compiledStatement.add(statement);
        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode); // De body komt dus rechtstreeks na de conditionalJumpNode (dus op de .Next property)
        compiledStatement.add(new DoNothing());

        conditionalJumpNode.setNextTrue(compiledStatement.getFirst());// NextOnTrue en NextOnFalse zijn extra properties ten opzichte van andere nodes.
        conditionalJumpNode.setNextFalse(compiledStatement.getHead());
	}

	@Override
	public CompileList compile(Node currentToken, compiler.Compiler compiler) {
		// TODO Auto-generated method stub
		return null;
	}
}
