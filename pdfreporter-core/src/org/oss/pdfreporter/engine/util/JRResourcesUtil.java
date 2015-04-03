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
package org.oss.pdfreporter.engine.util;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.registry.IRegistry;



/**
 * Provides methods for resource resolution via class loaders or IURL stream handlers.
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRResourcesUtil.java 5180 2012-03-29 13:23:12Z teodord $
 */
public final class JRResourcesUtil
{


	/**
	 * Tries to parse a <code>String</code> as an URL.
	 *
	 * @param spec the <code>String</code> to parse
	 * @return an IURL if the parsing is successful
	 */
	public static IURL createURL(String spec)
	{
		IURL url;
		try
		{
			url = IRegistry.getINetFactory().newURL(spec);
		}
		catch (MalformedURLException e)
		{
			url = null;
		}
		return url;
	}


	/**
	 * Attempts to find a file using a file resolver.
	 *
	 * @param location file name
	 * @param fileRes a file resolver
	 * @return the file, if found
	 */
	public static File resolveFile(String location, FileResolver fileRes)
	{
		FileResolver fileResolver = fileRes;//getFileResolver(fileRes);

		if (fileResolver != null)
		{
			return fileResolver.resolveFile(location);
		}

		File file = new File(location);
		if (file.exists() && file.isFile())
		{
			return file;
		}

		return null;
	}

	/**
	 * Loads a resource bundle for a given base name and locale.
	 *
	 * <p>
	 * This methods calls {@link #loadResourceBundle(String, Locale, ClassLoader)} with a null classloader.
	 * </p>
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @return the resource bundle for the given base name and locale
	 */
	public static ResourceBundle loadResourceBundle(String baseName, Locale locale)
	{
		return loadResourceBundle(baseName, locale, null);
	}

	/**
	 * Loads a resource bundle for a given base name and locale.
	 *
	 * <p>
	 * The method attempts to load the resource bundle using the following classloaders
	 * (and stops at the first successful attempt):
	 * <ul>
	 * 	<li>the class loader returned by {@link #getClassLoader(ClassLoader) <code>getClassLoader(classLoader)</code>}</li>
	 * 	<li>the context class loader</li>
	 * 	<li><code>JRClassLoader.class.getClassLoader()</code></li>
	 * </ul>
	 * </p>
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param clsLoader
	 * @return the resource bundle for the given base name and locale
	 * @see ResourceBundle#getBundle(String, Locale, ClassLoader)
	 */
	private static ResourceBundle loadResourceBundle(String baseName, Locale locale, ClassLoader clsLoader)
	{
		ResourceBundle resourceBundle = null;

		ClassLoader classLoader = clsLoader;

		if (resourceBundle == null)
		{
			classLoader = JRClassLoader.class.getClassLoader();
			if (classLoader == null)
			{
				resourceBundle = ResourceBundle.getBundle(baseName, locale);
			}
			else
			{
				resourceBundle = ResourceBundle.getBundle(baseName, locale, classLoader);
			}
		}

		return resourceBundle;
	}

	private JRResourcesUtil()
	{
	}
}
