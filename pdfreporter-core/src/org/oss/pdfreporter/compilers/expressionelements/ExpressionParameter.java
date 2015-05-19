/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.compilers.expressionelements;

import org.oss.pdfreporter.compilers.IDataHolder;
import org.oss.pdfreporter.compilers.IVariable;
import org.oss.pdfreporter.compilers.IExpressionChunk.ExpresionType;
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
