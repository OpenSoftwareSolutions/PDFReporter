package org.oss.pdfreporter.jasperreports.compilers.jeval;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpressionChunk;
import org.oss.pdfreporter.jasperreports.compilers.IExpressionElement;


public class JevalExpressionFactory {
	private static final Logger logger = Logger.getLogger(JevalExpressionFactory.class.getName());
	private StringBuilder rawExpression;
	private final IDataHolder dataholder;
	
	private JevalExpressionFactory(IDataHolder dataholder) {
		this.dataholder = dataholder;
	}
	
	public static IExpressionElement buildExpression(IDataHolder dataholder, JRExpressionChunk[] chunks, int expressionId) throws JRException {
		return new JevalExpressionFactory(dataholder).build(chunks, expressionId);
	}
	
	private IExpressionElement build(JRExpressionChunk[] chunks, int expressionId) throws JRException {
		ResultCast result = new ResultCast();
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
						if (ResultCast.isCast(chunkText)) {
							result = ResultCast.parseCast(chunkText);
							expressionBuilder.addText(ResultCast.getNext(chunkText));
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
						expressionBuilder.addResource(chunkText);
						break;
					}
				}
			}			
			
			result.setExpression(JEvalExpression.newInstance(expressionBuilder.getChunkList()));
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
		rawExpression.append("str{");
		rawExpression.append(chunkText);
		rawExpression.append("}");
	}
	

}
