package compiler.virtualMachine;

import java.util.*;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.Action;

public class VirtualMachine {
	public String returnValue;
	public HashMap<String, String> variables;
	private HashMap<String, BaseCommand> commands;
	private int lastAssignedVariableName = 0;
	
	public String getNextVariableName(){
		return "$" + lastAssignedVariableName++;
	}
	
	public VirtualMachine()
	{
		commands = new HashMap<String, BaseCommand>();
		commands.put("ADD", new AddCommand());
		commands.put("PRINT", new PrintCommand());
	}
	
	public void Run(CompileList list)
	{
		Action current = list.getFirst();
		while(current != null)
		{
			// command om iets uit te voeren
			if(current instanceof AbstractFunctionCall)
			{
				commands.get(current.parameters.get(0)).execute(current, this);
			}
			
			// get new current;
			current = current.getNext();
		}
	}
}
