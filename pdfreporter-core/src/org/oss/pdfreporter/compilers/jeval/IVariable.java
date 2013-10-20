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

import org.oss.pdfreporter.compilers.IExpressionElement;
import org.oss.pdfreporter.compilers.jeval.IExpressionChunk.ExpresionType;

public interface IVariable extends IExpressionElement {
	Object getVariableHolder();
	ExpresionType getType();
	String getName();
}
