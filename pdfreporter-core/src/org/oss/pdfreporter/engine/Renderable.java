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

/*
 * Contributors:
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 */
package org.oss.pdfreporter.engine;


import org.oss.pdfreporter.engine.type.ImageTypeEnum;
import org.oss.pdfreporter.engine.type.RenderableTypeEnum;
import org.oss.pdfreporter.geometry.IDimension;
import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.pdf.IPage;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: Renderable.java 5180 2012-03-29 13:23:12Z teodord $
 */
@SuppressWarnings("deprecation")
public interface Renderable extends JRRenderable
{
	/**
	 * Specifies the image resolution in dots-per-inch, for the images created by the engine when rasterizing SVGs or when clipping other renderers.
	 */
	public static final String PROPERTY_IMAGE_DPI = JRPropertiesUtil.PROPERTY_PREFIX + "image.dpi";

	/**
	 *
	 */
	public String getId();

	/**
	 *
	 */
	public RenderableTypeEnum getTypeValue();

	/**
	 *
	 */
	public ImageTypeEnum getImageTypeValue();

	/**
	 *
	 */
	public IDimension getDimension(JasperReportsContext jasperReportsContext) throws JRException;


	/**
	 *
	 */
	public IImage getImage(JasperReportsContext jasperReportsContext) throws JRException;


	/**
	 *
	 */
	public void render(JasperReportsContext jasperReportsContext, IPage page, IRectangle rectangle) throws JRException;
}
