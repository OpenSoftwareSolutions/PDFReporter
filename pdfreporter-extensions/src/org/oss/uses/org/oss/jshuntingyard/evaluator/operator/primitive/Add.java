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
package org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.IntegerArgument;

public class Add extends AbstractTwoArgNumericFunctionElement {

	public Add() {
		super("+", Precedence.ADDITIVE);
	}

	@Override
	protected FunctionElementArgument<?> execute(FunctionElementArgument<?> a,
			FunctionElementArgument<?> b) throws IllegalArgumentException {
		if (a.getType()==FunctionElementArgument.ArgumentType.INTEGER && b.getType()==FunctionElementArgument.ArgumentType.INTEGER) {
			return FunctionArgumentFactory.createObject(((IntegerArgument)a).getValue() + ((IntegerArgument)b).getValue());
		} if (a.getType()==FunctionElementArgument.ArgumentType.STRING || b.getType()==FunctionElementArgument.ArgumentType.STRING) {
			return FunctionArgumentFactory.createString(a.getValue().toString() + b.getValue().toString()); // TODO: hack what about auto type conversion ?
		}
		return FunctionArgumentFactory.createObject(getDouble(a) + getDouble(b));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}
