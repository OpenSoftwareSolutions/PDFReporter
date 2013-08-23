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

import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.design.JRDesignSubreportReturnValue;
import org.oss.pdfreporter.engine.design.JRValidationException;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.type.CalculationEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * Factory class for {@link org.oss.pdfreporter.engine.JRSubreportReturnValue JRSubreportReturnValue}
 * creation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRSubreportReturnValueFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRSubreportReturnValueFactory extends JRBaseFactory
{

	/**
	 * Creates an object from a subreport copied value XML element.
	 * 
	 * @param atts the element attributes
	 * @return a {@link JRDesignSubreportReturnValue JRDesignSubreportReturnValue} object
	 */
	public Object createObject(IAttributes atts)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader) digester.peek(digester.getCount() - 1);
		JasperDesign design = (JasperDesign) digester.peek(digester.getCount() - 2);
		
		JRDesignSubreportReturnValue returnValue = new JRDesignSubreportReturnValue();

		String variableName = atts.getValue(JRXmlConstants.ATTRIBUTE_toVariable);
		JRVariable variable = design.getVariablesMap().get(variableName);
		if (variable == null)
		{
			xmlLoader.addError(new JRValidationException("Unknown variable " + variableName, returnValue));
		}
		
		returnValue.setSubreportVariable(atts.getValue(JRXmlConstants.ATTRIBUTE_subreportVariable));
		returnValue.setToVariable(variableName);

		CalculationEnum calculation = CalculationEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_calculation));
		if (calculation != null)
		{
			returnValue.setCalculation(calculation);
		}
		
		String incrementerFactoryClass = atts.getValue(JRXmlConstants.ATTRIBUTE_incrementerFactoryClass);
		if (incrementerFactoryClass != null)
		{
			returnValue.setIncrementerFactoryClassName(incrementerFactoryClass);
		}

		return returnValue;
	}

}
