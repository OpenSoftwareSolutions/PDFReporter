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
package org.oss.pdfreporter.sql;

public class SQLException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public SQLException() {
		super();
	}

	public SQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public SQLException(String message) {
		super(message);
	}

	public SQLException(Throwable cause) {
		super(cause);
	}


}
