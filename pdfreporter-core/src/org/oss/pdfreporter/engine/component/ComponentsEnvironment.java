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
package org.oss.pdfreporter.engine.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.extensions.ExtensionsEnvironment;
import org.oss.pdfreporter.uses.org.apache.commons.collections.ReferenceMap;

/**
 * A class that provides access to {@link ComponentsBundle component bundles}.
 * 
 * <p>
 * Component bundles are registered as JasperReports extensions of type
 * {@link ComponentsBundle} via the central extension framework (see
 * {@link ExtensionsEnvironment}).
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentsEnvironment.java 5661 2012-09-13 19:00:14Z teodord $
 */
public final class ComponentsEnvironment
{
	
	private final ReferenceMap cache = new ReferenceMap(
			ReferenceMap.WEAK, ReferenceMap.HARD);
	
	private JasperReportsContext jasperReportsContext;


	/**
	 *
	 */
	private ComponentsEnvironment(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	
	/**
	 *
	 */
	private static ComponentsEnvironment getDefaultInstance()
	{
		return new ComponentsEnvironment(DefaultJasperReportsContext.getInstance());
	}
	
	
	/**
	 *
	 */
	public static ComponentsEnvironment getInstance(JasperReportsContext jasperReportsContext)
	{
		return new ComponentsEnvironment(jasperReportsContext);
	}
	
	
	/**
	 * Returns the set of all component bundles present in the registry. 
	 * 
	 * @return the set of component bundles
	 */
	public Collection<ComponentsBundle> getBundles()
	{
		Map<String, ComponentsBundle> components = getCachedBundles();
		return components.values();
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, ComponentsBundle> getCachedBundles()
	{
		Object cacheKey = ExtensionsEnvironment.getExtensionsCacheKey();
		synchronized (cache)
		{
			Map<String, ComponentsBundle> components = (Map<String, ComponentsBundle>) cache.get(cacheKey);
			if (components == null)
			{
				components = findBundles();
				cache.put(cacheKey, components);
			}
			return components;
		}
	}

	protected Map<String, ComponentsBundle> findBundles()
	{
		Map<String, ComponentsBundle> components = new HashMap<String, ComponentsBundle>();
		List<ComponentsBundle> bundles = jasperReportsContext.getExtensions(ComponentsBundle.class);
		for (Iterator<ComponentsBundle> it = bundles.iterator(); it.hasNext();)
		{
			ComponentsBundle bundle = it.next();
			String namespace = bundle.getXmlParser().getNamespace();
			if (components.containsKey(namespace))
			{
			}
			else
			{
				components.put(namespace, bundle);
			}
		}
		return components;
	}
	
	/**
	 * Returns a component bundle that corresponds to a namespace.
	 * 
	 * @param namespace a component bundle namespace
	 * @return the corresponding component bundle
	 * @throws JRRuntimeException if no bundle corresponding to the namespace
	 * is found in the registry
	 */
	public ComponentsBundle getBundle(String namespace)
	{
		Map<String, ComponentsBundle> components = getCachedBundles();
		ComponentsBundle componentsBundle = components.get(namespace);
		if (componentsBundle == null)
		{
			throw new JRRuntimeException("No components bundle registered for namespace " 
					+ namespace);
		}
		return componentsBundle;
	}
	
	/**
	 * Returns a component manager that corresponds to a particular component
	 * type key.
	 * 
	 * @param componentKey the component type key
	 * @return the manager for the component type
	 * @throws JRRuntimeException if the registry does not contain the specified
	 * component type
	 */
	public IComponentManager getManager(ComponentKey componentKey)
	{
		String namespace = componentKey.getNamespace();
		ComponentsBundle componentsBundle = getBundle(namespace);
		
		String name = componentKey.getName();
		return componentsBundle.getComponentManager(name);
	}

	/**
	 * @deprecated Replaced by {@link #getBundles()}.
	 */
	public static Collection<ComponentsBundle> getComponentBundles()
	{
		return getDefaultInstance().getBundles();
	}
	
	/**
	 * @deprecated Replaced by {@link #getCachedBundles()}.
	 */
	protected static Map<String, ComponentsBundle> getCachedComponentBundles()
	{
		return getDefaultInstance().getCachedBundles();
	}

	/**
	 * @deprecated Replaced by {@link #findBundles()}. 
	 */
	protected static Map<String, ComponentsBundle> findComponentBundles()
	{
		return getDefaultInstance().findBundles();
	}
	
	/**
	 * @deprecated Replaced by {@link #getBundle(String)}.
	 */
	public static ComponentsBundle getComponentsBundle(String namespace)
	{
		return getDefaultInstance().getBundle(namespace);
	}
	
	/**
	 * @deprecated Replaced by {@link #getManager(ComponentKey)}.
	 */
	public static IComponentManager getComponentManager(ComponentKey componentKey)
	{
		return getDefaultInstance().getManager(componentKey);
	}
}
