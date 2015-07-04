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
 * This is the argument referenced by the format specifiers in the format string.
 * If there are more arguments than format specifiers, the extra arguments are ignored.
 * The number of arguments is variable and may be zero.
 *
 */
public class NumberFormat extends AbstractTwoArgFunctionElement<String,String,Double> {

	public NumberFormat() {
		super("numberFormat", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/*This method returns a formatted string.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<String> execute(FunctionElementArgument<String> a,FunctionElementArgument<Double> b) throws IllegalArgumentException {
		return FunctionArgumentFactory.createString(String.format(a.getValue(), b.getValue()));
	}
}