/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRParagraph;

public class LineHeightCalculator {
	// TODO (13.04.2013, Donat, Open Software Solutions): Refactoring remove getLineHeight() from  AbstractTextRenderer in the exporter Project and replace usage by this
	// TODO (13.04.2013, Donat, Open Software Solutions): Refactoring remove LINE_BREAK_FONT_RENDER_CONTEXT from  AbstractTextRenderer in the exporter Project and replace usage by this
	// Make anb abstract base class, what name should it get ?
	

	/**
	 * 
	 */
	public static float getLineHeight(boolean isFirstLine, JRParagraph paragraph, float maxLeading, float maxAscent)
	{
		float lineHeight = 0;

		switch(paragraph.getLineSpacing())
		{
			case SINGLE:
			default :
			{
				lineHeight = maxLeading + 1f * maxAscent;
				break;
			}
			case ONE_AND_HALF:
			{
				if (isFirstLine)
				{
					lineHeight = maxLeading + 1f * maxAscent;
				}
				else
				{
					lineHeight = maxLeading + 1.5f * maxAscent;
				}
				break;
			}
			case DOUBLE:
			{
				if (isFirstLine)
				{
					lineHeight = maxLeading + 1f * maxAscent;
				}
				else
				{
					lineHeight = maxLeading + 2f * maxAscent;
				}
				break;
			}
			case PROPORTIONAL:
			{
				if (isFirstLine)
				{
					lineHeight = maxLeading + 1f * maxAscent;
				}
				else
				{
					lineHeight = maxLeading + paragraph.getLineSpacingSize().floatValue() * maxAscent;
				}
				break;
			}
			case AT_LEAST:
			{
				if (isFirstLine)
				{
					lineHeight = maxLeading + 1f * maxAscent;
				}
				else
				{
					lineHeight = Math.max(maxLeading + 1f * maxAscent, paragraph.getLineSpacingSize().floatValue());
				}
				break;
			}
			case FIXED:
			{
				if (isFirstLine)
				{
					lineHeight = maxLeading + 1f * maxAscent;
				}
				else
				{
					lineHeight = paragraph.getLineSpacingSize().floatValue();
				}
				break;
			}
		}
		return lineHeight;
	}
	
}
