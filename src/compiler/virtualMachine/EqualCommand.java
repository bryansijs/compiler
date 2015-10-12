package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public class EqualCommand extends BaseCommand{

	@Override
	public void Execute(AbstractFunctionCall node, VirtualMachine vm) {
		//[Equal, y, $5]
		int y = Integer.parseInt(vm.variables.get(node.parameters.get(1)));
		int x = Integer.parseInt(vm.variables.get(node.parameters.get(2)));
		
		if(x == y){
			vm.returnValue = "true";
		}
		else{
			vm.returnValue = "false";
		}
	}

}
