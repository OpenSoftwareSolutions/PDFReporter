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

import java.util.Date;

import org.oss.pdfreporter.compilers.jeval.functions.NullValue;
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
		return result.equals(NullValue.QUOTED_NULL);
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
		return JEvalExpression.EXP_TRUE.equals(result);
	}
	
}
