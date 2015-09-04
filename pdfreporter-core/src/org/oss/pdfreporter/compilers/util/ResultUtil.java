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
package org.oss.pdfreporter.compilers.util;

import java.util.Collection;
import java.util.Date;

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.IVariable;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionConstants;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionType;
import org.oss.pdfreporter.converters.DecimalConverter;



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
		return result.equals(ExpressionConstants.QUOTED_NULL);
	}
	public static String getStringResult(String result) {
		return result.substring(1, result.length() -1 );
	}

	public static Double getDoubleResult(String result) {
		return DecimalConverter.toDouble(result);
	}

	public static Integer getIntResult(String result) {
		return DecimalConverter.toDouble(result).intValue();
	}

	public static Long getLongResult(String result) {
		return DecimalConverter.toDouble(result).longValue();
	}

	public static Date geDateResult(String result) {
		return new Date(DecimalConverter.toDouble(result).longValue());
	}

	public static Boolean getBooleanResult(String result) {
		return ExpressionConstants.EXP_TRUE.equals(result);
	}
	
	public static void assertResultType(ExpressionType expectedType, Object result) throws ExpressionEvaluationException{
		switch (expectedType) {
		case DATE:
			if (!(result instanceof Date)) {
				throw new ExpressionEvaluationException("Date result expected and not: " + getType(result));
			}
			break;
		case DOUBLE:
			if (!(result instanceof Double)) {
				throw new ExpressionEvaluationException("Double result expected and not: " + getType(result));
			}
			break;
		case FLOAT:
			if (!(result instanceof Float)) {
				throw new ExpressionEvaluationException("Float result expected and not: " + getType(result));
			}
			break;
		case LONG:
			if (!(result instanceof Long)) {
				throw new ExpressionEvaluationException("Long result expected and not: " + getType(result));
			}
			break;
		case INTEGER:
			if (!(result instanceof Integer)) {
				throw new ExpressionEvaluationException("Integer result expected and not: " + getType(result));
			}
			break;
		case BOOLEAN:
			if (!(result instanceof Boolean)) {
				throw new ExpressionEvaluationException("Boolean result expected and not: " + getType(result));
			}
			break;
		case STRING:
			if (!(result instanceof String)) {
				throw new ExpressionEvaluationException("String result expected and not: " + getType(result));
			}
			break;
		}
	}
		
		@SuppressWarnings("incomplete-switch")
		public static Number numberCast(ExpressionType expectedType, Number result) throws ExpressionEvaluationException{
			switch (expectedType) {
			case FLOAT:
				if (!(result instanceof Float)) {
					return result.floatValue();
				}
				break;
			case DOUBLE:
				if (!(result instanceof Double)) {
					return result.doubleValue();
				}
				break;
			case LONG:
				if (!(result instanceof Long)) {
					return result.longValue();
				}
				break;
			case INTEGER:
				if (!(result instanceof Integer)) {
					return result.intValue();
				}
				break;
			}
			return result;
	}
	
	private static String getType(Object result) {
		return result == null ? "null" : result.getClass().getSimpleName();
	}

	public static String getDump(Collection<IVariable> variables) throws ExpressionEvaluationException {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		for (IVariable var : variables) {
			Object newValue = var.getValue();
			Object oldValue = var.getOldValue();
			buf.append(var.getName() + ": " + newValue + " [" + getType(newValue) + "] (" + oldValue + " [" + getType(oldValue) + "])");
			buf.append(",");
		}
		buf.append("}");
		return buf.toString();
	}
	
}
