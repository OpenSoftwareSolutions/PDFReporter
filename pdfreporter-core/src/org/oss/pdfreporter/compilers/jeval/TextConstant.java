package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.compilers.AbstractExpressionElement;
import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.IExpressionElement;

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
