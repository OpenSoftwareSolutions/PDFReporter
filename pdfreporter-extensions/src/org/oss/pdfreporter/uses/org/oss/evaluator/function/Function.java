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
package org.oss.pdfreporter.uses.org.oss.evaluator.function;

public interface Function extends ExpressionElement {
	public enum Associativity {LEFT,RIGHT};
	public enum Precedence {ASSIGNMENT,LOGICAL_OR, LOGICAL_AND, EQULITY, RELATIONAL, ADDITIVE, MULTIPLICATIVE, POWER, UNARY, PARANTHESES, USERFUNCTION};



	Associativity getAssociativity();
	int getNumberOfParameters();
	Precedence getPrecendence();
	String getName();
	boolean isUserFunction();
	FunctionArgument<?> execute(FunctionArgument<?>... args) throws IllegalArgumentException;
}
