package org.oss.pdfreporter.net.factory;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.net.NullUrl;

/**
 * We have to provide a default INetFactory as jasper searches for jasperreports.properties
 * prior to jasperreports_extension.properties. So in the case that there is no jasperreports.properties
 * provided a fallback to URL loading for jasperreports.properties would fail with a NullpointerException.
 * The INetFactory is initialized with the ExtensionsRegistry iregistry.
 * @author donatmuller, 2013, last change 12:47:18 PM
 */
public class DefaultNetFactory implements INetFactory {

	@Override
	public IURL newURL(String url) throws MalformedURLException {
		return new NullUrl();
	}

}
