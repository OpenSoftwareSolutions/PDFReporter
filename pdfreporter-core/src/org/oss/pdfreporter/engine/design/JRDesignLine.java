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


import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRLine;
import org.oss.pdfreporter.engine.JRVisitor;
import org.oss.pdfreporter.engine.base.JRBaseLine;
import org.oss.pdfreporter.engine.type.LineDirectionEnum;


/**
 * The actual implementation of a graphic element representing a straight line, used at design time.
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignLine.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRDesignLine extends JRDesignGraphicElement implements JRLine
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected LineDirectionEnum directionValue = LineDirectionEnum.TOP_DOWN;


	/**
	 *
	 */
	public JRDesignLine()
	{
		super(null);
	}
		
	/**
	 *
	 */
	public JRDesignLine(JRDefaultStyleProvider defaultStyleProvider)
	{
		super(defaultStyleProvider);
	}
		

	/**
	 *
	 */
	public void setWidth(int width)
	{
		if (width == 0)
		{
			width = 1;
		}
		
		super.setWidth(width);
	}

	/**
	 *
	 */
	public void setHeight(int height)
	{
		if (height == 0)
		{
			height = 1;
		}
		
		super.setHeight(height);
	}

	/**
	 * 
	 */
	public LineDirectionEnum getDirectionValue()
	{
		return directionValue;
	}

	/**
	 * 
	 */
	public void setDirection(LineDirectionEnum directionValue)
	{
		LineDirectionEnum old = this.directionValue;
		this.directionValue = directionValue;
		getEventSupport().firePropertyChange(JRBaseLine.PROPERTY_DIRECTION, old, this.directionValue);
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public void visit(JRVisitor visitor)
	{
		visitor.visitLine(this);
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte direction;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			directionValue = LineDirectionEnum.getByValue(direction);
//		}
//		
//	}

}
