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

import org.oss.pdfreporter.crosstabs.design.JRDesignCrosstab;
import org.oss.pdfreporter.crosstabs.design.JRDesignCrosstabDataset;
import org.oss.pdfreporter.engine.xml.JRBaseFactory;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRCrosstabDatasetFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRCrosstabDatasetFactory extends JRBaseFactory
{
	public static final String ELEMENT_crosstabDataset = "crosstabDataset";

	public static final String ATTRIBUTE_isDataPreSorted = "isDataPreSorted";

	public Object createObject(IAttributes atts)
	{
		JRDesignCrosstab crosstab = (JRDesignCrosstab) digester.peek();
		JRDesignCrosstabDataset dataset = crosstab.getDesignDataset();
		
		String sortedAttr = atts.getValue(ATTRIBUTE_isDataPreSorted);
		if (sortedAttr != null && sortedAttr.length() > 0)
		{
			dataset.setDataPreSorted(Boolean.valueOf(sortedAttr).booleanValue());
		}
		
		return dataset;
	}

}
