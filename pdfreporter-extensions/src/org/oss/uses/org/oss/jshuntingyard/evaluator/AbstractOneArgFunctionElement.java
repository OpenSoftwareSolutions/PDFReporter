package org.oss.uses.org.oss.jshuntingyard.evaluator;

public abstract class AbstractOneArgFunctionElement<R,T> extends
		AbstractFunctionElement {

	public AbstractOneArgFunctionElement(String name,
			Associativity associativity, Precedence precendence) {
		super(name, 1, associativity, precendence);
	}
	
	public AbstractOneArgFunctionElement(String name, Precedence precendence) {
		super(name, 1, precendence);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute((FunctionElementArgument<T>)args[0]);
	}

	abstract protected FunctionElementArgument<R> execute(FunctionElementArgument<T> a) throws IllegalArgumentException;

}
