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
package org.oss.pdfreporter.sql;

import java.io.Closeable;

/**
 * A coonection to a database.
 * @author donatmuller, 2013, last change 9:39:38 AM
 */
public interface IConnection extends Closeable {
	
	/**
	 * Returns a prepared statement.
	 * @param query
	 * @return
	 */
	IPreparedStatement prepareStatement(String query) throws SQLException;	
}
