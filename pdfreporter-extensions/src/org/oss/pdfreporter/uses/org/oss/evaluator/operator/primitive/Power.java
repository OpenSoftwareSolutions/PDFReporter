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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive;
import java.math.BigInteger;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftTwoArg;

public class Power extends AbstractNumericOperatorAssociativityLeftTwoArg {

	public Power() {
		super("^", Precedence.POWER);
	}


	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a,
			FunctionArgument<?> b) throws IllegalArgumentException {
		if (a.getType()==FunctionArgument.ArgumentType.INTEGER && b.getType()==FunctionArgument.ArgumentType.INTEGER) {
			BigInteger bigA = new BigInteger(a.getString());
			return FunctionArgumentFactory.createObject(bigA.pow(((FunctionArgument<Integer>)b).getValue().intValue()).intValue());
		}
		return FunctionArgumentFactory.createObject(Math.pow(getDouble(a), getDouble(b)));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}
