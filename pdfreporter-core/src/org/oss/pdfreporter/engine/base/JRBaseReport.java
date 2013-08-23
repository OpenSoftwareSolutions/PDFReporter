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
package org.oss.pdfreporter.engine.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.oss.pdfreporter.engine.JRBand;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRField;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRQuery;
import org.oss.pdfreporter.engine.JRReport;
import org.oss.pdfreporter.engine.JRReportTemplate;
import org.oss.pdfreporter.engine.JRScriptlet;
import org.oss.pdfreporter.engine.JRSection;
import org.oss.pdfreporter.engine.JRSortField;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.OrientationEnum;
import org.oss.pdfreporter.engine.type.PrintOrderEnum;
import org.oss.pdfreporter.engine.type.RunDirectionEnum;
import org.oss.pdfreporter.engine.type.WhenNoDataTypeEnum;
import org.oss.pdfreporter.engine.type.WhenResourceMissingTypeEnum;
import org.oss.pdfreporter.uses.java.util.UUID;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseReport.java 5756 2012-10-31 15:30:34Z teodord $
 */
public class JRBaseReport implements JRReport, Serializable, JRChangeEventsSupport
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_WHEN_NO_DATA_TYPE = "whenNoDataType";

	/**
	 *
	 */
	protected String name;
	protected String language = LANGUAGE_JAVA;
	protected int columnCount = 1;
	protected PrintOrderEnum printOrderValue = PrintOrderEnum.VERTICAL;
	protected RunDirectionEnum columnDirection = RunDirectionEnum.LTR;
	protected int pageWidth = 595;
	protected int pageHeight = 842;
	protected OrientationEnum orientationValue = OrientationEnum.PORTRAIT;
	protected WhenNoDataTypeEnum whenNoDataTypeValue = WhenNoDataTypeEnum.NO_PAGES;
	protected int columnWidth = 555;
	protected int columnSpacing;
	protected int leftMargin = 20;
	protected int rightMargin = 20;
	protected int topMargin = 30;
	protected int bottomMargin = 30;
	protected boolean isTitleNewPage;
	protected boolean isSummaryNewPage;
	protected boolean isSummaryWithPageHeaderAndFooter;
	protected boolean isFloatColumnFooter;
	protected boolean ignorePagination;

	/**
	 *
	 */
	protected String formatFactoryClass;

	/**
	 *
	 */
	protected Set<String> importsSet;

	/**
	 * Report templates.
	 */
	protected JRReportTemplate[] templates;

	protected JRStyle defaultStyle;
	protected JRStyle[] styles;

	/**
	 * The main dataset of the report.
	 */
	protected JRDataset mainDataset;

	/**
	 * Sub datasets of the report.
	 */
	protected JRDataset[] datasets;

	protected JRBand background;
	protected JRBand title;
	protected JRBand pageHeader;
	protected JRBand columnHeader;
	protected JRSection detailSection;
	protected JRBand columnFooter;
	protected JRBand pageFooter;
	protected JRBand lastPageFooter;
	protected JRBand summary;
	protected JRBand noData;


	/**
	 *
	 */
	public JRBaseReport()
	{
	}

	public JRBaseReport(JRReport report, JRExpressionCollector expressionCollector)
	{
		this(report, new JRBaseObjectFactory(expressionCollector));
	}
	
	/**
	 * Constructs a copy of a report.
	 */
	public JRBaseReport(JRReport report, JRBaseObjectFactory factory)
	{
		/*   */
		name = report.getName();
		language = report.getLanguage();
		columnCount = report.getColumnCount();
		printOrderValue = report.getPrintOrderValue();
		columnDirection = report.getColumnDirection();
		pageWidth = report.getPageWidth();
		pageHeight = report.getPageHeight();
		orientationValue = report.getOrientationValue();
		whenNoDataTypeValue = report.getWhenNoDataTypeValue();
		columnWidth = report.getColumnWidth();
		columnSpacing = report.getColumnSpacing();
		leftMargin = report.getLeftMargin();
		rightMargin = report.getRightMargin();
		topMargin = report.getTopMargin();
		bottomMargin = report.getBottomMargin();
		isTitleNewPage = report.isTitleNewPage();
		isSummaryNewPage = report.isSummaryNewPage();
		isSummaryWithPageHeaderAndFooter = report.isSummaryWithPageHeaderAndFooter();
		isFloatColumnFooter = report.isFloatColumnFooter();
		ignorePagination = report.isIgnorePagination();

		formatFactoryClass = report.getFormatFactoryClass();

		/*   */
		String[] imports = report.getImports();
		if (imports != null && imports.length > 0)
		{
			importsSet = new HashSet<String>(imports.length);
			importsSet.addAll(Arrays.asList(imports));
		}

		/*   */
		factory.setDefaultStyleProvider(this);

		copyTemplates(report, factory);

		/*   */
		defaultStyle = factory.getStyle(report.getDefaultStyle());

		/*   */
		JRStyle[] jrStyles = report.getStyles();
		if (jrStyles != null && jrStyles.length > 0)
		{
			styles = new JRStyle[jrStyles.length];
			for(int i = 0; i < styles.length; i++)
			{
				styles[i] = factory.getStyle(jrStyles[i]);
			}
		}

		mainDataset = factory.getDataset(report.getMainDataset());

		JRDataset[] datasetArray = report.getDatasets();
		if (datasetArray != null && datasetArray.length > 0)
		{
			datasets = new JRDataset[datasetArray.length];
			for (int i = 0; i < datasets.length; i++)
			{
				datasets[i] = factory.getDataset(datasetArray[i]);
			}
		}

		background = factory.getBand(report.getBackground());
		title = factory.getBand(report.getTitle());
		pageHeader = factory.getBand(report.getPageHeader());
		columnHeader = factory.getBand(report.getColumnHeader());
		detailSection = factory.getSection(report.getDetailSection());
		columnFooter = factory.getBand(report.getColumnFooter());
		pageFooter = factory.getBand(report.getPageFooter());
		lastPageFooter = factory.getBand(report.getLastPageFooter());
		summary = factory.getBand(report.getSummary());
		noData = factory.getBand(report.getNoData());
	}


	protected void copyTemplates(JRReport report, JRBaseObjectFactory factory)
	{
		JRReportTemplate[] reportTemplates = report.getTemplates();
		if (reportTemplates == null || reportTemplates.length == 0)
		{
			templates = null;
		}
		else
		{
			templates = new JRReportTemplate[reportTemplates.length];
			for (int i = 0; i < reportTemplates.length; i++)
			{
				templates[i] = factory.getReportTemplate(reportTemplates[i]);
			}
		}
	}

	public JRBaseReport(JRReport report)
	{
		this(report, (JRExpressionCollector) null);
	}


	/**
	 *
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 *
	 */
	public int getColumnCount()
	{
		return columnCount;
	}

	/**
	 *
	 */
	public PrintOrderEnum getPrintOrderValue()
	{
		return printOrderValue;
	}

	/**
	 *
	 */
	public RunDirectionEnum getColumnDirection()
	{
		return columnDirection;
	}

	/**
	 *
	 */
	public int getPageWidth()
	{
		return pageWidth;
	}

	/**
	 *
	 */
	public int getPageHeight()
	{
		return pageHeight;
	}

	/**
	 *
	 */
	public OrientationEnum getOrientationValue()
	{
		return orientationValue;
	}

	/**
	 *
	 */
	public WhenNoDataTypeEnum getWhenNoDataTypeValue()
	{
		return whenNoDataTypeValue;
	}

	/**
	 *
	 */
	public void setWhenNoDataType(WhenNoDataTypeEnum whenNoDataTypeValue)
	{
		Object old = this.whenNoDataTypeValue;
		this.whenNoDataTypeValue = whenNoDataTypeValue;
		getEventSupport().firePropertyChange(PROPERTY_WHEN_NO_DATA_TYPE, old, whenNoDataTypeValue);
	}

	/**
	 *
	 */
	public int getColumnWidth()
	{
		return columnWidth;
	}

	/**
	 *
	 */
	public int getColumnSpacing()
	{
		return columnSpacing;
	}

	/**
	 *
	 */
	public int getLeftMargin()
	{
		return leftMargin;
	}

	/**
	 *
	 */
	public int getRightMargin()
	{
		return rightMargin;
	}

	/**
	 *
	 */
	public int getTopMargin()
	{
		return topMargin;
	}

	/**
	 *
	 */
	public int getBottomMargin()
	{
		return bottomMargin;
	}

	/**
	 *
	 */
	public boolean isTitleNewPage()
	{
		return isTitleNewPage;
	}

	/**
	 *
	 */
	public boolean isSummaryNewPage()
	{
		return isSummaryNewPage;
	}

	/**
	 *
	 */
	public boolean isSummaryWithPageHeaderAndFooter()
	{
		return isSummaryWithPageHeaderAndFooter;
	}

	/**
	 *
	 */
	public boolean isFloatColumnFooter()
	{
		return isFloatColumnFooter;
	}

	/**
	 *
	 */
	public String getScriptletClass()
	{
		return mainDataset.getScriptletClass();
	}

	/**
	 *
	 */
	public String getFormatFactoryClass()
	{
		return formatFactoryClass;
	}

	/**
	 *
	 */
	public String getResourceBundle()
	{
		return mainDataset.getResourceBundle();
	}

	/**
	 *
	 */
	public String[] getPropertyNames()
	{
		return mainDataset.getPropertiesMap().getPropertyNames();
	}

	/**
	 *
	 */
	public String getProperty(String propName)
	{
		return mainDataset.getPropertiesMap().getProperty(propName);
	}

	/**
	 *
	 */
	public void setProperty(String propName, String value)
	{
		mainDataset.getPropertiesMap().setProperty(propName, value);
	}

	/**
	 *
	 */
	public void removeProperty(String propName)
	{
		mainDataset.getPropertiesMap().removeProperty(propName);
	}

	/**
	 *
	 */
	public String[] getImports()
	{
		if (importsSet != null)
		{
			return importsSet.toArray(new String[importsSet.size()]);
		}
		return null;
	}

	/**
	 *
	 */
	public JRStyle getDefaultStyle()
	{
		return defaultStyle;
	}

	/**
	 *
	 */
	public JRStyle[] getStyles()
	{
		return styles;
	}

	/**
	 * Gets an array of report scriptlets (excluding the scriptletClass one).
	 */
	public JRScriptlet[] getScriptlets()
	{
		return mainDataset.getScriptlets();
	}

	/**
	 * Gets an array of report parameters (including built-in ones).
	 */
	public JRParameter[] getParameters()
	{
		return mainDataset.getParameters();
	}

	/**
	 *
	 */
	public JRQuery getQuery()
	{
		return mainDataset.getQuery();
	}

	/**
	 *  Gets an array of report fields.
	 */
	public JRField[] getFields()
	{
		return mainDataset.getFields();
	}

	/**
	 *  Gets an array of sort report fields.
	 */
	public JRSortField[] getSortFields()
	{
		return mainDataset.getSortFields();
	}

	/**
	 * Gets an array of report variables.
	 */
	public JRVariable[] getVariables()
	{
		return mainDataset.getVariables();
	}

	/**
	 *
	 */
	public JRGroup[] getGroups()
	{
		return mainDataset.getGroups();
	}

	/**
	 *
	 */
	public JRBand getBackground()
	{
		return background;
	}

	/**
	 *
	 */
	public JRBand getTitle()
	{
		return title;
	}

	/**
	 *
	 */
	public JRBand getPageHeader()
	{
		return pageHeader;
	}

	/**
	 *
	 */
	public JRBand getColumnHeader()
	{
		return columnHeader;
	}

	/**
	 *
	 */
	public JRSection getDetailSection()
	{
		return detailSection;
	}

	/**
	 *
	 */
	public JRBand getColumnFooter()
	{
		return columnFooter;
	}

	/**
	 *
	 */
	public JRBand getPageFooter()
	{
		return pageFooter;
	}

	/**
	 *
	 */
	public JRBand getLastPageFooter()
	{
		return lastPageFooter;
	}

	/**
	 *
	 */
	public JRBand getSummary()
	{
		return summary;
	}


	/**
	 *
	 */
	public WhenResourceMissingTypeEnum getWhenResourceMissingTypeValue()
	{
		return mainDataset.getWhenResourceMissingTypeValue();
	}

	/**
	 *
	 */
	public void setWhenResourceMissingType(WhenResourceMissingTypeEnum whenResourceMissingType)
	{
		mainDataset.setWhenResourceMissingType(whenResourceMissingType);
	}


	public JRDataset getMainDataset()
	{
		return mainDataset;
	}


	public JRDataset[] getDatasets()
	{
		return datasets;
	}


	public boolean isIgnorePagination()
	{
		return ignorePagination;
	}

	public boolean hasProperties()
	{
		return mainDataset.hasProperties();
	}

	public JRPropertiesMap getPropertiesMap()
	{
		return mainDataset.getPropertiesMap();
	}

	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}

	public JRReportTemplate[] getTemplates()
	{
		return templates;
	}

	/**
	 * @return the noData
	 */
	public JRBand getNoData() {
		return noData;
	}
	
	/**
	 *
	 */
	public JRBand[] getAllBands()
	{
		List<JRBand> bands = new ArrayList<JRBand>();
		
		addBand(title, bands);
		addBand(pageHeader, bands);
		addBand(columnHeader, bands);

		JRGroup[] groups = mainDataset.getGroups();
		if (groups != null)
		{
			for (JRGroup group : groups)
			{
				addBands(group.getGroupHeaderSection(), bands);
				addBands(group.getGroupFooterSection(), bands);
			}
		}

		addBands(detailSection, bands);
		
		addBand(columnFooter, bands);
		addBand(pageFooter, bands);
		addBand(lastPageFooter, bands);
		addBand(summary, bands);
		addBand(noData, bands);
		
		return bands.toArray(new JRBand[bands.size()]);
	}

	/**
	 *
	 */
	private void addBand(JRBand band, List<JRBand> bands)
	{
		if (band != null)
		{
			bands.add(band);
		}
	}

	/**
	 *
	 */
	private void addBands(JRSection section, List<JRBand> bands)
	{
		if (section != null)
		{
			for (JRBand band : section.getBands())
			{
				addBand(band, bands);
			}
		}
	}

	public UUID getUUID()
	{
		return mainDataset.getUUID();
	}
	
	private transient JRPropertyChangeSupport eventSupport;//FIXMECLONE cloneable for reset?
	
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private JRBand detail;
//	/**
//	 * @deprecated
//	 */
//	private byte whenNoDataType;
//	/**
//	 * @deprecated
//	 */
//	private byte printOrder;
//	/**
//	 * @deprecated
//	 */
//	private byte orientation;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (detail != null)
//		{
//			detailSection = new JRBaseSection(detail);
//			detail = null;
//		}
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			whenNoDataTypeValue = WhenNoDataTypeEnum.getByValue(whenNoDataType);
//			printOrderValue = PrintOrderEnum.getByValue(printOrder);
//			orientationValue = OrientationEnum.getByValue(orientation);
//		}
//	}

}
