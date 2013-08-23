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

import org.oss.pdfreporter.engine.Deduplicable;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.JRPenContainer;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRStyleContainer;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.LineStyleEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.engine.util.ObjectUtils;
import org.oss.pdfreporter.geometry.IColor;




/**
 * This is useful for drawing borders around text elements and images. Boxes can have borders and paddings, which can
 * have different width and colour on each side of the element.
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBasePen.java 4647 2011-10-06 10:19:07Z lucianc $
 */
public class JRBasePen implements JRPen, Serializable, Cloneable, JRChangeEventsSupport, Deduplicable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_LINE_WIDTH = "lineWidth";
	
	public static final String PROPERTY_LINE_STYLE = "lineStyle";
	
	public static final String PROPERTY_LINE_COLOR = "lineColor";
	

	protected JRPenContainer penContainer;

	/**
	 *
	 */
	protected Float lineWidth;
	protected LineStyleEnum lineStyleValue;
	protected IColor lineColor;

	
	/**
	 *
	 */
	public JRBasePen(JRPenContainer penContainer)
	{
		super();
		
		this.penContainer = penContainer;
	}
	
	
	/**
	 *
	 */
	public JRStyleContainer getStyleContainer()
	{
		return penContainer;
	}

	/**
	 *
	 */
	public Float getLineWidth()
	{
		return JRStyleResolver.getLineWidth(this, penContainer.getDefaultLineWidth());
	}

	/**
	 *
	 */
	public Float getOwnLineWidth()
	{
		return lineWidth;
	}

	/**
	 *
	 */
	public void setLineWidth(float lineWidth)
	{
		setLineWidth(new Float(lineWidth));
	}

	/**
	 *
	 */
	public void setLineWidth(Float lineWidth)
	{
		Object old = this.lineWidth;
		this.lineWidth = lineWidth;
		getEventSupport().firePropertyChange(PROPERTY_LINE_WIDTH, old, this.lineWidth);
	}

	/**
	 *
	 */
	public LineStyleEnum getLineStyleValue()
	{
		return JRStyleResolver.getLineStyleValue(this);
	}

	/**
	 *
	 */
	public LineStyleEnum getOwnLineStyleValue()
	{
		return lineStyleValue;
	}

	/**
	 *
	 */
	public void setLineStyle(LineStyleEnum lineStyleValue)
	{
		Object old = this.lineStyleValue;
		this.lineStyleValue = lineStyleValue;
		getEventSupport().firePropertyChange(PROPERTY_LINE_STYLE, old, this.lineStyleValue);
	}

	/**
	 *
	 */
	public IColor getLineColor()
	{
		return JRStyleResolver.getLineColor(this, penContainer.getDefaultLineColor());
	}

	/**
	 *
	 */
	public IColor getOwnLineColor()
	{
		return lineColor;
	}

	/**
	 *
	 */
	public void setLineColor(IColor lineColor)
	{
		Object old = this.lineColor;
		this.lineColor = lineColor;
		getEventSupport().firePropertyChange(PROPERTY_LINE_COLOR, old, this.lineColor);
	}


	public String getStyleNameReference()
	{
		return null;
	}

	/**
	 * 
	 */
	public JRPen clone(JRPenContainer penContainer)
	{
		JRBasePen clone = null;
		try
		{
			clone = (JRBasePen)super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		clone.penContainer = penContainer;
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
//	private Byte lineStyle;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			lineStyleValue = LineStyleEnum.getByValue(lineStyle);
//			
//			lineStyle = null;
//		}
//	}


	public int getHashCode()
	{
		ObjectUtils.HashCode hash = ObjectUtils.hash();
		hash.add(lineWidth);
		hash.add(lineStyleValue);
		hash.add(lineColor);
		return hash.getHashCode();
	}


	public boolean isIdentical(Object object)
	{
		if (this == object)
		{
			return true;
		}
		
		if (!(object instanceof JRBasePen))
		{
			return false;
		}
		
		JRBasePen pen = (JRBasePen) object;

		return 
				ObjectUtils.equals(lineWidth, pen.lineWidth)
				&& ObjectUtils.equals(lineStyleValue, pen.lineStyleValue)
				&& ObjectUtils.equals(lineColor, pen.lineColor);
	}

}
