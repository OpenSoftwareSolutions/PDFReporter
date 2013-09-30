package org.oss.pdfreporter.net.factory;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.net.factory.INetFactory;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.net.URL;


public class NetFactory implements INetFactory {
	
	public static void registerFactory() {
		IRegistry.register(new NetFactory());
	}

	@Override
	public IURL newURL(String url) throws MalformedURLException {
		return new URL(url);
	}

}
