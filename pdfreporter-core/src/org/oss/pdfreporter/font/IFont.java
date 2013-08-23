package org.oss.pdfreporter.font;

/**
 * A font with style and size.
 * @author donatmuller, 2013, last change 1:20:32 PM
 */
public interface IFont extends IFontPeer {
	String ZAPF_DINGBATS = "ZapfDingbats";
	String SYMBOL = "Symbol";
	String TIMES_ROMAN = "Times-Roman";
	String HELVETICA = "Helvetica";
	String COURIER = "Courier";
	
	public enum FontDecoration {
		SUPERSCRIPT,
		SUBSCRIPT,
		UNDERLINE,
		STRIKE_THROUGH,
		NONE
	}
	
	public enum FontStyle {
		PLAIN,
		BOLD,
		OBLIQUE,
		BOLD_OBLIQUE
	}
	
	/**
	 * Returns font family name.
	 * @return
	 */
	String getName();
	
	/**
	 * Returns the size of the font in points.
	 * @return
	 */
	float getSize();
	
	
	/**
	 * Returns the style of the font.
	 * @return
	 */
	FontStyle getStyle();
	
	/**
	 * Returns the decoration of the font.
	 * @return
	 */
	FontDecoration getDecoration();
	
	/**
	 * Returns path to font resource.
	 * @return
	 */
	String getResourcePath();	
	
	/**
	 * Encoding to be used with this font.
	 * @return
	 */
	String getEncoding();
	
	/**
	 * Returns the fontmanager.
	 * @return
	 */
	IFontManager getFontManager();
}
