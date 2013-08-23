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
package org.oss.pdfreporter.engine.util;

import org.oss.pdfreporter.crosstabs.JRCrosstab;
import org.oss.pdfreporter.engine.JRBreak;
import org.oss.pdfreporter.engine.JRComponentElement;
import org.oss.pdfreporter.engine.JRElementGroup;
import org.oss.pdfreporter.engine.JREllipse;
import org.oss.pdfreporter.engine.JRFrame;
import org.oss.pdfreporter.engine.JRGenericElement;
import org.oss.pdfreporter.engine.JRImage;
import org.oss.pdfreporter.engine.JRLine;
import org.oss.pdfreporter.engine.JRRectangle;
import org.oss.pdfreporter.engine.JRStaticText;
import org.oss.pdfreporter.engine.JRSubreport;
import org.oss.pdfreporter.engine.JRTextField;
import org.oss.pdfreporter.engine.JRVisitor;


/**
 * Abstract implementation of {@link JRVisitor} that has empty visit methods for
 * all visitable types.
 * 
 * This class can be used as base class by visitors that do not want to implement
 * all methods.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRVisitorSupport.java 4595 2011-09-08 15:55:10Z teodord $
 */
public abstract class JRVisitorSupport implements JRVisitor
{

	public void visitBreak(JRBreak breakElement)
	{
		// NOOP
	}

	public void visitCrosstab(JRCrosstab crosstab)
	{
		// NOOP
	}

	public void visitElementGroup(JRElementGroup elementGroup)
	{
		// NOOP
	}

	public void visitEllipse(JREllipse ellipse)
	{
		// NOOP
	}

	public void visitFrame(JRFrame frame)
	{
		// NOOP
	}

	public void visitImage(JRImage image)
	{
		// NOOP
	}

	public void visitLine(JRLine line)
	{
		// NOOP
	}

	public void visitRectangle(JRRectangle rectangle)
	{
		// NOOP
	}

	public void visitStaticText(JRStaticText staticText)
	{
		// NOOP
	}

	public void visitSubreport(JRSubreport subreport)
	{
		// NOOP
	}

	public void visitTextField(JRTextField textField)
	{
		// NOOP
	}

	public void visitComponentElement(JRComponentElement componentElement)
	{
		// NOOP
	}

	public void visitGenericElement(JRGenericElement element)
	{
		// NOOP
	}

}
