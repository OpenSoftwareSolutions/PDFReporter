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
package org.oss.pdfreporter.engine.query;

import java.util.Map;

import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRAbstractQueryExecuterFactory.java 5305 2012-04-26 15:17:33Z teodord $
 * @deprecated Replaced by {@link AbstractQueryExecuterFactory}.
 */
public abstract class JRAbstractQueryExecuterFactory implements QueryExecuterFactory 
{
	
	/**
	 *
	 */
	public JRQueryExecuter createQueryExecuter(
		JasperReportsContext jasperReportsContext,
		JRDataset dataset, 
		Map<String, ? extends JRValueParameter> parameters
		) throws JRException 
	{
		return createQueryExecuter(dataset, parameters);
	}

}
