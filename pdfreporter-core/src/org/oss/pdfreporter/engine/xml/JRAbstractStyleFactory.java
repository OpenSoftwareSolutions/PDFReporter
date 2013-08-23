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
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.design.JRDesignStyle;
import org.oss.pdfreporter.engine.type.FillEnum;
import org.oss.pdfreporter.engine.type.HorizontalAlignEnum;
import org.oss.pdfreporter.engine.type.LineSpacingEnum;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.type.PenEnum;
import org.oss.pdfreporter.engine.type.RotationEnum;
import org.oss.pdfreporter.engine.type.ScaleImageEnum;
import org.oss.pdfreporter.engine.type.VerticalAlignEnum;
import org.oss.pdfreporter.engine.util.JRColorUtil;
import org.oss.pdfreporter.engine.util.JRPenUtil;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRAbstractStyleFactory.java 4595 2011-09-08 15:55:10Z teodord $
 */
public abstract class JRAbstractStyleFactory extends JRBaseFactory
{
	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRDesignStyle style = new JRDesignStyle();

		// get style name
		style.setName(atts.getValue(JRXmlConstants.ATTRIBUTE_name));

		String isDefault = atts.getValue(JRXmlConstants.ATTRIBUTE_isDefault);
		if (isDefault != null && isDefault.length() > 0)
		{
			style.setDefault(Boolean.valueOf(isDefault).booleanValue());
		}

		// get parent style
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_style) != null)
		{
			setParentStyle(style, atts.getValue(JRXmlConstants.ATTRIBUTE_style));
		}

		// set common style attributes
		setCommonStyle(style, atts);

		return style;
	}
	
	/**
	 *
	 */
	protected void setCommonStyle(JRStyle style, IAttributes atts)
	{
		// get JRElement attributes
		ModeEnum mode = ModeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_mode));
		if (mode != null)
		{
			style.setMode(mode);
		}

		String forecolor = atts.getValue(JRXmlConstants.ATTRIBUTE_forecolor);
		style.setForecolor(JRColorUtil.getColor(forecolor, null));

		String backcolor = atts.getValue(JRXmlConstants.ATTRIBUTE_backcolor);
		style.setBackcolor(JRColorUtil.getColor(backcolor, null));

		// get graphic element attributes
		PenEnum pen = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_pen));
		if (pen != null)
		{
			JRPenUtil.setLinePenFromPen(pen, style.getLinePen());
		}
		

		FillEnum fill = FillEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_fill));
		if(fill != null)
		{
			style.setFill(fill);
		}


		// get rectangle attributes
		String radius = atts.getValue(JRXmlConstants.ATTRIBUTE_radius);
		if (radius != null && radius.length() > 0)
		{
			style.setRadius(Integer.parseInt(radius));
		}

		// get image attributes
		ScaleImageEnum scaleImage = ScaleImageEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_scaleImage));
		if (scaleImage != null)
		{
			style.setScaleImage(scaleImage);
		}

		HorizontalAlignEnum horizontalAlignment = HorizontalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_hAlign));
		if (horizontalAlignment != null)
		{
			style.setHorizontalAlignment(horizontalAlignment);
		}

		VerticalAlignEnum verticalAlignment = VerticalAlignEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_vAlign));
		if (verticalAlignment != null)
		{
			style.setVerticalAlignment(verticalAlignment);
		}


		// get box attributes
		PenEnum border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_border));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, style.getLineBox().getPen());
		}

		IColor borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_borderColor), null);
		if (borderColor != null)
		{
			style.getLineBox().getPen().setLineColor(borderColor);
		}

		String padding = atts.getValue(JRXmlConstants.ATTRIBUTE_padding);
		if (padding != null && padding.length() > 0)
		{
			style.getLineBox().setPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_topBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, style.getLineBox().getTopPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_topBorderColor), IColor.black);
		if (borderColor != null)
		{
			style.getLineBox().getTopPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_topPadding);
		if (padding != null && padding.length() > 0)
		{
			style.getLineBox().setTopPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_leftBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, style.getLineBox().getLeftPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_leftBorderColor), IColor.black);
		if (borderColor != null)
		{
			style.getLineBox().getLeftPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_leftPadding);
		if (padding != null && padding.length() > 0)
		{
			style.getLineBox().setLeftPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_bottomBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, style.getLineBox().getBottomPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_bottomBorderColor), IColor.black);
		if (borderColor != null)
		{
			style.getLineBox().getBottomPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_bottomPadding);
		if (padding != null && padding.length() > 0)
		{
			style.getLineBox().setBottomPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_rightBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, style.getLineBox().getRightPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_rightBorderColor), IColor.black);
		if (borderColor != null)
		{
			style.getLineBox().getRightPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_rightPadding);
		if (padding != null && padding.length() > 0)
		{
			style.getLineBox().setRightPadding(Integer.parseInt(padding));
		}


		RotationEnum rotation = RotationEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_rotation));
		if (rotation != null)
		{
			style.setRotation(rotation);
		}

		LineSpacingEnum lineSpacing = LineSpacingEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_lineSpacing));
		if (lineSpacing != null)
		{
			style.getParagraph().setLineSpacing(lineSpacing);
		}

		style.setMarkup(atts.getValue(JRXmlConstants.ATTRIBUTE_markup));

		String isStyledText = atts.getValue(JRXmlConstants.ATTRIBUTE_isStyledText);
		if (isStyledText != null && isStyledText.length() > 0)
		{
			style.setMarkup(Boolean.valueOf(isStyledText) ? JRCommonText.MARKUP_STYLED_TEXT : JRCommonText.MARKUP_NONE);
		}

		style.setPattern(atts.getValue(JRXmlConstants.ATTRIBUTE_pattern));

		String isBlankWhenNull = atts.getValue(JRXmlConstants.ATTRIBUTE_isBlankWhenNull);
		if (isBlankWhenNull != null && isBlankWhenNull.length() > 0)
		{
			style.setBlankWhenNull(Boolean.valueOf(isBlankWhenNull));
		}

		if (atts.getValue(JRXmlConstants.ATTRIBUTE_fontName) != null)
		{
			style.setFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_fontName));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isBold) != null)
		{
			style.setBold(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isBold)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isItalic) != null)
		{
			style.setItalic(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isItalic)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isUnderline) != null)
		{
			style.setUnderline(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isUnderline)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isStrikeThrough) != null)
		{
			style.setStrikeThrough(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isStrikeThrough)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_fontSize) != null)
		{
			style.setFontSize(Integer.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_fontSize)));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_pdfFontName) != null)
		{
			style.setPdfFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfFontName));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_pdfEncoding) != null)
		{
			style.setPdfEncoding(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfEncoding));
		}
		if (atts.getValue(JRXmlConstants.ATTRIBUTE_isPdfEmbedded) != null)
		{
			style.setPdfEmbedded(Boolean.valueOf(atts.getValue(JRXmlConstants.ATTRIBUTE_isPdfEmbedded)));
		}
	}
	
	protected abstract void setParentStyle(JRDesignStyle currentStyle, String parentStyleName);
	
}
