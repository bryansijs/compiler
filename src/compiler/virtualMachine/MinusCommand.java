package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class MinusCommand extends BaseCommand{

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		//[Min, x, $4]
		int y = 0;
		int x = Integer.parseInt(vm.variables.get(node.parameters.get(1)));
		if(node.parameters.get(2).charAt(0) == '$')
		{
			y = Integer.parseInt(vm.variables.get(node.parameters.get(2)));
		}
		else
		{
			y = Integer.parseInt(node.parameters.get(2));
		}
		
		vm.returnValue = Integer.toString(x - y);
	}

}
