package org.oss.pdfreporter.jasperreports.compilers.jeval;

import java.util.Date;

import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.NullValue;



public class ResultUtil {

	public static boolean isString(String result, char quotedChar) {
		if (result != null && result.length() >= 2) {

			if (result.charAt(0) == quotedChar
					&& result.charAt(result.length() - 1) == quotedChar) {

				return true;
			}
		}

		return false;
	}
	

	public static boolean isNull(String result) {
		return result.equals(NullValue.QUOTED_NULL);
	}
	public static String getStringResult(String result) {
		return result.substring(1, result.length() -1 );
	}
	
	public static Double getDoubleResult(String result) {
		return new Double(result);
	}
	
	public static Integer getIntResult(String result) {
		return new Double(result).intValue();
	}
	
	public static Long getLongResult(String result) {
		return new Double(result).longValue();
	}
	
	public static Date geDateResult(String result) {
		return new Date(new Double(result).longValue());
	}
	
	public static Boolean getBooleanResult(String result) {
		return JEvalExpression.EXP_TRUE.equals(result);
	}
	
}
