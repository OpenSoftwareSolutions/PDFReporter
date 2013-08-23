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

import java.util.Set;

import org.oss.pdfreporter.engine.JRElementDataset;
import org.oss.pdfreporter.engine.design.JRDesignElementDataset;
import org.oss.pdfreporter.engine.design.JRDesignGroup;
import org.oss.pdfreporter.engine.type.IncrementTypeEnum;
import org.oss.pdfreporter.engine.type.ResetTypeEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRElementDatasetFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRElementDatasetFactory extends JRBaseFactory
{

	
	public Object createObject(IAttributes atts)
	{
		JRDesignElementDataset dataset = (JRDesignElementDataset) digester.peek();

		setDatasetAtts(atts, dataset);

		return dataset;
	}

	protected void setDatasetAtts(IAttributes atts, JRDesignElementDataset dataset)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader)digester.peek(digester.getCount() - 1);
		Set<JRElementDataset> groupBoundDatasets = xmlLoader.getGroupBoundDatasets();
		
		ResetTypeEnum resetType = ResetTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_resetType));
		if (resetType != null)
		{
			dataset.setResetType(resetType);
		}
		if (dataset.getResetTypeValue() == ResetTypeEnum.GROUP)
		{
			groupBoundDatasets.add(dataset);

			String groupName = atts.getValue(JRXmlConstants.ATTRIBUTE_resetGroup);
			if (groupName != null)
			{
				JRDesignGroup group = new JRDesignGroup();
				group.setName(groupName);
				dataset.setResetGroup(group);
			}
		}

		IncrementTypeEnum incrementType = IncrementTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_incrementType));
		if (incrementType != null)
		{
			dataset.setIncrementType(incrementType);
		}
		if (dataset.getIncrementTypeValue() == IncrementTypeEnum.GROUP)
		{
			groupBoundDatasets.add(dataset);

			String groupName = atts.getValue(JRXmlConstants.ATTRIBUTE_incrementGroup);
			if (groupName != null)
			{
				JRDesignGroup group = new JRDesignGroup();
				group.setName(groupName);
				dataset.setIncrementGroup(group);
			}
		}
	}

}
