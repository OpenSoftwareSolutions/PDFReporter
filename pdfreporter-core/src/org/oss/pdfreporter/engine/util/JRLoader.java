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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.repo.DigireportFileResourceLoader;
import org.oss.pdfreporter.repo.FileSystemResource;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRLoader.java 5521 2012-07-30 13:25:30Z teodord $
 */
public final class JRLoader
{

	/**
	 *
	 */
	//private static boolean wasWarning;


	/**
	 * @deprecated Replaced by {@link #loadObjectFromFile(String)}.
	 */
	public static Object loadObject(String fileName) throws JRException
	{
		return loadObjectFromFile(fileName);
	}


	/**
	 *
	 */
	public static Object loadObjectFromFile(String fileName) throws JRException
	{
		return loadObject(new File(fileName));
	}


	/**
	 *
	 */
	public static Object loadObject(File file) throws JRException
	{
		return loadObject(DefaultJasperReportsContext.getInstance(), file);
	}


	/**
	 *
	 */
	public static Object loadObject(JasperReportsContext jasperReportsContext, File file) throws JRException
	{
		if (!file.exists() || !file.isFile())
		{
			throw new JRException( new FileNotFoundException(String.valueOf(file)) );
		}

		throw new JRException("Error not supported to deserialize Objects from File: " + file.getAbsolutePath());
	}


	/**
	 *
	 */
	public static Object loadObject(IURL url) throws JRException
	{
		return loadObject(DefaultJasperReportsContext.getInstance(), url);
	}


	/**
	 *
	 */
	public static Object loadObject(JasperReportsContext jasperReportsContext, IURL url) throws JRException
	{
		throw new JRException("Error not supported to deserialize Objects from URL: " + url);
	}


	/**
	 *
	 */
	public static Object loadObject(InputStream is) throws JRException
	{
		return loadObject(DefaultJasperReportsContext.getInstance(), is);
	}


	/**
	 *
	 */
	public static Object loadObject(JasperReportsContext jasperReportsContext, InputStream is) throws JRException
	{
		throw new JRException("Error not supported to deserialize Objects from InputStream: " + is);
	}




	/**
	 *
	 */
	public static InputStream getInputStream(File file) throws JRException
	{
		if (!file.exists() || !file.isFile())
		{
			throw new JRException( new FileNotFoundException(String.valueOf(file)) );//FIXMEREPO this probably useless
		}

		FileInputStream fis = null;

		try
		{
			fis = new FileInputStream(file);
		}
		catch (IOException e)
		{
			throw new JRException("Error opening input stream from file : " + file, e);
		}

		return fis;
	}


	/**
	 *
	 */
	public static InputStream getInputStream(IURL url) throws JRException
	{
		InputStream is = null;

		try
		{
			is = url.openStream();
		}
		catch (IOException e)
		{
			throw new JRException("Error opening input stream from IURL : " + url, e);
		}

		return is;
	}





	/**
	 *
	 */
	public static byte[] loadBytes(File file) throws JRException
	{
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;

		try
		{
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();

			byte[] bytes = new byte[10000];
			int ln = 0;
			while ((ln = fis.read(bytes)) > 0)
			{
				baos.write(bytes, 0, ln);
			}

			baos.flush();
		}
		catch (IOException e)
		{
			throw new JRException("Error loading byte data : " + file, e);
		}
		finally
		{
			if (baos != null)
			{
				try
				{
					baos.close();
				}
				catch(IOException e)
				{
				}
			}

			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch(IOException e)
				{
				}
			}
		}

