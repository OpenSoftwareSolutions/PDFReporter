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

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IExpressionChunk;
import org.oss.pdfreporter.compilers.IExpressionChunk.ExpresionType;
import org.oss.pdfreporter.compilers.IVariable;
import org.oss.pdfreporter.compilers.IVariableExpressionChunk;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionConstants;
import org.oss.pdfreporter.compilers.jeval.functions.MessageWithArg;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.VariableResolver;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.org.oss.evaluator.Evaluator;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;


public class JSHuntingYardExpression {


	private final static Logger logger = Logger.getLogger(JSHuntingYardExpression.class.getName());
	private final Map<String,IVariable> variables;
	private final Map<String,Function> userFunctions;
	private Evaluator newEval;
	private Evaluator oldEval;
	private String expression;

	public JSHuntingYardExpression() {
		this.variables = new HashMap<String, IVariable>();
		this.userFunctions = new HashMap<String, Function>();
		putFunction(new MessageWithArg());
	}


	private void putFunction(Function function) {
		userFunctions.put(function.getName(), function);
	}


	public static JSHuntingYardExpression newInstance(List<IExpressionChunk> chunks) throws ExpressionParseException {
		JSHuntingYardExpression expression = new JSHuntingYardExpression();
		expression.parse(chunks);
		return expression;
	}

	private void parse(List<IExpressionChunk> chunks) throws ExpressionParseException  {
		this.expression = buildExpression(chunks);
		this.newEval = new Evaluator(expression);
		this.oldEval = new Evaluator(expression);
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


	public Object evaluateValue() throws ExpressionEvaluationException {
		try {
			FunctionArgument<?> evaluate = newEval.evaluate();
			return evaluate.getValue();
		} catch (RuntimeException e) {
			throw new ExpressionEvaluationException("Error while evaluating '" + expression + "'",e);
		}
	}

	public Object evaluateOldValue() throws ExpressionEvaluationException {
		try {
			FunctionArgument<?> evaluate = oldEval.evaluate();
			return  evaluate.getValue();
		} catch (RuntimeException e) {
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
