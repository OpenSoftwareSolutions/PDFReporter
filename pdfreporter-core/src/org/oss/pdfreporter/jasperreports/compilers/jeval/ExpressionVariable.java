package org.oss.pdfreporter.jasperreports.compilers.jeval;

import org.oss.pdfreporter.engine.fill.JRFillVariable;
import org.oss.pdfreporter.jasperreports.compilers.jeval.IExpressionChunk.ExpresionType;


public class ExpressionVariable implements IVariable {

	private final IDataHolder data;
	private final String name;
		
	public ExpressionVariable(IDataHolder data, String name) {
		this.data = data;
		this.name = name;
	}

	@Override
	public Object getValue() {
		return getVariable().getValue();
	}

	@Override
	public Object getOldValue() {
		return getVariable().getOldValue();
	}
	
	@Override
	public Object getVariableHolder() {
		return getVariable();
	}

	@Override
	public ExpresionType getType() {
		return ExpresionType.TYPE_VARIABLE;
	}

	@Override
	public String getName() {
		return name;
	}

	private JRFillVariable getVariable() {
		return data.getVariable(getName());
	}

	@Override
	public String toString() {
		return "ExpressionVariable [name=" + name + "]";
	}
	
}
