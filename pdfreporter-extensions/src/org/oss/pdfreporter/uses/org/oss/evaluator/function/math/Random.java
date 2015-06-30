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
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;


/**
 * The java.lang.Math.random() returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
 * Returned values are chosen pseudorandomly with (approximately) uniform distribution from that range.
 * When this method is first called, it creates a single new pseudorandom-number generator, exactly as if by the expression new java.util.Random
 * This new pseudorandom-number generator is used thereafter for all calls to this method and is used nowhere else.
 * This method is properly synchronized to allow correct use by more than one thread. However,
 * if many threads need to generate pseudorandom numbers at a great rate, it may reduce contention for each thread to have its own pseudorandom-number generator.
 */
public class Random extends AbstractNumericOperatorAssociativityLeftNoArg {

	public Random() {
		super("random", Precedence.USERFUNCTION);
	}



	/* This method returns a pseudorandom double greater than or equal to 0.0 and less than 1.0.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionArgument<?> execute() throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(Math.random());
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}