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
			return execute(args[0],args[1], evaluatesTo(args[0],args[1]));
		}
		throw new IllegalArgumentException("Two string operands or two numeric operands are allowed but not " + args[0].getType() + " and " + args[1].getType());
	}

	abstract protected FunctionElementArgument<?> execute(FunctionElementArgument<?> a, FunctionElementArgument<?> b, FunctionElementArgument.ArgumentType evaluatesTo) throws IllegalArgumentException;
	
	protected FunctionElementArgument.ArgumentType evaluatesTo(FunctionElementArgument<?> a, FunctionElementArgument<?> b) {
		if (a.getType()==FunctionElementArgument.ArgumentType.STRING || b.getType()==FunctionElementArgument.ArgumentType.STRING) {
			return FunctionElementArgument.ArgumentType.STRING;
		}
		if (a.getType()==FunctionElementArgument.ArgumentType.DOUBLE || b.getType()==FunctionElementArgument.ArgumentType.DOUBLE) {
			return FunctionElementArgument.ArgumentType.DOUBLE;
		}
		if (a.getType()==FunctionElementArgument.ArgumentType.FLOAT || b.getType()==FunctionElementArgument.ArgumentType.FLOAT) {
			return FunctionElementArgument.ArgumentType.FLOAT;
		}
		if (a.getType()==FunctionElementArgument.ArgumentType.LONG || b.getType()==FunctionElementArgument.ArgumentType.LONG) {
			return FunctionElementArgument.ArgumentType.LONG;
		}
		return FunctionElementArgument.ArgumentType.INTEGER;
	}
}
