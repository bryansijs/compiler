package compiler.virtualMachine;

import java.util.*;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.Action;

public class VirtualMachine {
	
	public String returnValue;
	public HashMap<String, String> variables;
	private HashMap<String, BaseCommand> commands;
	
	
	public VirtualMachine()
	{
		commands = new HashMap<String, BaseCommand>();
		commands.put("ADD", new AddCommand());
		commands.put("PRINT", new PrintCommand());
		commands.put("ConstToReturn", new constToReturnCommand());
		variables = new HashMap<String, String>();
	}
	
	public void Run(CompileList list)
	{
		Action current = list.getFirst();
		while(current != null)
		{
			// command om iets uit te voeren
			if(current instanceof AbstractFunctionCall)
			{
				AbstractFunctionCall func = (AbstractFunctionCall) current;
				commands.get(func.parameters.get(0)).Execute(func, this);
			}
			
			// get new current;
			current = current.getNext();
		}
	}
}
