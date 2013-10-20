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

import java.io.IOException;

import org.oss.pdfreporter.engine.type.ImageTypeEnum;
import org.oss.pdfreporter.engine.type.RenderableTypeEnum;
import org.oss.pdfreporter.geometry.IDimension;
import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.pdf.DocumentException;
import org.oss.pdfreporter.pdf.IPage;
import org.oss.pdfreporter.pdf.IPage.ScaleMode;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRImageRenderer.java 5397 2012-05-21 01:10:02Z teodord $
 */
public class JRImageRenderer extends JRAbstractRenderer
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private IImage image = null;
	private String imageLocation = null;
	private ImageTypeEnum imageTypeValue = ImageTypeEnum.UNKNOWN;



	/**
	 *
	 */
	protected JRImageRenderer(IImage image)
	{
		this.image = image;
					
	}


	/**
	 *
	 */
	protected JRImageRenderer(String imageLocation)
	{
		this.imageLocation = imageLocation;
	}


	/**
	 *
	 */
	public static JRImageRenderer getInstance(IImage image)
	{
		return new JRImageRenderer(image);
	}

	public static JRImageRenderer getInstance(String location)
	{
		return new JRImageRenderer(location);
	}
	
	/**
	 *
	 */
	public String getImageLocation()
	{
		return imageLocation;
	}

	/**
	 *
	 */
	public RenderableTypeEnum getTypeValue()
	{
		return RenderableTypeEnum.IMAGE;
	}
	
	
	/**
	 *
	 */
	public ImageTypeEnum getImageTypeValue()
	{
		return imageTypeValue;
	}


	/**
	 *
	 */
	public IDimension getDimension(JasperReportsContext jasperReportsContext) throws JRException
	{
		IImage img = getImage(jasperReportsContext);
		return ApiRegistry.getGeometryFactory().newDimension(img.getWidth(), img.getHeight());
	}


	@Override
	public IImage getImage(JasperReportsContext jasperReportsContext)
			throws JRException
	{
		if (image == null)
		{
			try {
				IURL url = FileResourceLoader.getURL(imageLocation);
				if (url==null) {
					throw new RuntimeException("Image file: " + imageLocation + " not found.");
				}
				// TODO (28.06.2013, Donat, Open Software Solutions): Define and read compression parameter to pass to image loading
				image = ApiRegistry.getImageFactory().getImageManager().loadImage(url.getPath());
			} catch (IOException e) {
				throw new JRException(e);
			}
		}

		return image;
	}




	/**
	 *
	 */
	public void render(JasperReportsContext jasperReportsContext, IPage page, IRectangle rectangle) throws JRException
	{
		IImage img = getImage(jasperReportsContext);
		try {
			page.draw(img, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), ScaleMode.NONE);
		} catch (DocumentException e) {
			throw new JRException(e);
		}
	}


}
