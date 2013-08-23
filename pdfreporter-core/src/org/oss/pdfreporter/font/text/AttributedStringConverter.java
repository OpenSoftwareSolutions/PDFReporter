package org.oss.pdfreporter.font.text;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.font.IFontManager;
import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.IFont.FontStyle;
import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.IPositionedLine;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.text.ParagraphText;
import org.oss.pdfreporter.text.PositionedLined;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;
import org.oss.pdfreporter.uses.java.awt.text.ICharacterIterator;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;


/**
 * Converts an AttributedString to a Paragraph.
 * @author donatmuller, 2013, last change 1:49:35 PM
 */
public class AttributedStringConverter {
	
	/**
	 * The attributes that can divide the paragraph into chunks of text.
	 * SEGMENTATION_ATTRIBUTES
	 */
	private final static Set<Attribute> SEGMENTATION_ATTRIBUTES = new HashSet<Attribute>();
	
	static {
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.FONT);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.FOREGROUND);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.BACKGROUND);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.WEIGHT);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.POSTURE);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.UNDERLINE);
		SEGMENTATION_ATTRIBUTES.add(TextAttribute.SUPERSCRIPT);		
	}
	
	private final IFontFactory fontFactory;
	private final AttributedString attributedText;
	private final String plainText;
	
	private AttributedStringConverter(AttributedString attributedText, String plainText) {
		this.fontFactory = ApiRegistry.getFontFactory();
		this.attributedText = attributedText;
		this.plainText = plainText;
	}
	
	private AttributedStringConverter(AttributedString attributedText) {
		this(attributedText, getPlainText(attributedText));
	}
	
	public static Paragraph convert(AttributedString attributedText, String plainText) {
		return new AttributedStringConverter(attributedText,plainText).convert();
	}
	
	public static Paragraph convert(AttributedString attributedText) {
		return new AttributedStringConverter(attributedText).convert();
	}
	
	public Paragraph convert() {
		Paragraph paragraph = new Paragraph();
		int runLimit = 0;
		IAttributedCharacterIterator iterator = attributedText.getIterator();
		while(runLimit < plainText.length() && (runLimit = iterator.getRunLimit(SEGMENTATION_ATTRIBUTES)) <= iterator.getEndIndex())
		{
			Map<Attribute,Object> attributes = iterator.getAttributes();
			ParagraphText paragraphText = getParagrapghText(attributes, plainText.substring(iterator.getIndex(), runLimit));
			paragraph.add(paragraphText);
			iterator.setIndex(runLimit);
		}
		return paragraph;
	}
	
	private ParagraphText getParagrapghText(Map<Attribute, Object> attributes, String text) {
		IFont font = (IFont) attributes.get(TextAttribute.FONT);
		if (font==null) {
			// TODO (12.07.2013, Donat, Digireport): If the font is not in the attributes where we get the correct style from ?
			IFontManager fontManager = fontFactory.getFontManager();
			String fontFamily = (String) attributes.get(TextAttribute.FAMILY);
			Float fontSize = (Float) attributes.get(TextAttribute.SIZE);
			font = fontManager.getFont(fontFamily, FontStyle.PLAIN);
			font = fontManager.getModifiedFont(font, fontSize, FontDecoration.NONE);
		}
		boolean underline = hasUnderline(attributes);
		boolean strikethrough = hasStrikethrough(attributes);
		boolean superscript = hasSuperscipt(attributes);
		boolean subscript = hasSubscipt(attributes);
		IPositionedLine line = underline ? PositionedLined.newUnderline() : strikethrough ? PositionedLined.newStrikethrough() : null;
    	FontDecoration decoration = underline ? FontDecoration.UNDERLINE : strikethrough ? FontDecoration.STRIKE_THROUGH : superscript ? FontDecoration.SUPERSCRIPT : subscript ? FontDecoration.SUBSCRIPT : FontDecoration.NONE;
		float fontSizeScale = superscript || subscript ? 2f / 3 : 1f;
    	font = fontFactory.getFontManager().getModifiedFont(font, font.getSize() * fontSizeScale, decoration);
		IColor forecolor = (IColor)attributes.get(TextAttribute.FOREGROUND);
		IColor backcolor = (IColor)attributes.get(TextAttribute.BACKGROUND);
		return new ParagraphText(text,font,forecolor,backcolor,line);
	}
	
	
	private static boolean hasUnderline(Map<Attribute,Object> attributes)
	{
		Integer underline = (Integer) attributes.get(TextAttribute.UNDERLINE);
		return TextAttribute.UNDERLINE_ON.equals(underline);
	}

	private static boolean hasStrikethrough(Map<Attribute,Object> attributes)
	{
		Boolean strike = (Boolean) attributes.get(TextAttribute.STRIKETHROUGH);
		return TextAttribute.STRIKETHROUGH_ON.equals(strike);
	}

	private static boolean hasSuperscipt(Map<Attribute, Object> attributes) {
		Integer scriptStyle = (Integer) attributes.get(TextAttribute.SUPERSCRIPT);
		return (scriptStyle != null && TextAttribute.SUPERSCRIPT_SUPER.equals(scriptStyle));
	}
	
	private static boolean hasSubscipt(Map<Attribute, Object> attributes) {
		Integer scriptStyle = (Integer) attributes.get(TextAttribute.SUPERSCRIPT);
		return (scriptStyle != null && TextAttribute.SUPERSCRIPT_SUB.equals(scriptStyle));
	}
	
	/**
	 * Extracts the plain text from an attributed string.
	 * @param attributedText
	 * @return
	 */
	public static String getPlainText(AttributedString attributedText) {
		StringBuilder builder = new StringBuilder();
		IAttributedCharacterIterator iterator = attributedText.getIterator();
	     for(char c = iterator.first(); c != ICharacterIterator.DONE; c = iterator.next()) {
	         builder.append(c);
	     }
	     return builder.toString();
	}

	
}
