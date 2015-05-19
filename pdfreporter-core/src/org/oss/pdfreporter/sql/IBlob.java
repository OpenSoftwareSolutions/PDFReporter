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

import java.io.InputStream;

/**
 * Binary large object.
 * @author donatmuller, 2013, last change 10:18:18 AM
 */
public interface IBlob {
	/**
	 * Returns the object as binary InputStream.
	 * @return
	 */
	InputStream getInputStream() throws SQLException;
	
	/**
	 * Returns the object as byte array.
	 * @return
	 */
	byte[] getBytes() throws SQLException;
}
