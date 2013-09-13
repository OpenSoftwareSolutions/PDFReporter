package org.oss.pdfreporter.compilers;

/**
 * @author donatmuller
 *
 */
public interface IExpressionElement {
	Object getValue() throws ExpressionEvaluationException;
	Object getOldValue() throws ExpressionEvaluationException;
}
