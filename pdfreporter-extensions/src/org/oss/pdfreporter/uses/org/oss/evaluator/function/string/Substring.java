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

public class Substring extends AbstractStringOperatorAssociativityLeftThreeArg {

	public Substring() {
		super("substr", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a,FunctionArgument<?> b, FunctionArgument<?> c) throws IllegalArgumentException {

		if (a.getType()==FunctionArgument.ArgumentType.STRING && b.getType()==FunctionArgument.ArgumentType.INTEGER
				&& c.getType()==FunctionArgument.ArgumentType.INTEGER) {
			String string = (String) a.getValue();
			Integer begin = (Integer) b.getValue();
			Integer end = (Integer) c.getValue();
			return FunctionArgumentFactory.createString(string.substring(begin, end));
		}
		throw new IllegalArgumentException(String.format("only first argument string as type and second and third argument integer as type are supported and not ", a.getType(), b.getType(), c.getType()));

	}
}
