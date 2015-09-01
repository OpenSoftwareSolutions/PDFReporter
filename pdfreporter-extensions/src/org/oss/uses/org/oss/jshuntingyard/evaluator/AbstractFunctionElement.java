package org.oss.uses.org.oss.jshuntingyard.evaluator;



public abstract class AbstractFunctionElement implements FunctionElement {

	private final String name;
	private final Associativity associativity;
	private final int numberOfParameters;
	private final Precedence precendence;

	public AbstractFunctionElement(String name, int numberOfParameters,
			Associativity associativity, Precedence precendence) {
		super();
		this.name = name;
		this.numberOfParameters = numberOfParameters;
		this.associativity = associativity;
		this.precendence = precendence;
	}
	
	public AbstractFunctionElement(String name, int numberOfParameters, Precedence precendence) {
		this(name,numberOfParameters,Associativity.LEFT,precendence);
	}

	@Override
	public Associativity getAssociativity() {
		return associativity;
	}

	@Override
	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	@Override
	public Precedence getPrecendence() {
		return precendence;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public String getString() {
		return getName();
	}
	
	@SuppressWarnings("unchecked")
	protected Double getDouble(FunctionElementArgument<?> arg) {
		return arg.getType()==FunctionElementArgument.ArgumentType.INTEGER ? ((FunctionElementArgument<Integer>)arg).getValue().doubleValue() : 
			arg.getType()==FunctionElementArgument.ArgumentType.FLOAT ? ((FunctionElementArgument<Float>)arg).getValue().doubleValue() :
				((FunctionElementArgument<Double>)arg).getValue();
	}

	@SuppressWarnings("unchecked")
	protected Float getFloat(FunctionElementArgument<?> arg) {
		return arg.getType()==FunctionElementArgument.ArgumentType.INTEGER ? ((FunctionElementArgument<Integer>)arg).getValue().floatValue() : ((FunctionElementArgument<Float>)arg).getValue();
	}
	
	@SuppressWarnings("unchecked")
	protected Long getLong(FunctionElementArgument<?> arg) {
		return arg.getType()==FunctionElementArgument.ArgumentType.INTEGER ? ((FunctionElementArgument<Integer>)arg).getValue().longValue() : ((FunctionElementArgument<Long>)arg).getValue();
	}

	protected void assertNumArgs(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		if (args.length!=getNumberOfParameters()) {
			throw new IllegalArgumentException(String.format("wrong numbers of arguments expexted %s actual %s", getNumberOfParameters(),args.length));
		}
	}

	protected boolean isDouble(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if (!(arg.getType()==FunctionElementArgument.ArgumentType.DOUBLE)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isBoolean(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if (!(arg.getType()==FunctionElementArgument.ArgumentType.BOOLEAN)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isString(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if (!(arg.getType()==FunctionElementArgument.ArgumentType.STRING)) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isOneString(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if ((arg.getType()==FunctionElementArgument.ArgumentType.STRING)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInteger(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if (!(arg.getType()==FunctionElementArgument.ArgumentType.INTEGER)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isNumeric(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		for (FunctionElementArgument<?> arg : args) {
			if (!(arg.getType()==FunctionElementArgument.ArgumentType.INTEGER || arg.getType()==FunctionElementArgument.ArgumentType.DOUBLE || arg.getType()==FunctionElementArgument.ArgumentType.LONG || arg.getType()==FunctionElementArgument.ArgumentType.FLOAT)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
