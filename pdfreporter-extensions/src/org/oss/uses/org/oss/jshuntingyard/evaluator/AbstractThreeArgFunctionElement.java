package org.oss.uses.org.oss.jshuntingyard.evaluator;


public abstract class AbstractThreeArgFunctionElement<R,T,U,V> extends
		AbstractFunctionElement {

	public AbstractThreeArgFunctionElement(String name,
			Associativity associativity, Precedence precendence) {
		super(name, 3, associativity, precendence);
	}

	public AbstractThreeArgFunctionElement(String name, Precedence precendence) {
		super(name, 3, precendence);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute((FunctionElementArgument<T>)args[0], (FunctionElementArgument<U>)args[1], (FunctionElementArgument<V>)args[2]);
	}

	abstract protected FunctionElementArgument<R> execute(FunctionElementArgument<T> a, FunctionElementArgument<U> b, FunctionElementArgument<V> c) throws IllegalArgumentException;
	
}
