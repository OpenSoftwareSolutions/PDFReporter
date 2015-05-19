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
package org.oss.pdfreporter.font;

import org.oss.pdfreporter.font.text.BreakIterator;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.LineBreakMeasurer;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;


public class FontFactory extends AbstractFontFactory {

	
	public static void registerFactory() {
		ApiRegistry.register(new FontFactory());
	}
	
	private final IFontManager fontManager;
	
	private FontFactory() {
		super();
		this.fontManager = new FontManager();
	}
	
	@Override
	public IFontManager getFontManager() {
		return fontManager;
	}
	
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
		return BreakIterator.newCharacterInstance();
	}

	@Override
	public IBreakIterator newWordBreakIterator() {
		return BreakIterator.newWordInstance();
	}

	
}
