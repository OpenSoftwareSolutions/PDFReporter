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

import org.oss.pdfreporter.crosstabs.JRCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.type.CrosstabPercentageEnum;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.fill.JRDefaultIncrementerFactory;
import org.oss.pdfreporter.engine.fill.JRExtendedIncrementerFactory;
import org.oss.pdfreporter.engine.fill.JRFillVariable;
import org.oss.pdfreporter.engine.fill.JRIncrementerFactoryCache;
import org.oss.pdfreporter.engine.type.CalculationEnum;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillCrosstabMeasure.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRFillCrosstabMeasure implements JRCrosstabMeasure
{
	protected JRCrosstabMeasure parentMeasure;
	protected JRFillVariable variable;
	protected JRExtendedIncrementerFactory incrementerFactory;
	protected JRPercentageCalculator percentageCalculator;

	public JRFillCrosstabMeasure(JRCrosstabMeasure measure, JRFillCrosstabObjectFactory factory)
	{
		factory.put(measure, this);
		
		parentMeasure = measure;
		
		variable = factory.getVariable(measure.getVariable());
		
		incrementerFactory = createIncrementerFactory();
		percentageCalculator = createPercentageCalculator();
	}

	public String getName()
	{
		return parentMeasure.getName();
	}

	public String getValueClassName()
	{
		return parentMeasure.getValueClassName();
	}

	public Class<?> getValueClass()
	{
		return parentMeasure.getValueClass();
	}

	public JRExpression getValueExpression()
	{
		return parentMeasure.getValueExpression();
	}

	public CalculationEnum getCalculationValue()
	{
		return parentMeasure.getCalculationValue();
	}

	public String getIncrementerFactoryClassName()
	{
		return parentMeasure.getIncrementerFactoryClassName();
	}

	public Class<?> getIncrementerFactoryClass()
	{
		return parentMeasure.getIncrementerFactoryClass();
	}

	public CrosstabPercentageEnum getPercentageType()
	{
		return parentMeasure.getPercentageType();
	}

	public JRVariable getVariable()
	{
		return variable;
	}

	public JRFillVariable getFillVariable()
	{
		return variable;
	}
	
	
	public JRExtendedIncrementerFactory getIncrementerFactory()
	{
		return incrementerFactory;
	}
	
	
	public JRPercentageCalculator getPercentageCalculator()
	{
		return percentageCalculator;
	}

	
	private JRExtendedIncrementerFactory createIncrementerFactory()
	{
		JRExtendedIncrementerFactory incrFactory;

		String incrementerFactoryClassName = getIncrementerFactoryClassName();
		if (incrementerFactoryClassName == null)
		{
			incrFactory = JRDefaultIncrementerFactory.getFactory(getValueClass());
		}
		else
		{
			incrFactory = (JRExtendedIncrementerFactory) JRIncrementerFactoryCache.getInstance(getIncrementerFactoryClass());
		}
		
		return incrFactory;
	}

	public JRPercentageCalculator createPercentageCalculator()
	{
		JRPercentageCalculator percentageCalc;
		
		if (getPercentageType() == CrosstabPercentageEnum.GRAND_TOTAL)
		{
			percentageCalc = JRPercentageCalculatorFactory.getPercentageCalculator(getPercentageCalculatorClass(), getValueClass());
		}
		else
		{
			percentageCalc = null;
		}
		
		return percentageCalc;
	}

	public String getPercentageCalculatorClassName()
	{
		return parentMeasure.getPercentageCalculatorClassName();
	}

	public Class<?> getPercentageCalculatorClass()
	{
		return parentMeasure.getPercentageCalculatorClass();
	}
	
	/**
	 *
	 */
	public Object clone() 
	{
		throw new UnsupportedOperationException();
	}
}
