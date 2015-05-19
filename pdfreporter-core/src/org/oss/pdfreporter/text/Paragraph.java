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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Paragraph implements Iterable<ParagraphText>{
	
	/**
	 * List with the ParagraphText elements of this paragraph
	 * textList
	 */
	private final List<ParagraphText> textList;
	/**
	 * Map with the begin index of each ParagraphElement in the paragraph text.
	 * beginPosMap
	 */
	private final Map<Integer,ParagraphText> beginPosMap;
	
	/**
	 * The begin index of the last ParagraphText in the list of paragraphs.
	 * lastIndexPos
	 */
	private int lastBeginPos;
	
	public Paragraph() {
		this.textList = new ArrayList<ParagraphText>();
		this.beginPosMap = new HashMap<Integer, ParagraphText>();
		this.lastBeginPos = 0;
	}

	public Paragraph(List<ParagraphText> textList) {
		this();
		addAll(textList);
	}
	
	public void add(ParagraphText text) {
		this.textList.add(text);
		this.beginPosMap.put(new Integer(lastBeginPos), text);
		this.lastBeginPos += text.getLength();
	}

	public void addAll(List<ParagraphText> all) {
		for (ParagraphText text : all) {
			add(text);
		}
	}
	
	@Override
	public Iterator<ParagraphText> iterator() {
		return new SplitableIterator(textList);
	}
	
	
	/**
	 * Returns a new paragraph with the characters from befinIndex to endIndex. 
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public Paragraph subParagraph(int beginIndex, int endIndex) {
		if (beginIndex < 0 || endIndex < 0) {
			throw new IllegalArgumentException("Index cannot be before first paragraph text.");
		}
		if  (endIndex < beginIndex) {
			throw new IllegalArgumentException("End starts before begin index.");
		}
		Paragraph subParagraph = new Paragraph();
		int start = 0;
		for (ParagraphText text : textList) {
			int end = start + text.getLength();
			if (beginIndex >= start && beginIndex < end) {
				if (endIndex > start && endIndex <= end) {
					subParagraph.add(new ParagraphText(text.getText().substring(beginIndex - start, endIndex - start),text));
					return subParagraph; 
				}
				if (beginIndex==start) {
					subParagraph.add(text);
				} else {					
					subParagraph.add(new ParagraphText(text.getText().substring(beginIndex - start),text));				
				}
			}
			if (beginIndex < start && endIndex > end) {
				subParagraph.add(text);												
			}
			if (endIndex > start && endIndex <= end) {
				if (endIndex==start) {
					subParagraph.add(text);
				} else {					
					subParagraph.add(new ParagraphText(text.getText().substring(0, endIndex - start),text));	
				}
				return subParagraph; 
			}
			start = end;
		}
		throw new IllegalArgumentException("Index cannot be past last paragraph text.");
	}
	
	
	/**
	 * Returns a new paragraph with the characters from beginIndex. 
	 * @param beginIndex
	 * @return
	 */
	public Paragraph subParagraph(int beginIndex) {
		if (beginIndex < 0) {
			throw new IllegalArgumentException("Index cannot be before first paragraph text.");
		}
		Paragraph subParagraph = new Paragraph();
		int start = 0;
		int index = 0;
		for (ParagraphText text : textList) {
			int end = start + text.getLength();
			if (beginIndex >= start && beginIndex < end) {
				if (beginIndex==start) {
					subParagraph.addAll(textList.subList(index, textList.size()));
				} else {					
					subParagraph.add(new ParagraphText(text.getText().substring(beginIndex - start),text));	
					subParagraph.addAll(textList.subList(index + 1, textList.size()));
				}
				return subParagraph; 
			}
			start = end;
			index++;
		}
		throw new IllegalArgumentException("Index cannot be past last paragraph text.");
	}
	
	
	public String getText() {
		StringBuilder builder = new StringBuilder();
		for (ParagraphText text : textList) {
			builder.append(text.getText());
		}
		return builder.toString();
	}
	
	public ParagraphText getFirstParagraphText() {
		return textList.isEmpty() ? null : textList.get(0);
	}
	
	public ParagraphText getLastParagraphText() {
		return textList.isEmpty() ? null : textList.get(textList.size() - 1);
	}
	
	private static class SplitableIterator implements Iterator<ParagraphText>, ISplitListener<ParagraphText> {

		private final List<ParagraphText> textList;
		int index = 0;
		
		SplitableIterator(List<ParagraphText> original) {
			this.textList = new ArrayList<ParagraphText>(original);			
		}
		@Override
		public boolean hasNext() {
			return index < textList.size();
		}

		@Override
		public ParagraphText next() {
			ParagraphText current = textList.get(index++);
			current.setSplitListener(this);
			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void split(ParagraphText before, ParagraphText left,
				ParagraphText right) {
			int oldIndex = textList.indexOf(before);
			textList.set(oldIndex, left);
			textList.add(oldIndex + 1, right);
		}
	}
}
