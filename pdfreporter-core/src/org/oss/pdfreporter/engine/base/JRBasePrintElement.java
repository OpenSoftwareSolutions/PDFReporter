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

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JROrigin;
import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.PrintElementVisitor;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.uses.java.util.UUID;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBasePrintElement.java 5542 2012-08-03 12:39:11Z teodord $
 */
public class JRBasePrintElement implements JRPrintElement, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected UUID uuid;
	protected JROrigin origin;
	protected String key;
	
	/**
	 *
	 */
	protected ModeEnum modeValue;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected IColor forecolor;
	protected IColor backcolor;

	protected JRDefaultStyleProvider defaultStyleProvider;
	protected JRStyle style;
	
	private JRPropertiesMap propertiesMap;
	private int sourceElementId;

	
	/**
	 *
	 */
	public JRBasePrintElement(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}
	

	/**
	 *
	 */
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}
	
	/**
	 *
	 */
	public UUID getUUID()
	{
		return uuid;
	}

	/**
	 *
	 */
	public void setUUID(UUID uuid)
	{
		this.uuid = uuid;
	}

	/**
	 *
	 */
	public JROrigin getOrigin()
	{
		return origin;
	}
	
	/**
	 *
	 */
	public void setOrigin(JROrigin origin)
	{
		this.origin = origin;
	}

	/**
	 *
	 */
	public JRStyle getStyle()
	{
		return style;
	}
	
	/**
	 *
	 */
	public void setStyle(JRStyle style)
	{
		this.style = style;
	}

	/**
	 *
	 */
	public ModeEnum getModeValue()
	{
		return JRStyleResolver.getMode(this, ModeEnum.OPAQUE);
	}

	/**
	 *
	 */
	public ModeEnum getOwnModeValue()
	{
		return modeValue;
	}

	/**
	 *
	 */
	public void setMode(ModeEnum modeValue)
	{
		this.modeValue = modeValue;
	}

	/**
	 *
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 *
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 *
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 *
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 *
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	/**
	 *
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	/**
	 *
	 */
	public int getHeight()
	{
		return this.height;
	}
	
	/**
	 *
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	/**
	 *
	 */
	public IColor getForecolor()
	{
		return JRStyleResolver.getForecolor(this);
	}
	
	/**
	 *
	 */
	public IColor getOwnForecolor()
	{
		return forecolor;
	}
	
	/**
	 *
	 */
	public void setForecolor(IColor forecolor)
	{
		this.forecolor = forecolor;
	}
	
	/**
	 *
	 */
	public IColor getBackcolor()
	{
		return JRStyleResolver.getBackcolor(this);
	}

	/**
	 *
	 */
	public IColor getOwnBackcolor()
	{
		return backcolor;
	}

	/**
	 *
	 */
	public void setBackcolor(IColor backcolor)
	{
		this.backcolor = backcolor;
	}


	
	public String getKey()
	{
		return key;
	}


	/**
	 * Sets the print element key.
	 * 
	 * @param key the element key
	 * @see #getKey()
	 */
	public void setKey(String key)
	{
		this.key = key;
	}


	/**
	 * Returns null as external style references are not allowed for print objects.
	 */
	public String getStyleNameReference()
	{
		return null;
	}
	

	public synchronized boolean hasProperties()
	{
		return propertiesMap != null && propertiesMap.hasProperties();
	}

	public synchronized JRPropertiesMap getPropertiesMap()
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

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private Byte mode;
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
//	}


	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		throw new UnsupportedOperationException();
	}

	public int getSourceElementId()
	{
		return sourceElementId;
	}

	/**
	 * Sets the source/fill element Id for the print element.
	 * 
	 * @param sourceElementId
	 * @see #getSourceElementId()
	 */
	public void setSourceElementId(int sourceElementId)
	{
		this.sourceElementId = sourceElementId;
	}

}
