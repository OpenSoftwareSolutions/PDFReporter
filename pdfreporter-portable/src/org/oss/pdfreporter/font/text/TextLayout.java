/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.font.IFontMetric;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.text.ParagraphText;

public class TextLayout implements ITextLayout {

	private final Paragraph paragraph;
	private final int characterCount;
	private Float advance = null;
	private Float visibleAdvance = null;
	private Float ascent = null;
	private Float descent = null;
	private Float leading = null;
	private float availableWidth = 0f;
	
	TextLayout(Paragraph paragraph, int characterCount) {
		this.paragraph = paragraph;
		this.characterCount = characterCount;
	}
	
	@Override
	public ITextLayout getJustifiedLayout(float justificationWidth) {
		this.availableWidth = justificationWidth;
		return this;
	}

	@Override
	public float getAdvance() {
		if (advance==null) {
			calcAdvance();
		}
		return advance;
	}

	@Override
	public float getVisibleAdvance() {
		if (visibleAdvance==null) {
			calcAdvance();
		}
		return visibleAdvance;
	}

	@Override
	public float getAscent() {
		if (ascent==null) {
			calcMetric();
		}
		return ascent;
	}

	@Override
	public float getDescent() {
		if (descent==null) {
			calcMetric();
		}
		return descent;
	}

	@Override
	public float getLeading() {
		if (leading==null) {
			calcMetric();
		}
		return leading;
	}

	@Override
	public boolean isLeftToRight() {
		return true;
	}

	@Override
	public int getCharacterCount() {
		return characterCount;
	}

	@Override
	public Paragraph getParagraph() {
		return paragraph;
	}

	private void calcMetric() {
		float calcAscent = 0;
		float calcDescent = 0;
		float calcLeading = 0;
		for (ParagraphText text : paragraph) {
			IFontMetric metric = text.getFont().getMetric();
			calcAscent= Math.max(calcAscent, metric.getAscent());
			calcDescent = Math.max(calcDescent, metric.getDescent());
			calcLeading = Math.max(calcLeading, metric.getLeading());
		}
		this.ascent = calcAscent;
		this.descent = calcDescent;
		this.leading = calcLeading;
	}
	
	private void calcAdvance() {
		StringBuilder builder = new StringBuilder();
		float totalAdvance = 0;
		for (ParagraphText text  : paragraph) {
			totalAdvance += text.getWidth();
			builder.append(text.getText());
		}
		float invisibleAdvance = new ParagraphText(TextLayout.invisibleCharsAtEnd(builder.toString()),paragraph.getLastParagraphText()).getWidth();
		this.advance = totalAdvance;
		this.visibleAdvance = totalAdvance - invisibleAdvance;		
	}
		
	private static String invisibleCharsAtEnd(String text) {
		int begin = text.length();
		while (begin > 0 && text.charAt(begin - 1) <= ' ') {
			begin--;
		}
		return text.substring(begin, text.length());
	}

	@Override
	public float getAvailableWidth() {
		return availableWidth;
	}


}
