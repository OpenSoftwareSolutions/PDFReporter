/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.expressionelements.AbstractExpressionElement;

public class TextConstant extends AbstractExpressionElement implements IExpressionElement{
	private static String TEXT_MATCH = "^\".*\"$";
	
	private final String text;

	public static boolean isText(String text) {
		return text.matches(TEXT_MATCH);
	}
	
	public static TextConstant parseText(String s) throws ExpressionParseException {
		if (s.matches(TEXT_MATCH)) {
			return new TextConstant(convertTextChunk(s));
		} 
		throw new ExpressionParseException("Unsupported Text constant " + s);
	}
	
	
	/**
	 * Removes double quotes around text and replaces escaped tabulator, line feed and quotes with tabulator, line feed characters and quote.   
	 * @param chunkText
	 * @return
	 */
	private static String convertTextChunk(String chunkText) {
		chunkText = chunkText.substring(1, chunkText.length() - 1);
		chunkText = chunkText.replace("\\n", "\n");
		chunkText = chunkText.replace("\\t", "\t");
		chunkText = chunkText.replace("\\\"", "\"");
		return chunkText;
	}
	
	
	public TextConstant(String text) {
		this.text = text;
	}

	@Override
	public Object getValue() throws ExpressionEvaluationException {
		return text;
	}
}
