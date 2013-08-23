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
package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.JRPrintGraphicElement;
import org.oss.pdfreporter.engine.type.FillEnum;
import org.oss.pdfreporter.geometry.IColor;



/**
 * Base implementation of {@link org.oss.pdfreporter.engine.JRPrintGraphicElement} that uses
 * a {@link org.oss.pdfreporter.engine.fill.JRTemplateGraphicElement} instance to
 * store common attributes. 
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplatePrintGraphicElement.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRTemplatePrintGraphicElement extends JRTemplatePrintElement implements JRPrintGraphicElement
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;


	/**
	 *
	 * @deprecated provide a source Id via {@link #JRTemplatePrintGraphicElement(JRTemplateGraphicElement, int)}
	 */
	protected JRTemplatePrintGraphicElement(JRTemplateGraphicElement graphicElement)
	{
		super(graphicElement);
	}

	/**
	 *
	 * @param sourceElementId the Id of the source element
	 */
	protected JRTemplatePrintGraphicElement(JRTemplateGraphicElement graphicElement, int sourceElementId)
	{
		super(graphicElement, sourceElementId);
	}

	/**
	 *
	 */
	public JRPen getLinePen()
	{
		return ((JRTemplateGraphicElement)template).getLinePen();
	}
		

	/**
	 * 
	 */
	public FillEnum getFillValue()
	{
		return ((JRTemplateGraphicElement)this.template).getFillValue();
	}

	/**
	 * 
	 */
	public FillEnum getOwnFillValue()
	{
		return ((JRTemplateGraphicElement)this.template).getOwnFillValue();
	}

	/**
	 *
	 */
	public void setFill(FillEnum fill)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 */
	public Float getDefaultLineWidth() 
	{
		return ((JRTemplateGraphicElement)template).getDefaultLineWidth();
	}

	/**
	 * 
	 */
	public IColor getDefaultLineColor() 
	{
		return ((JRTemplateGraphicElement)template).getDefaultLineColor();
	}

	
}
