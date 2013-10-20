/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
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
