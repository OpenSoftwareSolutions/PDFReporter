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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

public class GreaterThanOrEqualTo extends AbstractRelationalOperator {

	public GreaterThanOrEqualTo() {
		super(">=",Precedence.RELATIONAL);
	}

	@Override
	protected FunctionArgument<Boolean> execute(Relation relation) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(Relation.GREATER==relation || Relation.EQUAL==relation);
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}
