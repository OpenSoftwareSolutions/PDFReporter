package org.oss.pdfreporter.net;

import java.io.IOException;
import java.io.InputStream;

public interface IURL {
	/**
	 * Returns an InputSteram for the resource.
	 * @return
	 * @throws IOException
	 */
	InputStream openStream() throws IOException;

	/**
	 * Returns the path to the resource.
	 * @return
	 */
	String getPath();
}
