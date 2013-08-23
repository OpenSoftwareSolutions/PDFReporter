package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.text.Paragraph;

public interface IBreakIterator {
	/**
	 * Sets the paragraph to break.
	 * @param paragraph
	 */
	void setText(Paragraph paragraph);
	
	/**
	 * returns the character position to break at.
	 * @param wrappingWidth
	 * @return
	 */
	int next(float wrappingWidth);
}
