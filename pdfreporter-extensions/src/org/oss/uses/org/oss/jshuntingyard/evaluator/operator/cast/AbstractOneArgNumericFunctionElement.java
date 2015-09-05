package org.oss.uses.org.oss.jshuntingyard.evaluator.operator.cast;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public abstract class AbstractOneArgNumericFunctionElement<R> extends AbstractFunctionElement {

	public AbstractOneArgNumericFunctionElement(String name, Precedence precendence) {
		super(name, 1, precendence);
	}

	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		if (isNumeric(args)) {
			return execute(args[0]);
		}
		throw new IllegalArgumentException("Single numeric operand are expected but not " + args[0].getType());
	}

	abstract protected FunctionElementArgument<R> execute(FunctionElementArgument<?> a) throws IllegalArgumentException;
}
