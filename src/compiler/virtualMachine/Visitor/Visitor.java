package compiler.virtualMachine.Visitor;

import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.nodes.FunctionCall;
import compiler.nodes.Jump;

public interface Visitor {
	
	public void visit(ConditionalJump conditionalJump);
	public void visit(DirectFunctionCall directFunctionCall);
	public void visit(DoNothing doNothing);
	public void visit(FunctionCall functionCall);
	public void visit(Jump jump);
}
