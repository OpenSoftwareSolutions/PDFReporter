/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;

public class FontManager extends AbstractFontManager  {

	private int fontId = 1;
	private Map<String,Alias> loadedFonts = new HashMap<String,Alias>();
	
	@Override
	String loadFontInternal(String filePath, String encoding, boolean embed) throws IOException {
		Alias alias = new Alias(fontId++,encoding,embed);
		FontFactory.register(filePath,alias.getName());
		loadedFonts.put(alias.getName(), alias);
		return alias.getName();
	}

	@Override
	IFontPeer getFontInternal(String fontname) {
		boolean embedded = false;
		String encoding = "CP1252";
		if (loadedFonts.containsKey(fontname)) {
			Alias alias = loadedFonts.get(fontname);
			fontname = alias.getName();
			encoding = alias.getEncoding();
			embedded = alias.isEmbed();
		}
		Font font;
		try {
			font = FontFactory.getFont(fontname,encoding,embedded,Font.UNDEFINED, Font.UNDEFINED, null);
		} catch (Exception e) {
			throw new RuntimeException("Unable to load Font: " + fontname + " with encoding:  " + encoding,e);
		}
		if (font==null) {
			throw new RuntimeException("Font: " + fontname + " not found.");
		}
		return new FontPeer(font.getBaseFont());
	}

	@Override
	void disposeInternal() {
		loadedFonts.clear();
	}
	
	
	private static class Alias {
		private final String name;
		private final String encoding;
		private final boolean embed;
		
		Alias(int fontId, String encoding, boolean embed) {
			this.name = "TTF-" + fontId;
			this.encoding = encoding;
			this.embed = embed;
		}

		String getName() {
			return name;
		}

		boolean isEmbed() {
			return embed;
		}

		String getEncoding() {
			return encoding;
		}
		
		
	}

	
}
