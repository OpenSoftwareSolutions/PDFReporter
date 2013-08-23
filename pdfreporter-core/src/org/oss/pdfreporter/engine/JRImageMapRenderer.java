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
package org.oss.pdfreporter.engine;

import java.util.List;

import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.pdf.IPage;



/**
 * Image renderer able to produce image maps.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRImageMapRenderer.java 5180 2012-03-29 13:23:12Z teodord $
 * @deprecated Replaced by {@link ImageMapRenderable}.
 */
public interface JRImageMapRenderer extends JRRenderable
{

	/**
	 * Returns the list of {@link JRPrintImageAreaHyperlink image map areas}.
	 *
	 * @deprecated Replaced by {@link #renderWithHyperlinks(IPage, IRectangle)}
	 * @param renderingArea the area on which the image would be rendered
	 * @return a list of {@link JRPrintImageAreaHyperlink JRPrintImageAreaHyperlink} instances.
	 * @throws JRException
	 */
	List<JRPrintImageAreaHyperlink> getImageAreaHyperlinks(IRectangle renderingArea) throws JRException;

	/**
	 * Indicates whether the renderer actually includes any image map areas.
	 * 
	 * @return whether the renderer actually includes any image map areas
	 */
	boolean hasImageAreaHyperlinks();
	
	/**
	 * Returns the list of {@link JRPrintImageAreaHyperlink image map areas}.
	 * 
	 * @param rectangle the area on which the image would be rendered
	 * @return a list of {@link JRPrintImageAreaHyperlink JRPrintImageAreaHyperlink} instances.
	 * @throws JRException
	 */
	public List<JRPrintImageAreaHyperlink> renderWithHyperlinks(IPage page, IRectangle rectangle) throws JRException;

}
