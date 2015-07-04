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

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;


/**
 * The java.lang.Math.atan2(double y,double x) Converts rectangular coordinates (x, y) to polar (r, theta). This method computes the phase theta by computing an arc tangent of y/x in the range of -pi to pi. Special cases:
 * If either argument is NaN, then the result is NaN.
 * If the first argument is positive zero and the second argument is positive, or the first argument is positive and finite and the second argument is positive infinity, then the result is positive zero.
 * If the first argument is negative zero and the second argument is positive, or the first argument is negative and finite and the second argument is positive infinity, then the result is negative zero.
 * If the first argument is positive zero and the second argument is negative, or the first argument is positive and finite and the second argument is negative infinity, then the result is the double value closest to pi.
 * If the first argument is negative zero and the second argument is negative, or the first argument is negative and finite and the second argument is negative infinity, then the result is the double value closest to -pi.
 * If the first argument is positive and the second argument is positive zero or negative zero, or the first argument is positive infinity and the second argument is finite, then the result is the double value closest to pi/2.
 * If the first argument is negative and the second argument is positive zero or negative zero, or the first argument is negative infinity and the second argument is finite, then the result is the double value closest to -pi/2.
 * If both arguments are positive infinity, then the result is the double value closest to pi/4.
 * If the first argument is positive infinity and the second argument is negative infinity, then the result is the double value closest to 3*pi/4.
 * If the first argument is negative infinity and the second argument is positive infinity, then the result is the double value closest to -pi/4.
 * If both arguments are negative infinity, then the result is the double value closest to -3*pi/4.
 * A result must be within 2 ulps of the correctly rounded result. Results must be semi-monotonic.
 */
public class Atan2 extends AbstractTwoArgFunctionElement<Double,Double,Double> {

	public Atan2() {
		super("atan2", Precedence.USERFUNCTION);
	}



	/* This method returns the theta component of the point (r, theta) in polar coordinates that corresponds to the point (x, y) in Cartesian coordinates.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a, FunctionElementArgument<Double> b) throws IllegalArgumentException {

		if (a instanceof DoubleArgument && b instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.atan2(a.getValue(), b.getValue()));
		}
		throw new IllegalArgumentException(String.format("only double operators supported and not ", a.getType(), b.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}
}