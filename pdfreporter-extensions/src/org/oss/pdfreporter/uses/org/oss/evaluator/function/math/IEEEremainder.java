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
 * The java.lang.Math.IEEEremainder(double f1, double f2) returns Computes the remainder operation on two arguments as prescribed by the IEEE 754 standard.
 * The remainder value is mathematically equal to f1 - f2 x n, where n is the mathematical integer closest to the exact mathematical value of the quotient f1/f2, and if two mathematical integers are equally close to f1/f2,
 * then n is the integer that is even. If the remainder is zero, its sign is the same as the sign of the first argument. Special cases:
 * If either argument is NaN, or the first argument is infinite, or the second argument is positive zero or negative zero, then the result is NaN.
 * If the first argument is finite and the second argument is infinite, then the result is the same as the first argument.
 */
public class IEEEremainder extends AbstractNumericOperatorAssociativityLeftTwoDoubleArg {

	public IEEEremainder() {
		super("ieeeremainder", Precedence.USERFUNCTION);
	}



	/* This method returns the remainder when f1 is divided by f2.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionArgument<Double> execute(FunctionArgument<Double> a, FunctionArgument<Double> b) throws IllegalArgumentException {

		if (a instanceof DoubleArgument && b instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.IEEEremainder(a.getValue(), b.getValue()));
		}
		throw new IllegalArgumentException(String.format("only double operators supported and not ", a.getType(), b.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}
}