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

import java.util.List;
import java.util.Map;

import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.SimpleJasperReportsContext;
import org.oss.pdfreporter.repo.DefaultRepositoryService;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: LocalJasperReportsContext.java 5292 2012-04-23 15:30:44Z teodord $
 */
public class LocalJasperReportsContext extends SimpleJasperReportsContext
{
	/**
	 *
	 */
	private AbstrLocalJasperReportsContext delegate;
	private DefaultRepositoryService localRepositoryService;

	/**
	 *
	 */
	public LocalJasperReportsContext(JasperReportsContext parent)
	{
		delegate = new AbstrLocalJasperReportsContext(parent);
	}

	/**
	 *
	 */
	@SuppressWarnings("deprecation")
	public static JasperReportsContext getLocalContext(JasperReportsContext jasperReportsContext, Map<String,Object> parameterValues)
	{
		if (
			parameterValues.containsKey(JRParameter.REPORT_CLASS_LOADER)
			|| parameterValues.containsKey(JRParameter.REPORT_FILE_RESOLVER)
			)
		{
			LocalJasperReportsContext localJasperReportsContext = new LocalJasperReportsContext(jasperReportsContext);

			if (parameterValues.containsKey(JRParameter.REPORT_CLASS_LOADER))
			{
				localJasperReportsContext.setClassLoader((ClassLoader)parameterValues.get(JRParameter.REPORT_CLASS_LOADER));
			}

			if (parameterValues.containsKey(JRParameter.REPORT_FILE_RESOLVER))
			{
				localJasperReportsContext.setFileResolver((FileResolver)parameterValues.get(JRParameter.REPORT_FILE_RESOLVER));
			}
			
			return localJasperReportsContext;
		}

		return jasperReportsContext;
	}

	/**
	 *
	 */
	private DefaultRepositoryService getLocalRepositoryService()
	{
		if (localRepositoryService == null)
		{
			localRepositoryService = new DefaultRepositoryService(this);
		}
		return localRepositoryService;
	}

	/**
	 *
	 */
	public void setClassLoader(ClassLoader classLoader)
	{
		getLocalRepositoryService().setClassLoader(classLoader);
	}


	/**
	 * @deprecated To be removed.
	 */
	public FileResolver getFileResolver()
	{
		return localRepositoryService == null ? null : localRepositoryService.getFileResolver();
	}

	/**
	 *
	 */
	public void setFileResolver(FileResolver fileResolver)
	{
		getLocalRepositoryService().setFileResolver(fileResolver);
	}

	@Override
	public <T> List<T> getExtensions(Class<T> extensionType)
	{
		return delegate.getExtensions(extensionType);
	}
	
}
