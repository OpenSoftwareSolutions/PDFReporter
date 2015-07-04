package org.oss.uses.org.oss.jshuntingyard.evaluator;


public abstract class AbstractTwoArgFunctionElement<R,T,U> extends
		AbstractFunctionElement {

	public AbstractTwoArgFunctionElement(String name,
			Associativity associativity, Precedence precendence) {
		super(name, 2, associativity, precendence);
	}

	public AbstractTwoArgFunctionElement(String name, Precedence precendence) {
		super(name, 2, precendence);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute((FunctionElementArgument<T>)args[0],(FunctionElementArgument<U>)args[1]);
	}

	abstract protected FunctionElementArgument<R> execute(FunctionElementArgument<T> a, FunctionElementArgument<U> b) throws IllegalArgumentException;

}
