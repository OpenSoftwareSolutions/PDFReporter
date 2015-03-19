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
package org.oss.pdfreporter.font.text;

import java.awt.font.FontRenderContext;
import java.text.BreakIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.text.AttributedStringConverter;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;

import ors.oss.pdfreporter.awt.marshaller.AwtUnmarshallingCharacterIterator;


public class LineBreakMeasurer implements ILineBreakMeasurer {
	
	private static final FontRenderContext LINE_BREAK_FONT_RENDER_CONTEXT = new FontRenderContext(null, true, true);
	private final static Logger logger = Logger.getLogger(LineBreakMeasurer.class.getName());
	private final java.awt.font.LineBreakMeasurer delegate;
	private final Paragraph paragraph;


	public LineBreakMeasurer(AttributedString attributedText) {
		this.delegate = new java.awt.font.LineBreakMeasurer(new AwtUnmarshallingCharacterIterator(attributedText.getIterator()), BreakIterator.getLineInstance(),
				LINE_BREAK_FONT_RENDER_CONTEXT);
		this.paragraph = AttributedStringConverter.convert(attributedText);
	}
	
	public LineBreakMeasurer(AttributedString attributedText, IBreakIterator breakIterator) {
		this.delegate = new java.awt.font.LineBreakMeasurer(new AwtUnmarshallingCharacterIterator(attributedText.getIterator()), ((org.oss.pdfreporter.font.text.BreakIterator)breakIterator).getDelegate(),
				LINE_BREAK_FONT_RENDER_CONTEXT);
		this.paragraph = AttributedStringConverter.convert(attributedText);
	}
	
	
	@Override
	public int nextOffset(float wrappingWidth) {
		return delegate.nextOffset(wrappingWidth);
	}

	@Override
	public int nextOffset(float wrappingWidth, int offsetLimit,
			boolean requireNextWord) {
		return delegate.nextOffset(wrappingWidth, offsetLimit, requireNextWord);
	}

	@Override
	public ITextLayout nextLayout(float wrappingWidth) {
		int currentOffset = getPosition();
		java.awt.font.TextLayout awtTextLayout = delegate.nextLayout(wrappingWidth);
		return toTextLayou(currentOffset, awtTextLayout);
	}

	@Override
	public ITextLayout nextLayout(float wrappingWidth, int offsetLimit,
			boolean requireNextWord) {
		int currentOffset = getPosition();
		java.awt.font.TextLayout awtTextLayout = delegate.nextLayout(wrappingWidth,offsetLimit,requireNextWord);
		return toTextLayou(currentOffset, awtTextLayout);
	}
	
	private ITextLayout toTextLayou(int currentOffset, java.awt.font.TextLayout awtTextLayout) {
		if (awtTextLayout!=null) {
			int nextOffset = getPosition();
			Paragraph layoutParagraph = paragraph.subParagraph(currentOffset, nextOffset);
			ITextLayout textLayout = new TextLayout(awtTextLayout,layoutParagraph);
			if (logger.isLoggable(Level.FINEST)) {
				logger.finest(textLayout.toString());
			}
			return textLayout;			
		}
		return null;
	}

	@Override
	public int getPosition() {
		return delegate.getPosition();
	}

	@Override
	public void setPosition(int newPosition) {
		delegate.setPosition(newPosition);
	}

	@Override
	public void insertChar(IAttributedCharacterIterator newParagraph,
			int insertPos) {
		delegate.insertChar(new AwtUnmarshallingCharacterIterator(newParagraph), insertPos);

	}

	@Override
	public void deleteChar(IAttributedCharacterIterator newParagraph,
			int deletePos) {
		delegate.deleteChar(new AwtUnmarshallingCharacterIterator(newParagraph), deletePos);
	}

}
