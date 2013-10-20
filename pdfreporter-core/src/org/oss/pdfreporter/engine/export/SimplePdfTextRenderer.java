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
package org.oss.pdfreporter.engine.export;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRPrintText;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.util.JRStyledText;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: SimplePdfTextRenderer.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class SimplePdfTextRenderer extends AbstractPdfTextRenderer
{
	/**
	 * @deprecated Replaced by {@link #SimplePdfTextRenderer(JasperReportsContext, boolean)}.
	 */
	public static SimplePdfTextRenderer getInstance()
	{
		return 
			new SimplePdfTextRenderer(
				DefaultJasperReportsContext.getInstance(),
				JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getBooleanProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT)
				);
	}
	
	
	/**
	 * @deprecated Replaced by {@link #SimplePdfTextRenderer(JasperReportsContext, boolean)}.
	 */
	public SimplePdfTextRenderer(boolean ignoreMissingFont)
	{
		this(DefaultJasperReportsContext.getInstance(), ignoreMissingFont);
	}
	
	
	/**
	 * 
	 */
	public SimplePdfTextRenderer(JasperReportsContext jasperReportsContext, boolean ignoreMissingFont)
	{
		super(jasperReportsContext, ignoreMissingFont);
	}
	
	
	/**
	 *
	 */
	protected Paragraph getParagraph(JRStyledText styledText, JRPrintText textElement)
	{
		String text = styledText.getText();

		AttributedString as = styledText.getAttributedString();

		return pdfExporter.getParagraph(as, text, textElement);
	}

	
	/**
	 * 
	 */
	public void render()
	{
		JRPdfExporter.drawParagraph(pdfPage,
			getParagraph(styledText, text),
			x + leftPadding,
			pdfExporter.exporterContext.getExportedReport().getPageHeight()
				- y
				- topPadding
				- verticalAlignOffset
				- text.getLeadingOffset(),
				//+ text.getLineSpacingFactor() * text.getFont().getSize(),
			x + width - rightPadding,
			pdfExporter.exporterContext.getExportedReport().getPageHeight()
				- y
				- height
				+ bottomPadding,
			0,//text.getLineSpacingFactor(),// * text.getFont().getSize(),
			horizontalAlignment
			);

		// TODO (15.06.2013, Donat, Open Software Solutions): Do we need the stuff below ?
//		colText.setLeading(0, text.getLineSpacingFactor());// * text.getFont().getSize());
//		colText.setRunDirection(
//			text.getRunDirectionValue() == RunDirectionEnum.LTR
//			? PdfWriter.RUN_DIRECTION_LTR : PdfWriter.RUN_DIRECTION_RTL
//			);

	}


	/**
	 * 
	 */
	public void draw()
	{
		//nothing to do
	}
}
