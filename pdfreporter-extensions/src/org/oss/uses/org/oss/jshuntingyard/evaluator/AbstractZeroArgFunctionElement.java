package org.oss.uses.org.oss.jshuntingyard.evaluator;


public abstract class AbstractZeroArgFunctionElement<R> extends AbstractFunctionElement {

	public AbstractZeroArgFunctionElement(String name,
			Associativity associativity, Precedence precendence) {
		super(name, 0, associativity, precendence);
	}

	public AbstractZeroArgFunctionElement(String name, Precedence precendence) {
		super(name, 0, precendence);
	}
	
	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute();
	}

	abstract protected FunctionElementArgument<R> execute() throws IllegalArgumentException;

}
