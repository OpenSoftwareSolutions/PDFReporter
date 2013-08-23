package org.oss.pdfreporter.jasperreports.compilers.jeval;

import org.oss.pdfreporter.jasperreports.compilers.IExpressionElement;
import org.oss.pdfreporter.jasperreports.compilers.jeval.IExpressionChunk.ExpresionType;

public interface IVariable extends IExpressionElement {
	Object getVariableHolder();
	ExpresionType getType();
	String getName();
}
