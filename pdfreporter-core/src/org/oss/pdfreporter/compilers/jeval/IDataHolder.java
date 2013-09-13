package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.fill.JRFillField;
import org.oss.pdfreporter.engine.fill.JRFillVariable;

public interface IDataHolder {
	JRValueParameter getParameter(String name);
	JRFillField getField(String name);
	JRFillVariable getVariable(String name);	
}
