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
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.type.RunDirectionEnum;
import org.oss.pdfreporter.pdf.IPage;
import org.oss.pdfreporter.text.HorizontalAlignment;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: AbstractPdfTextRenderer.java 5180 2012-03-29 13:23:12Z teodord $
 */
public abstract class AbstractPdfTextRenderer extends AbstractTextRenderer
{
	/**
	 * 
	 */
	protected JRPdfExporter pdfExporter;
	protected IPage pdfPage;
	protected HorizontalAlignment horizontalAlignment;
	protected float leftOffsetFactor;
	protected float rightOffsetFactor;

	
	/**
	 * @deprecated Replaced by {@link #AbstractPdfTextRenderer(JasperReportsContext, boolean)}.
	 */
	public AbstractPdfTextRenderer(boolean ignoreMissingFont)
	{
		this(DefaultJasperReportsContext.getInstance(), ignoreMissingFont);
	}
	
	
	/**
	 * 
	 */
	public AbstractPdfTextRenderer(JasperReportsContext jasperReportsContext, boolean ignoreMissingFont)
	{
		super(jasperReportsContext, false, ignoreMissingFont);
	}
	
	
	/**
	 * 
	 */
	public void initialize(
		JRPdfExporter pdfExporter, 
		IPage pdfPage,
		JRPrintText text,
		int offsetX,
		int offsetY
		)
	{
		this.pdfExporter = pdfExporter;
		this.pdfPage = pdfPage;
		
		horizontalAlignment = HorizontalAlignment.ALIGN_LEFT;
		leftOffsetFactor = 0f;
		rightOffsetFactor = 0f;
		
		//FIXMETAB 0.2f was a fair approximation
		switch (text.getHorizontalAlignmentValue())
		{
			case JUSTIFIED :
			{
				horizontalAlignment = HorizontalAlignment.ALIGN_JUSTIFY;
				leftOffsetFactor = 0f;
				rightOffsetFactor = 0f;
				break;
			}
			case RIGHT :
			{
				if (text.getRunDirectionValue() == RunDirectionEnum.LTR)
				{
					horizontalAlignment = HorizontalAlignment.ALIGN_RIGHT;
				}
				else
				{
					horizontalAlignment = HorizontalAlignment.ALIGN_LEFT;
				}
				leftOffsetFactor = -0.2f;
				rightOffsetFactor = 0f;
				break;
			}
			case CENTER :
			{
				horizontalAlignment = HorizontalAlignment.ALIGN_CENTER;
				leftOffsetFactor = -0.1f;
				rightOffsetFactor = 0.1f;
				break;
			}
			case LEFT :
			default :
			{
				if (text.getRunDirectionValue() == RunDirectionEnum.LTR)
				{
					horizontalAlignment = HorizontalAlignment.ALIGN_LEFT;
				}
				else
				{
					horizontalAlignment = HorizontalAlignment.ALIGN_RIGHT;
				}
				leftOffsetFactor = 0f;
				rightOffsetFactor = 0.2f;
				break;
			}
		}

		super.initialize(text, offsetX, offsetY);
	}
}
