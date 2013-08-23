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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPrintElementContainer;
import org.oss.pdfreporter.engine.JRPrintFrame;
import org.oss.pdfreporter.engine.PrintElementVisitor;
import org.oss.pdfreporter.geometry.IColor;


/**
 * Implementation of {@link org.oss.pdfreporter.engine.JRPrintFrame JRPrintFrame} that uses
 * {@link org.oss.pdfreporter.engine.fill.JRTemplateFrame template frames} to store common
 * attributes. 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRTemplatePrintFrame.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRTemplatePrintFrame extends JRTemplatePrintElement implements JRPrintFrame, JRPrintElementContainer
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private List<JRPrintElement> elements;

	/**
	 * Creates a print frame element.
	 * 
	 * @param templateFrame the template frame that the element will use
	 * @deprecated provide a source Id via {@link #JRTemplatePrintFrame(JRTemplateFrame, int)}
	 */
	public JRTemplatePrintFrame(JRTemplateFrame templateFrame)
	{
		super(templateFrame);
		
		elements = new ArrayList<JRPrintElement>();
	}

	/**
	 * Creates a print frame element.
	 * 
	 * @param templateFrame the template frame that the element will use
	 * @param sourceElementId the Id of the source element
	 */
	public JRTemplatePrintFrame(JRTemplateFrame templateFrame, int sourceElementId)
	{
		super(templateFrame, sourceElementId);
		
		elements = new ArrayList<JRPrintElement>();
	}

	public List<JRPrintElement> getElements()
	{
		return elements;
	}

	public void addElement(JRPrintElement element)
	{
		elements.add(element);
	}

	public void addElements(Collection<? extends JRPrintElement> elements)
	{
		this.elements.addAll(elements);
	}

	/**
	 *
	 */
	public JRLineBox getLineBox()
	{
		return ((JRTemplateFrame)template).getLineBox();
	}
		
	/**
	 * 
	 */
	public IColor getDefaultLineColor() 
	{
		return getForecolor();
	}

	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		visitor.visit(this, arg);
	}
}
