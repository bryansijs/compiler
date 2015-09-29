package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class Main {

	private static linked_list list;
	private static HashMap<String, NodeType> map;
	private static Stack<NodeType> stack;

	private static boolean readContent;
	private static String numberRegex;
	private static String stringRegex;

	public static void main(String[] args) {
		// INIT
		int lineNumber = 0;
		int level = 0;

		list = new linked_list();
		readContent = true;
		map = new HashMap<String, NodeType>();
		stack = new Stack<NodeType>();

		// Settings
		numberRegex = "[0-9]+";
		stringRegex = "^\\*\\w*\\*+";

		fillHash(map);
		try (BufferedReader br = new BufferedReader(new FileReader("src/compiler/code.txt"))) {

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
							handleOpenAndCloseTags(part);

							// Single row comment
							if (part.equals("lees")) {
								break;
							}

							list.add(map.get(part), part, lineNumber, node - 1, level, 0);
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
	}

	private static void categorizeUnknownElement(String part, int lineNumber, int node, int level) {

		// Numbers
		if (part.matches(numberRegex)) {
			list.add(NodeType.NUMBER, part, lineNumber, node - 1, level, 0);
		}
		// START WITH *?
		else if (part.matches(stringRegex)) {
			list.add(NodeType.STRING, part, lineNumber, node - 1, level, 0);
		} else {
			// IDENTIFIER
			list.add(NodeType.IDENTIFIER, part, lineNumber, node - 1, level, 0);
		}
	}

	private static void handleOpenAndCloseTags(String part) {
		NodeType type = map.get(part);
		if (type == NodeType.BRACKETSOPEN || type == NodeType.ELLIPSISOPEN)
			stack.push(type);
		if (type == NodeType.BRACKETSCLOSE)
			if (stack.pop() != NodeType.BRACKETSOPEN)
				throw new RuntimeException("Geen opentag gevonden");
		if (type == NodeType.ELLIPSISCLOSED)
			if (stack.pop() != NodeType.ELLIPSISOPEN)
				throw new RuntimeException("Geen opentag gevonden");
	}

	private static void isLongComment(String part) {
		// COMMENT?
		if (part.equals("gelul"))
			readContent = false;
		if (part.equals("eindgelul"))
			readContent = true;
	}

	private static void fillHash(HashMap<String, NodeType> map) {
		map.put("lade", NodeType.VARIABELE);
		map.put("==", NodeType.EQUALS);
		map.put(";", NodeType.SEMICOLON);
		map.put("zolang", NodeType.WHILE);
		map.put("(", NodeType.ELLIPSISOPEN);
		map.put(")", NodeType.ELLIPSISCLOSED);
		map.put("{", NodeType.BRACKETSOPEN);
		map.put("}", NodeType.BRACKETSCLOSE);
		map.put("++", NodeType.PLUSVALUE);
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