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
package org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 * The java.lang.String.indexOf(int ch) method returns the index within this string of the first occurrence of the specified character.
 * If a character with value ch occurs in the character sequence represented by this String object,
 * then the index(Unicode code units) of the first such occurrence is returned.
 *
 */
public class IndexOf extends AbstractTwoArgFunctionElement<Integer,String,String> {

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
	protected FunctionElementArgument<Integer> execute(FunctionElementArgument<String> a,FunctionElementArgument<String> b) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(a.getValue().indexOf(b.getValue()));
	}
}