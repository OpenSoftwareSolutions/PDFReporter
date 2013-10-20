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
package org.oss.pdfreporter.pdf;

import org.oss.pdfreporter.font.text.ITextLayout;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.text.IPositionedLine;
import org.oss.pdfreporter.text.ParagraphText;

/**
 * Renders a ITextLayout including decoration.
 * @author donatmuller, 2013, last change 12:19:20 AM
 */
public class TextLineRenderer {
	/**
	 * Renders a ITextLayout on a pdf page at text position x,y.
	 * @param layout
	 * @param page
	 * @param x
	 * @param y
	 */
	public static void render(ITextLayout layout, IPage page, float x, float y) {
		for (ParagraphText text : layout.getParagraph()) {
			renderBackground(page, y, x, text);
			page.beginText();
			page.setRGBColorFill(text.getForeground());
			page.setFont(text.getFont());
			page.setTextPos(x, y);
			page.textOut(text.getText());
			page.endText();
			renderLine(page, y, x, text);
			x+=text.getWidth();
		}
	}
	
	private static void renderLine(IPage page, float y, float x, ParagraphText text) {
		IPositionedLine line = text.getLine();
		if (line!=null) {
			page.setLineWidth(line.getThikness());
			page.setRGBColorStroke(text.getForeground());
			page.moveTo(x, y + line.getPosition());
			page.lineTo(x + text.getWidth(), y + line.getPosition());
			page.stroke();
		}
	}
	
	private static void renderBackground(IPage page, float y, float x, ParagraphText text) {
		IColor background = text.getBackground();
		if (background!=null) {
			page.setRGBColorFill(background);
			page.setRGBColorStroke(background);
			float fontSize = text.getFont().getSize();
			page.rectangle(x, y - 0.25f * fontSize + 0.5f, text.getWidth(),  fontSize);
			page.fillStroke();
		}
	}
}
