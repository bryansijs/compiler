package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import compiler.tokenizer.Node;
import compiler.tokenizer.NodeType;
import compiler.tokenizer.linked_list;

public class Tokenizer {
	
	private static linked_list list;
	private static HashMap<String, NodeType> map;
	private static Stack<Node> stack;

	private static boolean readContent;
	private static String numberRegex;
	private static String stringRegex;
	private static String identifierRegex;
	private static int level;
	
	public Tokenizer(){
		
	}
	
	public linked_list getTokensFromCode(String path){
		// INIT
		int lineNumber = 0;
		level = 0;
		
		list = new linked_list();
		readContent = true;
		map = new HashMap<String, NodeType>();
		stack = new Stack<Node>();
		
		// Settings
		numberRegex = "[0-9]+";
		stringRegex = "^\\*\\w*\\*+";
		identifierRegex = "[A-Za-z]*";
		
		fillHash(map);
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		
			String line = br.readLine();
			int node = 0;
		
			while (line != null) {
				lineNumber++;
		
				String[] parts = line.split("\\s+");
				for (String part : parts) {
					node++;
					isLongComment(part); // Check if element is a long comment
					if (readContent) {
						if (map.containsKey(part)) {
							// known element
							
							// Single row comment
							if (part.equals("lees")) {
								break;
							}
							handleCloseTagLevel(part); // Level lower for close tags
							list.add(map.get(part), part, lineNumber, node - 1, level, 0);
							handleOpenAndCloseTags(part,list.getHead(),lineNumber);
							
						} else {
							// Unknown element
							categorizeUnknownElement(part, lineNumber, node, level);
						}
					}
				}
				line = br.readLine();
				node = 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("good syntax!");
		return this.list;
	}
	
	private void categorizeUnknownElement(String part, int lineNumber, int node, int level) {

		// Numbers
		if (part.matches(numberRegex)) {
			list.add(NodeType.NUMBER, part, lineNumber, node - 1, level, 0);
		}
		// START WITH *?
		else if (part.matches(stringRegex)) {
			list.add(NodeType.STRING, part, lineNumber, node - 1, level, 0);
		} 
		
		else if(part.matches(identifierRegex)){
			// IDENTIFIER
			if(!part.equals(""))	
				list.add(NodeType.IDENTIFIER, part, lineNumber, node - 1, level, 0);
		}
		else{
			throw new RuntimeException("Onbekend teken gevonden: "+ part +  " op regel:" + lineNumber);
		}
	}
	
	private void handleCloseTagLevel(String part){
		if (part.equals("}")||part.equals(")"))
		{
			level = level -1;
		}
	}

	private void handleOpenAndCloseTags(String part, Node newNode, int lineNumber) {
		NodeType type = map.get(part);
		if (type == NodeType.BRACKETSOPEN || type == NodeType.ELLIPSISOPEN)
		{
			stack.push(newNode);
			level++;
		}
		
		if (type == NodeType.IF)// if does not have a higher level
			stack.push(newNode);
		
		if (type == NodeType.BRACKETSCLOSE)
			if (stack.isEmpty() || stack.peek().getToken() != NodeType.BRACKETSOPEN)
				throw new RuntimeException("Geen opentag gevonden bij de sluittag op regel:" + lineNumber);
			else
			{
				Node oldNode = stack.pop();
				newNode.setPartner(oldNode.positionInList);
				oldNode.setPartner(newNode.positionInList);
			}
		if (type == NodeType.ELLIPSISCLOSED)
			if (stack.isEmpty() || stack.peek().getToken() != NodeType.ELLIPSISOPEN)
				throw new RuntimeException("Geen opentag gevonden bij de sluittag op regel:" + lineNumber);
			else
			{
				Node oldNode = stack.pop();
				newNode.setPartner(oldNode.positionInList);
				oldNode.setPartner(newNode.positionInList);
			}
		if (type == NodeType.ELSE)
			if (stack.isEmpty() || stack.peek().getToken() != NodeType.IF)
				throw new RuntimeException("Geen opentag gevonden bij de sluittag op regel:" + lineNumber);
			else
			{
				Node oldNode = stack.pop();
				newNode.setPartner(oldNode.positionInList);
				oldNode.setPartner(newNode.positionInList);
			}
	}

	private void isLongComment(String part) {
		// COMMENT?
		if (part.equals("gelul"))
			readContent = false;
		if (part.equals("eindgelul"))
			readContent = true;
	}

	private void fillHash(HashMap<String, NodeType> map) {
		map.put("lade", NodeType.VARIABELE);
		map.put("==", NodeType.EQUALS);
		map.put("print", NodeType.FUNCTION);
		map.put("!=", NodeType.NOTEQUALS);
		map.put(";", NodeType.SEMICOLON);
		map.put("zolang", NodeType.WHILE);
		map.put("(", NodeType.ELLIPSISOPEN);
		map.put(")", NodeType.ELLIPSISCLOSED);
		map.put("{", NodeType.BRACKETSOPEN);
		map.put("}", NodeType.BRACKETSCLOSE);
		map.put("++", NodeType.PLUSVALUE);
		map.put("+", NodeType.OPERATOR);
		map.put("-", NodeType.OPERATOR);
		map.put("/", NodeType.OPERATOR);
		map.put("*", NodeType.OPERATOR);
		map.put("als", NodeType.IF);
		map.put("alsanders", NodeType.IFELSE);
		map.put("anders", NodeType.ELSE);
		map.put("gelul", NodeType.COMMENTOPEN);
		map.put("eindgelul", NodeType.COMMENTCLOSE);
		map.put("lees", NodeType.COMMENTLINE);
		map.put("schrijf", NodeType.ECHO);
		map.put("=", NodeType.ASSIGN);
		map.put("voor", NodeType.FOR);
	}
}
