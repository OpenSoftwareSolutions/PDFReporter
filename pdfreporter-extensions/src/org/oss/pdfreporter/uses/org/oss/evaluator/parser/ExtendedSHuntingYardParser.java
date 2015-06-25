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
package org.oss.pdfreporter.uses.org.oss.evaluator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.ExpressionElement;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Associativity;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Abs;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Acos;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Asin;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Atan;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Atan2;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Ceil;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Cos;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Exp;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Floor;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.IEEEremainder;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Log;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Max;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Min;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Random;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Rint;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Round;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Sin;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Sqrt;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.Tan;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.ToDegrees;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.math.ToRadians;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.CharAt;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.CompareTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.CompareToIgnoreCase;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Concat;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Contains;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.EndsWith;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Equals;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.EqualsIgnoreCase;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.IndexOf;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.LastIndexOf;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Length;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Like;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Matches;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.NumberFormat;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Replace;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.StartsWith;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.Substring;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.ToLowerCase;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.ToUpperCase;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.conditional.IfElse;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.converter.BooleanStringConverter;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.converter.DateStringConverter;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.converter.DoubleStringConverter;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.converter.IntegerStringConverter;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.date.CurrentDate;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.date.Now;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.date.TruncateDateTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.logic.AndOperator;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.logic.NotOperator;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.logic.OrOperator;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Add;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Divide;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Modulo;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Multiply;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Power;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.primitive.Subtract;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.EqualTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.GreaterThan;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.GreaterThanOrEqualTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.LessThan;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.LessThanOrEqualTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.relational.NotEqualTo;
import org.oss.pdfreporter.uses.org.oss.evaluator.tokenizer.ExpressionToken;
import org.oss.pdfreporter.uses.org.oss.evaluator.tokenizer.ExpressionTokenizer;


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

	private final Map<String,Function> operators;
	public ExtendedSHuntingYardParser() {
		operators = new HashMap<String, Function>();
		// Primitiv
		addFunction(new Add());
		addFunction(new Subtract());
		addFunction(new Multiply());
		addFunction(new Divide());
		addFunction(new Power());
		addFunction(new Modulo());
		// Math
		addFunction(new Round());
		addFunction(new Max());
		addFunction(new Min());
		addFunction(new Abs());
		addFunction(new Acos());
		addFunction(new Cos());
		addFunction(new Asin());
		addFunction(new Atan());
		addFunction(new Atan2());
		addFunction(new Ceil());
		addFunction(new Exp());
		addFunction(new Floor());
		addFunction(new IEEEremainder());
		addFunction(new Log());
		addFunction(new Random());
		addFunction(new Rint());
		addFunction(new Sin());
		addFunction(new Sqrt());
		addFunction(new Tan());
		addFunction(new ToDegrees());
		addFunction(new ToRadians());

		// Relational
		addFunction(new EqualTo());
		addFunction(new NotEqualTo());
		addFunction(new LessThan());
		addFunction(new GreaterThan());
		addFunction(new LessThanOrEqualTo());
		addFunction(new GreaterThanOrEqualTo());
		// Logic
		addFunction(new AndOperator());
		addFunction(new OrOperator());
		addFunction(new NotOperator());
		// String
		addFunction(new Length());
		addFunction(new Substring());
		addFunction(new CharAt());
		addFunction(new CompareTo());
		addFunction(new CompareToIgnoreCase());
		addFunction(new Equals());
		addFunction(new EqualsIgnoreCase());
		addFunction(new Concat());
		addFunction(new Contains());
		addFunction(new EndsWith());
		addFunction(new StartsWith());
		addFunction(new Replace());
		addFunction(new ToUpperCase());
		addFunction(new ToLowerCase());
		addFunction(new IndexOf());
		addFunction(new LastIndexOf());
		addFunction(new Like());
		addFunction(new Matches());
		addFunction(new NumberFormat());
		// Date
		addFunction(new CurrentDate());
		addFunction(new Now());
		addFunction(new TruncateDateTo());
		// converter
		addFunction(new BooleanStringConverter());
		addFunction(new DateStringConverter());
		addFunction(new DoubleStringConverter());
		addFunction(new IntegerStringConverter());
		// Conditional
		addFunction(new IfElse());

	}

	/**
	 * add user defined function.
	 * @param function
	 */
	public void addFunction(Function function) {
		operators.put(function.getName(), function);
	}

	public void addFunctions(Map<String,Function> operators) {
		operators.putAll(operators);
	}

	/**
	 * Test if a certain is an operator .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private boolean isOperator(String token) {
		return operators.containsKey(token);
	}
	/**
	 * Test if a certain is an operator .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private boolean isOperator(ExpressionElement stackItem) {
		if (stackItem instanceof Function) {
			Function operator = (Function) stackItem;
			return operators.containsKey(operator.getName());
		}
		return false;
	}


	/**
	 * Test the associativity of a certain operator token .
	 * @param token The token to be tested (needs to operator).
	 * @param type LEFT_ASSOC or RIGHT_ASSOC
	 * @return True if the tokenType equals the input parameter type .
	 */
	private boolean isAssociative(Function operation, Associativity associativity) {
		return operators.get(operation.getName()).getAssociativity() == associativity;
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
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1
					+ " " + token2);
		}
		Function operator1 = (Function) token1;
		Function operator2 = (Function) token2;
		return operator1.getPrecendence().ordinal() - operator2.getPrecendence().ordinal();
	}


	/**
	 * @param expression
	 * @return List<ExpressionElement>
	 */
	public List<ExpressionElement> infixToRPN(String expression) {

		//String[] tokens = TokenizerUtil.modifyExpression(expression).split(TokenizerUtil.DELIMITER);
		List<ExpressionToken> tokens = ExpressionTokenizer.tokenize(expression);
		return infixToRPN(ExpressionTokenizer.getTokenAsStringArray(tokens));
	}

	public List<ExpressionElement> infixToRPN(String[] inputTokens) {
		ArrayList<ExpressionElement> out = new ArrayList<ExpressionElement>();
		Stack<ExpressionElement> stack = new Stack<ExpressionElement>();
			// For all the input tokens [S1] read the next token [S2]
			for (int tokenIndex = 0; tokenIndex<inputTokens.length;tokenIndex++) {
				String token = inputTokens[tokenIndex];
				if (isOperator(token)) {
					Function operator = operators.get(token);
					if (operator.isUserFunction()) {
						tokenIndex = new UserFunctionParser(out).parse(inputTokens,tokenIndex);
						out.add(operator);
						continue;
					}
					// If token is an operator (x) [S3]
					while (!stack.empty() && isOperator(stack.peek())) {
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
				} else if (token.equals("(")) {
					stack.push(new LeftParenthese()); 	// [S8]
				} else if (token.equals(")")) {
					// [S9]
					while (!stack.empty() && !(stack.peek() instanceof LeftParenthese)) {
						out.add(stack.pop()); // [S10]
					}
					stack.pop(); // [S11]
				} else if (!token.isEmpty()){
					out.add(FunctionArgumentFactory.createObject(token)); // [S12]
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
