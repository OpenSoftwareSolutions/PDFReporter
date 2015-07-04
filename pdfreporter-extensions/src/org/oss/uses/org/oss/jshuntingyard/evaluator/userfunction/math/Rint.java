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

package org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;


/**
 * The java.lang.Math.rint(double a) returns the double value that is closest in value to the argument and is equal to a mathematical integer.
 * If two double values that are mathematical integers are equally close, the result is the integer value that is even. Special cases:
 * If the argument value is already equal to a mathematical integer, then the result is the same as the argument.
 * If the argument is NaN or an infinity or positive zero or negative zero, then the result is the same as the argument.
 */
public class Rint extends AbstractOneArgFunctionElement<Double,Double> {

	public Rint() {
		super("rint", Precedence.USERFUNCTION);
	}



	/* This method returns the closest floating-point value to a that is equal to a mathematical integer.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.rint(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}