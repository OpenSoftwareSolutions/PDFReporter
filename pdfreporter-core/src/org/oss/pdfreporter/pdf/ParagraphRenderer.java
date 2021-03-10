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
package org.oss.pdfreporter.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.text.ITextLayout;
import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.text.HorizontalAlignment;
import org.oss.pdfreporter.text.IPositionedLine;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.text.ParagraphText;

public class ParagraphRenderer {
	private static float LEADING_FACTOR = 1.25f;
	private final Paragraph paragraph;
	private final HorizontalAlignment alignment;
	private final IRectangle bounding;
	private final List<ParagraphText> textLine;
	private float leading;
	private float y, widthLeft;
	private ITextLayout textLayout;
	
	public ParagraphRenderer(Paragraph paragraph,
			HorizontalAlignment alignment, IRectangle bounding) {
		super();
		this.paragraph = paragraph;
		this.alignment = alignment;
		this.bounding = bounding;
		this.textLine = new ArrayList<ParagraphText>();
	}
	
	public ParagraphRenderer(Paragraph paragraph,
			HorizontalAlignment alignment, IRectangle bounding, ITextLayout textLayout) {
		super();
		this.paragraph = paragraph;
		this.alignment = alignment;
		this.bounding = bounding;
		this.textLine = new ArrayList<ParagraphText>();
		this.textLayout = textLayout;
	}
	
	public void render(IPage page, boolean wordwrap) {
		page.beginText();
		leading = 0;
		y = bounding.getY();
		widthLeft = bounding.getWidth();
		for (ParagraphText text : paragraph) {
			int chars = text.measureText(widthLeft, wordwrap);
			if (chars<text.getLength()) {
				add(text.split(calcSpiltPos(chars,wordwrap)));
				renderLine(page);
			} else {
				add(text);
				// TODO (27.06.2013, Donat, Open Software Solutions): Hack to render Styled Text Report (add proper support for linebreaks and tabs)
				if (text.getText().contains("\n")) {
					renderLine(page);
				}
			}
		}
		renderLine(page);
		page.endText();
	}

	private int calcSpiltPos(int splitpos, boolean wordwrap) {
		if (wordwrap && splitpos > 0) {
			if (alignment==HorizontalAlignment.ALIGN_RIGHT) {
				splitpos--;
			}
		}
		return splitpos;
	}

	private void add(ParagraphText text) {
		textLine.add(text);
		widthLeft -= text.getWidth();
		leading = Math.max(leading, text.getFont().getSize() * LEADING_FACTOR);
	}

