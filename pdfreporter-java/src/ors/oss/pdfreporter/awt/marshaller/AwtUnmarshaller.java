package ors.oss.pdfreporter.awt.marshaller;

import java.awt.Color;
import java.awt.Font;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.geometry.IColor;




/**
 * for each Class in AWT, there will probable be an unmarshaller methode.
 * 
 * @author magnus
 *
 */
public class AwtUnmarshaller {

	
	public static Color getColor(IColor c){
		if (c == null) return null;
		return ((org.oss.pdfreporter.geometry.Color)c).delegate;
	}
	
	
	public static final Font getFont(IFont font) {
		if (font == null) return null;
		Font awtFont =  (Font) font.getPeer();
		return awtFont.deriveFont(font.getSize());
	}
	
}
