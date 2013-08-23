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
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.util.JRCloneUtils;
import org.oss.pdfreporter.uses.java.util.UUID;



/**
 * Base implementation of the {@link org.oss.pdfreporter.engine.JRDatasetRun JRDatasetRun} interface.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseDatasetRun.java 5832 2012-11-26 15:50:06Z narcism $
 */
public class JRBaseDatasetRun implements JRDatasetRun, Serializable
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected UUID uuid;
	protected String datasetName;
	protected JRExpression parametersMapExpression;
	protected JRDatasetParameter[] parameters;
	protected JRExpression connectionExpression;
	protected JRExpression dataSourceExpression;
	protected JRPropertiesMap propertiesMap;
	
	
	/**
	 * Creates an empty object.
	 */
	protected JRBaseDatasetRun()
	{
	}

	
	/**
	 * Creates a copy of a dataset instantiation.
	 * 
	 * @param datasetRun the dataset instantiation
	 * @param factory the base object factory
	 */
	protected JRBaseDatasetRun(JRDatasetRun datasetRun, JRBaseObjectFactory factory)
	{
		factory.put(datasetRun, this);
		
		uuid = datasetRun.getUUID();
		datasetName = datasetRun.getDatasetName();
		parametersMapExpression = factory.getExpression(datasetRun.getParametersMapExpression());
		connectionExpression = factory.getExpression(datasetRun.getConnectionExpression());
		dataSourceExpression = factory.getExpression(datasetRun.getDataSourceExpression());
		propertiesMap = JRPropertiesMap.getPropertiesClone(datasetRun);
		
		JRDatasetParameter[] datasetParams = datasetRun.getParameters();
		if (datasetParams != null && datasetParams.length > 0)
		{
			parameters = new JRBaseDatasetParameter[datasetParams.length];
			for (int i = 0; i < parameters.length; i++)
			{
				parameters[i] = factory.getDatasetParameter(datasetParams[i]);
			}
		}
	}

	public UUID getUUID()
	{
		if (uuid == null)
		{
			uuid = UUID.randomUUID();
		}
		return uuid;
	}

	public String getDatasetName()
	{
		return datasetName;
	}

	public JRExpression getParametersMapExpression()
	{
		return parametersMapExpression;
	}

	public JRDatasetParameter[] getParameters()
	{
		return parameters;
	}

	public JRExpression getConnectionExpression()
	{
		return connectionExpression;
	}

	public JRExpression getDataSourceExpression()
	{
		return dataSourceExpression;
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseDatasetRun clone = null;
		
		try
		{
			clone = (JRBaseDatasetRun)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}

		clone.parametersMapExpression = JRCloneUtils.nullSafeClone(parametersMapExpression);
		clone.connectionExpression = JRCloneUtils.nullSafeClone(connectionExpression);
		clone.dataSourceExpression = JRCloneUtils.nullSafeClone(dataSourceExpression);

		clone.parameters = JRCloneUtils.cloneArray(parameters);
		clone.propertiesMap = JRPropertiesMap.getPropertiesClone(this);
		clone.uuid = null;

		return clone;
	}

	public boolean hasProperties()
	{
		return propertiesMap != null && propertiesMap.hasProperties();
	}

	public JRPropertiesMap getPropertiesMap()
	{
		if (propertiesMap == null)
		{
			propertiesMap = new JRPropertiesMap();
		}
		return propertiesMap;
	}

	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}
}
