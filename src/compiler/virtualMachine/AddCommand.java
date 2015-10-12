package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class AddCommand extends BaseCommand {

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		// Param1 -> "Add"
		// Param2 -> "$001"
		// Param3 -> "$002"
		
		// Param1 -> "Add"
				// Param2 -> "x"
				// Param3 -> "y"
		
		vm.returnValue = Integer.toString(Integer.parseInt(vm.variables.get(node.parameters.get(1))) + Integer.parseInt(vm.variables.get(node.parameters.get(2))));
		//vm.returnValue = vm.variables.get("x") + vm.variables.get("y");
	}

}
