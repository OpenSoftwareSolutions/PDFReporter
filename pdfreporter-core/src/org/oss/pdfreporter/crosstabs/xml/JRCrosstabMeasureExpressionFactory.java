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
package org.oss.pdfreporter.crosstabs.xml;

import org.oss.pdfreporter.crosstabs.design.JRDesignCrosstabMeasure;
import org.oss.pdfreporter.engine.design.JRDesignExpression;
import org.oss.pdfreporter.engine.type.CalculationEnum;
import org.oss.pdfreporter.engine.xml.JRBaseFactory;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRCrosstabMeasureExpressionFactory.java 5180 2012-03-29 13:23:12Z teodord $
 * @deprecated To be removed.
 */
public class JRCrosstabMeasureExpressionFactory extends JRBaseFactory
{
	public Object createObject(IAttributes attributes)
	{
		JRDesignCrosstabMeasure measure = (JRDesignCrosstabMeasure) digester.peek();

		JRDesignExpression expression = new JRDesignExpression();
		if (
			measure.getCalculationValue() == CalculationEnum.COUNT
			|| measure.getCalculationValue() == CalculationEnum.DISTINCT_COUNT
			)
		{
			expression.setValueClassName(Object.class.getName());
		}
		else
		{
			expression.setValueClassName(measure.getValueClassName());
		}

		return expression;
	}
}
