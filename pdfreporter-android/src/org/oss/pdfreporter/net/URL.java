package org.oss.pdfreporter.net;

import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;


public class URL implements IURL {
	
	private final java.net.URL url;

	public URL(String url) throws MalformedURLException {
		try {
			this.url = new java.net.URL(url);
		} catch (java.net.MalformedURLException e) {
			throw new MalformedURLException(e);
		}
	}
	@Override
	public InputStream openStream() throws IOException {
		return url.openStream();
	}
	
	@Override
	public String getPath() {
		return url.getPath();
	}

}
