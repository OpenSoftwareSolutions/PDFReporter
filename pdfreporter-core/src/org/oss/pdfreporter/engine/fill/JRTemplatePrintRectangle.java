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
import org.oss.pdfreporter.engine.JRPrintRectangle;
import org.oss.pdfreporter.engine.PrintElementVisitor;


/**
 * Implementation of {@link org.oss.pdfreporter.engine.JRPrintRectangle} that uses
 * a {@link org.oss.pdfreporter.engine.fill.JRTemplateRectangle} instance to
 * store common attributes. 
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplatePrintRectangle.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRTemplatePrintRectangle extends JRTemplatePrintGraphicElement implements JRPrintRectangle
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * Creates a print rectangle element.
	 * 
	 * @param rectangle the template rectangle that the element will use
	 * @deprecated provide a source Id via {@link #JRTemplatePrintRectangle(JRTemplateRectangle, int)}
	 */
	public JRTemplatePrintRectangle(JRTemplateRectangle rectangle)
	{
		super(rectangle);
	}

	/**
	 * Creates a print rectangle element.
	 * 
	 * @param rectangle the template rectangle that the element will use
	 * @param sourceElementId the Id of the source element
	 */
	public JRTemplatePrintRectangle(JRTemplateRectangle rectangle, int sourceElementId)
	{
		super(rectangle, sourceElementId);
	}


	/**
	 *
	 */
	public int getRadius()
	{
		return ((JRTemplateRectangle)this.template).getRadius();
	}

	/**
	 *
	 */
	public Integer getOwnRadius()
	{
		return ((JRTemplateRectangle)this.template).getOwnRadius();
	}

	/**
	 *
	 */
	public void setRadius(int radius)
	{
	}

	/**
	 *
	 */
	public void setRadius(Integer radius)
	{
	}

	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		visitor.visit(this, arg);
	}


}
