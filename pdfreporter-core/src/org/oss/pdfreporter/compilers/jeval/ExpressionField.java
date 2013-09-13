package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.compilers.jeval.IExpressionChunk.ExpresionType;
import org.oss.pdfreporter.engine.fill.JRFillField;


public class ExpressionField implements IVariable {

	private final IDataHolder data;
	private final String name;
	
	
	public ExpressionField(IDataHolder data, String name) {
		this.data = data;
		this.name = name;
	}

	@Override
	public Object getValue() {
		return getField().getValue();
	}

	@Override
	public Object getOldValue() {
		return getField().getOldValue();
	}
	
	@Override
	public Object getVariableHolder() {
		return getField();
	}

	@Override
	public ExpresionType getType() {
		return ExpresionType.TYPE_FIELD;
	}

	@Override
	public String getName() {
		return name;
	}
	
	private JRFillField getField() {
		return data.getField(getName());
	}

	@Override
	public String toString() {
		return "ExpressionField [name=" + name + "]";
	}

}
