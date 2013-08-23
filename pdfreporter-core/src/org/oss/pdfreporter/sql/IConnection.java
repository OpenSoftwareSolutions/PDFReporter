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
