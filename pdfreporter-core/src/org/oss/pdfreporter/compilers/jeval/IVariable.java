package org.oss.pdfreporter.compilers.jeval;

import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.jeval.IExpressionChunk.ExpresionType;

public interface IVariable extends IExpressionElement {
	Object getVariableHolder();
	ExpresionType getType();
	String getName();
}
