package org.oss.pdfreporter.compilers;

public abstract class AbstractExpressionElement implements IExpressionElement {


	@Override
	public Object getOldValue() throws ExpressionEvaluationException {
		return getValue();
	}

}