	private void renderLine(IPage page) {
		float x;
		int spaceToAdd = 0;
		float textLineLengthJustify = 0f;
		switch (alignment) {
		case ALIGN_LEFT:
			x = bounding.getX();
			break;
		case ALIGN_RIGHT:
			x = bounding.getX() + widthLeft;
			break;
		case  ALIGN_CENTER:
			x = bounding.getX() + widthLeft / 2;
			break;
		case ALIGN_JUSTIFY:
			// adjust word and character spacing to consume widthLeft
			// How is this done best with different fonts and sizes  ?
			x = bounding.getX();
			if (textLayout != null) {
				try {
					float spaceSize = textLayout.getAdvance()-textLayout.getVisibleAdvance();
//					System.out.println("spaceSize:"+spaceSize);
					float extraWidthToOffSet = textLayout.getAvailableWidth()-bounding.getWidth();
					/** SKNG : calculate the extra space to add to fill up the blank **/
					spaceToAdd = (int) (extraWidthToOffSet/spaceSize);
					if (extraWidthToOffSet%spaceSize > spaceSize*0.75) {
		//				System.out.println("remaining : "+extraWidthToOffSet%spaceWidthConstantForCarlito+", spaceWidthConstantForCarlito*0.75 : "+spaceWidthConstantForCarlito*0.75);
						spaceToAdd++;
					}
				} catch (Exception e) {
					// anything happen, no justify.
					System.out.println("Error in calculating spaceToAdd!!! "+e.toString());
					spaceToAdd = 0;
				}
				textLineLengthJustify = textLayout.getAvailableWidth();
			}
			break;
		default:	
			x = bounding.getX();
		}
		for (ParagraphText text : textLine) {
			// TODO render background
			float textLineLength = text.getWidth();
			if (textLineLengthJustify > 0f) {
				textLineLength = textLineLengthJustify;
			}
			page.setFont(text.getFont());
			page.setRGBColorFill(text.getForeground());
			page.setTextPos(x, y);
			/** SKNG : reset the text rose for supsubscript **/
			page.setTextRise(0f);
			StringBuilder sb = new StringBuilder();
			/** SKNG : add some space to fill the gap **/
			if (spaceToAdd > 0) {
				// if the last char is a space.
				if (text.getText().endsWith(" ")) {
					spaceToAdd++;
				}
//				System.out.println("text : "+text.getText());
				StringTokenizer st = new StringTokenizer(text.getText());
				int tokens = st.countTokens()-1;
//				System.out.println("space tokens found:"+tokens);
				int multiplier = 1;
				int spaceToAddWithMultiplier = spaceToAdd;
				if (spaceToAdd > tokens) {
					multiplier += (int) spaceToAdd/tokens;
					spaceToAddWithMultiplier = spaceToAdd % tokens;
				}
				for (char c : text.getText().toCharArray()) {
					sb.append(c);
					if (' ' == c && spaceToAdd > 0) {
						if (multiplier>1 && spaceToAddWithMultiplier>0) {
//							System.out.println("[adding space] multiplier:"+multiplier);
							for (int times=0; times<multiplier; times++) {
//								System.out.println("adding "+(times+1)+" space...");
								sb.append(c);
								spaceToAdd--;
							}
							spaceToAddWithMultiplier--;
						} else if (multiplier>1) {
							int remainderMultiplier = multiplier -1;
							for (int times=0; times<remainderMultiplier; times++) {
//								System.out.println("adding "+(times+1)+" space...");
								sb.append(c);
								spaceToAdd--;
							}
						} else {
							sb.append(c);
							spaceToAdd--;
						}
					}
				}
			} else {
				sb.append(text.getText());
			}
//			System.out.println("text:"+sb.toString());
			
			/** SKNG : decorated font line here... **/
			IFont font = text.getFont();
			if (font!=null) {
				IPage tempPage = page;
				
				/** ParagraphText doesn't support multiple fontDecoration yet!! **/
				FontDecoration fontDecor = font.getDecoration();
				if (fontDecor!=null) {
					switch (fontDecor) {
						case UNDERLINE : 
							System.out.println("UNDERLINE");
							tempPage.textOut(sb.toString());
							IPositionedLine line = text.getLine();
							float position = line.getPosition();
							float lineWidth = line.getThikness();
							
							tempPage.setLineWidth(lineWidth);
							tempPage.setLineCap(IPage.LineCap.BUTT_END);
							// follow the text color
							tempPage.setRGBColorStroke(text.getForeground());
							tempPage.setLineDash(null,0);
							tempPage.moveTo(x, y+position);
							tempPage.lineTo(x+textLineLength, y+position);
							tempPage.stroke();
						/** TODO : SKNG : don't break, multiple decoration allow in the same line **/
						break;
						case STRIKE_THROUGH:
							System.out.println("STRIKE_THROUGH");
							tempPage.textOut(sb.toString());
							IPositionedLine lineST = text.getLine();
							float positionST = lineST.getPosition();
							float lineWidthST = lineST.getThikness();
							
							tempPage.setLineWidth(lineWidthST);
							tempPage.setLineCap(IPage.LineCap.BUTT_END);
							// follow the text color
							tempPage.setRGBColorStroke(text.getForeground());
							tempPage.setLineDash(null,0);
							tempPage.moveTo(x, y+positionST);
							tempPage.lineTo(x+textLineLength, y+positionST);
							tempPage.stroke();
						break;
						case SUPERSCRIPT :
							System.out.println("SUPERSCRIPT");
							tempPage.setTextRise(font.getSize()/2);
							tempPage.textOut(sb.toString());
						case SUBSCRIPT :
							System.out.println("SUBSCRIPT");
							tempPage.setTextRise(-font.getSize()/4);
							tempPage.textOut(sb.toString());
						break;
						case NONE:
							tempPage.textOut(sb.toString());
						break;
					}
				}
			}
			/** **/
			else {
				page.textOut(sb.toString());
			}
			
			x += text.getWidth();
		}
		y -= leading;
		leading = 0;
		widthLeft = bounding.getWidth();
		textLine.clear();
	}

}
