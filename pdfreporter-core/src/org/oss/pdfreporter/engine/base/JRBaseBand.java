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

import org.oss.pdfreporter.engine.JRBand;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.SplitTypeEnum;
import org.oss.pdfreporter.engine.util.JRCloneUtils;


/**
 * Used for implementing band functionality. A report can contain the following bands: background, title,
 * summary, page header, page footer, last page footer, column header and column footer.
 * @see JRBaseSection
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseBand.java 5495 2012-07-13 09:39:25Z lucianc $
 */
public class JRBaseBand extends JRBaseElementGroup implements JRBand, JRChangeEventsSupport
{
	

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	public static final String PROPERTY_SPLIT_TYPE = "splitType";

	/**
	 *
	 */
	protected int height;
	protected SplitTypeEnum splitTypeValue;

	/**
	 *
	 */
	protected JRExpression printWhenExpression;
	
	private JRPropertiesMap propertiesMap;
	

	/**
	 *
	 */
	protected JRBaseBand(JRBand band, JRBaseObjectFactory factory)
	{
		super(band, factory);
		
		height = band.getHeight();
		splitTypeValue = band.getSplitTypeValue();

		printWhenExpression = factory.getExpression(band.getPrintWhenExpression());
		this.propertiesMap = JRPropertiesMap.getPropertiesClone(band);
	}
		

	/**
	 *
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 *
	 */
	public SplitTypeEnum getSplitTypeValue()
	{
		return splitTypeValue;
	}

	/**
	 *
	 */
	public void setSplitType(SplitTypeEnum splitTypeValue)
	{
		SplitTypeEnum old = this.splitTypeValue;
		this.splitTypeValue = splitTypeValue;
		getEventSupport().firePropertyChange(JRBaseBand.PROPERTY_SPLIT_TYPE, old, this.splitTypeValue);
	}

	/**
	 *
	 */
	public JRExpression getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}

	/**
	 *
	 */
	public Object clone() 
	{
		JRBaseBand clone = (JRBaseBand)super.clone();
		clone.printWhenExpression = JRCloneUtils.nullSafeClone(printWhenExpression);
		clone.propertiesMap = JRPropertiesMap.getPropertiesClone(this);
		clone.eventSupport = null;
		return clone;
	}
	
	private transient JRPropertyChangeSupport eventSupport;
	
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
//	private boolean isSplitAllowed = true;
//	/**
//	 * @deprecated
//	 */
//	private Byte splitType;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_5_2)
//		{
//			splitType = isSplitAllowed ? SplitTypeEnum.STRETCH.getValueByte() : SplitTypeEnum.PREVENT.getValueByte();
//		}
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)//FIXMEENUM check order of ifs for all
//		{
//			splitTypeValue = SplitTypeEnum.getByValue(splitType);
//			
//			splitType = null;
//		}
//	}

	public boolean hasProperties()
	{
		return propertiesMap != null && propertiesMap.hasProperties();
	}

	public JRPropertiesMap getPropertiesMap()
	{
		if (propertiesMap == null)
		{
			propertiesMap = new JRPropertiesMap();
		}
		return propertiesMap;
	}

	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}

}
