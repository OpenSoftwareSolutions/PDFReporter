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
package org.oss.uses.org.oss.jshuntingyard.lexer;


public class ExpressionToken {

	private final TokenType type;
	private final int startPos;
	private final int endPos;
	private final String token;

	public ExpressionToken(TokenType type, int startPos, int endPos, String source){
		this.type = type;
		this.startPos = startPos;
		this.endPos = endPos;
		this.token = source.substring(startPos, endPos);
	}

	public TokenType getType() {
		return type;
	}


	public String getToken() {
		return token;
	}

	@Override
	public String toString(){
		return String.format("ExpressionToken [%2d, %2d, %s, %s]", startPos, endPos, type, token);
	}
}
