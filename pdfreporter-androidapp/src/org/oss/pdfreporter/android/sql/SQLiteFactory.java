/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.android.sql;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.factory.AbstractSqlFactory;

public class SQLiteFactory extends AbstractSqlFactory {
	
	public static void registerFactory() {
		ApiRegistry.register(new SQLiteFactory());
	}
	
	@Override
	public IConnection newConnection(String url, String user,
			String password) throws SQLException {
		return new SQLiteConnection(url);
	}

}
