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
 * The java.lang.Math.asin(double a) returns the arc sine of an angle, in the range of -pi/2 through pi/2.Special cases:
 * If the argument is NaN or its absolute value is greater than 1, then the result is NaN.
 * If the argument is zero, then the result is a zero with the same sign as the argument.
 * A result must be within 1 ulp of the correctly rounded result. Results must be semi-monotonic.
 */
public class Asin extends AbstractOneArgFunctionElement<Double,Double> {

	public Asin() {
		super("asin", Precedence.USERFUNCTION);
	}



	/* This method returns the arc sine of the argument.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.asin(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}