package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.text.Paragraph;



public interface ITextLayout {

	/**
	 * @param justificationWidth
	 * @return
	 * @see java.awt.font.TextLayout#getJustifiedLayout(float)
	 */
	ITextLayout getJustifiedLayout(float justificationWidth);

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getAdvance()
	 */
	float getAdvance();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getVisibleAdvance()
	 */
	float getVisibleAdvance();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getAscent()
	 */
	float getAscent();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getDescent()
	 */
	float getDescent();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getLeading()
	 */
	float getLeading();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#isLeftToRight()
	 */
	boolean isLeftToRight();

	/**
	 * @return
	 * @see java.awt.font.TextLayout#getCharacterCount()
	 */
	int getCharacterCount();
	
	/**
	 * Returns the paragraph that have to be drawn.
	 * @return
	 */
	Paragraph getParagraph();	
	
}