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
package org.oss.pdfreporter.engine.xml;

import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.base.JRBasePrintImage;
import org.oss.pdfreporter.engine.type.HorizontalAlignEnum;
import org.oss.pdfreporter.engine.type.OnErrorTypeEnum;
import org.oss.pdfreporter.engine.type.ScaleImageEnum;
import org.oss.pdfreporter.engine.type.VerticalAlignEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintImageFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRPrintImageFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JasperPrint jasperPrint = (JasperPrint)digester.peek(digester.getCount() - 2);

		JRBasePrintImage image = new JRBasePrintImage(jasperPrint.getDefaultStyleProvider());

		// get image attributes
		ScaleImageEnum scaleImage = ScaleImageEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_scaleImage));
		if (scaleImage != null)
		{
			image.setScaleImage(scaleImage);
		}

		HorizontalAlignEnum horizontalAlignment = HorizontalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_hAlign));
		if (horizontalAlignment != null)
		{
			image.setHorizontalAlignment(horizontalAlignment);
		}

		VerticalAlignEnum verticalAlignment = VerticalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_vAlign));
		if (verticalAlignment != null)
		{
			image.setVerticalAlignment(verticalAlignment);
		}

		String isLazy = atts.getValue(JRXmlConstants.ATTRIBUTE_isLazy);
		if (isLazy != null && isLazy.length() > 0)
		{
			image.setLazy(Boolean.valueOf(isLazy).booleanValue());
		}

		OnErrorTypeEnum onErrorType = OnErrorTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_onErrorType));
		if (onErrorType != null)
		{
			image.setOnErrorType(onErrorType);
		}

		image.setLinkType(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkType));
		image.setLinkTarget(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkTarget));
		image.setAnchorName(atts.getValue(JRXmlConstants.ATTRIBUTE_anchorName));
		image.setHyperlinkReference(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkReference));
		image.setHyperlinkAnchor(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkAnchor));
		
		String hyperlinkPage = atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkPage);
		if (hyperlinkPage != null)
		{
			image.setHyperlinkPage(Integer.valueOf(hyperlinkPage));
		}
		
		image.setHyperlinkTooltip(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkTooltip));

		String bookmarkLevelAttr = atts.getValue(JRXmlConstants.ATTRIBUTE_bookmarkLevel);
		if (bookmarkLevelAttr != null)
		{
			image.setBookmarkLevel(Integer.parseInt(bookmarkLevelAttr));
		}

		return image;
	}
	

}
