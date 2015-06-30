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
package org.oss.pdfreporter.uses.org.oss.evaluator.function.math;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.DoubleArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

/**
 * The java.lang.Math.atan(double a) returns the arc tangent of an angle, in the range of -pi/2 through pi/2. Special cases:
 * If the argument is NaN, then the result is NaN.
 * If the argument is zero, then the result is a zero with the same sign as the argument.
 * A result must be within 1 ulp of the correctly rounded result. Results must be semi-monotonic.
 */
public class Atan extends AbstractNumericOperatorAssociativityLeftOneArg {

	public Atan() {
		super("atan", Precedence.USERFUNCTION);
	}



	/* This method returns the arc tangent of the argument.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionArgument<Double> execute(FunctionArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.atan(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}