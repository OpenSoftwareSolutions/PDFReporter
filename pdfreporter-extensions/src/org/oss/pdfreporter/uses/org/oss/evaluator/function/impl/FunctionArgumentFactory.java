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
package org.oss.pdfreporter.uses.org.oss.evaluator.function.impl;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;

public class FunctionArgumentFactory {

	public static FunctionArgument<?> createObject(String token) {
		if (token.startsWith("'") && token.endsWith("'")) {
			return new StringArgument(token.substring(1, token.length() - 1));
		}
		if (token.equalsIgnoreCase("true")) {
			return new BooleanArgument(Boolean.TRUE);
		}
		if (token.equalsIgnoreCase("false")) {
			return new BooleanArgument(Boolean.FALSE);
		}
		if (isNumeric(token)) {
			if (token.indexOf(".")>=0) {
				return new DoubleArgument(Double.valueOf(token));
			} else {
				return new IntegerArgument(Integer.valueOf(token));
			}
		}
		if (token.startsWith("$")) {
			return new VariableArgument(token.substring(1));
		}
		if(token.equals("NaN")){
			return new DoubleArgument(Double.valueOf(token));
		}
		throw new IllegalArgumentException("No implementation for non numeric types yet: '" + token + "'");
	}

	public static FunctionArgument<Boolean> createObject(Boolean value) {
		return new BooleanArgument(value);
	}

	public static FunctionArgument<Double> createObject(Double value) {
		return new DoubleArgument(value);
	}

	public static FunctionArgument<Integer> createObject(Integer value) {
		return new IntegerArgument(value);
	}

	public static FunctionArgument<Boolean> createBoolean(Integer value) {
		return value == 0 ? new BooleanArgument(Boolean.TRUE) : new BooleanArgument(Boolean.FALSE);
	}

	public static FunctionArgument<Object> createObject(Object value) {
		return value == null ? new NullArgument() : new ObjectArgument(value);
	}

	public static FunctionArgument<String> createString(String value) {
		return new StringArgument(value);
	}

	public static FunctionArgument<String> createString(char value) {
		return new StringArgument(value);
	}

	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	public static FunctionArgument<Double> createDouble(String value) {
		return value.indexOf(",") > 0 ? new DoubleArgument(new Double(value.replace('.', ','))) : new DoubleArgument(new Double(value));
	}

}
