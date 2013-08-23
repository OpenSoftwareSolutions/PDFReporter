package org.oss.pdfreporter.jasperreports.compilers;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts a Text into a IExpressionElement of type ObjectTypeElement if<br>
 * the text can be converted to an integrated data type supported by <br>
 * TextField see on Page 131 in THE JASPERREPORTS ULTIMATE GUIDE.<br>
 * If the text cannot be converted to an integrated type a TextElement<br>
 * is created.<br>
 * <br>
 * TODO use exp4j math expression evaluator. Check first if exp4j can be<br>
 * converted with J2ObjC. There are 3 possible expression types<br>
 * Logic, Math and String. A string can convert to Logic with the<br>
 * <code>equals</code> operator. Any expression prefixed with a String<br>
 * will turn to String type. Math expression can convert to Logic with<br>
 * the operators ==, >, >= , <, <= and !=.<br>
 * Operand: MathOperand, BoolOperand, StringOperand<br>
 * Operator: MathOperator (+,-,*,/,f()), BoolOperator (==,>,<,>=,<=,!=,equals),<br>
 * StringOpetrator (concat) <br>
 * Expression evaluation id done with UPN (operand, operand, operator)
 * Each operator holds one operand. 
 * @author donatmuller
 *
 */
public class TextTypeElementFactory {
	private static String INTEGER_MATCH = "new java\\.lang\\.Integer\\(-?\\d+\\)";
	private static String LONG_MATCH = "new java\\.lang\\.Long\\(-?\\d+\\)";
	private static String SHORT_MATCH = "new java\\.lang\\.Short\\(-?\\d+\\)";
	private static String NUMBER_MATCH = "new java\\.lang\\.Number\\(-?\\d+\\)";
	private static String BYTE_MATCH = "new java\\.lang\\.Byte\\(-?\\d+\\)";

	private static String DOUBLE_MATCH = "new java\\.lang\\.Double\\(-?\\d+\\.\\d+\\)";
	private static String FLOAT_MATCH = "new java\\.lang\\.Float\\(-?\\d+\\.\\d+\\)";
	private static String BIG_DECIMAL_MATCH = "new java\\.math\\.BigDecimal\\(-?\\d+\\.\\d+\\)";
	
	private static String BOOLEAN_MATCH = "new java\\.lang\\.Boolean\\((true|false)\\)";
	private static String DATE_MATCH = "new java\\.util\\.Date\\([\\d]{2}\\.[\\d]{2}\\.[\\d]{4}\\)";
	private static String TIME_MATCH = "new java\\.sql\\.Time\\([\\d]{1,2}:[\\d]{1,2}\\)";
	private static String TIMESTAMP_MATCH = "new java\\.sql\\.Timestamp\\((\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\)";

	private static String TEXT_MATCH = "^\".*\"$";
	
	private static Pattern NUMBER_SPLIT = Pattern.compile("-?\\d+");
	private static Pattern DECIMAL_SPLIT = Pattern.compile("-?\\d+.\\d+");
	private static Pattern BOOLEAN_SPLIT = Pattern.compile("(true|false)");
	private static Pattern DATE_SPLIT = Pattern.compile("[\\d]{2}\\.[\\d]{2}\\.[\\d]{4}");
	private static Pattern TIME_SPLIT = Pattern.compile("[\\d]{1,2}:[\\d]{1,2}");
	private static Pattern TIMESTAMP_SPLIT = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
	
//	private static DateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yyyy");
//	private static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
//	private static DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSSSSS");
	
	private static String MATCH_EQUALS_FALSE = "\\.equals\\( \"\" \\) == false";

	public static IExpressionElement newTextElement(String text) {
		return toExpressionElement(text);
	}


	private static IExpressionElement toExpressionElement(String s) {
		try {
			if (s.matches(INTEGER_MATCH)) {
				return new ObjectTypeElement(Integer.valueOf(extract(NUMBER_SPLIT, s)));
			} else if (s.matches(LONG_MATCH)) {
				return new ObjectTypeElement(Long.valueOf(extract(NUMBER_SPLIT, s)));
			} else if (s.matches(SHORT_MATCH)) {
				return new ObjectTypeElement(Short.valueOf(extract(NUMBER_SPLIT, s)));
			} else if (s.matches(BYTE_MATCH)) {
				return new ObjectTypeElement(Byte.valueOf(extract(NUMBER_SPLIT, s)));
			} else if (s.matches(NUMBER_MATCH)) {
				return new ObjectTypeElement(Integer.valueOf(extract(NUMBER_SPLIT, s)));
			} else if (s.matches(DOUBLE_MATCH)) {
				return new ObjectTypeElement(Double.valueOf(extract(DECIMAL_SPLIT, s)));
			} else if (s.matches(FLOAT_MATCH)) {
				return new ObjectTypeElement(Float.valueOf(extract(DECIMAL_SPLIT, s)));			
			} else if (s.matches(BIG_DECIMAL_MATCH)) {
				return new ObjectTypeElement(new BigDecimal(Long.valueOf(extract(DECIMAL_SPLIT, s))));
			} else if (s.matches(BOOLEAN_MATCH)) {
				return new ObjectTypeElement(Boolean.valueOf(extract(BOOLEAN_SPLIT, s)));
//			} else if (s.matches(DATE_MATCH)) {
//				return new ObjectTypeElement(DATE_FORMAT.parse(extract(DATE_SPLIT, s)));
//			} else if (s.matches(TIME_MATCH)) {
//				return new ObjectTypeElement(TIME_FORMAT.parse(extract(TIME_SPLIT, s)));
//			} else if (s.matches(TIMESTAMP_MATCH)) {
//				return new ObjectTypeElement(TIMESTAMP_FORMAT.parse(extract(TIMESTAMP_SPLIT, s)));
			} else if (s.matches(MATCH_EQUALS_FALSE)) {
				return new EqualsFalseOperator();
			} else if (s.matches(TEXT_MATCH)){
				return new ObjectTypeElement(convertTextChunk(s));
			} else {
				throw new RuntimeException("Not supported expression '" + s + "'");
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		} 
	}

	private static String extract(Pattern p, String text) {
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group();
		}
		throw new RuntimeException("Pattern " + p + " does not match " + text);
	}
	
	private static class EqualsFalseOperator extends AbstractExpressionElement implements IExpressionOperator {

		@Override
		public Object getValue() {
			return "";
		}

		@Override
		public IExpressionElement evaluate(IExpressionElement operand) throws ExpressionEvaluationException {
			Object value  = operand.getValue();
			return new ObjectTypeElement(getValue().equals(value==null ? "" : value)==false);
		}
		
	}
	private static class ObjectTypeElement extends AbstractExpressionElement implements IExpressionElement {
		private final Object value;
		
		private ObjectTypeElement(Object value) {
			
			this.value = value;
		}

		@Override
		public Object getValue() {
			return value;
		}
	}
	
	
	/**
	 * Removes double quotes around text and replaces escaped tabulator and line feed with tabulator and line feed characters.   
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
	
}
