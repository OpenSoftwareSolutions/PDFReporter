package org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public abstract class AbstractTwoArgNumericFunctionElement extends AbstractFunctionElement {

	public AbstractTwoArgNumericFunctionElement(String name, Precedence precendence) {
		super(name, 2, precendence);
	}

	@Override
	public FunctionElementArgument<?> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		if (isNumeric(args) || isOneString(args)) {
			return execute(args[0],args[1]);
		}
		throw new IllegalArgumentException(String.format("Two string operands or two numeric operands are allowed but not ", args[0].getType() + " and " + args[1].getType()));
	}

	abstract protected FunctionElementArgument<?> execute(FunctionElementArgument<?> a, FunctionElementArgument<?> b) throws IllegalArgumentException;
	

}
