package org.oss.pdfreporter.font.text;

import java.util.Locale;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.text.Paragraph;


public class BreakIterator implements IBreakIterator {

	private final java.text.BreakIterator delegate;
	
	public BreakIterator(java.text.BreakIterator delegate) {
		super();
		this.delegate = delegate;
	}

	public java.text.BreakIterator getDelegate() {
		return delegate;
	}
	
	public static IBreakIterator newWordInstance() {
		return new BreakIterator(java.text.BreakIterator.getWordInstance());
	}

	public static IBreakIterator newWordInstance(Locale locale) {
		return new BreakIterator(java.text.BreakIterator.getWordInstance(locale));
	}

	public static IBreakIterator newLineInstance() {
		return new BreakIterator(java.text.BreakIterator.getLineInstance());
	}

	public static IBreakIterator newLineInstance(Locale locale) {
		return new BreakIterator(java.text.BreakIterator.getLineInstance(locale));
	}

	public static IBreakIterator newCharacterInstance() {
		return new BreakIterator(java.text.BreakIterator.getCharacterInstance());
	}

	public static IBreakIterator newCharacterInstance(Locale locale) {
		return new BreakIterator(java.text.BreakIterator.getCharacterInstance(locale));
	}

	public static IBreakIterator newSentenceInstance() {
		return new BreakIterator(java.text.BreakIterator.getSentenceInstance());
	}

	public static IBreakIterator newSentenceInstance(Locale locale) {
		return new BreakIterator(java.text.BreakIterator.getSentenceInstance(locale));
	}

	@Override
	public void setText(Paragraph paragraph) {
		throw new NotImplementedException("This implementation is a only a wrapper and does not implement any logic.");
		
	}

	@Override
	public int next(float wrappingWidth) {
		throw new NotImplementedException("This implementation is a only a wrapper and does not implement any logic.");
	}
	

}
