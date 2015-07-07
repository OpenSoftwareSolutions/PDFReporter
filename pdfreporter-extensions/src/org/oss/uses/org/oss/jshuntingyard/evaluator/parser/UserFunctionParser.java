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

import java.util.Iterator;

import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.interpreter.Expression;
import org.oss.uses.org.oss.jshuntingyard.lexer.ExpressionToken;

public class UserFunctionParser {
	
	public static Expression parse(Iterator<ExpressionToken> tokenIterator) {
		Expression out = new Expression();
		if (tokenIterator.hasNext()) {
			ExpressionToken token = tokenIterator.next();
			if (token.getToken().equals("(")) {
				boolean colonExpected = false;
				while (tokenIterator.hasNext()) {
					token = tokenIterator.next();
					if (token.getToken().equals(")")) {
						break;
					}
					if (colonExpected) {
						if (token.getToken().equals(",")) {
							colonExpected=false;
							continue;
						}
						throw new IllegalArgumentException("Missing token ','" );
					} else {
						out.add(FunctionArgumentFactory.createObject(token.getToken()));
						colonExpected = true;
					}
				}
				if (!token.getToken().equals(")")) {
					throw new IllegalArgumentException("Missing token ')'" );
				}
			} else {
				throw new IllegalArgumentException("Invalid Token '(' expected and not: " + token.getToken());
			}
		}
		return out;
	}

}
