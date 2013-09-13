package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.compilers.AbstractExpressionElement;
import org.oss.pdfreporter.compilers.jeval.IExpressionChunk.ExpresionType;
import org.oss.pdfreporter.engine.JRValueParameter;


public class ExpressionParameter extends AbstractExpressionElement implements IVariable {

	private final IDataHolder data;
	private final String name;
		
	public ExpressionParameter(IDataHolder data, String name) {
		this.data = data;
		this.name = name;
	}

	@Override
	public Object getValue() {
		return getParameter().getValue();
	}


	@Override
	public Object getVariableHolder() {
		return getParameter();
	}

	@Override
	public ExpresionType getType() {
		return ExpresionType.TYPE_PARAMETER;
	}

	@Override
	public String getName() {
		return name;
	}

	private JRValueParameter getParameter() {
		return data.getParameter(getName());
	}

	@Override
	public String toString() {
		return "ExpressionParameter [name=" + name + "]";
	}

}
