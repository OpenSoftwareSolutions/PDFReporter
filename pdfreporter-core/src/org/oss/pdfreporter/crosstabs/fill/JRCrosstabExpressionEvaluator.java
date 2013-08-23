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
package org.oss.pdfreporter.crosstabs.fill;

import java.util.Map;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.fill.IJRFillParameter;
import org.oss.pdfreporter.engine.fill.JREvaluator;
import org.oss.pdfreporter.engine.fill.JRFillDataset;
import org.oss.pdfreporter.engine.fill.JRFillExpressionEvaluator;
import org.oss.pdfreporter.engine.fill.JRFillParameter;
import org.oss.pdfreporter.engine.fill.JRFillVariable;
import org.oss.pdfreporter.engine.type.WhenResourceMissingTypeEnum;


/**
 * Expression evaluator used for crosstabs at fill time.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRCrosstabExpressionEvaluator.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRCrosstabExpressionEvaluator implements JRFillExpressionEvaluator
{
	private final JREvaluator evaluator;
	private JRFillDataset dataset;


	public JRCrosstabExpressionEvaluator(JREvaluator evaluator)
	{
		this.evaluator = evaluator;
	}
	
	
	public Object evaluate(JRExpression expression, byte evaluationType) throws JRException
	{
		if (evaluationType != JRExpression.EVALUATION_DEFAULT)
		{
			throw new JRException("The crosstab evaluator doesn't support old or estimated expression evaluation.");
		}
		
		return evaluator.evaluate(expression);
	}

	
	public void init(Map<String, IJRFillParameter> parametersMap, 
			Map<String, JRFillVariable> variablesMap, WhenResourceMissingTypeEnum whenResourceMissingType) throws JRException
	{
		evaluator.init(parametersMap, null, variablesMap, whenResourceMissingType);
	}
	
	public void setFillDataset(JRFillDataset dataset)
	{
		this.dataset = dataset;
	}

	public JRFillDataset getFillDataset()
	{
		return dataset;
	}
}
