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

public class ParserUtil {


	final static char SINGLEQUOTE = '\'';
	final static char WHITESPACE = ' ';
	public final static String DELIMITER = "DEL";

	public static String modifyExpression(String expression) {
		StringBuffer tokenBuffer = new StringBuffer();
		int currentSize = expression.length();

		for(int index = 0; currentSize > 0; index++) {
			char currentCharacter = expression.charAt(index);
			// single quote handling
			if(isSingleQuote(currentCharacter)){
				tokenBuffer.append(SINGLEQUOTE);
				do{
					index++;
					currentSize--;
					currentCharacter = expression.charAt(index);
					tokenBuffer.append(currentCharacter);
					// cases like - 'I hasn't' - will fail
				} while (!isSingleQuote(currentCharacter));
			}
			// standard handling of characters and digits
			if(!isWhiteSpace(currentCharacter) && !isSingleQuote(currentCharacter)){
				tokenBuffer.append(currentCharacter);
			} else {
				if(!isSingleQuote(currentCharacter)){
					tokenBuffer.append(DELIMITER);
				}
			}
			currentSize--;
		}
		return tokenBuffer.toString();
	}

	private static boolean isSingleQuote(char currentCharacter) {
		return currentCharacter == SINGLEQUOTE;
	}

	private static boolean isWhiteSpace(char currentCharacter) {
		return currentCharacter == WHITESPACE;
	}

}
