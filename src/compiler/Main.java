package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.classfile.LineNumber;

public class Main {

	private static linked_list list;

	public static void main(String[] args) {
		int lineNumber = 0;
		int charNumber = 0;
		int level = 0;
		boolean readContent = true;
		
		String numberRegex = "[0-9]+"; 
		String stringRegex = "^\\*\\w*\\*+";

		HashMap<String, NodeType> map = new HashMap<String, NodeType>();
		fillHash(map);

		list = new linked_list();

		try (BufferedReader br = new BufferedReader(new FileReader(
				"src/compiler/code.txt"))) {

			String line = br.readLine();
			int node = 0;

			while (line != null) {
				lineNumber++;

				String[] parts = line.split("\\s+");
				for (String part : parts) {
					node++;
					//COMMENT?
					if (part.equals("gelul"))
						readContent = false;
					if(part.equals("eindgelul"))
						readContent = true;

					if (readContent) {
						if (map.containsKey(part)) {
							if (part.equals("lees")) {
								break;
							}
							list.add(map.get(part), part, lineNumber, node-1,level, 0);
						} else {
							// Numbers
							if(part.matches(numberRegex)){
								list.add(NodeType.NUMBER, part, lineNumber,node-1, level, 0);
							}
							// START WITH *?
							else if(part.matches(stringRegex)){
								list.add(NodeType.STRING, part, lineNumber,node-1, level, 0);
							}
							else{
							// IDENTIFIER
							list.add(NodeType.IDENTIFIER, part, lineNumber,node-1, level, 0);
							}
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