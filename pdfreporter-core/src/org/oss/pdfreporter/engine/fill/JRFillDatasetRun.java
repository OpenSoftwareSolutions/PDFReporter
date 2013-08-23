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

import org.oss.pdfreporter.data.cache.DataCacheHandler;
import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRDatasetParameter;
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JRQuery;
import org.oss.pdfreporter.engine.JRScriptletException;
import org.oss.pdfreporter.engine.type.IncrementTypeEnum;
import org.oss.pdfreporter.engine.type.ResetTypeEnum;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.uses.java.util.UUID;



/**
 * Class used to instantiate sub datasets.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillDatasetRun.java 5414 2012-05-25 09:51:28Z lucianc $
 */
public class JRFillDatasetRun implements JRDatasetRun
{
	
	protected final JRBaseFiller filler;

	protected final JRDatasetRun parentDatasetRun;
	protected final JRFillDataset dataset;

	protected JRExpression parametersMapExpression;

	protected JRDatasetParameter[] parameters;

	protected JRExpression connectionExpression;

	protected JRExpression dataSourceExpression;

	
	/**
	 * Construct an instance for a dataset run.
	 * 
	 * @param filler the filler
	 * @param datasetRun the dataset run
	 * @param factory the fill object factory
	 */
	public JRFillDatasetRun(JRBaseFiller filler, JRDatasetRun datasetRun, JRFillObjectFactory factory)
	{
		this(filler, datasetRun, 
				filler.datasetMap.get(datasetRun.getDatasetName()));
		
		factory.put(datasetRun, this);
	}

	protected JRFillDatasetRun(JRBaseFiller filler, JRDatasetRun datasetRun, 
			JRFillDataset dataset)
	{
		this.filler = filler;
		this.dataset = dataset;

		this.parentDatasetRun = datasetRun;
		parametersMapExpression = datasetRun.getParametersMapExpression();
		parameters = datasetRun.getParameters();
		connectionExpression = datasetRun.getConnectionExpression();
		dataSourceExpression = datasetRun.getDataSourceExpression();
	}

	
	/**
	 * Instantiates and iterates the sub dataset for a chart dataset evaluation.
	 * 
	 * @param elementDataset the chart dataset
	 * @param evaluation the evaluation type
	 * @throws JRException
	 */
	public void evaluate(JRFillElementDataset elementDataset, byte evaluation) throws JRException
	{
		Map<String,Object> parameterValues = 
			JRFillSubreport.getParameterValues(
				filler, 
				parametersMapExpression, 
				parameters, 
				evaluation, 
				false, 
				dataset.getResourceBundle() != null,//hasResourceBundle
				false//hasFormatFactory
				);

		try
		{
			// set fill position for caching
			FillDatasetPosition datasetPosition = new FillDatasetPosition(filler.mainDataset.fillPosition);
			datasetPosition.addAttribute("datasetRunUUID", getUUID());
			filler.mainDataset.setCacheRecordIndex(datasetPosition, evaluation);		
			dataset.setFillPosition(datasetPosition);
			
			String cacheIncludedProp = JRPropertiesUtil.getOwnProperty(this, DataCacheHandler.PROPERTY_INCLUDED); 
			boolean cacheIncluded = JRPropertiesUtil.asBoolean(cacheIncludedProp, true);// default to true
			dataset.setCacheSkipped(!cacheIncluded);
			
			if (dataSourceExpression != null)
			{
				if (!(filler.fillContext.hasDataSnapshot() && cacheIncluded)) 
				{
					JRDataSource dataSource = (JRDataSource) filler.evaluateExpression(dataSourceExpression, evaluation);
					dataset.setDatasourceParameterValue(parameterValues, dataSource);
				}
			}
			else if (connectionExpression != null)
			{
				IConnection connection = (IConnection) filler.evaluateExpression(connectionExpression, evaluation);
				dataset.setConnectionParameterValue(parameterValues, connection);
			}

			copyConnectionParameter(parameterValues);
			
			dataset.initCalculator();
			dataset.setParameterValues(parameterValues);
			dataset.initDatasource();
			
			dataset.filterElementDatasets(elementDataset);

			iterate();
		}
		finally
		{
			dataset.closeDatasource();
			dataset.disposeParameterContributors();
			dataset.restoreElementDatasets();
		}
	}

	protected void copyConnectionParameter(Map<String,Object> parameterValues)
	{
		JRQuery query = dataset.getQuery();
		if (query != null)
		{
			String language = query.getLanguage();
			if (connectionExpression == null && 
					(language.equals("sql") || language.equals("SQL")) &&
					!parameterValues.containsKey(JRParameter.REPORT_CONNECTION))
			{
				IJRFillParameter connParam = filler.getParametersMap().get(JRParameter.REPORT_CONNECTION);
				IConnection connection = (IConnection) connParam.getValue();
				parameterValues.put(JRParameter.REPORT_CONNECTION, connection);
			}
		}
	}

	protected void iterate() throws JRException
	{
		dataset.start();

		init();

		if (advanceDataset())
		{
			detail();

			while (advanceDataset())
			{
				checkInterrupted();

				group();

				detail();
			}
		}

	}

	protected boolean advanceDataset() throws JRException
	{
		return dataset.next();
	}

	
	protected void checkInterrupted()
	{
		filler.checkInterrupted();
	}

	
	protected void group() throws JRException, JRScriptletException
	{
		dataset.calculator.estimateGroupRuptures();

		dataset.calculator.initializeVariables(ResetTypeEnum.GROUP, IncrementTypeEnum.GROUP);
	}

	protected void init() throws JRScriptletException, JRException
	{
		dataset.calculator.initializeVariables(ResetTypeEnum.REPORT, IncrementTypeEnum.REPORT);
	}

	protected void detail() throws JRScriptletException, JRException
	{
		dataset.calculator.calculateVariables();
	}

	public String getDatasetName()
	{
		return dataset.getName();
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
	
	protected JRFillDataset getDataset()
	{
		return dataset;
	}

	public UUID getUUID()
	{
		return parentDatasetRun.getUUID();
	}
	
	/**
	 *
	 */
	public Object clone() 
	{
		throw new UnsupportedOperationException();
	}
	
	public boolean hasProperties()
	{
		return parentDatasetRun.hasProperties();
	}

	public JRPropertiesMap getPropertiesMap()
	{
		return parentDatasetRun.getPropertiesMap();
	}
	
	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}
}
