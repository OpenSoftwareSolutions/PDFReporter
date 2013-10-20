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

import java.util.Collection;
import java.util.List;

import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.IFont.FontStyle;
import org.oss.pdfreporter.registry.ISessionListener;


/**
 * Singleton to manage Fonts.
 * @author donatmuller, 2013, last change 1:07:33 PM
 * 
 */
public interface IFontManager extends ISessionListener {

	/**
	 * Returns a list of font family names.
	 * @return
	 */
	List<String> getFontFamilyNames();
	
	/**
	 * Loaded fonts plus fonts available from the platform plus PDF Base14 fonts.
	 * @return
	 */
	Collection<IFont> getLoadedFonts();
		
	/**
	 * Load a TrueType font from a ttf or ttc file.
	 * @param filePath full path to the font file
	 * @param encoding the encoding of the font
	 * @param embed embeds font in pdf if true
	 * @param asName assigns a family name to the font
	 * @param asStyle assigns a style to the font
	 */
	IFont loadFont(String filePath, String encoding, boolean embed, String asName, FontStyle asStyle);	
	
	/**
	 * Returns font by family name and style.
	 * @param name
	 * @param style
	 * @return IFont if name and style match otherwise null
	 */
	IFont getFont(String name, FontStyle style);
	
	/**
	 * Finds best matching font for family name and style.
	 * @param name
	 * @param style
	 * @return IFont if any available otherwise null
	 */
	IFont findFont(String name, FontStyle style);
	
	/**
	 * Get the specified font for a size and optional decoration.
	 * @param baseFont
	 * @param size
	 * @param decoration
	 * @param line
	 * @return
	 */
	IFont getModifiedFont(IFont baseFont, float size, FontDecoration decoration);
				
}
