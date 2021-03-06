package compiler.compileLists;

import compiler.CompileList;
import compiler.tokenizer.*;

public class CompileListFactory {

	public CompileList getCompileList(Node node, compiler.Compiler compiler) {

		CompileList compList = null;
		NodeType nodeType = node.getToken();
		
		//TODO best implement method?
		//TODO loop threw end of nessesary

		switch (nodeType) {
		case VARIABELE:
			compList = new CompileLValue().compile(node, compiler);
			break;
		case IDENTIFIER:
			compList = new CompileFunction().compile(node, compiler);
			break;
		case ASSIGN:
			//compList = new OdbcConnection();
			break;
		case NUMBER:
			break;
		case OPERATOR:
			break;
		case SEMICOLON:
			break;	
		case WHILE:
			compList = new CompiledWhile().compile(node, compiler);
			break;
		case ELLIPSISOPEN:
			break;
		case NOTEQUALS:
			break;
		case ELLIPSISCLOSED:
			break;
		case BRACKETSOPEN:
			break;
		case BRACKETSCLOSE:
			break;
		case FUNCTION:
			compList = new CompileFunction().compile(node, compiler);
			break;
		case IF:
			Node finder = node;
			while(finder.getToken() != NodeType.BRACKETSCLOSE && finder != null)//TODO & not null
			{
				finder = finder.getNext();
			}
			finder = finder.getNext();
			if(finder.getToken() == NodeType.ELSE)
				compList = new CompileIfElse().compile(node, compiler);
			else
				compList = new CompileIf().compile(node, compiler);
			break;
		case ELSE:
			break;
		case EQUALS:
			break;
		default:
			throw new RuntimeException("not found in factory: " + nodeType.toString());
		}
		return compList;
	}
}
