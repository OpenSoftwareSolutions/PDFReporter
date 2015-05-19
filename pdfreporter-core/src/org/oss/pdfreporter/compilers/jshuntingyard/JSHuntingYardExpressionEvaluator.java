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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IDataHolder;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.SingleChunkExpressionFactory;
import org.oss.pdfreporter.compilers.SingleChunkTextTypeFactory;
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


public class JSHuntingYardExpressionEvaluator extends JREvaluator implements IDataHolder {
	private final Map<String,JRValueParameter> m_parameters = new HashMap<String,JRValueParameter>();
	private final Map<String,JRFillField> m_fields = new HashMap<String, JRFillField>();
	private final Map<String,JRFillVariable> m_variables = new HashMap<String, JRFillVariable>();
	private final Map<Integer,IExpressionElement> m_expressions = new HashMap<Integer,IExpressionElement>();



	public void initializeWithDefaults(JRSourceCompileTask sourceTask) throws JRException {
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

	public void parseExpressions(JRSourceCompileTask sourceTask) throws JRException {
		m_expressions.clear();
		List<JRExpression> expressions = sourceTask.getExpressions();
		for (JRExpression expression : expressions) {
			Integer id = sourceTask.getExpressionId(expression);
			m_expressions.put(id, buildExpression(expression,id));
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
		return m_expressions.containsKey(id) ?
				m_expressions.get(id).getValue() : null;
	}

	@Override
	protected Object evaluateOld(int id) throws Throwable {
		return m_expressions.containsKey(id) ?
				m_expressions.get(id).getOldValue() : null;
	}

	@Override
	protected Object evaluateEstimated(int id) throws Throwable {
		return m_expressions.containsKey(id) ?
				m_expressions.get(id).getValue() : null;
	}

	@Override
	public JRValueParameter getParameter(String name) {
		return m_parameters.get(name);
	}

	@Override
	public JRFillField getField(String name) {
		return m_fields.get(name);
	}

	@Override
	public JRFillVariable getVariable(String name) {
		return m_variables.get(name);
	}

	private IExpressionElement buildExpression(JRExpression expression, int expressionId) throws JRException {
		JRExpressionChunk[] designExpressionChunks = expression.getChunks();
		try {
			boolean singleChunk = (designExpressionChunks==null || designExpressionChunks.length == 1);
			if (designExpressionChunks != null && designExpressionChunks.length > 0) {
				if (singleChunk) {
					IExpressionElement result = SingleChunkExpressionFactory.buildExpression(this, designExpressionChunks[0]);
					return result==null ? JSHuntingYardExpressionFactory.buildExpression(this, designExpressionChunks, expressionId) : result;
				} else {
					return JSHuntingYardExpressionFactory.buildExpression(this, designExpressionChunks, expressionId);
				}
			}
			return SingleChunkTextTypeFactory.buildExpression("null");
		} catch (ExpressionParseException e) {
			throw new JRException(e);
		}
	}

}
