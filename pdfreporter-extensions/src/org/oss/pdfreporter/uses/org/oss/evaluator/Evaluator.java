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
package org.oss.pdfreporter.uses.org.oss.evaluator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.ExpressionElement;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.VariableArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.parser.ExtendedSHuntingYardParser;

public class Evaluator {

	private final Map<String,Variable> variables;
	private final List<ExpressionElement> expression;

	public Evaluator(String evalExpression){
		this.variables = new HashMap<String,Variable>();
		this.expression = new ExtendedSHuntingYardParser().infixToRPN(evalExpression);
	}

	public void bindVariable(Variable variable) {
		variables.put(variable.getName(), variable);
	}


	public FunctionArgument<?> evaluate() {

		LinkedList<ExpressionElement> stack = new LinkedList<ExpressionElement>();
		for (ExpressionElement element : expression) {
			if (element instanceof FunctionArgument) {
				stack.push(element);
			} else if (element instanceof Function) {
				Function function = (Function) element;
				int numArgs = function.getNumberOfParameters();
				FunctionArgument<?> args[] = new FunctionArgument<?>[numArgs];
				for (int i=0;i<numArgs;i++) {
					ExpressionElement arg = stack.pop();
					if (arg instanceof FunctionArgument) {
						args[numArgs -1 - i] = (FunctionArgument<?>) arg;
						if (arg instanceof VariableArgument) {
							VariableArgument variable = (VariableArgument) arg;
							Variable value = variables.get(variable.getName());
							variable.setValue(value.getValue());
						}
					} else {
						throw new IllegalArgumentException("FunctionArgument expected and not " + arg.getClass().getName());
					}
				}
				stack.push(function.execute(args));
			} else {
				throw new IllegalArgumentException("FunctionArgument or Function expected and not " + element.getClass().getName());
			}
		}
		if (stack.size()!=1) {
			throw new IllegalArgumentException("Exact one final result expected and not " + stack.size());
		}
		ExpressionElement element = stack.pop();
		if (element instanceof FunctionArgument) {
			return (FunctionArgument<?>) element;
		} else {
			throw new IllegalArgumentException("FunctionArgument expected and not " + element.getClass().getName());
		}
	}
}
