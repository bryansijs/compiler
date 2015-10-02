package compiler;

import compiler.compileLists.CompileListFactory;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;
import compiler.tokenizer.linked_list;

public class Compiler {

	private CompileList list;
	private CompileList sublist;

	public Compiler() {
		list = new CompileList();
	}

	public CompileList getCompiledListFromTokenList(linked_list tokenList){
		Node node = tokenList.getFirst();
		
		CompileListFactory factory = new CompileListFactory();
		

		do{
			sublist = factory.getCompileList(node);
			list.add(sublist);
			skipToNextItem(node);
		}
		while(node != null);

		return list;
	}
	
	private void skipToNextItem(Node node){
		//skip to next item
		if(node.getToken() == NodeType.VARIABELE){
			boolean found = false;
			while(found){
				if(node.getToken() == NodeType.SEMICOLON)
					found = true;
				else
					node = node.getNext();
			}
		}
		node = node.getNext();
	}
}
