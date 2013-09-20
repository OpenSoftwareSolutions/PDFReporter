package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.text.Paragraph;


public class TextLayout implements ITextLayout {
	public final java.awt.font.TextLayout delegate;
	private final Paragraph paragraph;
	private Float ascent = null;
	private Float descent = null;
	private Float leading = null;

	TextLayout(java.awt.font.TextLayout delegate, Paragraph text) {
		if (delegate==null) {
			throw new RuntimeException("illegal parameter null");
		}
		this.delegate = delegate;
		this.paragraph = text;
	}

	@Override
	public ITextLayout getJustifiedLayout(float justificationWidth) {
		return new TextLayout(delegate.getJustifiedLayout(justificationWidth),paragraph);
	}

	@Override
	public float getAdvance() {
		return delegate.getAdvance();
	}

	@Override
	public float getVisibleAdvance() {
		return delegate.getVisibleAdvance();
	}
	
	@Override
	public float getAscent() {
		return delegate.getAscent();
	}

	@Override
	public float getDescent() {
		return delegate.getDescent();
	}

	@Override
	public float getLeading() {
		return delegate.getLeading();
	}

	@Override
	public boolean isLeftToRight() {
		return delegate.isLeftToRight();
	}

	@Override
	public int getCharacterCount() {
		return delegate.getCharacterCount();
	}

	@Override
	public Paragraph getParagraph() {
		return paragraph;
	}

	@Override
	public String toString() {
		return "TextLayout [characterCount: " + getCharacterCount()
				+ ", advance: " + getAdvance() + ", ascent: "
				+ getAscent() + ", descent: " + getDescent()
				+ ", leading: " + getLeading() + ", text: '"
				+ getParagraph().getText() + "']";
	}

}
