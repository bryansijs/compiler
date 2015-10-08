package compiler.virtualMachine;

import compiler.nodes.AbstractFunctionCall;

public abstract class BaseCommand {
	public abstract void Execute(AbstractFunctionCall node, VirtualMachine vm);
}
