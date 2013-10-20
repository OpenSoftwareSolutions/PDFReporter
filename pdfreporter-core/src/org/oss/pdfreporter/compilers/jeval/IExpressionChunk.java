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

/**
 * @author donatmuller, 2013, last change 10:09:52 AM
 * 
 */
public interface IExpressionChunk {
	/**
	 * Type of Chunk
	 */
	public enum ExpresionType {
		TYPE_TEXT,
		TYPE_PARAMETER,
		TYPE_FIELD,
		TYPE_VARIABLE,
		TYPE_RESOURCE
	}

	/**
	 * 
	 */
	public ExpresionType getType();

	/**
	 * 
	 */
	public String getText();

}
