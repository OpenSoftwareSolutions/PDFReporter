package org.oss.pdfreporter.net;

import java.io.IOException;
import java.io.InputStream;

public class NullUrl implements IURL {

	@Override
	public InputStream openStream() throws IOException {
		return null;
	}

	@Override
	public String getPath() {
		return null;
	}

}
