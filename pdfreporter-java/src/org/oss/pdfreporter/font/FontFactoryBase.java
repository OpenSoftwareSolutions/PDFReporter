package org.oss.pdfreporter.font;

import org.oss.pdfreporter.font.text.BreakIterator;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.LineBreakMeasurer;
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
		return BreakIterator.newCharacterInstance();
	}

	@Override
	public IBreakIterator newWordBreakIterator() {
		return BreakIterator.newWordInstance();
	}

}
