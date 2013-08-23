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

import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.SimpleJasperReportsContext;
import org.oss.pdfreporter.repo.RepositoryService;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: LocalJasperReportsContext.java 5292 2012-04-23 15:30:44Z teodord $
 */
public class AbstrLocalJasperReportsContext extends SimpleJasperReportsContext
{
	/**
	 *
	 */
	private List<RepositoryService> localRepositoryServices;

	/**
	 *
	 */
	public AbstrLocalJasperReportsContext(JasperReportsContext parent)
	{
		super(parent);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getExtensions(Class<T> extensionType)
	{
		if (
			RepositoryService.class.equals(extensionType)
			)
		{
			// we cache repository service extensions from parent and replace the DefaultRepositoryService instance, if present among them 
			if (localRepositoryServices == null)
			{
				List<RepositoryService> repoServices = super.getExtensions(RepositoryService.class);
				if (repoServices != null && repoServices.size() > 0)
				{
					localRepositoryServices = new ArrayList<RepositoryService>();
					for (RepositoryService repoService : repoServices)
					{
						localRepositoryServices.add(repoService);
						
					}
				}
			}
			return (List<T>)localRepositoryServices;
		}
		return super.getExtensions(extensionType);
	}
	
}
