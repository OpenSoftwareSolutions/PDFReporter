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
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractStringOperatorAssociativityLeftThreeArg;

/**
 * The java.lang.String.replace(CharSequence target, CharSequence replacement) method replaces
 * each substring of this string that matches the literal target sequence with the specified literal replacement sequence.
 * The replacement proceeds from the beginning of the string to the end:
 * Example: replacing "cc" with "b" in the string "ccc" will result in "bc" rather than "cb".
 *
 */
public class Replace extends AbstractStringOperatorAssociativityLeftThreeArg {

	public Replace() {
		super("replace", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* This method returns the resulting string.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftThreeArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a,FunctionArgument<?> b, FunctionArgument<?> c) throws IllegalArgumentException {

		if (a.getType()==FunctionArgument.ArgumentType.STRING && b.getType()==FunctionArgument.ArgumentType.STRING
				&& c.getType()==FunctionArgument.ArgumentType.STRING) {
			String string = (String) a.getValue();
			String target = (String) b.getValue();
			String replacement = (String) c.getValue();
			return FunctionArgumentFactory.createString(string.replace(target, replacement));
		}
		throw new IllegalArgumentException(String.format("only argument string as type are supported and not ", a.getType(), b.getType(), c.getType()));

	}
}
