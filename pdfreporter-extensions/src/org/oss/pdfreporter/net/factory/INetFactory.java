package org.oss.pdfreporter.net.factory;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;

public interface INetFactory {
	IURL newURL(String url) throws MalformedURLException;
}
