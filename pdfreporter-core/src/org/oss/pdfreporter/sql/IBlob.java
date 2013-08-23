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
