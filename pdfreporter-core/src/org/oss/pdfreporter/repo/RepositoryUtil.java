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
package org.oss.pdfreporter.repo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.ReportContext;



/**
 * TODO (30.03.2013, Magnus, Digireport): Class stays but the implementation will only support reading files from ./ directory locally
 * since on mobile devices no remote calls for files can be supported - the net could be broken...
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: RepositoryUtil.java 5346 2012-05-08 12:08:01Z teodord $
 */
public final class RepositoryUtil
{
	//private static final Log log = LogFactory.getLog(RepositoryUtil.class);

	

	private JasperReportsContext jasperReportsContext;


	/**
	 *
	 */
	private RepositoryUtil(JasperReportsContext jasperReportsContext)//FIXMECONTEXT try to reuse utils as much as you can
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	
	/**
	 *
	 */
	private static RepositoryUtil getDefaultInstance()
	{
		return new RepositoryUtil(DefaultJasperReportsContext.getInstance());
	}
	
	
	/**
	 *
	 */
	public static RepositoryUtil getInstance(JasperReportsContext jasperReportsContext)
	{
		return new RepositoryUtil(jasperReportsContext);
	}
	
	
	/**
	 * 
	 */
	private List<RepositoryService> getServices()
	{
		return jasperReportsContext.getExtensions(RepositoryService.class);
	}
	
	
	/**
	 * @deprecated Replaced by {@link #getServices()}.
	 */
	public static List<RepositoryService> getRepositoryServices()
	{
		return getDefaultInstance().getServices();
	}
	
	
	/**
	 * 
	 *
	private static RepositoryContext getRepositoryContext()
	{
		return (RepositoryContext)localContextStack.top();
	}
	
	
	
	
//	/**
//	 * @deprecated To be removed.
//	 */
//	public static void revertRepositoryContext()
//	{
//		//RepositoryContext repositoryContext = getRepositoryContext();
//		List<RepositoryService> services = getRepositoryServices();
//		if (services != null)
//		{
//			for (RepositoryService service : services)
//			{
//				service.revertContext();//FIXMEREPO context?
//			}
//		}
//		localContextStack.pop();
//	}
//	
//	
//	/**
//	 * @deprecated To be removed.
//	 */
//	public static ReportContext getThreadReportContext()
//	{
//		return threadReportContext.get();
//	}
//
//	/**
//	 * @deprecated To be removed.
//	 */
//	public static void setThreadReportContext(ReportContext reportContext)
//	{
//		threadReportContext.set(reportContext);
//	}
//
//	/**
//	 * @deprecated To be removed.
//	 */
//	public static void resetThreadReportContext()
//	{
//		threadReportContext.set(null);
//	}

	
	/**
	 *
	 */
	public JasperReport getReport(ReportContext reportContext, String location) throws JRException 
	{
		JasperReport jasperReport = null;
		
		JasperDesignCache cache = JasperDesignCache.getInstance(jasperReportsContext, reportContext);
		if (cache != null)
		{
			jasperReport = cache.getJasperReport(location);
		}

		if (jasperReport == null)
		{
			ReportResource resource = getResourceFromLocation(location, ReportResource.class);
			if (resource == null)
			{
				throw new JRException("Report not found at : " + location);
			}

			jasperReport = resource.getReport();

			if (cache != null)
			{
				cache.set(location, jasperReport);
			}
		}

		return jasperReport;
	}


//	/**
//	 * @deprecated Replaced by {@link #getReport(ReportContext, String)}.
//	 */
//	public static JasperReport getReport(String location) throws JRException 
//	{
//		return getDefaultInstance().getReport(getThreadReportContext(), location);
//	}


	/**
	 * 
	 */
	public <K extends Resource> K getResourceFromLocation(String location, Class<K> resourceType) throws JRException
	{
		K resource = null;
		List<RepositoryService> services = getServices();
		if (services != null)
		{
			for (RepositoryService service : services)
			{
				resource = service.getResource(location, resourceType);
				if (resource != null)
				{
					break;
				}
			}
		}
		if (resource == null)
		{
			throw new JRException("Resource not found at : " + location);//FIXMEREPO decide whether to return null or throw exception; check everywhere
		}
		return resource;
	}


	/**
	 * @deprecated Replaced by {@link #getResourceFromLocation(String, Class)}.
	 */
	public static <K extends Resource> K getResource(String location, Class<K> resourceType) throws JRException
	{
		return getDefaultInstance().getResourceFromLocation(location, resourceType);
	}


	/**
	 *
	 */
	public InputStream getInputStreamFromLocation(String location) throws JRException
	{
		InputStream is = findInputStream(location);
		if (is == null)
		{
			throw new JRException("Input stream not found at : " + location);
		}
		return is;
	}


	/**
	 * @deprecated Replaced by {@link #getInputStreamFromLocation(String)}.
	 */
	public static InputStream getInputStream(String location) throws JRException
	{
		return getDefaultInstance().getInputStreamFromLocation(location);
	}
	
	
	/**
	 *
	 */
	private InputStream findInputStream(String location) throws JRException
	{
		return DigireportFileResourceLoader.getInputStream(location);
	}
	
	
	/**
	 *
	 */
	public byte[] getBytesFromLocation(String location) throws JRException
	{
		InputStream is = findInputStream(location);
//		// TODO (12.04.2013, Donat, Digireport): Replace repository with FileResourceLoader
//		if (is==null) {			
//			is = DigireportFileResourceLoader.getInputStream(location);
//		}
		
		if (is == null)
		{
			throw new JRException("Byte data not found at : " + location);
		}

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
			throw new JRException("Error loading byte data from : " + location, e);
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
	 * @deprecated Replaced by {@link #getBytesFromLocation(String)}.
	 */
	public static byte[] getBytes(String location) throws JRException
	{
		return getDefaultInstance().getBytesFromLocation(location);
	}
}
