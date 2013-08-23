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

import org.oss.pdfreporter.engine.design.JRDesignGroup;
import org.oss.pdfreporter.engine.design.JRDesignVariable;
import org.oss.pdfreporter.engine.type.CalculationEnum;
import org.oss.pdfreporter.engine.type.IncrementTypeEnum;
import org.oss.pdfreporter.engine.type.ResetTypeEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRVariableFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRVariableFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRDesignVariable variable = new JRDesignVariable();
		
		variable.setName(atts.getValue(JRXmlConstants.ATTRIBUTE_name));

		if (atts.getValue(JRXmlConstants.ATTRIBUTE_class) != null)
		{
			variable.setValueClassName(atts.getValue(JRXmlConstants.ATTRIBUTE_class));
		}

		ResetTypeEnum resetType = ResetTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_resetType));
		if (resetType != null)
		{
			variable.setResetType(resetType);
		}
		
		String groupName = atts.getValue(JRXmlConstants.ATTRIBUTE_resetGroup);
		if (groupName != null)
		{
			JRDesignGroup group = new JRDesignGroup();
			group.setName(groupName);
			variable.setResetGroup(group);
		}

		IncrementTypeEnum incrementType = IncrementTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_incrementType));
		if (incrementType != null)
		{
			variable.setIncrementType(incrementType);
		}
		
		groupName = atts.getValue(JRXmlConstants.ATTRIBUTE_incrementGroup);
		if (groupName != null)
		{
			JRDesignGroup group = new JRDesignGroup();
			group.setName(groupName);
			variable.setIncrementGroup(group);
		}

		CalculationEnum calculation = CalculationEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_calculation));
		if (calculation != null)
		{
			variable.setCalculation(calculation);
		}

		if (atts.getValue(JRXmlConstants.ATTRIBUTE_incrementerFactoryClass) != null)
		{
			variable.setIncrementerFactoryClassName(atts.getValue(JRXmlConstants.ATTRIBUTE_incrementerFactoryClass));
		}

		return variable;
	}
	

}
