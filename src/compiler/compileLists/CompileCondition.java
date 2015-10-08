package compiler.compileLists;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;
import compiler.tokenizer.Node;

public class CompileCondition extends AbstractCompiler {
	
	private CompileList compiledStatement;
    private CompileList condition;
	
    public CompileCondition() {
    	
		compiledStatement = new CompileList(true);
        condition = new CompileList(true);

        ConditionalJump conditionalJumpNode = new ConditionalJump();
        Action jumpBackNode = new Jump();

        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(new DoNothing());


        jumpBackNode.setNext(compiledStatement.getFirst()); // JumpToNode is een extra property ten opzichte van andere nodes.
        conditionalJumpNode.setNextFalse(compiledStatement.getHead());
	}

	@Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
		condition.insertBeforeLast(new ConditionalJump());
		System.out.println("Set True or false on return Value");
		return compiledStatement;
	}

}
