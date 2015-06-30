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
package org.oss.pdfreporter.uses.org.oss.evaluator.function.string;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.StringArgument;

/**
 * The java.lang.String.compareTo() method compares two strings lexicographically.
 * The comparison is based on the Unicode value of each character in the strings.
 * The character sequence represented by this String object is compared lexicographically to the character sequence represented by the argument string.
 * The result is a negative integer if this String object lexicographically precedes the argument string.
 * The result is a positive integer if this String object lexicographically follows the argument string.
 * The result is zero if the strings are equal, compareTo returns 0 exactly when the equals(Object) method would return true.
 *
 */
public class Concat extends AbstractStringOperatorAssociativityLeftTwoStringArg {

	public Concat() {
		super("concat", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* This method returns the value 0 if the argument string is equal to this string,
	 * a value less than 0 if this string is lexicographically less than the string argument
	 * and a value greater than 0 if this string is lexicographically greater than the string argument.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<String> a,FunctionArgument<String> b) throws IllegalArgumentException {

		if (a instanceof StringArgument && b instanceof StringArgument) {
			return FunctionArgumentFactory.createString(a.getValue().concat(b.getValue()));
		}
		throw new IllegalArgumentException(String.format("only argument string as type are supported and not ", a.getType(), b.getType()));

	}
}