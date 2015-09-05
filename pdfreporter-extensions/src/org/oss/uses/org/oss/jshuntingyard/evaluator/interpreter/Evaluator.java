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
package org.oss.uses.org.oss.jshuntingyard.evaluator.interpreter;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.NullArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.VariableArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.parser.ExtendedSHuntingYardParser;

public class Evaluator {

	private static Logger logger = Logger.getLogger(Evaluator.class.getName());
	private final Map<String,Variable> boundVariables;
	private final ExtendedSHuntingYardParser parser;
	private Expression expression;

	public Evaluator(String evalExpression){
		this.boundVariables = new HashMap<String,Variable>();
		this.parser = new ExtendedSHuntingYardParser();
		this.expression = evalExpression == null ? null : parser.infixToRPN(evalExpression);
	}

	public Evaluator(){
		this(null);
	}

	public void bindVariable(Variable variable) {
		boundVariables.put(variable.getName(), variable);
	}

	/**
	 * add user defined function.
	 * @param function
	 */
	public void addFunction(FunctionElement function) {
		parser.addFunction(function);
	}
	
	/**
	 * add user defined functions
	 * @param functions
	 */
	public void addFunctions(Collection<FunctionElement> functions) {
		parser.addFunctions(functions);
	}	

	public void parse(String evalExpression) {
		expression = parser.infixToRPN(evalExpression);
	}

	public FunctionElementArgument<?> evaluate() {
		if(expression == null || expression.isEmpty()){
			throw new RuntimeException("There are no ExpressionElements in the List");
		}
		LinkedList<ExpressionElement> stack = new LinkedList<ExpressionElement>();
		for (ExpressionElement element : expression) {
			if (element instanceof FunctionElementArgument) {
				stack.push(element);
			} else if (element instanceof FunctionElement) {
				FunctionElement function = (FunctionElement) element;
				int numArgs = function.getNumberOfParameters();
				FunctionElementArgument<?> args[] = new FunctionElementArgument<?>[numArgs];
				for (int i=0;i<numArgs;i++) {
					if (stack.isEmpty()) {
						throw new IllegalArgumentException("The function: " + function.getName() + " expects " + numArgs + " parameters");
					}
					ExpressionElement arg = stack.pop();
					if (arg instanceof FunctionElementArgument) {
						int argIndex = numArgs -1 - i;
						args[argIndex] = (FunctionElementArgument<?>) arg;
						if (arg instanceof VariableArgument) {
							VariableArgument variable = (VariableArgument) arg;
							Variable boundVariable = boundVariables.get(variable.getName());
							
							args[argIndex] = replaceVariable(boundVariable);
						}
					} else {
						throw new IllegalArgumentException("FunctionElementArgument expected and not " + arg.getClass().getName());
					}
				}
				FunctionElementArgument<?> result = function.execute(args);
				if (logger.isLoggable(Level.INFO)) {
					String message = "Evaluated: " + result.getValue() + "[" + result.getType() + "] " + function.getName() + "(";
					for (int i=0; i<numArgs; i++) {
						message += args[i].getValue() + "[" + args[i].getType() + "]";
						if (i+1 < numArgs) {
							message += ",";
						}
					}
					message += ")";
					logger.info(message);
				}
				stack.push(result);
			} else {
				throw new IllegalArgumentException("FunctionElementArgument or FunctionElement expected and not " + element.getClass().getName());
			}
		}
		if (stack.size()!=1) {
			throw new IllegalArgumentException("Exact one final result expected and not " + stack.size());
		}
		ExpressionElement element = stack.pop();
		if (element instanceof FunctionElementArgument) {
			return (FunctionElementArgument<?>) element;
		} else {
			throw new IllegalArgumentException("FunctionArgument expected and not " + element.getClass().getName());
		}
	}

	
	
	private FunctionElementArgument<?> replaceVariable(Variable variable) {
		logger.log(Level.INFO, "replacing variable: {0} (value: {1})", new Object[]{variable.getName(),variable.getValue()});
		Object value = variable.getValue();
		if (value instanceof Integer) {
			return FunctionArgumentFactory.createObject((Integer) value);
		}
		if (value instanceof Long) {
			return FunctionArgumentFactory.createObject((Long) value);
		}
		if (value instanceof Float) {
			return FunctionArgumentFactory.createObject((Float) value);
		}
		if (value instanceof Double) {
			return FunctionArgumentFactory.createObject((Double) value);
		}
		if (value instanceof Date) {
			return FunctionArgumentFactory.createObject((Date) value);
		}
		if (value instanceof String) {
			return FunctionArgumentFactory.createString((String) value);
		}
		if (value instanceof Boolean) {
			return FunctionArgumentFactory.createObject((Boolean) value);
		}
		if (value instanceof Object) {
			return FunctionArgumentFactory.createObject(value);
		}
		return new NullArgument();
	}

	
	
}
