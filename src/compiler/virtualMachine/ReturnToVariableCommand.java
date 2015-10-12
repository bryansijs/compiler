package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class ReturnToVariableCommand extends BaseCommand{
	
	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		// ReturnToVariable, $0
		
		vm.variables.put(node.parameters.get(1), vm.returnValue);
		
	}
	
}
