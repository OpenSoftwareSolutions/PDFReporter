package org.oss.pdfreporter.components;

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

import java.util.HashMap;

import org.oss.pdfreporter.components.list.FillListFactory;
import org.oss.pdfreporter.components.list.ListComponentCompiler;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.component.DefaultComponentXmlParser;
import org.oss.pdfreporter.engine.component.DefaultComponentsBundle;
import org.oss.pdfreporter.engine.component.IComponentManager;
import org.oss.pdfreporter.extensions.ExtensionsRegistry;
import org.oss.pdfreporter.extensions.ExtensionsRegistryFactory;
import org.oss.pdfreporter.extensions.SingletonExtensionRegistry;
import org.oss.pdfreporter.engine.component.ComponentsBundle;

/**
 * Extension registry factory that includes built-in component element
 * implementations.
 *
 * <p>
 * This registry factory is registered by default in JasperReports.
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentsExtensionsRegistryFactory.java 5723 2012-10-16 14:14:42Z shertage $
 * @see ListComponent
 */
public class ComponentsExtensionsRegistryFactory implements
		ExtensionsRegistryFactory
{

	public static final String NAMESPACE =
		"http://jasperreports.sourceforge.net/jasperreports/components";
	public static final String XSD_LOCATION =
		"http://jasperreports.sourceforge.net/xsd/components.xsd";
	public static final String XSD_RESOURCE =
		"org/oss/pdfreporter/components/components.xsd";

	protected static final String LIST_COMPONENT_NAME = "list";
	protected static final String TABLE_COMPONENT_NAME = "table";
	protected static final String BARBECUE_COMPONENT_NAME = "barbecue";
	protected static final String[] BARCODE4J_COMPONENT_NAMES =
		new String[]{"Codabar", "Code128", "EAN128", "DataMatrix", "Code39", "Interleaved2Of5",
		"UPCA", "UPCE", "EAN13", "EAN8", "USPSIntelligentMail", "RoyalMailCustomer",
		"POSTNET", "PDF417"};
	protected static final String SPIDERCHART_COMPONENT_NAME = "spiderChart";
	protected static final String MAP_COMPONENT_NAME = "map";
	protected static final String SORT_COMPONENT_NAME = "sort";

	private static final ExtensionsRegistry REGISTRY;

	static
	{
		final DefaultComponentsBundle bundle = new DefaultComponentsBundle();

		DefaultComponentXmlParser parser = new DefaultComponentXmlParser();
		parser.setNamespace(NAMESPACE);
		parser.setPublicSchemaLocation(XSD_LOCATION);
		parser.setInternalSchemaResource(XSD_RESOURCE);
		parser.setDigesterConfigurer(new ComponentsXmlDigesterConfigurer());
		bundle.setXmlParser(parser);

		HashMap<String, IComponentManager> componentManagers = new HashMap<String, IComponentManager>();

		ComponentsManager listManager = new ComponentsManager();
		// Converter is not needed since we don't provide any previews.
		//listManager.setDesignConverter(new ListDesignConverter());
		listManager.setComponentCompiler(new ListComponentCompiler());
		//listManager.setComponentXmlWriter(xmlHandler);
		listManager.setComponentFillFactory(new FillListFactory());
		componentManagers.put(LIST_COMPONENT_NAME, listManager);

		//ComponentsManager tableManager = new ComponentsManager();
		//tableManager.setDesignConverter(new TableDesignConverter());
		//tableManager.setComponentCompiler(new TableCompiler());
		//tableManager.setComponentXmlWriter(xmlHandler);
		//tableManager.setComponentFillFactory(new FillTableFactory());
		//componentManagers.put(TABLE_COMPONENT_NAME, tableManager);

		//ComponentsManager barbecueManager = new ComponentsManager();
		//barbecueManager.setDesignConverter(new BarbecueDesignConverter());
		//barbecueManager.setComponentCompiler(new BarbecueCompiler());
		//barbecueManager.setComponentXmlWriter(xmlHandler);
		//barbecueManager.setComponentFillFactory(new BarbecueFillFactory());
		//componentManagers.put(BARBECUE_COMPONENT_NAME, barbecueManager);

		//ComponentsManager barcode4jManager = new ComponentsManager();
		//barcode4jManager.setDesignConverter(new BarcodeDesignConverter());
		//barcode4jManager.setComponentCompiler(new BarcodeCompiler());
		//barcode4jManager.setComponentXmlWriter(xmlHandler);
		//barcode4jManager.setComponentFillFactory(new BarcodeFillFactory());
		//for (int i = 0; i < BARCODE4J_COMPONENT_NAMES.length; i++)
		//{
			//componentManagers.put(BARCODE4J_COMPONENT_NAMES[i], barcode4jManager);
		//}

		//ComponentsManager spiderChartManager = new ComponentsManager();
		//spiderChartManager.setDesignConverter(new SpiderChartDesignConverter());
		//spiderChartManager.setComponentCompiler(new SpiderChartCompiler());
		//spiderChartManager.setComponentXmlWriter(xmlHandler);
		//spiderChartManager.setComponentFillFactory(new SpiderChartFillFactory());
		//componentManagers.put(SPIDERCHART_COMPONENT_NAME, spiderChartManager);

		//ComponentsManager mapManager = new ComponentsManager();
		//mapManager.setDesignConverter(MapDesignConverter.getInstance());
		//mapManager.setComponentCompiler(new MapCompiler());
		//mapManager.setComponentXmlWriter(xmlHandler);
		//mapManager.setComponentFillFactory(new MapFillFactory());
		//componentManagers.put(MAP_COMPONENT_NAME, mapManager);

		//ComponentsManager sortManager = new ComponentsManager();
		//sortManager.setDesignConverter(SortComponentDesignConverter.getInstance());
		//sortManager.setComponentCompiler(new SortComponentCompiler());
		//sortManager.setComponentXmlWriter(xmlHandler);
		//sortManager.setComponentFillFactory(new SortComponentFillFactory());
		//componentManagers.put(SORT_COMPONENT_NAME, sortManager);

		bundle.setComponentManagers(componentManagers);

		REGISTRY = new SingletonExtensionRegistry<ComponentsBundle>(ComponentsBundle.class, bundle);
	}

	public ExtensionsRegistry createRegistry(String registryId,
			JRPropertiesMap properties)
	{
		return REGISTRY;
	}

}
