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
package org.oss.pdfreporter.engine.base;

import java.io.Serializable;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDatasetParameter;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRRuntimeException;



/**
 * Base read-only implementation of {@link org.oss.pdfreporter.engine.JRDatasetParameter JRDatasetParameter}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseDatasetParameter.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseDatasetParameter implements JRDatasetParameter, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected String name;
	protected JRExpression expression;


	/**
	 *
	 */
	protected JRBaseDatasetParameter()
	{
	}
	
	
	/**
	 *
	 */
	protected JRBaseDatasetParameter(JRDatasetParameter datasetParameter, JRBaseObjectFactory factory)
	{
		factory.put(datasetParameter, this);

		name = datasetParameter.getName();
		expression = factory.getExpression(datasetParameter.getExpression());
	}
		

	/**
	 *
	 */
	public String getName()
	{
		return this.name;
	}
		
	/**
	 *
	 */
	public JRExpression getExpression()
	{
		return this.expression;
	}

	
	/**
	 *
	 */
	public Object clone() 
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
	}

}
