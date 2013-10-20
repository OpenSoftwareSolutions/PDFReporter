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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JRPropertiesUtil.PropertySuffix;
import org.oss.pdfreporter.engine.util.ClassUtils;
import org.oss.pdfreporter.engine.util.JRLoader;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.repo.FileSystemResource;


/**
 * The default {@link ExtensionsRegistry extension registry} implementation.
 *
 * <p>
 * The implementation builds an extension registry by scanning the context
 * classloader for resources named <code>jasperreports_extension.properties</code>.
 * 
 * <p>
 * Each such resource is loaded as a properties file, and properties that start
 * with <code>net.sf.jasperreports.extension.registry.factory.</code> are identified.
 * 
 * <p>
 * Each such property should have as value the name of a 
 * {@link ExtensionsRegistryFactory} implementation.  The registry factory class is
 * instantiated, and
 * {@link ExtensionsRegistryFactory#createRegistry(String, JRPropertiesMap)}
 * is called on it, using the propery suffix as registry ID and passing the
 * properties map.  The registry factory can collect properties that apply to the
 * specific registry by using a property prefix obtain by appending the registry ID
 * to "<code>net.sf.jasperreports.extension.</code>".
 * 
 * <p>
 * If instantiating an extension registry results in an exception, the registry
 * is skipped and an error message is logged.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: DefaultExtensionsRegistry.java 5050 2012-03-12 10:11:26Z teodord $
 */
public class DefaultExtensionsRegistry implements ExtensionsRegistry
{
	private final static Logger logger = Logger.getLogger(DefaultExtensionsRegistry.class.getName());
	
	/**
	 * The name of property file resources that are used to load JasperReports 
	 * extensions.
	 */
	public final static String EXTENSION_RESOURCE_NAME = 
		"jasperreports_extension.properties";
	
	/**
	 * The property prefix of extension registry factories.
	 */
	public final static String PROPERTY_REGISTRY_FACTORY_PREFIX = 
			JRPropertiesUtil.PROPERTY_PREFIX + "extension.registry.factory.";
	
	/**
	 * A prefix that can be used to provide registry-specific properties,
	 * by appending the registry ID and a fixed property suffix to it. 
	 */
	public static final String PROPERTY_REGISTRY_PREFIX = 
			JRPropertiesUtil.PROPERTY_PREFIX + "extension.";

	@SuppressWarnings("rawtypes")
	private final Map registrySetCache = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private final Map registryCache = new HashMap();

	public <T> List<T> getExtensions(Class<T> extensionType)
	{
		List<ExtensionsRegistry> registries = getRegistries();
		List<T> extensions = new ArrayList<T>(registries.size());
		for (Iterator<ExtensionsRegistry> it = registries.iterator(); it.hasNext();)
		{
			ExtensionsRegistry registry = it.next();
			List<T> registryExtensions = registry.getExtensions(extensionType);
			if (registryExtensions != null && !registryExtensions.isEmpty())
			{
				extensions.addAll(registryExtensions);
			}
		}
		return extensions;
	}
	
	@SuppressWarnings("unchecked")
	protected List<ExtensionsRegistry> getRegistries()
	{
		List<ExtensionsRegistry> registries;
		Object cacheKey = ExtensionsEnvironment.getExtensionsCacheKey();
		synchronized (registrySetCache)
		{
			registries = (List<ExtensionsRegistry>) registrySetCache.get(cacheKey);
			if (registries == null)
			{
				registries = loadRegistries();
				registrySetCache.put(cacheKey, registries);
			}
		}
		return registries;
	}
	
	protected List<ExtensionsRegistry> loadRegistries()
	{
		List<ExtensionsRegistry> allRegistries = new ArrayList<ExtensionsRegistry>();
		List<FileSystemResource> extensionResources = loadExtensionPropertyResources();
		for (FileSystemResource extensionResource : extensionResources)
		{
			String folderPath = extensionResource.getFolderPath();
			Map<IURL, List<ExtensionsRegistry>> classLoaderRegistries = getFileFolderRegistries(folderPath);
			
			IURL url = extensionResource.getUrl();
			List<ExtensionsRegistry> registries;
			synchronized (classLoaderRegistries)
			{
				registries = classLoaderRegistries.get(url);
				if (registries == null)
				{
					registries = loadRegistries(url);
					
					classLoaderRegistries.put(url, registries);
				}
			}
			
			allRegistries.addAll(registries);
		}
		return allRegistries;
	}

	protected List<FileSystemResource> loadExtensionPropertyResources()
	{
		return JRLoader.getFileSystemResources(
				EXTENSION_RESOURCE_NAME);
	}

	@SuppressWarnings("unchecked")
	protected Map<IURL, List<ExtensionsRegistry>> getFileFolderRegistries(String folderPath)
	{
		synchronized (registryCache)
		{
			Map<IURL, List<ExtensionsRegistry>> registries = (Map<IURL, List<ExtensionsRegistry>>) registryCache.get(folderPath);
			if (registries == null)
			{
				registries = new HashMap<IURL, List<ExtensionsRegistry>>();
				registryCache.put(folderPath, registries);
			}
			return registries;
		}
	}
	
	protected List<ExtensionsRegistry> loadRegistries(IURL url)
	{
		JRPropertiesMap properties = JRPropertiesMap.loadProperties(url);
		
		List<ExtensionsRegistry> registries = new ArrayList<ExtensionsRegistry>();
		List<PropertySuffix> factoryProps = JRPropertiesUtil.getProperties(properties, 
				PROPERTY_REGISTRY_FACTORY_PREFIX);
		for (Iterator<PropertySuffix> it = factoryProps.iterator(); it.hasNext();)
		{
			PropertySuffix factoryProp = it.next();
			String registryId = factoryProp.getSuffix();
			String factoryClass = factoryProp.getValue();
			
			try
			{
				ExtensionsRegistry registry = instantiateRegistry(
						properties, registryId, factoryClass);
				registries.add(registry);
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Exception loading Registry " + registryId, e);
			}
		}
		return registries;
	}

	protected ExtensionsRegistry instantiateRegistry(
			JRPropertiesMap props, String registryId, String factoryClass)
	{
		logger.info("Loading ExtensionsRegistry: " + registryId);
		ExtensionsRegistryFactory factory = (ExtensionsRegistryFactory) 
				ClassUtils.instantiateClass(factoryClass, ExtensionsRegistryFactory.class);
		return factory.createRegistry(registryId, props);
	}

	// TODO (20.07.2013, Donat, Open Software Solutions): Hack to reset the extensions environment, forcing a reload of configured extensions 
	void reset() {
		registrySetCache.clear();
	}
}
