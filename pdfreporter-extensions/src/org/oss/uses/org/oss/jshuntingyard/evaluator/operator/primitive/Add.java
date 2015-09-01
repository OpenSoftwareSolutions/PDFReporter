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
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument.ArgumentType;

public class Add extends AbstractTwoArgNumericFunctionElement {

	public Add() {
		super("+", Precedence.ADDITIVE);
	}

	@Override
	protected FunctionElementArgument<?> execute(FunctionElementArgument<?> a,
			FunctionElementArgument<?> b, ArgumentType evaluatesTo)
					throws IllegalArgumentException {
		switch (evaluatesTo) {
		case STRING:
			return FunctionArgumentFactory.createString(a.getValue().toString() + b.getValue().toString()); 
		case INTEGER:
			return FunctionArgumentFactory.createObject(((IntegerArgument)a).getValue() + ((IntegerArgument)b).getValue());
		case LONG:
			return FunctionArgumentFactory.createObject(getLong(a) + getLong(b));
		case FLOAT:
			return FunctionArgumentFactory.createObject(getFloat(a) + getFloat(b));
		case DOUBLE:
			return FunctionArgumentFactory.createObject(getDouble(a) + getDouble(b));
		default:
			throw new IllegalArgumentException("Unsupported add operation for the types " + a.getType() + " and " + b.getType() + " for expected evaluation to " + evaluatesTo);
		}
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}

}
