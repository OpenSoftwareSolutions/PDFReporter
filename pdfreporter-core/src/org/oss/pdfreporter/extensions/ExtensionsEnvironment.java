/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.extensions;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.util.ClassUtils;

/**
 * A class that provides means of setting and accessing
 * {@link ExtensionsRegistry} instances.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ExtensionsEnvironment.java 5521 2012-07-30 13:25:30Z teodord $
 * @see #getExtensionsRegistry()
 */
public final class ExtensionsEnvironment
{

	private ExtensionsEnvironment()
	{
	}
	
	protected final static Object NULL_CACHE_KEY = new Object();
	
	/**
	 * A property that provides the default {@link ExtensionsRegistry} 
	 * implementation class. 
	 * 
	 * <p>
	 * This property is only read at initialization time, therefore changing
	 * the property value at a later time will have no effect. 
	 */
	public static final String PROPERTY_EXTENSIONS_REGISTRY_CLASS = 
		JRPropertiesUtil.PROPERTY_PREFIX + "extensions.registry.class";
	
	private static ExtensionsRegistry systemRegistry;
	
	static
	{
		systemRegistry = createDefaultRegistry();
	}
	
	private static ExtensionsRegistry createDefaultRegistry()
	{
		String registryClass = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getProperty(PROPERTY_EXTENSIONS_REGISTRY_CLASS);
		
		ExtensionsRegistry registry = (ExtensionsRegistry) ClassUtils.
			instantiateClass(registryClass, ExtensionsRegistry.class);
		return registry;
	}
	
	/**
	 * Returns the system default extensions registry object.
	 * 
	 * <p>
	 * This is either the one instantiated based on {@link #PROPERTY_EXTENSIONS_REGISTRY_CLASS},
	 * or the one set by {@link #setSystemExtensionsRegistry(ExtensionsRegistry)}.
	 * 
	 * @return the system default extensions registry object
	 */
	public static synchronized ExtensionsRegistry getSystemExtensionsRegistry()
	{
		return systemRegistry;
	}

	/**
	 * Sets the system default extensions registry.
	 * 
	 * @param extensionsRegistry the extensions registry
	 */
	public static synchronized void setSystemExtensionsRegistry(ExtensionsRegistry extensionsRegistry)
	{
		if (extensionsRegistry == null)
		{
			throw new JRRuntimeException("Cannot set a null extensions registry.");
		}
		
		systemRegistry = extensionsRegistry;
	}

	
	/**
	 * Returns the extensions registry to be used in the current context.
	 * 
	 * <p>
	 * The method returns the thread extensions registry (as returned by 
	 * {@link #getThreadExtensionsRegistry()}) if it exists, and the system
	 * registry (as returned by {@link #getSystemExtensionsRegistry()}) otherwise.
	 * 
	 * @return the context extensions registry
	 */
	public static ExtensionsRegistry getExtensionsRegistry()//FIXMECONTEXT check all places where such methods are still used
	{
		return getSystemExtensionsRegistry();
	}
	
	/**
	 * Returns an object that can be used as cache key for extension-related
	 * caches.
	 * 
	 * @return an extension-related cache key
	 */
	public static Object getExtensionsCacheKey()
	{
		return NULL_CACHE_KEY;
	}
	
	// TODO (20.07.2013, Donat, Digireport): Hack to reset the extensions environment, forcing a reload of configured extensions 
	public static void reset() {
		if (systemRegistry instanceof DefaultExtensionsRegistry) {
			((DefaultExtensionsRegistry)systemRegistry).reset();
		}
	}
}
