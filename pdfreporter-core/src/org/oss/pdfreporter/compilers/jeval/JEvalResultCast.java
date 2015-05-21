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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionType;
import org.oss.pdfreporter.compilers.util.ResultUtil;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;


public class JEvalResultCast implements IExpressionElement{
	private static final Logger logger = Logger.getLogger(JEvalResultCast.class.getName());
	private static String CAST_MATCH = ".*\\(\\s*\\w+\\s*\\).*";
	private static Pattern CAST_SPLIT = Pattern.compile("\\w+");


	private final ExpressionType type;
	private JEvalExpression expression = null;

	private JEvalResultCast(ExpressionType type, JEvalExpression expression) {
		this.type = type;
		this.expression = expression;
	}

	private JEvalResultCast(ExpressionType type) {
		this(type,null);
	}

	public JEvalResultCast() {
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

	public static JEvalResultCast parseCast(String s) throws ExpressionParseException {
		if (s.matches(CAST_MATCH)) {
			String cast = extract(CAST_SPLIT, s);
			if (cast.equalsIgnoreCase("boolean")) {
				return new JEvalResultCast(ExpressionType.BOOLEAN);
			} else if (cast.equalsIgnoreCase("integer") || cast.equalsIgnoreCase("int")) {
				return new JEvalResultCast(ExpressionType.INTEGER);
			} else if (cast.equalsIgnoreCase("double") || cast.equalsIgnoreCase("float")) {
				return new JEvalResultCast(ExpressionType.DOUBLE);
			} else if (cast.equalsIgnoreCase("string")) {
				return new JEvalResultCast(ExpressionType.STRING);
			} else if (cast.equalsIgnoreCase("long")) {
				return new JEvalResultCast(ExpressionType.LONG);
			} else if (cast.equalsIgnoreCase("date")) {
				return new JEvalResultCast(ExpressionType.DATE);
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


	public void setExpression(JEvalExpression expression) {
		this.expression = expression;
	}

	private Object doCast(String result) throws ExpressionEvaluationException {
		if (ResultUtil.isNull(result)) {
			return null;
		}
		assertResultType(result);
		switch (type) {
		case STRING:
			return ResultUtil.getStringResult(result);
		case BOOLEAN:
			return ResultUtil.getBooleanResult(result);
		case INTEGER:
			return ResultUtil.getIntResult(result);
		case LONG:
			return ResultUtil.getLongResult(result);
		case DOUBLE:
			return ResultUtil.getDoubleResult(result);
		case DATE:
			return ResultUtil.geDateResult(result);
		}
		throw new ExpressionEvaluationException("Unreachable " + type + ", result: " + result);
	}

	public void assertResultType(String result) throws ExpressionEvaluationException {
		boolean isText = ResultUtil.isString(result, EvaluationConstants.SINGLE_QUOTE);
		if (type==ExpressionType.STRING  && !isText) {
			throw new ExpressionEvaluationException("Result of type String expected actual value is unquoted: " + result);
		} else if (type!=ExpressionType.STRING  && isText) {
			throw new ExpressionEvaluationException("Result of type " + type + " expected actual value is quoted: " + result);
		}
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
