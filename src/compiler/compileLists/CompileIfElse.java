package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileIfElse extends CompileIfGeneral{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
    private CompileList statement2;
    private CompileList compiledBodyPart;
    private boolean inElse;
    
	
    // LinkedList constructor
    public CompileIfElse() {
    	
    	compiledStatement = new CompileList();
        condition = new CompileList(true);
        statement = new CompileList(true);
        statement2 = new CompileList(true);
        inElse = false;

        ConditionalJump conditionalJumpNode = new ConditionalJump();
        Jump jump = new Jump();
        
        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode);
        compiledStatement.add(new DoNothing());
        compiledStatement.add(statement);
        compiledStatement.add(jump);
        compiledStatement.add(statement2);
        compiledStatement.add(new DoNothing());
        

        conditionalJumpNode.setNextTrue(conditionalJumpNode.getNext());
        conditionalJumpNode.setNextFalse(statement2.getFirst());
        jump.setJumpToNode(compiledStatement.getHead());
	}
    
    public CompileList compile(Node currentToken, compiler.Compiler compiler) {
    	int whileLevel = currentToken.getLevel();
    	
    	ArrayList<NodeType> expected = new ArrayList<NodeType>();
        
        expected.add(NodeType.IF);
        expected.add(NodeType.ELLIPSISOPEN);
        expected.add(NodeType.ANY); 
        expected.add(NodeType.ELLIPSISCLOSED);
        expected.add(NodeType.BRACKETSOPEN);
        expected.add(NodeType.ANY);
        expected.add(NodeType.BRACKETSCLOSE);
        expected.add(NodeType.ELSE);
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
        			if(currentToken.getToken() == NodeType.ELSE)
        			{
        				inElse = true;
        			}
        			currentToken = currentToken.getNext();
        		}
        	}
        	else
            {
        		if (condition.getListCount() == 2) // We komen eerst de conditie tegen, deze vullen we daarom eerst.
        		{
                   CompileCondition compiledCondition = new CompileCondition();
                   condition.insertListBeforeLast(compiledCondition.compile(currentToken, compiler));
                   
                   while(currentToken.getToken() != NodeType.ELLIPSISCLOSED) // Go until end statement
                   {
                	   currentToken = currentToken.getNext();
                   }
                }
                else
                { // Body
                	CompileListFactory factory = new CompileListFactory();
                    while(currentToken.getLevel() > whileLevel) // Zolang we in de body zitten mag de factory hiermee aan de slag. Dit is niet onze zaak.
                    {
                        compiledBodyPart = factory.getCompileList(currentToken,compiler);
                        
                        if(inElse)
                        	statement2.insertListBeforeLast(compiledBodyPart);
                        else
                        	statement.insertListBeforeLast(compiledBodyPart);
                        
                        while(currentToken.getToken() != NodeType.SEMICOLON) // Go until end statement
                        {
                     	   currentToken = currentToken.getNext();
                        }
                        currentToken = currentToken.getNext();
                    };
            	}
            }
        }        
        return compiledStatement;
	}
}
