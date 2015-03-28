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
 * The first argument is the source string and the second argument is the like expression.
 * The expression must contain at least one wildcard (%).
 * The test is case insensitive.
 *
 */
public class Like extends AbstractStringOperatorAssociativityLeftTwoArg {

	public Like() {
		super("like", Precedence.USERFUNCTION);

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
			if(!stringB.contains("%")){
				throw new IllegalArgumentException(String.format("There is no wildcard in the expression of the parameter ", b.getValue()));
			}
			// to lower case
			String lowerCaseA = stringA.toLowerCase();
			String lowerCaseB = stringB.toLowerCase();
			lowerCaseB = lowerCaseB.replace("%", ".*");
			return FunctionArgumentFactory.createObject(lowerCaseA.matches(lowerCaseB));

		}
		throw new IllegalArgumentException(String.format("only argument string as type are supported and not ", a.getType(), b.getType()));

	}
}