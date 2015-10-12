package compiler.virtualMachine;

import java.util.*;

import compiler.CompileList;
import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.Jump;

public class VirtualMachine {
	
	public String returnValue;
	public HashMap<String, String> variables;
	private HashMap<String, BaseCommand> commands;
	
	
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
		while(current != null)
		{
			// command om iets uit te voeren
			if(current instanceof AbstractFunctionCall)
			{
				AbstractFunctionCall func = (AbstractFunctionCall) current;
				commands.get(func.parameters.get(0)).Execute(func, this);
			}
			if(current instanceof Jump){
				current = ((Jump) current).JumpToNode;
			}
			else if(current instanceof ConditionalJump)
			{
				if(this.returnValue.equals("true")){
					current = ((ConditionalJump) current).getNextTrue();
				}
				else if(this.returnValue.equals("false")){
					current = ((ConditionalJump) current).getNextFalse();
				}
				else{
					throw new RuntimeException("expected true or false, found : " + returnValue);
				}
			}else{
				current = current.getNext();
			}
			
			// get new current TODO: visitorPattern
			
		}
	}
}
