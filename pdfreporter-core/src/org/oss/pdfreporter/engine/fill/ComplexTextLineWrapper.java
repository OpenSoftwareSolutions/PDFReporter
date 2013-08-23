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
package org.oss.pdfreporter.engine.fill;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRCommonText;
import org.oss.pdfreporter.engine.util.JRStyledText;
import org.oss.pdfreporter.engine.util.MaxFontSizeFinder;
import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.ITextLayout;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;
import org.oss.pdfreporter.uses.java.awt.text.ICharacterIterator;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ComplexTextLineWrapper.java 5541 2012-08-03 10:20:05Z lucianc $
 */
public class ComplexTextLineWrapper implements TextLineWrapper
{
	private final static Logger logger = Logger.getLogger(ComplexTextLineWrapper.class.getName());
	private TextMeasureContext context;
	private MaxFontSizeFinder maxFontSizeFinder;
	
	private IAttributedCharacterIterator allParagraphs;
	private Map<Attribute,Object> globalAttributes;
	
	private IAttributedCharacterIterator paragraph;
	private ILineBreakMeasurer lineMeasurer;
	private final IFontFactory fontFactory;
	
	public ComplexTextLineWrapper()
	{
		this.fontFactory = ApiRegistry.getFontFactory();
	}
	
	protected ComplexTextLineWrapper(ComplexTextLineWrapper parent)
	{
		this.context = parent.context;
		this.maxFontSizeFinder = parent.maxFontSizeFinder;
		
		this.allParagraphs = parent.allParagraphs;
		this.globalAttributes = parent.globalAttributes;
		this.fontFactory = ApiRegistry.getFontFactory();
	}

	@Override
	public void init(TextMeasureContext context)
	{
		this.context = context;
		
		boolean isStyledText = !JRCommonText.MARKUP_NONE.equals(context.getElement().getMarkup());
		maxFontSizeFinder = MaxFontSizeFinder.getInstance(isStyledText);
	}

	@Override
	public boolean start(JRStyledText styledText)
	{
		globalAttributes = styledText.getGlobalAttributes();
		allParagraphs = styledText.getAwtAttributedString(context.getJasperReportsContext(),
				context.isIgnoreMissingFont()).getIterator();
		return true;
	}

	@Override
	public void startParagraph(int paragraphStart, int paragraphEnd, boolean truncateAtChar)
	{
		AttributedString text = new AttributedString(allParagraphs, paragraphStart, paragraphEnd);
		startParagraph(text, truncateAtChar);
	}

	@Override
	public void startEmptyParagraph(int paragraphStart)
	{
		Map<Attribute, Object> attributes = new AttributedString(allParagraphs, 
			paragraphStart, paragraphStart + 1).getIterator().getAttributes();
		AttributedString text = new AttributedString(" ", attributes);
		startParagraph(text, false);
	}
	
	protected void startParagraph(AttributedString text, boolean truncateAtChar)
	{
		this.paragraph = text.getIterator();
		IBreakIterator breakIter = truncateAtChar ? fontFactory.newCharacterBreakIterator() : fontFactory.newWordBreakIterator();
		lineMeasurer = fontFactory.newLineBreakMeasurer(text,breakIter);
	}

	@Override
	public int paragraphPosition()
	{
		return lineMeasurer.getPosition();
	}

	@Override
	public int paragraphEnd()
	{
		return paragraph.getEndIndex();
	}

	@Override
	public TextLine nextLine(float width, int endLimit, boolean requireWord)
	{
		logger.finest("width: " + width + ", endLimit: " + endLimit + ", requireWord: " + requireWord);
		int beginIndex = lineMeasurer.getPosition();
		ITextLayout textLayout = lineMeasurer.nextLayout(width, endLimit, requireWord);
		int endIndex = lineMeasurer.getPosition();
		logText(beginIndex,endIndex, textLayout);
		return textLayout == null ? null : new TextLayoutLine(textLayout);
	}

