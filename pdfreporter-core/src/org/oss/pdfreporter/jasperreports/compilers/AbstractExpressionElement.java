package org.oss.pdfreporter.jasperreports.compilers;

public abstract class AbstractExpressionElement implements IExpressionElement {


	@Override
	public Object getOldValue() throws ExpressionEvaluationException {
		return getValue();
	}

}
