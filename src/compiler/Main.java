package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static linked_list list;
	
	public static void main(String[] args) {
		int lineNumber = 0;
		int charNumber = 0;
		int level = 0;
		
		list = new linked_list();
		
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/bryansijs/Google Drive/Leerjaar 3/java project/compiler/src/compiler/code.txt")))
		{

			String line = br.readLine();

			while (line != null) {
				lineNumber++;

				String[] parts = line.split(" ");
				//TODO charnumber/partner in line
				for (String part : parts) {
					//TODO: all in if/else? or Switch Case?
					if (part.equals("lade")) {
						list.add(NodeType.VARIABELE, part, lineNumber, 1, level, 0);
					}
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}