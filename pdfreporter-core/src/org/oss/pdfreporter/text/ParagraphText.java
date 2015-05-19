/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.text;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.geometry.IColor;

/**
 * A chunk of text.
 * @author donatmuller, 2013, last change 2:16:05 PM
 * 
 */
public final class ParagraphText {
	private final String text;
	private final IFont font;
	private final IColor foreground;
	private final IColor background;
	private final IPositionedLine line;
	private ISplitListener<ParagraphText> listener = null;
	private Float width = null;
	
	public ParagraphText(String text, IFont font, IColor foreground,
			IColor background,IPositionedLine line) {
		super();
		this.text = text;
		this.font = font;
		this.foreground = foreground;
		this.background = background;
		this.line = line;
	}

	public ParagraphText(String text, ParagraphText template) {
		this(text,template.getFont(), template.getForeground(), template.getBackground(), template.getLine());
	}

	public ParagraphText(String text, IFont font, IColor foreground) {
		this(text,font, foreground, null, null);
	}
	
	public String getText() {
		return text;
	}
	
	public int getLength() {
		return text.length();
	}

	public float getWidth() {
		if (width==null) {
			width = new Float(font.getMetric().getWidth(getText()) * getFont().getSize() / 1000);
		}
		return width.floatValue();
	}
	
	public int measureText(float wrappingwidth, boolean wordwrap) {
		return font.getMetric().measureText(text,(int)(wrappingwidth*1000/font.getSize()),wordwrap);
	}
	
	public IFont getFont() {
		return font;
	}

	public IColor getForeground() {
		return foreground;
	}

	public IColor getBackground() {
		return background;
	}
	
	public IPositionedLine getLine() {
		return line == null ? null : line.getType().equals(IPositionedLine.LineType.ABSOLUTE) ? line : new ScaledLine(line,getFont().getSize());
	}
	
	public void setSplitListener(ISplitListener<ParagraphText> listener) {
		this.listener = listener;
	}
		
	/**
	 * Splits this ParagraphText into a left and a right part
	 * at charIndex.
	 * @param charIndex
	 * @return left splitter
	 */
	public ParagraphText split(int charIndex) {
		if (charIndex==0) {
			charIndex = 1; // otherwise we run into an an infinite loop
		}
		ParagraphText left = new ParagraphText(text.substring(0,charIndex),this);
		ParagraphText right = new ParagraphText(text.substring(charIndex),this);
		if (listener!=null) {
			listener.split(this, left, right);
		}
		return left;
	}
}
