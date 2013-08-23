package org.oss.pdfreporter.font.text;

import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;


public interface ILineBreakMeasurer {

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public abstract int hashCode();

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public abstract boolean equals(Object obj);

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * @param wrappingWidth
	 * @return
	 * @see java.awt.font.LineBreakMeasurer#nextOffset(float)
	 */
	public abstract int nextOffset(float wrappingWidth);

	/**
	 * @param wrappingWidth
	 * @param offsetLimit
	 * @param requireNextWord
	 * @return
	 * @see java.awt.font.LineBreakMeasurer#nextOffset(float, int, boolean)
	 */
	public abstract int nextOffset(float wrappingWidth, int offsetLimit, boolean requireNextWord);

	/**
	 * @param wrappingWidth
	 * @return
	 * @see java.awt.font.LineBreakMeasurer#nextLayout(float)
	 */
	public abstract ITextLayout nextLayout(float wrappingWidth);

	/**
	 * @param wrappingWidth
	 * @param offsetLimit
	 * @param requireNextWord
	 * @return
	 * @see java.awt.font.LineBreakMeasurer#nextLayout(float, int, boolean)
	 */
	public abstract ITextLayout nextLayout(float wrappingWidth, int offsetLimit, boolean requireNextWord);

	/**
	 * @return
	 * @see java.awt.font.LineBreakMeasurer#getPosition()
	 */
	public abstract int getPosition();

	/**
	 * @param newPosition
	 * @see java.awt.font.LineBreakMeasurer#setPosition(int)
	 */
	public abstract void setPosition(int newPosition);

	/**
	 * @param newParagraph
	 * @param insertPos
	 * @see java.awt.font.LineBreakMeasurer#insertChar(java.text.AttributedCharacterIterator, int)
	 */
	public abstract void insertChar(IAttributedCharacterIterator newParagraph, int insertPos);

	/**
	 * @param newParagraph
	 * @param deletePos
	 * @see java.awt.font.LineBreakMeasurer#deleteChar(java.text.AttributedCharacterIterator, int)
	 */
	public abstract void deleteChar(IAttributedCharacterIterator newParagraph, int deletePos);

}