package compiler.compileLists;

import java.util.ArrayList;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;

public class CompileLValue extends AbstractCompiler {
	
	private CompileList compiledStatement;
    private CompileList assign;
	
    // LinkedList constructor
    public CompileLValue() {
    	
		compiledStatement = new CompileList();
        assign = new CompileList();

        compiledStatement.add(new DoNothing());
        compiledStatement.insertBeforeLast(new DirectFunctionCall());
        compiledStatement.add(new DoNothing());
	}
	
	@Override
	public CompileList compile(Node currentToken, AbstractCompiler compiler) {
        
        ArrayList<NodeType> expected = new ArrayList<NodeType>();
        
        expected.add(NodeType.VARIABELE);
        expected.add(NodeType.IDENTIFIER);
        expected.add(NodeType.ASSIGN); // Condition
        expected.add(NodeType.NUMBER);
        expected.add(NodeType.OPERATOR);
        expected.add(NodeType.NUMBER); // Body
        expected.add(NodeType.SEMICOLON);
        
        for(NodeType type: expected)
        {
        	if(type == currentToken.getToken()){
        		currentToken = currentToken.getNext();
        	}
        	else
        	{
        		throw new RuntimeException("Token niet verwacht: " + type.toString());
        	}
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

}
