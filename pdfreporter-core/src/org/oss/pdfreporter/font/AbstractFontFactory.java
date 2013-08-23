package org.oss.pdfreporter.font;

import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.font.text.CharacterBreakIterator;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.font.text.LineBreakMeasurer;
import org.oss.pdfreporter.font.text.WordBreakIterator;
import org.oss.pdfreporter.registry.Session;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;


public abstract class AbstractFontFactory implements IFontFactory {

	private Session session = null;
	
	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString) {
		return new LineBreakMeasurer(attributedString);
	}

	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString, String plainText) {
		return new LineBreakMeasurer(attributedString, plainText);
	}

	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString, IBreakIterator breakIterator) {
		return new LineBreakMeasurer(attributedString, breakIterator);
	}

	@Override
	public ILineBreakMeasurer newLineBreakMeasurer(
			AttributedString attributedString, String plainText,
			IBreakIterator breakIterator) {
		return new LineBreakMeasurer(attributedString, plainText, breakIterator);
	}

	@Override
	public IBreakIterator newCharacterBreakIterator() {
		return new CharacterBreakIterator();
	}

	@Override
	public IBreakIterator newWordBreakIterator() {
		return new WordBreakIterator();
	}

	@Override
	public void setSession(Session session) {
		if (this.session!=null) {
			session.removeListener(getFontManager());
		}
		this.session = session;
		session.addListener(getFontManager());
		
	}

	@Override
	public Session getSession() {
		return session;
	}

}
