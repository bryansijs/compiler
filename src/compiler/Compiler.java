package compiler;

import compiler.compileLists.CompileListFactory;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;
import compiler.tokenizer.linked_list;

public class Compiler {

	private CompileList list;
	private CompileList sublist;
	private Node node;
	private int lastAssignedVariableName = 0;

	public Compiler() {
		list = new CompileList();
	}
	
	public String getNextVariableName(){
		return "$" + lastAssignedVariableName++;
	}

	public CompileList getCompiledListFromTokenList(linked_list tokenList){
		node = tokenList.getFirst();
		
		CompileListFactory factory = new CompileListFactory();
		
		boolean endText = false;
		do{
			sublist = factory.getCompileList(node, this);
			System.out.println(sublist.getListCount());
			list.add(sublist);
			endText = skipToNextItem();
		}
		while(!endText);

		return list;
	}
	
	private boolean skipToNextItem(){
		//skip to next item
		if(node.getToken() == NodeType.VARIABELE || node.getToken() == NodeType.FUNCTION){
			//Go until ;
			boolean found = false;
			while(!found){
				if(node.getToken() == NodeType.SEMICOLON)
					found = true;
				else
					node = node.getNext();
			}
		}
		else if(node.getToken() == NodeType.IF || node.getToken() == NodeType.ELSE || node.getToken() == NodeType.WHILE){
			//Go until }
			boolean found = false;
			while(!found){
				if(node.getToken() == NodeType.BRACKETSCLOSE)
					found = true;
				else
					node = node.getNext();
			}
		}
		node = node.getNext();
		if(node == null)
			return true;
		
		if(node.getToken() == NodeType.ELSE){
			return skipToNextItem();
		}
		
		return false;
	}
}
