/**
 * Copyright [2015] [Open Software Solutions GmbH]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.oss.pdfreporter.uses.org.oss.evaluator.operator;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Associativity;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;

public abstract class AbstractOperator implements Function {


	private final String name;
	private final Associativity associativity;
	private final int numberOfParameters;
	private final Precedence precendence;

	public AbstractOperator(String name, int numberOfParameters,
			Associativity associativity, Precedence precendence) {
		super();
		this.name = name;
		this.numberOfParameters = numberOfParameters;
		this.associativity = associativity;
		this.precendence = precendence;
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
	protected Double getDouble(FunctionArgument<?> arg) {
		return arg.getType()==FunctionArgument.ArgumentType.INTEGER ? ((FunctionArgument<Integer>)arg).getValue().doubleValue() : ((FunctionArgument<Double>)arg).getValue();
	}


	protected void assertNumArgs(FunctionArgument<?>... args) throws IllegalArgumentException {
		if (args.length!=getNumberOfParameters()) {
			throw new IllegalArgumentException(String.format("wrong numbers of arguments expexted %s actual %s", getNumberOfParameters(),args.length));
		}
	}

	protected boolean isDouble(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.DOUBLE)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isBoolean(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.BOOLEAN)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isString(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.STRING)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isInteger(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.INTEGER)) {
				return false;
			}
		}
		return true;
	}

}
