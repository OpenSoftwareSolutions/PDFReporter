package org.oss.pdfreporter.font;

import org.oss.pdfreporter.font.text.CharacterBreakIterator;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.LineBreakMeasurer;
import org.oss.pdfreporter.font.text.WordBreakIterator;
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
		return new CharacterBreakIterator();
	}

	@Override
	public IBreakIterator newWordBreakIterator() {
		return new WordBreakIterator();
	}

	
}
