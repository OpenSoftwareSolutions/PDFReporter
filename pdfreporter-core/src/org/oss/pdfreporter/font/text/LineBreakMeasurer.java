package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;


public class LineBreakMeasurer implements ILineBreakMeasurer {

	private final Paragraph paragraph;
	private final IBreakIterator wordBreakIterator;
	private final IBreakIterator characterBreakIterator;
	private int limit;
    private int start;
    private int pos;
	
	public LineBreakMeasurer(AttributedString attributedText, String plainText, IBreakIterator breakIterator) {
		IAttributedCharacterIterator charcterIterator = attributedText.getIterator();
		this.limit = charcterIterator.getEndIndex();
	    this.pos = this.start = charcterIterator.getBeginIndex();
	    this.paragraph = AttributedStringConverter.convert(attributedText, plainText);
	    this.wordBreakIterator = breakIterator;
	    this.wordBreakIterator.setText(paragraph);
	    this.characterBreakIterator = new CharacterBreakIterator();
	    this.characterBreakIterator.setText(paragraph);
	}
	
	public LineBreakMeasurer(AttributedString attributedText, String plainText) {
		this(attributedText,plainText,new WordBreakIterator());
	}

	public LineBreakMeasurer(AttributedString attributedText, IBreakIterator breakIterator) {
		this(attributedText,AttributedStringConverter.getPlainText(attributedText),breakIterator);
	}

	public LineBreakMeasurer(AttributedString attributedText) {
		this(attributedText,new WordBreakIterator());
	}
	
	@Override
	public int nextOffset(float wrappingWidth) {
		return nextOffset(wrappingWidth, limit, false);
	}

	@Override
	public int nextOffset(float wrappingWidth, int offsetLimit,
			boolean requireNextWord) {
		int nextOffset = pos;
		if (pos < limit) {	
            if (offsetLimit <= pos) {
                throw new IllegalArgumentException("offsetLimit must be after current position");
            }
            nextOffset += wordBreakIterator.next(wrappingWidth);
            if (pos==nextOffset && !requireNextWord) {
            	nextOffset += characterBreakIterator.next(wrappingWidth);
            }
	        if (nextOffset > offsetLimit) {
	            nextOffset = offsetLimit;
	        }
		}
		return nextOffset;
	}

	@Override
	public ITextLayout nextLayout(float wrappingWidth) {
		return nextLayout(wrappingWidth, limit, false);
	}

	@Override
	public ITextLayout nextLayout(float wrappingWidth, int offsetLimit,
			boolean requireNextWord) {
		int currentOffset = pos;
		int nextOffset = nextOffset(wrappingWidth,offsetLimit,requireNextWord);
		if (currentOffset==nextOffset && requireNextWord) {
			return null;
		}
		setPosition(nextOffset);
		Paragraph textLine = paragraph.subParagraph(currentOffset, nextOffset);
        return new TextLayout(textLine, nextOffset - currentOffset);
	}
	
	
	@Override
	public int getPosition() {
        return pos;
	}

	@Override
	public void setPosition(int newPosition) {
        if (newPosition < start || newPosition > limit) {
            throw new IllegalArgumentException("position is out of range");
        }
		if (newPosition < limit) {	
			Paragraph nextParagraph = paragraph.subParagraph(newPosition);
			wordBreakIterator.setText(nextParagraph);
			characterBreakIterator.setText(nextParagraph);
		}
        pos = newPosition;
	}

	@Override
	public void insertChar(IAttributedCharacterIterator newParagraph,
			int insertPos) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteChar(IAttributedCharacterIterator newParagraph,
			int deletePos) {
		throw new UnsupportedOperationException();
	}

	
}
