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

import org.oss.pdfreporter.engine.JRCommonText;
import org.oss.pdfreporter.engine.design.JRDesignTextElement;
import org.oss.pdfreporter.engine.type.HorizontalAlignEnum;
import org.oss.pdfreporter.engine.type.LineSpacingEnum;
import org.oss.pdfreporter.engine.type.RotationEnum;
import org.oss.pdfreporter.engine.type.VerticalAlignEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTextElementFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRTextElementFactory extends JRBaseFactory
{
	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRDesignTextElement textElement = (JRDesignTextElement)digester.peek();

		HorizontalAlignEnum horizontalAlignment = HorizontalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_textAlignment));
		if (horizontalAlignment != null)
		{
			textElement.setHorizontalAlignment(horizontalAlignment);
		}

		VerticalAlignEnum verticalAlignment = VerticalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_verticalAlignment));
		if (verticalAlignment != null)
		{
			textElement.setVerticalAlignment(verticalAlignment);
		}

		RotationEnum rotation = RotationEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_rotation));
		if (rotation != null)
		{
			textElement.setRotation(rotation);
		}

		LineSpacingEnum lineSpacing = LineSpacingEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_lineSpacing));
		if (lineSpacing != null)
		{
			textElement.getParagraph().setLineSpacing(lineSpacing);
		}

		textElement.setMarkup(atts.getValue(JRXmlConstants.ATTRIBUTE_markup));

		String isStyledText = atts.getValue(JRXmlConstants.ATTRIBUTE_isStyledText);
		if (isStyledText != null && isStyledText.length() > 0)
		{
			textElement.setMarkup(Boolean.valueOf(isStyledText) ? JRCommonText.MARKUP_STYLED_TEXT : JRCommonText.MARKUP_NONE);
		}

		return textElement;
	}
	

}
