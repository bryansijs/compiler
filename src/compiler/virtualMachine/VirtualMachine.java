package compiler.virtualMachine;

import java.util.*;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.Jump;
import compiler.virtualMachine.Visitor.NodeVisitor;

public class VirtualMachine {
	
	public String returnValue;
	public HashMap<String, String> variables;
	public HashMap<String, BaseCommand> commands;
	
	
	public VirtualMachine()
	{
		commands = new HashMap<String, BaseCommand>();
		commands.put("Add", new AddCommand());
		commands.put("Min", new MinusCommand());
		commands.put("print", new PrintCommand());
		commands.put("ConstToReturn", new constToReturnCommand());
		commands.put("ReturnToVariable", new ReturnToVariableCommand());
		commands.put("NotEqual", new NotEqualCommand());
		commands.put("Equal", new EqualCommand());
		variables = new HashMap<String, String>();
	}
	
	public void Run(CompileList list)
	{
		Action current = list.getFirst();
		NodeVisitor visitor = new NodeVisitor(this);
		
		while(current != null)
		{
			// command om iets uit te voeren
			current.Accept(visitor);
			current = visitor.getNode();
		}
	}
}
