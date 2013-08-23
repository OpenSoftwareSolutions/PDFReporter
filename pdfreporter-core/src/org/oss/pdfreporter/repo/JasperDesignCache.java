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

import java.util.HashMap;
import java.util.Map;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.ReportContext;
import org.oss.pdfreporter.engine.design.JasperDesign;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperDesignCache.java 5347 2012-05-08 12:50:30Z teodord $
 */
public class JasperDesignCache
{
	/**
	 * 
	 */
	private static final String PARAMETER_JASPER_DESIGN_CACHE = "net.sf.jasperreports.parameter.jasperdesign.cache";

	/**
	 * 
	 */
	private JasperReportsContext jasperReportsContext;
	private Map<String, JasperDesignReportResource> cachedResourcesMap = new HashMap<String, JasperDesignReportResource>();
	//private Map<UUID, String> cachedSubreportsMap = new HashMap<UUID, String>();

	/**
	 * 
	 */
	public static JasperDesignCache getInstance(JasperReportsContext jasperReportsContext, ReportContext reportContext)//FIXMECONTEXT a jr context change would be inconsistent
	{
		JasperDesignCache cache = null;

		if (reportContext != null)
		{
			cache = (JasperDesignCache)reportContext.getParameterValue(PARAMETER_JASPER_DESIGN_CACHE);
			
			if (cache == null)
			{
				cache = new JasperDesignCache(jasperReportsContext);
				reportContext.setParameterValue(PARAMETER_JASPER_DESIGN_CACHE, cache);
			}
		}
		
		return cache;
	}
	
	public static JasperDesignCache getExistingInstance(ReportContext reportContext)
	{
		JasperDesignCache cache = null;
		if (reportContext != null)
		{
			cache = (JasperDesignCache) reportContext.getParameterValue(PARAMETER_JASPER_DESIGN_CACHE);
		}
		return cache;
	}
	
	/**
	 * 
	 */
	private JasperDesignCache(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	/**
	 * 
	 */
	public JasperReport getJasperReport(String uri)
	{
		JasperDesignReportResource resource = getResource(uri);
		if (resource != null)
		{
			return resource.getReport();
		}
		return null;
	}

	/**
	 * 
	 */
	public JasperDesign getJasperDesign(String uri)
	{
		JasperDesignReportResource resource = getResource(uri);
		if (resource != null)
		{
			return resource.getJasperDesign();
		}
		return null;
	}

	/**
	 * 
	 */
	public void set(String uri, JasperReport jasperReport)
	{
		JasperDesignReportResource resource = new JasperDesignReportResource();
		resource.setReport(jasperReport);
		cachedResourcesMap.put(uri, resource);
	}

	/**
	 * 
	 */
	public void set(String uri, JasperDesign jasperDesign)
	{
		JasperDesignReportResource resource = new JasperDesignReportResource();
		resource.setJasperDesign(jasperDesign);
		cachedResourcesMap.put(uri, resource);
	}

	/**
	 * 
	 */
	public void resetJasperReport(String uri)
	{
		JasperDesignReportResource resource = cachedResourcesMap.get(uri);
		if (resource != null)
		{
			resource.setReport(null);
		}
		//cachedResourcesMap.put(uri, resource);
	}

	public JasperDesignReportResource remove(String uri)
	{
		return cachedResourcesMap.remove(uri);
	}
	
	public void set(String uri, JasperDesignReportResource resource)
	{
		cachedResourcesMap.put(uri, resource);
	}
	
	public void clear()
	{
		cachedResourcesMap.clear();
	}
	
	/**
	 * 
	 */
	private JasperDesignReportResource getResource(String uri)
	{
		JasperDesignReportResource resource = cachedResourcesMap.get(uri);
		
		if (resource != null)
		{
			JasperDesign jasperDesign = resource.getJasperDesign();
			JasperReport jasperReport = resource.getReport();
			
			if (jasperDesign == null)
			{
				throw new JRRuntimeException("resource loading is not supported by Digireport AG");
			}
			else
			{
				if (jasperReport == null)
				{
					try
					{
						jasperReport = JasperCompileManager.getInstance(jasperReportsContext).compile(jasperDesign);
						resource.setReport(jasperReport);
					}
					catch (JRException e)
					{
						throw new JRRuntimeException(e);
					}
				}
				else
				{
					//nothing to do?
				}
			}
		}
		
		return resource;
	}


	/**
	 * 
	 */
	public Map<String, JasperDesignReportResource> getCachedResources()
	{
		return cachedResourcesMap;
	}
}
