/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FontManager extends AbstractFontManager  {

	private final static List<String> BASE14_FONTNAMES = Arrays.asList(new String[]{"Courier", "Courier-Bold", "Courier-Oblique", "Courier-BoldOblique",
			"Helvetica", "Helvetica-Bold", "Helvetica-Oblique", "Helvetica-BoldOblique","Times-Roman", "Times-Bold", "Times-Italic", "Times-BoldItalic",
			"Symbol","ZapfDingbats"});
	private int fontId = 1;
	private Map<String,Font> loadedFonts = new HashMap<String,Font>();
	private final Graphics graphics;
	
	public FontManager() {
		this.fontId = 1;
		BufferedImage bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
		this.graphics = bi.createGraphics();
	}
	
	@Override
	String loadFontInternal(String filePath, String encoding, boolean embed) throws IOException {
		try {
			String name = "TTF" + fontId++;
			InputStream is = new FileInputStream(filePath);
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			loadedFonts.put(name, font);
			return name;
		} catch (FontFormatException e) {
			throw new IOException("Unable to load Font: " + filePath + " with encoding:  " + encoding,e);
		}
	}

	@Override
	IFontPeer getFontInternal(String fontname) {
		Font font = loadedFonts.get(fontname);
		if (font==null) { 
			if (BASE14_FONTNAMES.contains(fontname)) {
				font = new Font(getName(fontname),getStyle(fontname),1);
			} else {
				throw new RuntimeException("Font: " + fontname + " not found.");
			}				
		}
		return new FontPeer(font,graphics.getFontMetrics(font.deriveFont(1000.0f)));
	}

	@Override
	void disposeInternal() {
		loadedFonts.clear();
	}
	
	private String getName(String fontname) {
		int dashPos = fontname.indexOf("-");
		if (dashPos > 0) {
			return fontname.substring(0, dashPos);
		}
		return fontname;
	}
	
	private int getStyle(String fontname) {
		int style = Font.PLAIN;
		if (fontname.endsWith("-Bold")) {
			style = Font.BOLD;
		} else if (fontname.endsWith("-Oblique")) {
			style = Font.ITALIC;
		} else if (fontname.endsWith("-BoldOblique")) {
			style = Font.BOLD | Font.ITALIC;
		}
		return style;
	}
	
}
