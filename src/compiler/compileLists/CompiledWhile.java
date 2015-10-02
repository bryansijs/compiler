package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.Node;
import compiler.NodeType;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;


public class CompiledWhile extends Compiler{
	
	private CompileList compiledStatement;
    private CompileList condition;
    private CompileList statement;
	
    // LinkedList constructor
    public CompiledWhile() {
    	
		compiledStatement = new CompileList();
        condition = new CompileList();
        statement = new CompileList();

        ConditionalJump conditionalJumpNode = new ConditionalJump();
        Action jumpBackNode = new Jump();

        compiledStatement.add(new DoNothing());
        compiledStatement.add(condition);
        compiledStatement.add(conditionalJumpNode); // De body komt dus rechtstreeks na de conditionalJumpNode (dus op de .Next property)
        compiledStatement.add(statement);
        compiledStatement.add(jumpBackNode);
        compiledStatement.add(new DoNothing());


        jumpBackNode.setNext(compiledStatement.getFirst()); // JumpToNode is een extra property ten opzichte van andere nodes.
        conditionalJumpNode.setNextTrue(statement.getFirst());// NextOnTrue en NextOnFalse zijn extra properties ten opzichte van andere nodes.
        conditionalJumpNode.setNextFalse(compiledStatement.getHead());
	}
    
    public CompileList compile(Node currentToken, Compiler compiler)
    {
        int whileLevel = currentToken.positionInList;
        
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
//            if (currentToken.get == whileLevel)
//            {
//                if (currentToken.Value.Token != expectation.TokenType)
//                {
//                    throw new Exception(String.Format("Unexpected end of statement, expected {0}", expectation.TokenType));
//                }
//                else
//                {
//                    currentToken = currentToken.Next;
//                }
//            }
//            else if (expectation.Level >= whileLevel)
//            {
//                if (_condition == null) // We komen eerst de conditie tegen, deze vullen we daarom eerst.
//                {
//                    var compiledCondition = new CompiledCondition();
//                    compiledCondition.Compile(ref currentToken, compiler);
//                    _condition.Add(compiledCondition.Compiled);
//                }
//                else
//                {
//                    while(currentToken.Value.Level > whileLevel) // Zolang we in de body zitten mag de factory hiermee aan de slag. Dit is niet onze zaak.
//                    {
//                        var compiledBodyPart = CompilerFactory.Instance.CreateCompiledStatement(currentToken.Value.Token);
//                        compiledBodyPart.Compile(ref currentToken, compiler);
//                        _body.Add(compiledBodyPart.Compiled);
//                    };
//                }
//            }
        }
        return compiledStatement;
    }


//	public void TokenExpectation()
//	{
//	    public int Level;
//	    public TokenType TokenType;
//	
//	    public TokenExpectation(int level, TokenType tokenType)
//	    {
//	        Level = level;
//	        TokenType = tokenType;
//	    }
//	}
}
