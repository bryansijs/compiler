package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class PrintCommand extends BaseCommand {

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		System.out.println(vm.variables.get(node.parameters.get(1)));
	}

}
