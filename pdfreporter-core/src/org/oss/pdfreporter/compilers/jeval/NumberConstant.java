package org.oss.pdfreporter.compilers.jeval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.oss.pdfreporter.compilers.AbstractExpressionElement;
import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.IExpressionElement;


public class NumberConstant extends AbstractExpressionElement implements IExpressionElement{
	private static String INTEGER_MATCH = "new java\\.lang\\.Integer\\(-?\\d+\\)";
	private static String LONG_MATCH = "new java\\.lang\\.Long\\(-?\\d+\\)";
	private static String SHORT_MATCH = "new java\\.lang\\.Short\\(-?\\d+\\)";
	private static String NUMBER_MATCH = "new java\\.lang\\.Number\\(-?\\d+\\)";
	private static String BYTE_MATCH = "new java\\.lang\\.Byte\\(-?\\d+\\)";
	private static Pattern NUMBER_SPLIT = Pattern.compile("-?\\d+");
	
	private final Number number;

	public static boolean isNumber(String text) {
		return text.matches(INTEGER_MATCH) ||
				 text.matches(LONG_MATCH) ||
				 text.matches(SHORT_MATCH) ||
				 text.matches(BYTE_MATCH) ||
				 text.matches(NUMBER_MATCH);
	}
	
	public static NumberConstant parseNumber(String s) throws ExpressionParseException {
		if (s.matches(INTEGER_MATCH)) {
			return new NumberConstant(Integer.valueOf(extract(NUMBER_SPLIT, s)));
		} else if (s.matches(LONG_MATCH)) {
			return new NumberConstant(Long.valueOf(extract(NUMBER_SPLIT, s)));
		} else if (s.matches(SHORT_MATCH)) {
			return new NumberConstant(Short.valueOf(extract(NUMBER_SPLIT, s)));
		} else if (s.matches(BYTE_MATCH)) {
			return new NumberConstant(Byte.valueOf(extract(NUMBER_SPLIT, s)));
		} else if (s.matches(NUMBER_MATCH)) {
			return new NumberConstant(Integer.valueOf(extract(NUMBER_SPLIT, s)));
		}
		throw new ExpressionParseException("Unsupported Numberconstant " + s);
	}
	
	private static String extract(Pattern p, String text) throws ExpressionParseException {
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group();
		}
		throw new ExpressionParseException("Pattern: " + p + " does not match: " + text);
	}
	
	public NumberConstant(Number number) {
		this.number = number;
	}

	@Override
	public Object getValue() throws ExpressionEvaluationException {
		return number;
	}
}
