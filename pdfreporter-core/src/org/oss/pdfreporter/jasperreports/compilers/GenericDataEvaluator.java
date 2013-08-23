package org.oss.pdfreporter.jasperreports.compilers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRExpressionChunk;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.design.JRSourceCompileTask;
import org.oss.pdfreporter.engine.fill.IJRFillParameter;
import org.oss.pdfreporter.engine.fill.JREvaluator;
import org.oss.pdfreporter.engine.fill.JRFillField;
import org.oss.pdfreporter.engine.fill.JRFillVariable;


public class GenericDataEvaluator extends JREvaluator {
	private static final Logger logger = Logger.getLogger(GenericDataEvaluator.class.getName());

	private final Map<String,JRValueParameter> m_parameters = new HashMap<String,JRValueParameter>();
	private final Map<String,JRFillField> m_fields = new HashMap<String, JRFillField>();
	private final Map<String,JRFillVariable> m_variables = new HashMap<String, JRFillVariable>();
	private final Map<Integer,IExpressionElement> m_default_expressions = new HashMap<Integer,IExpressionElement>();
	private final Map<Integer,IExpressionElement> m_old_expressions = new HashMap<Integer,IExpressionElement>();
	private final Map<Integer,IExpressionElement> m_estimated_expressions = new HashMap<Integer,IExpressionElement>();
	
	
	
	public void initializeWithDefaults(JRSourceCompileTask sourceTask) throws JRException {
		logger.finest("GenericDataEvaluator:generateDeclarations");
		
		if (sourceTask.getParametersMap()!=null) {
			m_parameters.clear();
			for (String key : sourceTask.getParametersMap().keySet()) {
				m_parameters.put(key, null);
			}
		}
		if (sourceTask.getFieldsMap()!=null) {
			m_fields.clear();
			for (String key : sourceTask.getFieldsMap().keySet()) {
				m_fields.put(key, null);
			}
		}
		if (sourceTask.getVariables()!=null) {
			m_variables.clear();
			for (JRVariable var : sourceTask.getVariables()) {
				m_variables.put(var.getName(), null);
			}
		}
	}
	
	public void parseExpressions(JRSourceCompileTask sourceTask) {
		m_default_expressions.clear();
		m_old_expressions.clear();
		m_estimated_expressions.clear();
		List<JRExpression> expressions = sourceTask.getExpressions();
		for (JRExpression expression : expressions) {
			Integer id = sourceTask.getExpressionId(expression);
			IExpressionElement defaultElement = generateExpressionElement(expression, JRExpression.EVALUATION_DEFAULT, sourceTask);
			m_default_expressions.put(id, defaultElement);
			if (!sourceTask.isOnlyDefaultEvaluation())
			{
				IExpressionElement oldEement = generateExpressionElement(expression, JRExpression.EVALUATION_OLD, sourceTask);
				m_old_expressions.put(id, oldEement);
				IExpressionElement estimatedEement = generateExpressionElement(expression, JRExpression.EVALUATION_ESTIMATED, sourceTask);
				m_estimated_expressions.put(id, estimatedEement);
			}
		}
	}
	
	@Override
	protected void customizedInit(Map<String, IJRFillParameter> parametersMap,
			Map<String, JRFillField> fieldsMap,
			Map<String, JRFillVariable> variablesMap) throws JRException {
		if (parametersMap!=null) {
			m_parameters.putAll(parametersMap);
		}
		if (fieldsMap!=null) {
			m_fields.putAll(fieldsMap);
		}
		if (variablesMap!=null) {
			m_variables.putAll(variablesMap);
		}
	}

	@Override
	protected Object evaluate(int id) throws Throwable {
		return m_default_expressions.containsKey(id) ?
				m_default_expressions.get(id).getValue() : null;
	}

	@Override
	protected Object evaluateOld(int id) throws Throwable {
		return m_old_expressions.containsKey(id) ?
				m_old_expressions.get(id).getOldValue() : null;
	}

	@Override
	protected Object evaluateEstimated(int id) throws Throwable {
		return m_estimated_expressions.containsKey(id) ?
				m_estimated_expressions.get(id).getValue() : null;
	}

	
	
