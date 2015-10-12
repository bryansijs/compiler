package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;


public class CompiledWhile extends AbstractCompiler{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
    private CompileList compiledBodyPart;
	
    // LinkedList constructor
    public CompiledWhile() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList(true);
        statement = new CompileList(true);

        ConditionalJump conditionalJumpNode = new ConditionalJump();
        Jump jumpBackNode = new Jump();
        
        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode); // De body komt dus rechtstreeks na de conditionalJumpNode (dus op de .Next property)
        compiledStatement.add(statement);
        compiledStatement.add(jumpBackNode);
        compiledStatement.add(new DoNothing());


        jumpBackNode.setJumpToNode(compiledStatement.getFirst()); 
        conditionalJumpNode.setNextTrue(statement.getFirst());// NextOnTrue en NextOnFalse zijn extra properties ten opzichte van andere nodes.
        conditionalJumpNode.setNextFalse(compiledStatement.getHead());
	}
    
    public CompileList compile(Node currentToken, compiler.Compiler compiler)
    {
        int whileLevel = currentToken.getLevel();
        
        ArrayList<NodeType> expected = new ArrayList<NodeType>();
        
        expected.add(NodeType.WHILE);
        expected.add(NodeType.ELLIPSISOPEN);
        expected.add(NodeType.ANY); // Condition
        expected.add(NodeType.ELLIPSISCLOSED);
        expected.add(NodeType.BRACKETSOPEN);
        expected.add(NodeType.ANY); // Body
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
        		if (condition.getListCount() == 2) // We komen eerst de conditie tegen, deze vullen we daarom eerst.
        		{
                   CompileCondition compiledCondition = new CompileCondition();
                   condition.add(compiledCondition.compile(currentToken, null));   // dn->dn-> Rvalue TODO: clean up
                   while(currentToken.getToken() != NodeType.ELLIPSISCLOSED) // Go until end statement
                   {
                	   currentToken = currentToken.getNext();
                   }
                }
                else
                {
                	//Body
                	CompileListFactory factory = new CompileListFactory();
                	CompileList body = new CompileList();
                    while(currentToken.getLevel() > whileLevel) // Zolang we in de body zitten mag de factory hiermee aan de slag. Dit is niet onze zaak.
                    {
                        compiledBodyPart = factory.getCompileList(currentToken,compiler);
                        body.add(compiledBodyPart);
                        while(currentToken.getToken() != NodeType.SEMICOLON) // Go until end statement
                        {
                     	   currentToken = currentToken.getNext();
                        }
                        currentToken = currentToken.getNext();
                        
                    }
                    statement.add(body);
            	}
            }
        }
        return compiledStatement;
    }
}
