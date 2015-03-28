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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.IntegerArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.StringArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftTwoArg;

public class Add extends AbstractNumericOperatorAssociativityLeftTwoArg {

	public Add() {
		super("+", Precedence.ADDITIVE);
	}

	@Override
	public FunctionArgument<?> execute(FunctionArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		if (isNumeric(args) || isString(args)) {
			return execute(args[0],args[1]);
		}
		throw new IllegalArgumentException(String.format("Two string operands or two numeric operands are allowed but not ", args[0].getType() + " and " + args[1].getType()));

	}


	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a,
			FunctionArgument<?> b) throws IllegalArgumentException {
		if (a.getType()==FunctionArgument.ArgumentType.INTEGER && b.getType()==FunctionArgument.ArgumentType.INTEGER) {
			return FunctionArgumentFactory.createObject(((IntegerArgument)a).getValue() + ((IntegerArgument)b).getValue());
		} if (a.getType()==FunctionArgument.ArgumentType.STRING) {
			return FunctionArgumentFactory.createString(((StringArgument)a).getValue() + ((StringArgument)b).getValue());
		}
		return FunctionArgumentFactory.createObject(getDouble(a) + getDouble(b));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}
