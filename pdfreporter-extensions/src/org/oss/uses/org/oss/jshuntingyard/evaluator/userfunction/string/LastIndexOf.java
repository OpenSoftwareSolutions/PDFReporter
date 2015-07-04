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
package org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 * The java.lang.String.lastIndexOf(String str) method Returns the index within this string of the rightmost occurrence of the specified substring.
 * The rightmost empty string "" is considered to occur at the index value this.length().
 * The returned index is the largest value k such that this.startsWith(str, k) is true.
 *
 */
public class LastIndexOf extends AbstractTwoArgFunctionElement<Integer,String,String> {

	public LastIndexOf() {
		super("lastIndexOf", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* If the string argument occurs one or more times as a substring within this object,
	 * then the index of the first character of the last such substring is returned. If it does not occur as a substring, -1 is returned.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<Integer> execute(FunctionElementArgument<String> a,FunctionElementArgument<String> b) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(a.getValue().lastIndexOf(b.getValue()));
	}
}