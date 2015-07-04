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
 * The java.lang.Math.log(double a) returns the natural logarithm (base e) of a double value. Special cases:
 * If the argument is NaN or less than zero, then the result is NaN.
 * If the argument is positive infinity, then the result is positive infinity.
 * If the argument is positive zero or negative zero, then the result is negative infinity.
 */
public class Log extends AbstractOneArgFunctionElement<Double,Double> {

	public Log() {
		super("log", Precedence.USERFUNCTION);
	}



	/* This method returns the value ln a, the natural logarithm of a.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.log(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}