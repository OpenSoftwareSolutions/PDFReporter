package org.oss.pdfreporter.jasperreports.compilers.jeval;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.jasperreports.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.jasperreports.compilers.jeval.IExpressionChunk.ExpresionType;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.BooleanConverter;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.Conditional;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.DateStringConverter;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.DoubleStringConverter;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.IntegerStringConverter;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.IsNull;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.Message;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.NullValue;
import org.oss.pdfreporter.jasperreports.compilers.jeval.functions.TruncateDateTo;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.VariableResolver;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;


public class JEvalExpression  {
	public static String EXP_TRUE = "1.0";

	private final static Logger logger = Logger.getLogger(JEvalExpression.class.getName());
	private final Map<String,IVariable> variables;
	private final Map<String,Function> userFunctions;
	private final Evaluator valueEvaluator;
	private final Evaluator oldValueEvaluator;
	private String expression;
	
	@SuppressWarnings("unchecked")
	public JEvalExpression() {
		this.variables = new HashMap<String, IVariable>();
		this.userFunctions = new HashMap<String, Function>();
		putFunction(new BooleanConverter());
		putFunction(new IntegerStringConverter());
		putFunction(new DoubleStringConverter());
		putFunction(new DateStringConverter());
		putFunction(new TruncateDateTo());
		putFunction(new Conditional());
		putFunction(new NullValue());
		putFunction(new IsNull());
		putFunction(new Message());
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


	public static JEvalExpression newInstance(List<IExpressionChunk> chunks) throws ExpressionParseException {
		JEvalExpression expression = new JEvalExpression();
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
			return NullValue.QUOTED_NULL;
		} else if (value instanceof String) {
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
