package org.oss.pdfreporter.commons.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface IBeansUtils {

	public void setProperty(Object top, String propertyName, Object value);

	public Object getProperty(Object top, String propertyName);

	public boolean isWriteable(Object bean, String name);

	public boolean hasProperty(Object bean, String name);

	@SuppressWarnings("rawtypes")
	public void populate(Object bean, Map properties) throws IllegalAccessException,
			InvocationTargetException;
}
