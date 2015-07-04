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
 * The java.lang.Math.cos(double a) returns the trigonometric cosine of an angle.
 * If the argument is NaN or an infinity, then the result is NaN.
 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
 *
 */
public class Cos extends AbstractOneArgFunctionElement<Double,Double> {

	public Cos() {
		super("cos", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}

	/* This method returns the cosine of the argument.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {
		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.cos(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}
}
