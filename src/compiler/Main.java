package compiler;

import compiler.nodes.Action;
import compiler.tokenizer.Node;
import compiler.tokenizer.linked_list;

public class Main {
	
	public static void main(String[] args) {	
		// Get tokens from code
		Tokenizer tokenizer = new Tokenizer();
		linked_list tokenList = tokenizer.getTokensFromCode("src/compiler/codeBasis.txt");
		
		//printTokenizer(tokenList);
		
		// Compile token list
		Compiler compiler = new Compiler();
		CompileList compileList = compiler.getCompiledListFromTokenList(tokenList);
		printCompilerList(compileList);
		// Compiled list to virtual machine
	}
	
	public static void printTokenizer(linked_list tokenList){
		Node first = tokenList.getFirst();
		for (int i = 0; i < tokenList.getListCount(); i++) {
			System.out.println(first.getValue() + " LEVEL: " + first.getLevel());
			first = first.getNext();
		}
	}
	
	public static void printCompilerList(CompileList compileList){
		Action action = compileList.getFirst();
		
		while(action != null){
			System.out.println(action.getClass());
			action = action.getNext();
		}
		
	}
}