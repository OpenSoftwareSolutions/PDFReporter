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

/*
 * Contributors:
 * Artur Biesiadowski - abies@users.sourceforge.net 
 */
package org.oss.pdfreporter.engine.xml;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRTemplate;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.repo.RepositoryUtil;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLParseException;




/**
 * Utility class that loads {@link JRTemplate templates} from XML representations.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRXmlTemplateLoader.java 5346 2012-05-08 12:08:01Z teodord $
 */
public class JRXmlTemplateLoader
{
	
	private JasperReportsContext jasperReportsContext;
	
	/**
	 * @deprecated Replaced by {@link #JRXmlTemplateLoader(JasperReportsContext)}.
	 */
	protected JRXmlTemplateLoader()
	{
		this(DefaultJasperReportsContext.getInstance());
	}
	
	/**
	 *
	 */
	private JRXmlTemplateLoader(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	/**
	 *
	 */
	private static JRXmlTemplateLoader getDefaultInstance()
	{
		return new JRXmlTemplateLoader(DefaultJasperReportsContext.getInstance());
	}
	
	
	/**
	 *
	 */
	public static JRXmlTemplateLoader getInstance(JasperReportsContext jasperReportsContext)
	{
		return new JRXmlTemplateLoader(jasperReportsContext);
	}
	
	
	/**
	 * Parses a template XML found at a specified location into a {@link JRTemplate template object}.
	 * 
	 * @param location the template XML location.
	 * 	Can be a URL, a file path or a classloader resource name.
	 * @return the template object
	 * @throws JRException when the location cannot be resolved or read
	 * @see RepositoryUtil#getBytes(String)
	 */
	public JRTemplate loadTemplate(String location) throws JRException
	{
		byte[] data = RepositoryUtil.getInstance(jasperReportsContext).getBytesFromLocation(location);
		return load(new ByteArrayInputStream(data));
	}
	
	/**
	 * Parses a template XML file into a {@link JRTemplate template object}.
	 * 
	 * @param file the template XML file
	 * @return the template object
	 */
	public JRTemplate loadTemplate(File file)
	{
		BufferedInputStream fileIn;
		try
		{
			fileIn = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
			throw new JRRuntimeException("Template XML file not found", e);
		}

		try
		{
			return load(fileIn);
		}
		finally
		{
			try
			{
				fileIn.close();
			}
			catch (IOException e)
			{
			}
		}		
	}
	
	/**
	 * Parses a template XML located at a IURL into a {@link JRTemplate template object}.
	 * 
	 * @param url the location of the template XML
	 * @return the template object
	 */
	public JRTemplate loadTemplate(IURL url)
	{
		InputStream input;
		try
		{
			input = url.openStream();
		}
		catch (IOException e)
		{
			throw new JRRuntimeException("Error opening connection to template IURL " + url, e);
		}

		try
		{
			return load(input);
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (IOException e)
			{
			}
		}		
	}
	
	/**
	 * Parses a template XML data stream into a {@link JRTemplate template object}.
	 * 
	 * @param data the data stream
	 * @return the template object
	 */
	public JRTemplate loadTemplate(InputStream data)
	{
		JRXmlDigester digester = JRXmlTemplateDigesterFactory.instance().createDigester(jasperReportsContext);
		try
		{
			return (JRTemplate) digester.parse(IRegistry.getIXmlParserFactory().newInputSource(data));
		}
		catch (IOException e)
		{
			throw new JRRuntimeException("Error reading template XML", e);
		}
		catch (XMLParseException e)
		{
			throw new JRRuntimeException("Error parsing template XML", e);
		} catch (ParserConfigurationException e) {
			throw new JRRuntimeException("Error parsing template XML", e);
		}
	}
	
	/**
	 * @see #loadTemplate(String)
	 */
	public static JRTemplate load(String location) throws JRException
	{
		return getDefaultInstance().loadTemplate(location);
	}
	
	/**
	 * @see #loadTemplate(File)
	 */
	public static JRTemplate load(File file)
	{
		return getDefaultInstance().loadTemplate(file);
	}
	
	/**
	 * @see #loadTemplate(URL)
	 */
	public static JRTemplate load(IURL url)
	{
		return getDefaultInstance().loadTemplate(url);
	}
	
	/**
	 * @see #loadTemplate(InputStream)
	 */
	public static JRTemplate load(InputStream data)
	{
		return getDefaultInstance().loadTemplate(data);
	}

}
