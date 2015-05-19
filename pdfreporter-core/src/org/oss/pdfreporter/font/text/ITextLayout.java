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
