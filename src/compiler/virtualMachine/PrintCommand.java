package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class PrintCommand extends BaseCommand {

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		if(isNumeric(vm.returnValue))
		{
			System.out.println(vm.returnValue);
		}
		else
		{
			System.out.println(vm.variables.get(vm.returnValue));
		}
	}
	
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
