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
package org.oss.pdfreporter.engine.fill;

import java.util.Map;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.sql.IConnection;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFiller.java 5180 2012-03-29 13:23:12Z teodord $
 */
public final class JRFiller
{


	/**
	 *
	 */
	public static JasperPrint fill(
		JasperReportsContext jasperReportsContext,
		JasperReport jasperReport,
		Map<String,Object> parameters,
		IConnection conn
		) throws JRException
	{
		JRBaseFiller filler = createFiller(jasperReportsContext, jasperReport);
		
		JasperPrint jasperPrint = null;
		
		try
		{
			jasperPrint = filler.fill(parameters, conn);
		}
		catch(JRFillInterruptedException e)
		{
			throw new JRException("The report filling thread was interrupted.", e);
		}
		
		return jasperPrint;
	}


	/**
	 *
	 */
	public static JasperPrint fill(
		JasperReportsContext jasperReportsContext,
		JasperReport jasperReport,
		Map<String,Object> parameters,
		JRDataSource dataSource
		) throws JRException
	{
		JRBaseFiller filler = createFiller(jasperReportsContext, jasperReport);
		
		JasperPrint jasperPrint = null;
		
		try
		{
			jasperPrint = filler.fill(parameters, dataSource);
		}
		catch(JRFillInterruptedException e)
		{
			throw new JRException("The report filling thread was interrupted.", e);
		}
		
		return jasperPrint;
	}
	

	/**
	 * Fills a report.
	 * <p/>
	 * The data source used to fill the report is determined in the following way:
	 * <ul>
	 * 	<li>If a non-null value of the {@link org.oss.pdfreporter.engine.JRParameter#REPORT_DATA_SOURCE REPORT_DATA_SOURCE}
	 * has been specified, it will be used as data source.</li>
	 * 	<li>Otherwise, if the report has a query then a data source will be created based on the query and connection
	 * parameter values.</li>
	 * 	<li>Otherwise, the report will be filled without a data source.</li>
	 * </ul>
	 * 
	 * @param jasperReport the report
	 * @param parameters the fill parameters
	 * @return the filled report
	 * @throws JRException
	 */
	public static JasperPrint fill(
		JasperReportsContext jasperReportsContext,
		JasperReport jasperReport, 
		Map<String,Object> parameters
		) throws JRException
	{
		JRBaseFiller filler = createFiller(jasperReportsContext, jasperReport);

		try
		{
			JasperPrint jasperPrint = filler.fill(parameters);

			return jasperPrint;
		}
		catch (JRFillInterruptedException e)
		{
			throw new JRException("The report filling thread was interrupted.", e);
		}
	}


	/**
	 *
	 */
	public static JRBaseFiller createFiller(JasperReportsContext jasperReportsContext, JasperReport jasperReport) throws JRException
	{
		JRBaseFiller filler = null;

		switch (jasperReport.getPrintOrderValue())
		{
			case HORIZONTAL :
			{
				filler = new JRHorizontalFiller(jasperReportsContext, jasperReport);
				break;
			}
			case VERTICAL :
			{
				filler = new JRVerticalFiller(jasperReportsContext, jasperReport);
				break;
			}
		}
		return filler;
	}
	
	private JRFiller()
	{
	}
}
