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
package org.oss.uses.org.oss.jshuntingyard.evaluator;

import java.util.Date;

import org.oss.uses.org.oss.jshuntingyard.lexer.ExpressionToken;
import org.oss.uses.org.oss.jshuntingyard.lexer.TokenType;


public class FunctionArgumentFactory {

	public static FunctionElementArgument<?> createObject(ExpressionToken expressionToken) {
		String token = expressionToken.getToken();
		TokenType type = expressionToken.getType();
		if (type == TokenType.SINGLEQUOTED) {
			return new StringArgument(token.substring(1, token.length() - 1));
		}
		if (type == TokenType.BOOLEANVALUE) {
			return new BooleanArgument(Boolean.valueOf(token));
		}
		if (type == TokenType.NULL) {
			return new NullArgument();
		}
		if (isNumeric(token)) {
			switch (type) {
			case INTEGERNUMBER:
				return new IntegerArgument(Integer.valueOf(token));
			case LONGNUMBER:
				if (token.endsWith("l") || token.endsWith("L")) {
					return new LongArgument(Long.valueOf(token.substring(0, token.length() - 1)));
				}
				return new LongArgument(Long.valueOf(token));
			case FLOATNUMBER:
				if (token.endsWith("f") || token.endsWith("F")) {
					return new FloatArgument(Float.valueOf(token.substring(0, token.length() - 1)));
				}
				return new FloatArgument(Float.valueOf(token));
			case DOUBLENUMBER:
				if (token.endsWith("d") || token.endsWith("D")) {
					return new DoubleArgument(Double.valueOf(token.substring(0, token.length() - 1)));
				}
				return new DoubleArgument(Double.valueOf(token));
			default:
				throw new IllegalArgumentException("Cannot parse number: '" + token + "' of type: " + type);
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

	public static FunctionElementArgument<Boolean> createObject(Boolean value) {
		return new BooleanArgument(value);
	}

	public static FunctionElementArgument<Double> createObject(Double value) {
		return new DoubleArgument(value);
	}

	public static FunctionElementArgument<Float> createObject(Float value) {
		return new FloatArgument(value);
	}

	public static FunctionElementArgument<Long> createObject(Long value) {
		return new LongArgument(value);
	}

	public static FunctionElementArgument<Date> createObject(Date value) {
		return new DateArgument(value);
	}

	public static FunctionElementArgument<Integer> createObject(Integer value) {
		return new IntegerArgument(value);
	}

	public static FunctionElementArgument<Boolean> createBoolean(Integer value) {
		return value == 0 ? new BooleanArgument(Boolean.TRUE) : new BooleanArgument(Boolean.FALSE);
	}

	public static FunctionElementArgument<Object> createObject(Object value) {
		return value == null ? new NullArgument() : new ObjectArgument(value);
	}
	public static FunctionElementArgument<?> createObject(FunctionElementArgument<?> value) {
		return value;
	}

	public static FunctionElementArgument<String> createString(String value) {
		return new StringArgument(value);
	}

	public static FunctionElementArgument<String> createString(char value) {
		return new StringArgument(value);
	}

	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?[dDfFlL]?");  //match a number with optional '-' and decimal.
	}

	public static FunctionElementArgument<Double> createDouble(String value) {
		return value.indexOf(",") > 0 ? new DoubleArgument(new Double(value.replace('.', ','))) : new DoubleArgument(new Double(value));
	}

}
