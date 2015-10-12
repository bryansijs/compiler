package compiler.virtualMachine.Visitor;

import compiler.nodes.*;
import compiler.virtualMachine.VirtualMachine;

public class NodeVisitor implements Visitor{

	public Action node;
	VirtualMachine vm;
	
	public NodeVisitor(VirtualMachine virtualMachine){
		this.vm = virtualMachine;
	}
	
	public Action getNode() {
		return node;
	}

	@Override
	public void visit(ConditionalJump conditionalJump) {
		if(vm.returnValue.equals("true")){
			node = (conditionalJump).getNextTrue();
		}
		else if(vm.returnValue.equals("false")){
			node = ((ConditionalJump) conditionalJump).getNextFalse();
		}
		else{
			throw new RuntimeException("expected true or false, found : " + vm.returnValue);
		}
	}

	@Override
	public void visit(DirectFunctionCall directFunctionCall) {
		vm.commands.get(directFunctionCall.parameters.get(0)).Execute(directFunctionCall, vm);
		this.node = directFunctionCall.getNext();
	}

	@Override
	public void visit(DoNothing doNothing) {
		node = doNothing.getNext();
	}

	@Override
	public void visit(FunctionCall functionCall) {
		vm.commands.get(functionCall.parameters.get(0)).Execute(functionCall, vm);
		this.node = functionCall.getNext();
	}

	@Override
	public void visit(Jump jump) {
		node = jump.JumpToNode;
	}
	
}
