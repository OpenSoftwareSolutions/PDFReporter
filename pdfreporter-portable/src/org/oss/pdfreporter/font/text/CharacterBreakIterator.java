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

import java.util.logging.Logger;

import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.text.ParagraphText;


/**
 * Iterates over a paragraph paragraph and breaks it into lines with character boundaries.
 * @author donatmuller, 2013, last change 5:06:24 PM
 */
public class CharacterBreakIterator implements IBreakIterator {
	private final static Logger logger = Logger.getLogger(CharacterBreakIterator.class.getName());
	private Paragraph paragraph;

	
	@Override
	public void setText(Paragraph paragraph) {
		this.paragraph = paragraph;
	}
	
	@Override
	public int next(float wrappingWidth) {
		int charactersAdvanced = 0;
		float remainingWidth = wrappingWidth;
		for (ParagraphText text : paragraph) {
			int chars = text.measureText(remainingWidth, false);
			charactersAdvanced += chars;
			if (chars < text.getLength()) {
				break;
			} else {
				remainingWidth -= text.getWidth();
			}
		}
		logger.finest("Characters advanced: " + charactersAdvanced);
		return charactersAdvanced;		
	}

}
