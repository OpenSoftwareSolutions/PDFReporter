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
package org.oss.pdfreporter.compilers;

public class ExpressionParseException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ExpressionParseException() {
		super();
	}

	public ExpressionParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpressionParseException(String message) {
		super(message);
	}

	public ExpressionParseException(Throwable cause) {
		super(cause);
	}
}
