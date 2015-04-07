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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.logic;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

public class NotOperator extends AbstractLogicOperation {

	public NotOperator() {
		super("!", 1, Precedence.UNARY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FunctionArgument<Boolean> execute(FunctionArgument<?>... args)
			throws IllegalArgumentException {
		assertNumArgs(args);
		assertBoolean(args);
		FunctionArgument<Boolean> a = (FunctionArgument<Boolean>) args[0];

		return FunctionArgumentFactory.createObject(!a.getValue());
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}

}
