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
package org.oss.uses.org.oss.jshuntingyard.evaluator.operator.logic;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public class NotOperator extends AbstractOneArgFunctionElement<Boolean,Boolean> {

	public NotOperator() {
		super("!", Precedence.UNARY);
	}

	@Override
	public FunctionElementArgument<Boolean> execute(FunctionElementArgument<Boolean> arg) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(!arg.getValue());
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}

}
