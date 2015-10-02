package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import jdk.nashorn.internal.parser.TokenLookup;
import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;
import compiler.tokenizer.linked_list;

public class Main {
	
	public static void main(String[] args) {	
		// Get tokens from code
		Tokenizer tokenizer = new Tokenizer();
		linked_list tokenList = tokenizer.getTokensFromCode("src/compiler/codeBasis.txt");
		
		Node first = tokenList.getFirst();
		for (int i = 0; i < tokenList.getListCount(); i++) {
			System.out.println(first.getToken().toString());
			first = first.getNext();
		}
		// Compile token list
		Compiler compiler = new Compiler();
		CompileList compileList = compiler.getCompiledListFromTokenList(tokenList);
		
		// Compiled list to virtual machine
	}
}