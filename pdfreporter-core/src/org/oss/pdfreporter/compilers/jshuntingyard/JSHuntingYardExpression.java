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

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.compilers.Expression;
import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IExpressionChunk;
import org.oss.pdfreporter.compilers.IExpressionChunk.ExpresionType;
import org.oss.pdfreporter.compilers.IVariable;
import org.oss.pdfreporter.compilers.IVariableExpressionChunk;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionConstants;
import org.oss.pdfreporter.compilers.jeval.functions.BooleanConverter;
import org.oss.pdfreporter.compilers.jeval.functions.Conditional;
import org.oss.pdfreporter.compilers.jeval.functions.CurrentDate;
import org.oss.pdfreporter.compilers.jeval.functions.DateStringConverter;
import org.oss.pdfreporter.compilers.jeval.functions.DisplayName;
import org.oss.pdfreporter.compilers.jeval.functions.DoubleStringConverter;
import org.oss.pdfreporter.compilers.jeval.functions.IntegerStringConverter;
import org.oss.pdfreporter.compilers.jeval.functions.IsNull;
import org.oss.pdfreporter.compilers.jeval.functions.Message;
import org.oss.pdfreporter.compilers.jeval.functions.MessageWithArg;
import org.oss.pdfreporter.compilers.jeval.functions.NullValue;
import org.oss.pdfreporter.compilers.jeval.functions.TruncateDateTo;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.VariableResolver;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;


public class JSHuntingYardExpression implements Expression {


	private final static Logger logger = Logger.getLogger(JSHuntingYardExpression.class.getName());
	private final Map<String,IVariable> variables;
	private final Map<String,Function> userFunctions;
	private final Evaluator valueEvaluator;
	private final Evaluator oldValueEvaluator;
	private String expression;

	@SuppressWarnings("unchecked")
	public JSHuntingYardExpression() {
		this.variables = new HashMap<String, IVariable>();
		this.userFunctions = new HashMap<String, Function>();
		putFunction(new BooleanConverter());
		putFunction(new IntegerStringConverter());
		putFunction(new DoubleStringConverter());
		putFunction(new DateStringConverter());
		putFunction(new TruncateDateTo());
		putFunction(new Conditional());
		putFunction(new CurrentDate());
		putFunction(new NullValue());
		putFunction(new IsNull());
		putFunction(new Message());
		putFunction(new DisplayName());
		putFunction(new MessageWithArg());
		this.valueEvaluator = new Evaluator();
		this.valueEvaluator.setVariableResolver(new ValueResolver());
		this.valueEvaluator.getFunctions().putAll(userFunctions);
		this.oldValueEvaluator = new Evaluator();
		this.oldValueEvaluator.setVariableResolver(new OldValueResolver());
		this.oldValueEvaluator.getFunctions().putAll(userFunctions);
	}


	private void putFunction(Function function) {
		userFunctions.put(function.getName(), function);
	}


	public static JSHuntingYardExpression newInstance(List<IExpressionChunk> chunks) throws ExpressionParseException {
		JSHuntingYardExpression expression = new JSHuntingYardExpression();
		expression.parse(chunks);
		return expression;
	}

	public void parse(List<IExpressionChunk> chunks) throws ExpressionParseException  {
		this.expression = buildExpression(chunks);
		try {
			valueEvaluator.parse(expression);
			oldValueEvaluator.parse(expression);
		} catch (EvaluationException e) {
			throw new ExpressionParseException("Cannot parse '" + expression + "', error: " + e.getMessage());
		}
	}

	public String getExpression() {
		return expression;
	}

	public IVariable getVariable(String name) {
		return variables.get(getKey(ExpresionType.TYPE_VARIABLE,name));
	}

	public IVariable getParameter(String name) {
		return variables.get(getKey(ExpresionType.TYPE_PARAMETER,name));
	}

	public IVariable getField(String name) {
		return variables.get(getKey(ExpresionType.TYPE_FIELD,name));
	}

	private String buildExpression(List<IExpressionChunk> chunks) {
		StringBuffer sb = new StringBuffer();
		String name;
		IVariable variable;
		for (IExpressionChunk chunk : chunks) {
			switch (chunk.getType()) {
			case TYPE_FIELD:
			case TYPE_PARAMETER:
			case TYPE_VARIABLE:
				name = getKey(chunk.getType(),chunk.getText());
				sb.append("#{");
				sb.append(name);
				sb.append("}");
				variable = ((IVariableExpressionChunk)chunk).getVariable();
				variables.put(name, variable);
				break;
			case TYPE_TEXT:
			case TYPE_RESOURCE:
				sb.append(chunk.getText());
				break;
			}
		}
		return sb.toString();
	}

	private String getKey(ExpresionType type, String name) {
		switch (type.ordinal()) {
		case 1:
			return "P_" + name;
		case 2:
			return "F_" + name;
		case 3:
			return "V_" + name;
		case 4:
			return "R_" + name;
		default:
			return "T_" + name;
		}
	}


	public String evaluateValue() throws ExpressionEvaluationException {
		try {
			return valueEvaluator.evaluate();
		} catch (EvaluationException e) {
			throw new ExpressionEvaluationException("Error while evaluating '" + expression + "'",e);
		}
	}

	public String evaluateOldValue() throws ExpressionEvaluationException {
		try {
			return  oldValueEvaluator.evaluate();
		} catch (EvaluationException e) {
			throw new ExpressionEvaluationException("Error while evaluating '" + expression + "'",e);
		}
	}


	private class ValueResolver implements VariableResolver {
		@Override
		public String resolveVariable(String variableName)
				throws FunctionException {
			try {
				return getString(variables.get(variableName).getValue());
			} catch (ExpressionEvaluationException e) {
				throw new FunctionException(e);
			}
		}
	}

	private class OldValueResolver implements VariableResolver {
		@Override
		public String resolveVariable(String variableName)
				throws FunctionException {
			try {
				return getString(variables.get(variableName).getOldValue());
			} catch (ExpressionEvaluationException e) {
				throw new FunctionException(e);
			}
		}
	}

	private String getString(Object value) {
		if (value instanceof Date) {
			return String.valueOf(((Date)value).getTime());
		} else if (value == null) {
			return ExpressionConstants.QUOTED_NULL;
		} else if (value instanceof String) {
			return EvaluationConstants.SINGLE_QUOTE + value.toString() + EvaluationConstants.SINGLE_QUOTE;
		} else if (value instanceof Locale) {
			return EvaluationConstants.SINGLE_QUOTE + value.toString() + EvaluationConstants.SINGLE_QUOTE;
		} else if (value instanceof Double){
			return BigDecimal.valueOf((Double)value).toPlainString();
		} else if (value instanceof Float){
			return BigDecimal.valueOf((Float)value).toPlainString();
		} else {
			logger.finest("Convert Object to String class: " + value.getClass() + ", toString: " + value.toString());
			return value.toString();
		}
	}
}
