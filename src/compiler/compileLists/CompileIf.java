package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileIf extends CompileIfGeneral{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
    private CompileList compiledBodyPart;
	
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
    
    @Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
    	int whileLevel = currentToken.positionInList;
    	
    	ArrayList<NodeType> expected = new ArrayList<NodeType>();
        
        expected.add(NodeType.IF);
        expected.add(NodeType.ELLIPSISOPEN);
        expected.add(NodeType.ANY); 
        expected.add(NodeType.ELLIPSISCLOSED);
        expected.add(NodeType.BRACKETSOPEN);
        expected.add(NodeType.ANY);
        expected.add(NodeType.BRACKETSCLOSE);
        
        for(NodeType type: expected)
        {
        	if (currentToken.getLevel() == whileLevel)
        	{
        		if (currentToken.getToken() != type)
                {
        				throw new RuntimeException("Unexpected end of statement, expected: " + type.toString());
                }
        		else{
        			currentToken = currentToken.getNext();
        		}
        	}
        	else
            {
        		if (condition == null) // We komen eerst de conditie tegen, deze vullen we daarom eerst.
        		{
                   CompileCondition compiledCondition = new CompileCondition();
                   condition.add(compiledCondition.compile(currentToken, null));              
                }
                else
                {
                	CompileListFactory factory = new CompileListFactory();
                	CompileList body = new CompileList();
                    while(currentToken.getLevel() > whileLevel) // Zolang we in de body zitten mag de factory hiermee aan de slag. Dit is niet onze zaak.
                    {
                        compiledBodyPart = factory.getCompileList(currentToken);
                        body.add(compiledBodyPart);
                    };
            	}
            }
        }        
        return compiledStatement;
	}
}
