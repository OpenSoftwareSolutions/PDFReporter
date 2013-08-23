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


import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.JRPrintGraphicElement;
import org.oss.pdfreporter.engine.type.FillEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.geometry.IColor;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBasePrintGraphicElement.java 5180 2012-03-29 13:23:12Z teodord $
 */
public abstract class JRBasePrintGraphicElement extends JRBasePrintElement implements JRPrintGraphicElement
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected JRPen linePen;
	protected FillEnum fillValue;


	/**
	 *
	 */
	public JRBasePrintGraphicElement(JRDefaultStyleProvider defaultStyleProvider)
	{
		super(defaultStyleProvider);

		linePen = new JRBasePen(this);
	}


	/**
	 *
	 */
	public JRPen getLinePen()
	{
		return linePen;
	}
		
	/**
	 *
	 */
	public void copyPen(JRPen linePen)
	{
		this.linePen = linePen.clone(this);
	}

	/**
	 *
	 */
	public FillEnum getFillValue()
	{
		return JRStyleResolver.getFillValue(this);
	}

	/**
	 *
	 */
	public FillEnum getOwnFillValue()
	{
		return this.fillValue;
	}


	/**
	 *
	 */
	public void setFill(FillEnum fillValue)
	{
		this.fillValue = fillValue;
	}
		

	/**
	 * 
	 */
	public Float getDefaultLineWidth() 
	{
		return JRPen.LINE_WIDTH_1;
	}

	/**
	 * 
	 */
	public IColor getDefaultLineColor() 
	{
		return getForecolor();
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private Byte pen;
//	/**
//	 * @deprecated
//	 */
//	private Byte fill;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			fillValue = FillEnum.getByValue(fill);
//			fill = null;
//		}
//		if (linePen == null)
//		{
//			linePen = new JRBasePen(this);
//			JRPenUtil.setLinePenFromPen(pen, linePen);
//			pen = null;
//		}
//	}
		
}
