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

import java.util.ResourceBundle;

import org.oss.pdfreporter.compilers.ExpressionEvaluationException;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.engine.JRExpressionChunk;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRValueParameter;


public class SingleChunkExpressionFactory {

	public static IExpressionElement buildExpression(IDataHolder dataholder, JRExpressionChunk chunk) throws ExpressionParseException {
		String chunkText = chunk.getText();
		if (chunkText == null)
		{
			chunkText = "";
		}

		switch (chunk.getType())
		{
			case JRExpressionChunk.TYPE_TEXT :
			{
				return SingleChunkTextTypeFactory.buildExpression(chunkText);
			}
			case JRExpressionChunk.TYPE_PARAMETER :
			{
				// jrParameter = sourceTask.getParametersMap().get(chunkText);
				// we do not need the class type for now: jrParameter.getValueClassName()
				return new ParameterElement(dataholder, chunkText);
			}
			case JRExpressionChunk.TYPE_FIELD :
			{
				// jrField = sourceTask.getFieldsMap().get(chunkText);
				// we do not need the class type for now: jrField.getValueClassName()
				return new FieldElement(dataholder, chunkText);
			}
			case JRExpressionChunk.TYPE_VARIABLE :
			{
				// jrVariable = sourceTask.getVariablesMap().get(chunkText);
				// we do not need the class type for now: jrVariable.getValueClassName()
				return new VariableElement(dataholder, chunkText);
			}
			case JRExpressionChunk.TYPE_RESOURCE :
			{
				return new RessourceElement(dataholder, chunkText);
			}
		}
		throw new ExpressionParseException("Unreachable type: " + chunk.getType() + ", text: " + chunkText);
	}

	private static abstract class AbstractElement implements IExpressionElement {
		protected final IDataHolder dataholder;
		protected final String key;

		private AbstractElement(IDataHolder dataholder, String key) {
			this.dataholder = dataholder;
			this.key = key;
		}
	}

	private static class ParameterElement extends AbstractElement {

		private ParameterElement(IDataHolder dataholder, String key) {
			super(dataholder,key);
		}

		@Override
		public Object getValue() throws ExpressionEvaluationException {
			return dataholder.getParameter(key).getValue();
		}

		@Override
		public Object getOldValue() throws ExpressionEvaluationException {
			return getValue();
		}
	}

	private static class RessourceElement extends AbstractElement {

		private RessourceElement(IDataHolder dataholder, String key) {
			super(dataholder,key);
		}

		@Override
		public Object getValue() throws ExpressionEvaluationException {
			JRValueParameter parameter = dataholder.getParameter(JRParameter.REPORT_RESOURCE_BUNDLE);
			ResourceBundle resourceBundle = (ResourceBundle) parameter.getValue();
			return resourceBundle.getString(key);
		}

		@Override
		public Object getOldValue() throws ExpressionEvaluationException {
			return getValue();
		}
	}

	private static class FieldElement extends AbstractElement {

		private FieldElement(IDataHolder dataholder, String key) {
			super(dataholder,key);
		}

		@Override
		public Object getValue() throws ExpressionEvaluationException {
			return dataholder.getField(key).getValue();
		}

		@Override
		public Object getOldValue() throws ExpressionEvaluationException {
			return dataholder.getField(key).getOldValue();
		}
	}

	private static class VariableElement extends AbstractElement {

		private VariableElement(IDataHolder dataholder, String key) {
			super(dataholder,key);
		}

		@Override
		public Object getValue() throws ExpressionEvaluationException {
			return dataholder.getVariable(key).getValue();
		}

		@Override
		public Object getOldValue() throws ExpressionEvaluationException {
			return dataholder.getVariable(key).getOldValue();
		}
	}


}
