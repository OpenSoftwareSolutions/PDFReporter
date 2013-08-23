/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.fonts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRFont;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.util.JRFontNotFoundException;
import org.oss.pdfreporter.engine.util.JRGraphEnvInitializer;
import org.oss.pdfreporter.engine.util.JRTextAttribute;
import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.font.IFontManager;
import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.IFont.FontStyle;
import org.oss.pdfreporter.font.text.TextAttribute;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFontUtil.java 5180 2012-03-29 13:23:12Z teodord $
 */
public final class FontUtil
{
	private final static Logger logger = Logger.getLogger(FontUtil.class.getName());
	private JasperReportsContext jasperReportsContext;


	/**
	 *
	 */
	private FontUtil(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	
	/**
	 *
	 */
	public static FontUtil getInstance(JasperReportsContext jasperReportsContext)
	{
		return new FontUtil(jasperReportsContext);
	}
	
	// TODO (29.04.2013, Donat, Digireport): Notice single threaded no threaded missing font cache support required
	
	
	/**
	 *
	 */
	public static void copyNonNullOwnProperties(JRFont srcFont, JRFont destFont)
	{
		if(srcFont != null && destFont != null)
		{
			if (srcFont.getOwnFontName() != null)
			{
				destFont.setFontName(srcFont.getOwnFontName());
			}
			if (srcFont.isOwnBold() != null)
			{
				destFont.setBold(srcFont.isOwnBold());
			}
			if (srcFont.isOwnItalic() != null)
			{
				destFont.setItalic(srcFont.isOwnItalic());
			}
			if (srcFont.isOwnUnderline() != null)
			{
				destFont.setUnderline(srcFont.isOwnUnderline());
			}
			if (srcFont.isOwnStrikeThrough() != null)
			{
				destFont.setStrikeThrough(srcFont.isOwnStrikeThrough());
			}
			if (srcFont.getOwnFontSize() != null)
			{
				destFont.setFontSize(srcFont.getOwnFontSize());
			}
			if (srcFont.getOwnPdfFontName() != null)
			{
				destFont.setPdfFontName(srcFont.getOwnPdfFontName());
			}
			if (srcFont.getOwnPdfEncoding() != null)
			{
				destFont.setPdfEncoding(srcFont.getOwnPdfEncoding());
			}
			if (srcFont.isOwnPdfEmbedded() != null)
			{
				destFont.setPdfEmbedded(srcFont.isOwnPdfEmbedded());
			}
		}
	}
	

	/**
	 *
	 */
	public Map<Attribute,Object> getAttributesWithoutAwtFont(Map<Attribute,Object> attributes, JRFont font)
	{
		attributes.put(TextAttribute.FAMILY, font.getFontName());

		attributes.put(TextAttribute.SIZE, new Float(font.getFontSize()));

		if (font.isBold())
		{
			attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		}
		if (font.isItalic())
		{
			attributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
		}
		if (font.isUnderline())
		{
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		}
		if (font.isStrikeThrough())
		{
			attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
		}
		
		attributes.put(JRTextAttribute.PDF_FONT_NAME, font.getPdfFontName());
		attributes.put(JRTextAttribute.PDF_ENCODING, font.getPdfEncoding());

		if (font.isPdfEmbedded())
		{
			attributes.put(JRTextAttribute.IS_PDF_EMBEDDED, Boolean.TRUE);
		}

		return attributes;
	}


	/**
	 * Returns font information containing the font family, font face and font style.
	 * 
	 * @param name the font family or font face name
	 * @param locale the locale
	 * @return a font info object
	 */
	public FontInfo getFontInfo(String name, Locale locale)
	{
		//FIXMEFONT do some cache
		List<FontFamily> families = jasperReportsContext.getExtensions(FontFamily.class);
		for (Iterator<FontFamily> itf = families.iterator(); itf.hasNext();)
		{
			FontFamily family = itf.next();
			if (locale == null || family.supportsLocale(locale))
			{
				if (name.equals(family.getName()))
				{
					return new FontInfo(family, null, FontStyle.PLAIN);
				}
				FontFace face = family.getNormalFace();
				if (face != null && name.equals(face.getName()))
				{
					return new FontInfo(family, face, FontStyle.PLAIN);
				}
				face = family.getBoldFace();
				if (face != null && name.equals(face.getName()))
				{
					return new FontInfo(family, face, FontStyle.BOLD);
				}
				face = family.getItalicFace();
				if (face != null && name.equals(face.getName()))
				{
					return new FontInfo(family, face, FontStyle.OBLIQUE);
				}
				face = family.getBoldItalicFace();
				if (face != null && name.equals(face.getName()))
				{
					return new FontInfo(family, face, FontStyle.BOLD_OBLIQUE);
				}
			}
		}
		//throw new JRRuntimeException("Font family/face named '" + name + "' not found.");
		return null;
	}


	/**
	 * Returns the font family names available through extensions, in alphabetical order.
	 */
	public Collection<String> getFontFamilyNames()
	{
		TreeSet<String> familyNames = new TreeSet<String>();//FIXMEFONT use collator for order?
		//FIXMEFONT do some cache
		List<FontFamily> families = jasperReportsContext.getExtensions(FontFamily.class);
		for (Iterator<FontFamily> itf = families.iterator(); itf.hasNext();)
		{
			FontFamily family = itf.next();
			familyNames.add(family.getName());
		}
		return familyNames;
	}


	/**
	 *
	 */
	public IFont getAwtFontFromBundles(String name, FontStyle style, int size, Locale locale, boolean ignoreMissingFont)
	{
		IFont awtFont = null;
		FontInfo fontInfo = getFontInfo(name, locale);
		
		if (fontInfo != null)
		{
			FontFamily family = fontInfo.getFontFamily();
			FontFace face = fontInfo.getFontFace();
			if (face == null)
			{
				if (style.equals(FontStyle.BOLD_OBLIQUE))
				{
					face = family.getBoldItalicFace();
				}
				
				if (face == null && style.equals(FontStyle.BOLD))
				{
					face = family.getBoldFace();
				}
				
				if (face == null && style.equals(FontStyle.OBLIQUE))
				{
					face = family.getItalicFace();
				}
				
				if (face == null)
				{
					face = family.getNormalFace();
				}
					
//				if (face == null)
//				{
//					throw new JRRuntimeException("Font family '" + family.getName() + "' does not have the normal font face.");
//				}
			}

			IFontManager fontManager = ApiRegistry.getFontFactory().getFontManager();
			if (face == null)
			{
				// The font family does not specify any font face, not even a normal one.
				// In such case, we take the family name and consider it as JVM available font name.
				checkAwtFont(family.getName(), ignoreMissingFont);
				
				awtFont = fontManager.getFont(family.getName(), style);
				awtFont = fontManager.getModifiedFont(awtFont, size, FontDecoration.NONE);
			}
			else
			{
				awtFont = face.getFont();
				if (awtFont == null)
				{
					throw new JRRuntimeException("The '" + face.getName() + "' font face in family '" + family.getName() + "' returns a null font.");
				}

				if (!awtFont.getStyle().equals(style)) {
					IFont derived = fontManager.getFont(family.getName(), style);		
					if (derived == null) {
						logger.warning("Font style: " + style + " not available for Font: " + family.getName() + " use Style: " + awtFont.getStyle() + " instead.");
					} else {
						awtFont = derived;
					}
				}
				awtFont = fontManager.getModifiedFont(awtFont, size, FontDecoration.NONE);
			}
		}
		
		return awtFont;
	}

	
	
	/**
	 *
	 */
	public void checkAwtFont(String name, boolean ignoreMissingFont)
	{
		if (!JRGraphEnvInitializer.isAwtFontAvailable(name))
		{
			if (!ignoreMissingFont)
			{
				throw new JRFontNotFoundException(name);
			}
		}
	}

	
	/**
	 * Returns a ch.digireport.awt.Font instance by converting a JRFont instance.
	 * Mostly used in combination with third-party visualization packages such as JFreeChart (for chart themes).
	 * Unless the font parameter is null, this method always returns a non-null AWT font, regardless whether it was
	 * found in the font extensions or not. This is because we do need a font to draw with and there is no point
	 * in raising a font missing exception here, as it is not JasperReports who does the drawing. 
	 */
	public IFont getAwtFont(JRFont font, Locale locale)
	{
		if (font == null)
		{
			return null;
		}
		// TODO (27.06.2013, Donat, Digireport): Too much heuristics to determine font name and style
		
		// ignoring missing font as explained in the Javadoc
		IFont awtFont = 
			getAwtFontFromBundles(
				font.getFontName(), 
				font.isBold() && font.isItalic() ? FontStyle.BOLD_OBLIQUE : font.isBold() ? FontStyle.BOLD : font.isItalic() ? FontStyle.OBLIQUE : FontStyle.PLAIN,
				font.getFontSize(),
				locale,
				true
				);
		
		if (awtFont == null)
		{
			// TODO (27.06.2013, Donat, Digireport): Add support to load fonts from attributes if required
			throw new JRRuntimeException("The '" + font.getFontName() + " returns a null font.");
		}
		else
		{
			// add underline and strikethrough attributes since these are set at
			// style/font level
			Map<Attribute, Object> attributes = new HashMap<Attribute, Object>();
			if (font.isUnderline())
			{
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			}
			if (font.isStrikeThrough())
			{
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
			}
			
			if (!attributes.isEmpty())
			{
				// TODO (27.06.2013, Donat, Digireport): We do set underline and strikethrough at text level
				logger.warning("Font " + awtFont.getName() + " cannot be decorated with underline or strikethrough");
//				awtFont = awtFont.deriveFont(attributes);
			}
		}
		
		return awtFont;
	}
	
	
	private FontUtil()
	{
	}
}
