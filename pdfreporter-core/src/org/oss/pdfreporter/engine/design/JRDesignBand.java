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
package org.oss.pdfreporter.engine.design;


import org.oss.pdfreporter.engine.JRBand;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JROrigin;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.base.JRBaseBand;
import org.oss.pdfreporter.engine.type.SplitTypeEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignBand.java 5495 2012-07-13 09:39:25Z lucianc $
 */
public class JRDesignBand extends JRDesignElementGroup implements JRBand
{
	

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_HEIGHT = "height";

	public static final String PROPERTY_PRINT_WHEN_EXPRESSION = "printWhenExpression";

	/**
	 *
	 */
	protected int height;
	protected SplitTypeEnum splitTypeValue;

	/**
	 *
	 */
	protected JRExpression printWhenExpression;
	
	private JROrigin origin;
	
	private JRPropertiesMap propertiesMap;

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
	public void setHeight(int height)
	{
		int old = this.height;
		this.height = height;
		getEventSupport().firePropertyChange(PROPERTY_HEIGHT, old, this.height);
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
	public void setPrintWhenExpression(JRExpression expression)
	{
		Object old = this.printWhenExpression;
		this.printWhenExpression = expression;
		getEventSupport().firePropertyChange(PROPERTY_PRINT_WHEN_EXPRESSION, old, this.printWhenExpression);
	}

	/**
	 * Returns the band origin, i.e. its location/role within the report
	 * (e.g. detail/title/group header/etc).
	 * The location is automatically set when the band is inserted
	 * into the report (via one of the
	 * {@link JasperDesign#setTitle(JRBand) setTitle} /
	 * {@link JasperDesign#setPageHeader(JRBand) setPageHeader}
	 * methods).
	 * 
	 * @return the band origin
	 */
	public JROrigin getOrigin()
	{
		return origin;
	}

	void setOrigin(JROrigin origin)
	{
		this.origin = origin;
	}
	

	/**
	 *
	 */
	public Object clone() 
	{
		JRDesignBand clone = (JRDesignBand)super.clone();
		if (printWhenExpression != null)
		{
			clone.printWhenExpression = (JRExpression)printWhenExpression.clone();
		}
		if (origin != null)
		{
			clone.origin = (JROrigin)origin.clone();
		}
		clone.propertiesMap = JRPropertiesMap.getPropertiesClone(this);
		return clone;
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
