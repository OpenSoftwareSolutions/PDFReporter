package org.oss.pdfreporter.net.factory;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;



public class NetFactory implements INetFactory {

	@Override
	public IURL newURL(String url) throws MalformedURLException {
		return null;
	}
	
	public static void registerFactory() {
		
	}

}
