package org.oss.pdfreporter.jasperreports.compilers;

public interface IExpressionOperator extends IExpressionElement {
	IExpressionElement evaluate(IExpressionElement operand) throws ExpressionEvaluationException;
}
