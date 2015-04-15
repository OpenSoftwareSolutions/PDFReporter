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
package org.oss.pdfreporter.components;

import org.oss.pdfreporter.components.list.DesignListContents;
import org.oss.pdfreporter.components.list.StandardListComponent;
import org.oss.pdfreporter.engine.component.XmlDigesterConfigurer;
import org.oss.pdfreporter.engine.xml.XmlConstantPropertyRule;
import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.engine.type.PrintOrderEnum;

/**
 * XML digester for built-in component implementations.
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentsXmlHandler.java 5655 2012-09-12 13:25:21Z teodord $
 * @see ComponentsExtensionsRegistryFactory
 */
public class ComponentsXmlDigesterConfigurer implements XmlDigesterConfigurer
{
	public void configureDigester(IDigester digester)
	{
		addListRules(digester);
		//addBarbecueRules(digester);
		//addBarcode4jRules(digester);
		//addTableRules(digester);
		//SpiderChartDigester.addSpiderChartRules(digester);
		//addMapRules(digester);
		//SortComponentDigester.addSortComponentRules(digester);
	}

	protected void addListRules(IDigester digester)
	{
		String listPattern = "*/componentElement/list";
		digester.addObjectCreate(listPattern, StandardListComponent.class);
		digester.addSetProperties(listPattern,
				//properties to be ignored by this rule
				new String[]{"printOrder"},
				new String[0]);
		digester.addRule(listPattern, new XmlConstantPropertyRule(
				"printOrder", "printOrderValue", PrintOrderEnum.values()));

		String listContentsPattern = listPattern + "/listContents";
		digester.addObjectCreate(listContentsPattern, DesignListContents.class);
		digester.addSetProperties(listContentsPattern);
		digester.addSetNext(listContentsPattern, "setContents");

	}
}
