package compiler.compileLists;

import compiler.CompileList;
import compiler.tokenizer.*;

public class CompileListFactory {

	public CompileList getCompileList(Node node) {

		CompileList compList = null;
		NodeType nodeType = node.getToken();
		
		//TODO best implement method?
		//TODO loop threw end of nessesary

		switch (nodeType) {
		case VARIABELE:
			compList = new CompileLValue().compile(node, null);
			break;
		case IDENTIFIER:
			compList = new CompileFunction().compile(node, null);
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
			compList = new CompiledWhile().compile(node, null);
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
		case IF:
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
