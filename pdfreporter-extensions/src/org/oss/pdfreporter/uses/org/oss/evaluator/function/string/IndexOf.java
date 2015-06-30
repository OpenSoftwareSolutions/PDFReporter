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

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.StringArgument;

/**
 * The java.lang.String.indexOf(int ch) method returns the index within this string of the first occurrence of the specified character.
 * If a character with value ch occurs in the character sequence represented by this String object,
 * then the index(Unicode code units) of the first such occurrence is returned.
 *
 */
public class IndexOf extends AbstractStringOperatorAssociativityLeftTwoStringArg {

	public IndexOf() {
		super("indexOf", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* This method returns the index of the first occurrence of the character in the character sequence represented by this object, or -1 if the character does not occur.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<String> a,FunctionArgument<String> b) throws IllegalArgumentException {

		if (a instanceof StringArgument && b instanceof StringArgument) {
			return FunctionArgumentFactory.createObject(a.getValue().indexOf(b.getValue()));
		}
		throw new IllegalArgumentException(String.format("only argument string as type are supported and not ", a.getType(), b.getType()));

	}
}