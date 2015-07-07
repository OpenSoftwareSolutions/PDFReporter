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
package org.oss.uses.org.oss.jshuntingyard.evaluator.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElement.Associativity;
import org.oss.uses.org.oss.jshuntingyard.evaluator.interpreter.Expression;
import org.oss.uses.org.oss.jshuntingyard.evaluator.interpreter.ExpressionElement;
import org.oss.uses.org.oss.jshuntingyard.lexer.ExpressionToken;
import org.oss.uses.org.oss.jshuntingyard.lexer.ExpressionTokenizer;
import org.oss.uses.org.oss.jshuntingyard.lexer.TokenType;

/**
 * Shunting-yard algorithm
 * In computer science, the shunting-yard algorithm is a method for parsing mathematical expressions specified in infix notation.
 * It can be used to produce output in Reverse Polish notation (RPN) or as an abstract syntax tree (AST).
 * The algorithm was invented by Edsger Dijkstra and named the "shunting yard" algorithm because its operation resembles that of a railroad shunting yard.
 * Dijkstra first described the Shunting Yard Algorithm in the Mathematisch Centrum report MR 34/61.
 * Like the evaluation of RPN, the shunting yard algorithm is stack-based. Infix expressions are the form of mathematical notation most people are used to,
 * for instance 3+4 or 3+4*(2−1). For the conversion there are two text variables (strings), the input and the output.
 * There is also a stack that holds operators not yet added to the output queue.
 * To convert, the program reads each symbol in order and does something based on that symbol.
 * The shunting-yard algorithm has been later generalized into operator-precedence parsing.
 *
 *
 */
public class ExtendedSHuntingYardParser {

	private final Map<String,FunctionElement> functionElements;
	
	public ExtendedSHuntingYardParser() {
		this.functionElements = new HashMap<String, FunctionElement>();
		addFunctions(UserFunctions.get());
	}

	/**
	 * add user defined function.
	 * @param function
	 */
	public void addFunction(FunctionElement function) {
		functionElements.put(function.getName(), function);
	}
	
	/**
	 * add user defined functions
	 * @param functions
	 */
	public void addFunctions(Collection<FunctionElement> functions) {
		for (FunctionElement function : functions) {
			addFunction(function);
		}
	}

	/**
	 * Test if a certain is an function element .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private boolean isFunctionElement(ExpressionElement stackItem) {
		if (stackItem instanceof FunctionElement) {
			FunctionElement operator = (FunctionElement) stackItem;
			return functionElements.containsKey(operator.getName());
		}
		return false;
	}


	/**
	 * Test the associativity of a certain operator token .
	 * @param token The token to be tested (needs to operator).
	 * @param type LEFT_ASSOC or RIGHT_ASSOC
	 * @return True if the tokenType equals the input parameter type .
	 */
	private boolean isAssociative(FunctionElement operation, Associativity associativity) {
		return functionElements.get(operation.getName()).getAssociativity() == associativity;
	}

	/**
	 * Compare precendece of two operators.
	 * @param token1 The first operator .
	 * @param token2 The second operator .
	 * @return A negative number if token1 has a smaller precedence than token2,
	 * 0 if the precendences of the two tokens are equal, a positive number
	 * otherwise.
	 */
	private final int cmpPrecedence(ExpressionElement token1, ExpressionElement token2) {
		if (!isFunctionElement(token1) || !isFunctionElement(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1
					+ " " + token2);
		}
		FunctionElement operator1 = (FunctionElement) token1;
		FunctionElement operator2 = (FunctionElement) token2;
		return operator1.getPrecendence().ordinal() - operator2.getPrecendence().ordinal();
	}


	/**
	 * @param expression
	 * @return List<ExpressionElement>
	 */
	public Expression infixToRPN(String expression) {

		//String[] tokens = TokenizerUtil.modifyExpression(expression).split(TokenizerUtil.DELIMITER);
		List<ExpressionToken> tokens = ExpressionTokenizer.tokenize(expression);
		return infixToRPN(tokens.iterator(),false);
	}

	public Expression infixToRPN(Iterator<ExpressionToken> tokenIterator, boolean stopAtNextCloseBrace) {
		Expression out = new Expression();
		Stack<ExpressionElement> stack = new Stack<ExpressionElement>();
			// For all the input tokens [S1] read the next token [S2]
			while (tokenIterator.hasNext()) {
				ExpressionToken token = tokenIterator.next();
				if (token.getType()==TokenType.COMMA) {
					continue;
				} else if (token.getType()==TokenType.FUNCTIONNAME) {
					FunctionElement function = functionElements.get(token.getToken());
					if (function== null) {
						throw new IllegalArgumentException("Unknown function: " + token.getToken());
					}
					int paramsStart = out.size();
					out.addAll(infixToRPN(tokenIterator,true));
					if (function.getNumberOfParameters()==-1) {
						function = new VarArgFunctionElementWrapper(function, out.size() - paramsStart);
					}
					out.add(function);
					continue;
				} else if (token.getType()==TokenType.OPERATOR) {
					FunctionElement operator = functionElements.get(token.getToken());
					// If token is an operator (x) [S3]
					while (!stack.empty() && isFunctionElement(stack.peek())) {
						// [S4]
						if ((isAssociative(operator, Associativity.LEFT) && cmpPrecedence(
								operator, stack.peek()) <= 0)
								|| (isAssociative(operator, Associativity.RIGHT) && cmpPrecedence(
										operator, stack.peek()) < 0)) {
							out.add(stack.pop()); 	// [S5] [S6]
							continue;
						}
						break;
					}
					// Push the new operator on the stack [S7]
					stack.push(operator);
				} else if (token.getType()==TokenType.OPENBRACE) {
					stack.push(new LeftParenthese()); 	// [S8]
				} else if (token.getType()==TokenType.CLOSEBRACE) {
					if (stopAtNextCloseBrace) {
						return out;
					}
					// [S9]
					while (!stack.empty() && !(stack.peek() instanceof LeftParenthese)) {
						out.add(stack.pop()); // [S10]
					}
					stack.pop(); // [S11]
				} else if (!token.getToken().isEmpty()){
					out.add(FunctionArgumentFactory.createObject(token.getToken())); // [S12]
				}
			}
			while (!stack.empty()) {
				out.add(stack.pop()); // [S13]
			}
			return out;
	}

	private static class LeftParenthese implements ExpressionElement{
		@Override
		public String getString() {
			return "(";
		}
	}

}