		return baos.toByteArray();
	}


	/**
	 *
	 */
	public static byte[] loadBytes(IURL url) throws JRException
	{
		ByteArrayOutputStream baos = null;
		InputStream is = null;

		try
		{
			is = url.openStream();
			baos = new ByteArrayOutputStream();

			byte[] bytes = new byte[10000];
			int ln = 0;
			while ((ln = is.read(bytes)) > 0)
			{
				baos.write(bytes, 0, ln);
			}

			baos.flush();
		}
		catch (IOException e)
		{
			throw new JRException("Error loading byte data : " + url, e);
		}
		finally
		{
			if (baos != null)
			{
				try
				{
					baos.close();
				}
				catch(IOException e)
				{
				}
			}

			if (is != null)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
				}
			}
		}

		return baos.toByteArray();
	}


	/**
	 *
	 */
	public static byte[] loadBytes(InputStream is) throws JRException
	{
		ByteArrayOutputStream baos = null;

		try
		{
			baos = new ByteArrayOutputStream();

			byte[] bytes = new byte[10000];
			int ln = 0;
			while ((ln = is.read(bytes)) > 0)
			{
				baos.write(bytes, 0, ln);
			}

			baos.flush();
		}
		catch (IOException e)
		{
			throw new JRException("Error loading byte data from input stream.", e);
		}
		finally
		{
			if (baos != null)
			{
				try
				{
					baos.close();
				}
				catch(IOException e)
				{
				}
			}
		}

		return baos.toByteArray();
	}




	/**
	 *
	 */
	public static byte[] loadBytesFromResource(String resourceName) throws JRException
	{
		IURL url = DigireportFileResourceLoader.getURL(resourceName);
		if (url != null)
		{
			return loadBytes(url);
		}

		throw new JRException("Resource '" + resourceName + "' not found.");
	}
		
	

	/**
	 * Tries to open an input stream for a location.
	 * <p>
	 * The method tries to interpret the location as a file name, a resource name or
	 * an URL.  If any of these succeed, an input stream is created and returned.
	 * 
	 * @param location the location
	 * @return an input stream if the location is an existing file name, a resource name on
	 * the classpath or an IURL or <code>null</code> otherwise.
	 * 
	 * @throws JRException
	 */
	public static InputStream getLocationInputStream(String location) throws JRException//FIXME deprecate this?
	{
		InputStream is = null;
		
		is = getResourceInputStream(location);
		
		if (is == null)
		{
			is = getFileInputStream(location);
		}
		
		if (is == null)
		{
			is = getURLInputStream(location);
		}
		
		return is;
	}


	/**
	 * Tries to open a file for reading.
	 * 
	 * @param filename the file name
	 * @return an input stream for the file or <code>null</code> if the file was not found
	 * @throws JRException
	 */
	public static InputStream getFileInputStream(String filename) throws JRException
	{
		InputStream is = null;
		
		File file = new File(filename);
		if (file.exists() && file.isFile())
		{
			try
			{
				is = new FileInputStream(file);
			}
			catch (FileNotFoundException e)
			{
				throw new JRException("Error opening file " + filename, e);
			}
		}
		
		return is;
	}


	/**
	 * Tries to open an input stream for a resource.
	 *  
	 * @param resource the resource name
	 * @return an input stream for the resource or <code>null</code> if the resource was not found
	 * @throws JRException 
	 */
	public static InputStream getResourceInputStream(String resource) throws JRException
	{
		InputStream is = null;
		
		IURL resourceUrl = DigireportFileResourceLoader.getURL(resource);
		
		if (resourceUrl!=null) {
			try {
				is = resourceUrl.openStream();
			} catch (IOException e) {
				throw new JRException("Error opening stream " + resource, e);
			}
		}

		return is;
	}

	/**
	 * Scans the context classloader and the classloader of this class for all 
	 * resources that have a specified name, and returns a list of
	 * {@link IURL}s for the found resources.
	 * 
	 * @param resource the resource names
	 * @return a list of {@link IURL}s of resources with the specified name;
	 * the list is empty if no resources have been found for the name
	 * @see ClassLoader#getResources(String)
	 */
	public static List<IURL> getResources(String resource)
	{
		return DigireportFileResourceLoader.getConfiguredFileResources();
	}


	public static List<FileSystemResource> getFileSystemResources(String resource) {
		return DigireportFileResourceLoader.findConfiguredFileSystemResources(resource);
	}

	/**
	 * Returns the resource IURL for a specified resource name.
	 * 
	 * @param resource the resource name
	 * @return the IURL of the resource having the specified name, or
	 * <code>null</code> if none found
	 * @see ClassLoader#getResource(String)
	 */
	public static IURL getResource(String resource)
	{
		return DigireportFileResourceLoader.getURL(resource);
	}

	/**
	 * Tries to open an input stream for an URL.
	 * 
	 * @param spec the string to parse as an URL
	 * @return an input stream for the IURL or null if <code>spec</code> is not a valid URL
	 * @throws JRException
	 */
	public static InputStream getURLInputStream(String spec) throws JRException
	{
		InputStream is = null;
		
		try
		{
			IURL url = IRegistry.getINetFactory().newURL(spec);
			is = url.openStream();
		}
		catch (MalformedURLException e)
		{
			// TODO (29.04.2013, Donat, Digireport): Very bad practice but keep it for compatibility			
		}
		catch (IOException e)
		{
			throw new JRException("Error opening IURL " + spec, e);
		}
		
		return is;
	}
	
	
	private JRLoader()
	{
	}
}
