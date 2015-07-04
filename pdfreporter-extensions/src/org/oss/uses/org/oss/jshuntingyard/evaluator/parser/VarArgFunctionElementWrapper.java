package org.oss.uses.org.oss.jshuntingyard.evaluator.parser;

import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public class VarArgFunctionElementWrapper implements FunctionElement {

	private final FunctionElement delegate;
	private final int numberOfParameters;
	
	VarArgFunctionElementWrapper(FunctionElement delegate, int numberOfParameters) {
		this.delegate = delegate;
		this.numberOfParameters = numberOfParameters;
	}

	public String getString() {
		return delegate.getString();
	}

	public Associativity getAssociativity() {
		return delegate.getAssociativity();
	}

	public Precedence getPrecendence() {
		return delegate.getPrecendence();
	}

	public String getName() {
		return delegate.getName();
	}

	public boolean isUserFunction() {
		return delegate.isUserFunction();
	}

	public FunctionElementArgument<?> execute(
			FunctionElementArgument<?>... args) throws IllegalArgumentException {
		return delegate.execute(args);
	}

	@Override
	public int getNumberOfParameters() {
		return numberOfParameters;
	}

}
