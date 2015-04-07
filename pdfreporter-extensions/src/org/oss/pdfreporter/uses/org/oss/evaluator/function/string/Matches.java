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
package org.oss.pdfreporter.uses.org.oss.evaluator.function.string;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

/**
 * The first argument is the source string to find the pattern in. The second argument is the pattern string to locate in the source string.
 * The string arguments must be enclosed in quotes.
 *
 */
public class Matches extends AbstractStringOperatorAssociativityLeftTwoArg {

	public Matches() {
		super("matches", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/*
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a,FunctionArgument<?> b) throws IllegalArgumentException {

		if (a.getType()==FunctionArgument.ArgumentType.STRING && b.getType()==FunctionArgument.ArgumentType.STRING) {
			String stringA = (String) a.getValue();
			String stringB = (String) b.getValue();
			return FunctionArgumentFactory.createObject(stringA.matches(stringB));
		}
		throw new IllegalArgumentException(String.format("only argument string as type are supported and not ", a.getType(), b.getType()));

	}
}