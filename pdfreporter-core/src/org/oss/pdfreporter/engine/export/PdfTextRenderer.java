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
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.util.JRStyledText;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: PdfTextRenderer.java 5050 2012-03-12 10:11:26Z teodord $
 */
public class PdfTextRenderer extends AbstractPdfTextRenderer
{
	/**
	 * @deprecated Replaced by {@link #PdfTextRenderer(JasperReportsContext, boolean)}.
	 */
	public static PdfTextRenderer getInstance()
	{
		return 
			new PdfTextRenderer(
				DefaultJasperReportsContext.getInstance(),
				JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getBooleanProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT)
				);
	}
	
	
	/**
	 * @deprecated Replaced by {@link #PdfTextRenderer(JasperReportsContext, boolean)}. 
	 */
	public PdfTextRenderer(boolean ignoreMissingFont)
	{
		this(DefaultJasperReportsContext.getInstance(), ignoreMissingFont);
	}
	
	
	/**
	 * 
	 */
	public PdfTextRenderer(JasperReportsContext jasperReportsContext, boolean ignoreMissingFont)
	{
		super(jasperReportsContext, ignoreMissingFont);
	}
	
	
	/**
	 * 
	 */
	public void draw()
	{
		TabSegment segment = segments.get(segmentIndex);
		
		float advance = segment.layout.getAdvance();
		
		JRPdfExporter.drawParagraph(pdfPage,
			pdfExporter.getParagraph(segment.as, segment.text, text),
			x + drawPosX + leftOffsetFactor * advance,// + leftPadding
			pdfExporter.exporterContext.getExportedReport().getPageHeight()
				- y
				- topPadding
				- verticalAlignOffset
				//- text.getLeadingOffset()
				+ lineHeight
				- drawPosY,
			x + drawPosX  + segment.layout.getAdvance() + rightOffsetFactor * advance,// + leftPadding
			pdfExporter.exporterContext.getExportedReport().getPageHeight()
				- y
				- topPadding
				- verticalAlignOffset
				//- text.getLeadingOffset()
				-400//+ lineHeight//FIXMETAB
				- drawPosY,
			0,//text.getLineSpacingFactor(),// * text.getFont().getSize(),
			horizontalAlignment
			);

		// TODO (15.06.2013, Donat, Open Software Solutions): Do we need the stuff below ?
//		colText.setLeading(lineHeight);
//		colText.setRunDirection(
//			text.getRunDirectionValue() == RunDirectionEnum.LTR
//			? PdfWriter.RUN_DIRECTION_LTR : PdfWriter.RUN_DIRECTION_RTL
//			);

	}
	

}
