/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.font;

import org.oss.pdfreporter.font.AbstractFontFactory;
import org.oss.pdfreporter.font.text.CharacterBreakIterator;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.LineBreakMeasurer;
import org.oss.pdfreporter.font.text.WordBreakIterator;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;



public abstract class FontFactoryBase extends AbstractFontFactory {

	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString) {
		return new LineBreakMeasurer(attributedString);
	}

	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString, IBreakIterator breakIterator) {
		return new LineBreakMeasurer(attributedString, breakIterator);
	}

	@Override
	public IBreakIterator newCharacterBreakIterator() {
		return new CharacterBreakIterator();
	}

	@Override
	public IBreakIterator newWordBreakIterator() {
		return new WordBreakIterator();
	}

}
