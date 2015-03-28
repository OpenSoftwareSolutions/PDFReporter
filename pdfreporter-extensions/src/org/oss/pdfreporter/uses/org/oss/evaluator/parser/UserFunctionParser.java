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

import org.oss.pdfreporter.uses.org.oss.evaluator.function.ExpressionElement;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

public class UserFunctionParser {

	private final ArrayList<ExpressionElement> out;

	UserFunctionParser(ArrayList<ExpressionElement> out) {
		this.out = out;
	}

	public int parse(String[] inputTokens, int tokenIndex) {
		tokenIndex++; // read over function name
		if (tokenIndex<inputTokens.length ) {
			if (inputTokens[tokenIndex].equals("(")) {
				boolean colonExpected = false;
				while (!inputTokens[++tokenIndex].equals(")") && tokenIndex<inputTokens.length ) {
					if (colonExpected) {
						if (inputTokens[tokenIndex].equals(",")) {
							colonExpected=false;
							continue;
						}
						throw new IllegalArgumentException("Missing token ','" );
					} else {
						out.add(FunctionArgumentFactory.createObject(inputTokens[tokenIndex]));
						colonExpected = true;
					}
				}
				if (tokenIndex>=inputTokens.length ) {
					throw new IllegalArgumentException("Missing token ')'" );
				}
			} else {
				throw new IllegalArgumentException("Invalid Token '(' expected." + inputTokens[tokenIndex]);
			}
		}
		return tokenIndex;
	}


}