	/**
	 *
	 */
	private IExpressionElement generateExpressionElement(
		JRExpression expression,
		byte evaluationType,
		JRSourceCompileTask sourceTask
		)
	{
		AppendableExpressionElement ee = new AppendableExpressionElement();
		
		JRExpressionChunk[] chunks = expression.getChunks();
		JRExpressionChunk chunk = null;
		String chunkText = null;
		if (chunks != null && chunks.length > 0)
		{
			for(int i = 0; i < chunks.length; i++)
			{
				chunk = chunks[i];

				chunkText = chunk.getText();
				if (chunkText == null)
				{
					chunkText = "";
				}
				
				switch (chunk.getType())
				{
					case JRExpressionChunk.TYPE_TEXT :
					{
						ee.add(TextTypeElementFactory.newTextElement(chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_PARAMETER :
					{
						// jrParameter = sourceTask.getParametersMap().get(chunkText);
						// we do not need the class type for now: jrParameter.getValueClassName()
						ee.add(new ParameterElement(chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_FIELD :
					{
						// jrField = sourceTask.getFieldsMap().get(chunkText);
						// we do not need the class type for now: jrField.getValueClassName()
						ee.add(new FieldElement(chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_VARIABLE :
					{
						// jrVariable = sourceTask.getVariablesMap().get(chunkText);
						// we do not need the class type for now: jrVariable.getValueClassName()
						ee.add(new VariableElement(chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_RESOURCE :
					{
						ee.add(TextTypeElementFactory.newTextElement("str(\"" + chunkText + "\")"));
						break;
					}
				}
			}
		}
		
		if (ee.isEmpty())
		{
			ee.add(TextTypeElementFactory.newTextElement("null"));
		}

		return ee;
	}

	// TODO (13.04.2013, Donat, Digireport): Build and evaluate expressions as described in document about this topic 
	private class AppendableExpressionElement implements IExpressionElement {
		private final List<IExpressionElement> elements = new ArrayList<IExpressionElement>();
		
		public void add(IExpressionElement element) {
			elements.add(element);
		}
		
		public boolean isEmpty() {
			return elements.isEmpty();
		}
		
		private Object getValue(boolean old) throws ExpressionEvaluationException {
			Result result = new Result();
			if (!isEmpty()) {
				IExpressionElement operand = elements.get(0);
				if (elements.size()>1) {
					for (int i=1;i<elements.size();i++) {
						IExpressionElement next = elements.get(i);
						if (next instanceof IExpressionOperator) {
							IExpressionOperator operator = (IExpressionOperator) next;
							operand = operator.evaluate(operand);		
						} else {
							result.append(old  ? operand.getOldValue() : operand.getValue());
							operand = next;
						}
					}
				}
				result.append(old  ? operand.getOldValue() : operand.getValue());
			}
			return result.getData();
		}

		@Override
		public Object getValue() throws ExpressionEvaluationException {
			return getValue(false);
		}

		@Override
		public Object getOldValue() throws ExpressionEvaluationException {
			return getValue(true);
		}
		
		
	}
	
	/**
	 * Concatenates the operands to a result. If there is a sequence in an expression
	 * with two or more subsequent operands they are converted to a string and concatenated.
	 * This implementation is required unless the algorithm for expressions for types
	 * boolean, character and number is complete. 
	 * @author donatmuller
	 */
	private static class Result {
		StringBuffer sb = new StringBuffer();
		boolean useStringBuffer = false;
		Object result = null;
		boolean isComputed = false;
		
		void append(Object o) {
			if (result==null && !useStringBuffer) {
				result = o;
			} else {
				useStringBuffer = true;
				sb.append(result);
				result = o;
			}
		}
		Object getData() {
			if (useStringBuffer) {
				if (isComputed) {
					return sb.toString();
				} else {
					sb.append(result);
					isComputed = true;
					result = null;
					return sb.toString();
				}
			} else {
				return result;
			}
		}
	}
	
	private class ParameterElement extends AbstractExpressionElement implements IExpressionElement {
		private final String key;
		
		private ParameterElement(String key) {
			this.key = key;
		}

		@Override
		public Object getValue() {
			return m_parameters.get(key).getValue();
		}
	}
	
	private class FieldElement implements IExpressionElement {
		private final String key;
		
		private FieldElement(String key) {
			this.key = key;
		}

		@Override
		public Object getValue() {
			return m_fields.get(key).getValue();
		}

		@Override
		public Object getOldValue() {
			return m_fields.get(key).getOldValue();
		}
	}
	
	private class VariableElement implements IExpressionElement {
		private final String key;
		
		private VariableElement(String key) {
			this.key = key;
		}

		@Override
		public Object getValue() {
			return m_variables.get(key).getValue();
		}

		@Override
		public Object getOldValue() {
			return m_variables.get(key).getOldValue();
		}
	}
	

	
	
}
