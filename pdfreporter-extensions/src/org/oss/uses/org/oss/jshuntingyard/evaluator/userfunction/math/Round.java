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

public class Round extends AbstractOneArgFunctionElement<Integer,Double> {

	public Round() {
		super("round", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	@Override
	protected FunctionElementArgument<Integer> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject((int)Math.round(getDouble(a)));
		}

		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}
}
