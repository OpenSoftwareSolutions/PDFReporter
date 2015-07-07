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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExpressionTokenizer{

	private ExpressionTokenizer(){
		// nothing
	}


	public static List<ExpressionToken> tokenize(String source){
		List<ExpressionToken> tokens = new ArrayList<ExpressionToken>();
		int pos = 0;
		final int end = source.length();
		Matcher m = Pattern.compile("dummy").matcher(source);
		m.useTransparentBounds(true).useAnchoringBounds(false);
		while (pos < end){
			m.region(pos, end);
			for (TokenType tokenType : TokenType.values()){
				if (m.usePattern(tokenType.pattern()).lookingAt()){
					tokens.add(new ExpressionToken(tokenType, m.start(), m.end(), source));
					pos = m.end()-1;
					break;
				}
			}
			pos++;
		}
		return tokens;
	}

}
