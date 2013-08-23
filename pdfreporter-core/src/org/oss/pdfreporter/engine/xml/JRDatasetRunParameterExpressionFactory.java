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
package org.oss.pdfreporter.engine.xml;

import org.oss.pdfreporter.engine.JRDatasetParameter;
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.design.JRDesignDataset;
import org.oss.pdfreporter.engine.design.JRDesignExpression;
import org.oss.pdfreporter.engine.design.JRValidationException;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRDatasetRunParameterExpressionFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRDatasetRunParameterExpressionFactory extends JRBaseFactory//FIXMENOW should this be moved into JRVerifier?
{
	
	public Object createObject(IAttributes attributes)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader) digester.peek(digester.getCount() - 1);
		JasperDesign design = (JasperDesign) digester.peek(digester.getCount() - 2);
		JRDatasetRun datasetRun = (JRDatasetRun) digester.peek(1);
		
		JRDesignDataset dataset = (JRDesignDataset) design.getDatasetMap().get(datasetRun.getDatasetName());
		if (dataset == null)
		{
			xmlLoader.addError(new JRValidationException("Unknown sub dataset " + datasetRun.getDatasetName(), datasetRun));
		}
		else
		{
			JRDatasetParameter runParameter = (JRDatasetParameter) digester.peek();
			JRParameter param = dataset.getParametersMap().get(runParameter.getName());
			
			if (param == null)
			{
				xmlLoader.addError(new JRValidationException("Unknown parameter " + runParameter.getName() + " in sub dataset " + datasetRun.getDatasetName(), runParameter));
			}
		}

		JRDesignExpression expression = new JRDesignExpression();
		
		return expression;
	}
}
