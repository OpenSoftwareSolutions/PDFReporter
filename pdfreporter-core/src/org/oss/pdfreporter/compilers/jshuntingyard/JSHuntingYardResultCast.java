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
package org.oss.pdfreporter.compilers.jshuntingyard;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionType;
import org.oss.pdfreporter.compilers.util.ResultUtil;


public class JSHuntingYardResultCast implements IExpressionElement{
	private static final Logger logger = Logger.getLogger(JSHuntingYardResultCast.class.getName());
	private static String CAST_MATCH = ".*\\(\\s*\\w+\\s*\\).*";
	private static Pattern CAST_SPLIT = Pattern.compile("\\w+");


	private final ExpressionType type;
	private JSHuntingYardExpression expression = null;

	private JSHuntingYardResultCast(ExpressionType type, JSHuntingYardExpression expression) {
		this.type = type;
		this.expression = expression;
	}

	private JSHuntingYardResultCast(ExpressionType type) {
		this(type,null);
	}

	public JSHuntingYardResultCast() {
		this(ExpressionType.STRING,null);
	}

	public static boolean isCast(String text) {
		return text.matches(CAST_MATCH);
	}

	public static String getNext(String text) throws ExpressionParseException {
		Matcher m = CAST_SPLIT.matcher(text);
		if (m.find()) {
			return text.substring(m.end() + 1);
		}
		throw new ExpressionParseException("Pattern: " + CAST_SPLIT + " does not match: " + text);
	}

	// TODO (27.08.2015, Donat, Open Software Solutions): Add case for void / object / null
	public static JSHuntingYardResultCast parseCast(String s) throws ExpressionParseException {
		if (s.matches(CAST_MATCH)) {
			String cast = extract(CAST_SPLIT, s);
			if (cast.equalsIgnoreCase("boolean")) {
				return new JSHuntingYardResultCast(ExpressionType.BOOLEAN);
			} else if (cast.equalsIgnoreCase("integer") || cast.equalsIgnoreCase("int")) {
				return new JSHuntingYardResultCast(ExpressionType.INTEGER);
			} else if (cast.equalsIgnoreCase("double")) {
				return new JSHuntingYardResultCast(ExpressionType.DOUBLE);
			} else if (cast.equalsIgnoreCase("float")) {
				return new JSHuntingYardResultCast(ExpressionType.FLOAT);
			} else if (cast.equalsIgnoreCase("string")) {
				return new JSHuntingYardResultCast(ExpressionType.STRING);
			} else if (cast.equalsIgnoreCase("long")) {
				return new JSHuntingYardResultCast(ExpressionType.LONG);
			} else if (cast.equalsIgnoreCase("date")) {
				return new JSHuntingYardResultCast(ExpressionType.DATE);
			}
		}
		throw new ExpressionParseException("Unsupported cast operator: " + s);
	}

	private static String extract(Pattern p, String text) throws ExpressionParseException {
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group();
		}
		throw new ExpressionParseException("Pattern: " + p + " does not match: " + text);
	}


	public void setExpression(JSHuntingYardExpression expression) {
		this.expression = expression;
	}
	
	private Object doCast(Object result) throws ExpressionEvaluationException {
		if (result == null) {
			return null;
		}
		if (result instanceof Number) {
			result = ResultUtil.numberCast(type, (Number) result);
		}
		ResultUtil.assertResultType(type, result);
		return result;
	}
	

	@Override
	public Object getValue() throws ExpressionEvaluationException {
		try {
			return doCast(this.expression.evaluateValue());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error evaluating expression: " + expression.getExpression(), e);
			throw new ExpressionEvaluationException(e);
		}
	}

	@Override
	public Object getOldValue() throws ExpressionEvaluationException {
		try {
			return doCast(this.expression.evaluateOldValue());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error evaluating expression: " + expression.getExpression(), e);
			throw new ExpressionEvaluationException(e);
		}
	}
}
