package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class constToReturnCommand extends BaseCommand {

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		// Param0 -> "ConstToReturn"
		// Param1 -> "x || 3"
		
		vm.returnValue = node.parameters.get(1);
	}

}
