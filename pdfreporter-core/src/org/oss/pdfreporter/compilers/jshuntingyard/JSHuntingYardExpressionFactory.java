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

import org.oss.pdfreporter.compilers.ExpressionParseException;
import org.oss.pdfreporter.compilers.IDataHolder;
import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.expressionelements.ChunkBuilder;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionField;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionParameter;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionRessource;
import org.oss.pdfreporter.compilers.expressionelements.ExpressionVariable;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpressionChunk;


public class JSHuntingYardExpressionFactory {
	private static final Logger logger = Logger.getLogger(JSHuntingYardExpressionFactory.class.getName());
	private StringBuilder rawExpression;
	private final IDataHolder dataholder;

	private JSHuntingYardExpressionFactory(IDataHolder dataholder) {
		this.dataholder = dataholder;
	}

	public static IExpressionElement buildExpression(IDataHolder dataholder, JRExpressionChunk[] chunks, int expressionId) throws JRException {
		return new JSHuntingYardExpressionFactory(dataholder).build(chunks, expressionId);
	}

	private IExpressionElement build(JRExpressionChunk[] chunks, int expressionId) throws JRException {
		JSHuntingYardResultCast result = new JSHuntingYardResultCast();
		ChunkBuilder expressionBuilder = new ChunkBuilder();
		rawExpression  = new StringBuilder();
		try {
			for (JRExpressionChunk designChunk : chunks) {

				String chunkText = designChunk.getText();

				if (chunkText == null) {
					chunkText = "";
				}
				switch (designChunk.getType()) {
					case JRExpressionChunk.TYPE_TEXT: {
						rawExpression.append(chunkText);
						if (JSHuntingYardResultCast.isCast(chunkText)) {
							result = JSHuntingYardResultCast.parseCast(chunkText);
							expressionBuilder.addText(JSHuntingYardResultCast.getNext(chunkText));
						} else {
							expressionBuilder.addText(chunkText);
						}
						break;
					}
					case JRExpressionChunk.TYPE_PARAMETER: {
						appendRawParameter(chunkText);
						expressionBuilder.addVariable(new ExpressionParameter(dataholder, chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_FIELD: {
						appendRawField(chunkText);
						expressionBuilder.addVariable(new ExpressionField(dataholder, chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_VARIABLE: {
						appendRawVariable(chunkText);
						expressionBuilder.addVariable(new ExpressionVariable(dataholder, chunkText));
						break;
					}
					case JRExpressionChunk.TYPE_RESOURCE: {
						appendRawResource(chunkText);
						expressionBuilder.addVariable(new ExpressionRessource(dataholder, chunkText));
						break;
					}
				}
			}

			result.setExpression(JSHuntingYardExpression.newInstance(expressionBuilder.getChunkList()));
			logger.finest("Compiled expression " + expressionId + " - " + rawExpression.toString());
			return result;
		} catch (ExpressionParseException e) {
			logger.log(Level.SEVERE, "Error parsing '" + rawExpression.toString() + "' in JRXML DesignReport.", e);
			throw new JRException("Error parsing '" + rawExpression.toString() + "' in JRXML DesignReport.", e);
		}

	}

	private void appendRawParameter(String chunkText) {
		rawExpression.append("$P{");
		rawExpression.append(chunkText);
		rawExpression.append("}");
	}
	private void appendRawField(String chunkText) {
		rawExpression.append("$F{");
		rawExpression.append(chunkText);
		rawExpression.append("}");
	}
	private void appendRawVariable(String chunkText) {
		rawExpression.append("$V{");
		rawExpression.append(chunkText);
		rawExpression.append("}");
	}
	private void appendRawResource(String chunkText) {
		rawExpression.append("$R{");
		rawExpression.append(chunkText);
		rawExpression.append("}");
	}


}
