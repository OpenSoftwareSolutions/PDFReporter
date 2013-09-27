package org.oss.pdfreporter.beans.factory;

import org.oss.pdfreporter.beans.BeanUtils;
import org.oss.pdfreporter.commons.beans.IBeansUtils;
import org.oss.pdfreporter.commons.beans.factory.IBeansFactory;
import org.oss.pdfreporter.registry.IRegistry;


public class BeansFactory implements IBeansFactory {

	public static void registerFactory() {
		IRegistry.register(new BeansFactory());
	}

	@Override
	public IBeansUtils newBeansUtils() {
		return new BeanUtils();
	}

}
