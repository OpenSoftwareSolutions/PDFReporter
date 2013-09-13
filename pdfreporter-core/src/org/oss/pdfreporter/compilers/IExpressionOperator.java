package org.oss.pdfreporter.compilers;

public interface IExpressionOperator extends IExpressionElement {
	IExpressionElement evaluate(IExpressionElement operand) throws ExpressionEvaluationException;
}
