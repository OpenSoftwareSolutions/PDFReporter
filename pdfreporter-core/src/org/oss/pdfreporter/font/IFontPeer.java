package org.oss.pdfreporter.font;

public interface IFontPeer {
	/**
	 * Returns the font metric for size and style.
	 * @return
	 */
	IFontMetric getMetric();
		
	/**
	 * Returns an internal or native representation of the font.
	 * @return
	 */
	Object getPeer();

}
