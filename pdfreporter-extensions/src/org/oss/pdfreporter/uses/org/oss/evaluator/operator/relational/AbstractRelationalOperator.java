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

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractOperator;

public abstract class AbstractRelationalOperator extends AbstractOperator {

	public enum Relation {GREATER, EQUAL, LESSER, NOTEQUAL};

	public AbstractRelationalOperator(String name, Precedence precedence) {
		super(name, 2, Function.Associativity.LEFT, precedence);
	}

	private boolean isNull(FunctionArgument<?> arg) {
		return arg.getType()==FunctionArgument.ArgumentType.NULL;
	}

	@Override
	public FunctionArgument<Boolean> execute(FunctionArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute(args[0],args[1]);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FunctionArgument<Boolean> execute(FunctionArgument<?> a, FunctionArgument<?> b) throws IllegalArgumentException {
		if (isNull(a) && isNull(b)) {
			return execute(Relation.EQUAL);
		}
		if (isNull(a)) {
			return execute(Relation.NOTEQUAL);
		}
		if (a instanceof Comparable && b instanceof Comparable) {
			int result = ((Comparable)a).compareTo((Comparable)b);
			if (result < 0) {
				return execute(Relation.LESSER);
			} else if (result == 0) {
				return execute(Relation.EQUAL);
			} else {
				return execute(Relation.GREATER);
			}
		}
		throw new IllegalArgumentException("FunctionArguments must implement Comparable");

	}

	abstract protected FunctionArgument<Boolean> execute(Relation relation) throws IllegalArgumentException;

}
