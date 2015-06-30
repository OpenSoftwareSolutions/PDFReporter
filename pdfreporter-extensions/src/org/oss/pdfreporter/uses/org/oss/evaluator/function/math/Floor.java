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
 * The java.lang.Math.floor(double a) returns the largest (closest to positive infinity) double value
 * that is less than or equal to the argument and is equal to a mathematical integer. Special cases:
 * If the argument value is already equal to a mathematical integer, then the result is the same as the argument.
 * If the argument is NaN or an infinity or positive zero or negative zero, then the result is the same as the argument.
 */
public class Floor extends AbstractNumericOperatorAssociativityLeftOneArg {

	public Floor() {
		super("floor", Precedence.USERFUNCTION);
	}



	/* This method returns the largest (closest to positive infinity) floating-point value that less than or equal to the argument and is equal to a mathematical integer.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionArgument<Double> execute(FunctionArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.floor(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}