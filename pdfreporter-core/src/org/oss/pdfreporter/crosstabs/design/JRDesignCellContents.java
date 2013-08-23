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
package org.oss.pdfreporter.crosstabs.design;

import org.oss.pdfreporter.crosstabs.JRCellContents;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.base.JRBaseLineBox;
import org.oss.pdfreporter.engine.base.JRBaseStyle;
import org.oss.pdfreporter.engine.design.JRDesignElementGroup;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.geometry.IColor;



/**
 * Implementation of {@link org.oss.pdfreporter.crosstabs.JRCellContents JRCellContents} used for
 * report design.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRDesignCellContents.java 5599 2012-08-22 12:42:27Z lucianc $
 */
public class JRDesignCellContents extends JRDesignElementGroup implements JRCellContents
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_BOX = "box";
	
	public static final String PROPERTY_STYLE = "style";
	
	public static final String PROPERTY_STYLE_NAME_REFERENCE = "styleNameReference";

	protected JRDefaultStyleProvider defaultStyleProvider;
	protected JRStyle style;
	protected String styleNameReference;
	
	protected ModeEnum modeValue;
	private IColor backcolor;
	private JRLineBox lineBox;
	private int width = JRCellContents.NOT_CALCULATED;
	private int height = JRCellContents.NOT_CALCULATED;

	private JRCrosstabOrigin origin;
	
	private JRPropertiesMap propertiesMap;
	
	/**
	 * Creates an empty cell contents.
	 */
	public JRDesignCellContents()
	{
		super();
		
		lineBox = new JRBaseLineBox(this);
	}
	
	public IColor getBackcolor()
	{
		return backcolor;
	}
	
	
	/**
	 * Sets the cell background color.
	 * 
	 * @param color the background color
	 * @see JRCellContents#getBackcolor()
	 */
	public void setBackcolor(IColor color)
	{
		Object old = this.backcolor;
		backcolor = color;
		getEventSupport().firePropertyChange(JRBaseStyle.PROPERTY_BACKCOLOR, old, this.backcolor);
	}

	public JRLineBox getLineBox()
	{
		return lineBox;
	}
	
	
	public int getHeight()
	{
		return height;
	}

	
	/**
	 * Sets the computed cell height.
	 * 
	 * <p>
	 * The method should NOT be called by external code.
	 * </p>
	 * 
	 * @param height the cell height
	 * @see JRCellContents#getHeight()
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}


	/**
	 * Sets the computed cell width.
	 * 
	 * @param width the cell width
	 * @see JRCellContents#getWidth()
	 */
	protected void setWidth(int width)
	{
		this.width = width;
	}

	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}

	public JRStyle getStyle()
	{
		return style;
	}
	
	
	/**
	 * Sets the style used by this cell.
	 * <p/>
	 * The style is only used for cell background and borders and is not inherited by
	 * elements inside the cell.
	 * 
	 * @param style the style to be used
	 */
	public void setStyle(JRStyle style)
	{
		Object old = this.style;
		this.style = style;
		getEventSupport().firePropertyChange(PROPERTY_STYLE, old, this.style);
	}

	public ModeEnum getModeValue()
	{
		return modeValue;
	}
	
	/**
	 * Sets the cell transparency mode.
	 * 
	 * @param modeValue the transparency mode
	 * @see JRCellContents#getModeValue()
	 */
	public void setMode(ModeEnum modeValue)
	{
		Object old = this.modeValue;
		this.modeValue = modeValue;
		getEventSupport().firePropertyChange(JRBaseStyle.PROPERTY_MODE, old, this.modeValue);
	}

	public String getStyleNameReference()
	{
		return styleNameReference;
	}

	
	/**
	 * Set the name of the external style to be used for this cell.
	 * <p/>
	 * An external style is only effective when there is no internal style set for this cell,
	 * i.e. {@link #getStyle() getStyle()} returns <code>null</code>
	 * The external style will be resolved at fill time from the templates used in the report.
	 * 
	 * @param styleName the name of the external style
	 * @see #getStyleNameReference()
	 */
	public void setStyleNameReference(String styleName)
	{
		Object old = this.styleNameReference;
		this.styleNameReference = styleName;
		getEventSupport().firePropertyChange(PROPERTY_STYLE_NAME_REFERENCE, old, this.styleNameReference);
	}
	
	public JRCrosstabOrigin getOrigin()
	{
		return origin;
	}
	
	public void setOrigin(JRCrosstabOrigin origin)
	{
		this.origin = origin;
	}
	
	/**
	 * 
	 */
	public IColor getDefaultLineColor() 
	{
		return IColor.black;
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRDesignCellContents clone = (JRDesignCellContents) super.clone();
		clone.lineBox = lineBox == null ? null : (JRLineBox) lineBox.clone(clone);
		clone.propertiesMap = JRPropertiesMap.getPropertiesClone(this);
		return clone;
	}

	// TODO: Daniel (19.4.2013) - Removed, unused
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private Byte mode;
//	/**
//	 * @deprecated
//	 */
//	private net.sf.jasperreports.engine.JRBox box;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			modeValue = ModeEnum.getByValue(mode);
//			
//			mode = null;
//		}
//
//		if (lineBox == null)
//		{
//			lineBox = new JRBaseLineBox(this);
//			JRBoxUtil.setBoxToLineBox(
//				box,
//				lineBox
//				);
//			box = null;
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