	private void logText(int beginIndex, int endIndex, ITextLayout textLayout) {
		if (textLayout!=null) {
			logger.finest("Space advance: " + textLayout.getAdvance() + ", Visible space advance: " + textLayout.getVisibleAdvance());
		}
		StringBuilder builder = new StringBuilder();
		AttributedString attributedText = new AttributedString(paragraph,beginIndex,endIndex);
		IAttributedCharacterIterator iterator = attributedText.getIterator();
	     for(char c = iterator.first(); c != ICharacterIterator.DONE; c = iterator.next()) {
	         builder.append(c);
	     }
	     logger.finest("Pos: " + beginIndex + ", next: " + endIndex + ", text: " + builder.toString());
	}
	
	@Override
	public TextLine baseTextLine(int index)
	{
		AttributedString tmpText = new AttributedString(paragraph, index, index + 1);
		ILineBreakMeasurer lbm = fontFactory.newLineBreakMeasurer(tmpText);
		ITextLayout tlyt = lbm.nextLayout(100);//FIXME what is this? why 100?
		return new TextLayoutLine(tlyt);
	}
	
	@Override
	public int maxFontSize(int start, int end)
	{
		return maxFontSizeFinder.findMaxFontSize(
				new AttributedString(paragraph, start, end).getIterator(),
				context.getElement().getFontSize());
	}
	
	@Override
	public String getLineText(int start, int end)
	{
		StringBuilder lineText = new StringBuilder();
		allParagraphs.setIndex(start);
		while (allParagraphs.getIndex() < end 
				&& allParagraphs.current() != '\n')
		{
			lineText.append(allParagraphs.current());
			allParagraphs.next();
		}
		return lineText.toString();
	}

	@Override
	public char charAt(int index)
	{
		return allParagraphs.setIndex(index);
	}

	@Override
	public TextLineWrapper lastLineWrapper(String lineText, int start, int textLength, boolean truncateAtChar)
	{
		AttributedString attributedText = new AttributedString(lineText);
		
		//set original attributes for the text part
		IAttributedCharacterIterator textAttributes = new AttributedString(allParagraphs, 
				start, start + textLength).getIterator();
		setAttributes(attributedText, textAttributes, 0);
		
		//set global attributes for the suffix part
		setAttributes(attributedText, globalAttributes, textLength, lineText.length());
		
		ComplexTextLineWrapper lastLineWrapper = new ComplexTextLineWrapper(this);
		lastLineWrapper.startParagraph(attributedText, truncateAtChar);
		return lastLineWrapper;
	}
	
	protected void setAttributes(AttributedString string, IAttributedCharacterIterator attributes, 
		int stringOffset)
	{
		for (char c = attributes.first(); c != ICharacterIterator.DONE; c = attributes.next())
		{
			for (Iterator<Map.Entry<Attribute,Object>> it = attributes.getAttributes().entrySet().iterator(); it.hasNext();)
			{
				Map.Entry<Attribute,Object> attributeEntry = it.next();
				IAttributedCharacterIterator.Attribute attribute = attributeEntry.getKey();
				if (attributes.getRunStart(attribute) == attributes.getIndex())
				{
					Object attributeValue = attributeEntry.getValue();
					string.addAttribute(
						attribute, 
						attributeValue, 
						attributes.getIndex() + stringOffset,
						attributes.getRunLimit(attribute) + stringOffset
						);
				}
			}
		}
	}
	
	protected void setAttributes(AttributedString string, Map<Attribute,Object> attributes, 
		int startIndex, int endIndex)
	{
		for (Iterator<Map.Entry<Attribute,Object>> it = attributes.entrySet().iterator(); it.hasNext();)
		{
			Map.Entry<Attribute,Object> entry = it.next();
			Attribute attribute = entry.getKey();
			Object attributeValue = entry.getValue();
			string.addAttribute(attribute, attributeValue, startIndex, endIndex);
		}
	}
}
