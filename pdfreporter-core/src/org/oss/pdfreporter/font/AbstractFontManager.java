package org.oss.pdfreporter.font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.IFont.FontStyle;
import org.oss.pdfreporter.registry.ISessionObject;


public abstract class AbstractFontManager implements IFontManager {

	private final static Logger logger = Logger.getLogger(AbstractFontManager.class.getName());
	private final Map<FontKey,IFont> fontCache;
	private final List<String> familyNames;
 	
	AbstractFontManager() {
		fontCache = new HashMap<FontKey, IFont>();
		familyNames = new ArrayList<String>();
		registerPdfInternalFonts();
	}

	private void registerPdfInternalFonts() {
		for (IFont font : Base14Font.getList(this)) {
			addFont(new FontKey(font),font);
		}
		for (Map.Entry<String, String> logicalFontEntry : Base14Font.getLogicalFontNames().entrySet()) {
			String fontName = logicalFontEntry.getValue();
			String alias = logicalFontEntry.getKey();
			addFontAlias(fontName, alias, FontStyle.PLAIN);
			addFontAlias(fontName, alias, FontStyle.BOLD);
			addFontAlias(fontName, alias, FontStyle.OBLIQUE);
			addFontAlias(fontName, alias, FontStyle.BOLD_OBLIQUE);
		}
	}
	
	private void addFontAlias(String fontName, String alias, FontStyle style) {
		IFont font = getFont(fontName,style);
		addFont(new FontKey(alias,style),font);
	}
	
	protected void addFont(FontKey key, IFont font) {
		fontCache.put(key, font);
		if (!familyNames.contains(key.getName())) {
			familyNames.add(key.getName());
		}
	}
		
	@Override
	public List<String> getFontFamilyNames() {
		return familyNames;
	}

	@Override
	public Collection<IFont> getLoadedFonts() {
		return fontCache.values();
	}

	@Override
	public IFont loadFont(String filePath, String encoding, boolean embed, String asName, FontStyle asStyle) {
		FontKey key = new FontKey(asName, asStyle);
		if (!fontCache.containsKey(key)) {
			addFont(key, new FontProxy(this,filePath,encoding, embed,asName,asStyle));
			logger.finest("Caching font: " + filePath + ", Style: " + asStyle);
		} else {
			logger.finest("Loading font from cache: " + filePath + ", Style: " + asStyle);
		}
		return fontCache.get(key);
	}

	@Override
	public IFont getFont(String name, FontStyle style) {
		FontKey key = new FontKey(name, style);
		return fontCache.get(key);
	}

	@Override
	public IFont findFont(String name, FontStyle style) {
		IFont found = getFont(name,style);
		if (found==null) {
			found = Base14Font.findFont(this, name, style);
		}
		return found;
	}

	@Override
	public IFont getModifiedFont(IFont baseFont, float size,
			FontDecoration decoration) {
		return new DecoratedFont(baseFont, size, decoration);
	}


	@Override
	public void dispose() {
		fontCache.clear();
		disposeInternal();
		registerPdfInternalFonts();
	}
	
	@Override
	public void get(String key) {
	}

	@Override
	public void put(String key, ISessionObject value) {
	}

	@Override
	public void remove(String key) {
	}


	/**
	 * Load font from file.
	 * @param filePath
	 * @param encoding
	 * @param embed
	 * @return the name to get the font with getFontInternal
	 * @throws IOException
	 */
	abstract String loadFontInternal(String filePath, String encoding, boolean embed) throws IOException;
	
	/**
	 * Returns a font loaded with loadFontInternal or a Base14 font.<br>
	 * The Base14 font names are:<ul>
	 * <li>Courier, Courier-Bold, Courier-Oblique, Courier-BoldOblique</li>
	 * <li>Helvetica, Helvetica-Bold, Helvetica-Oblique, Helvetica-BoldOblique</li>
	 * <li>Times-Roman, Times-Bold, Times-Italic, Times-BoldItalic</li>
	 * <li>Symbol</li>
	 * <li>ZapfDingbats</li>
	 * </ul>
	 * @param fontname
	 * @return
	 */
	abstract IFontPeer getFontInternal(String fontname);
	
	/**
	 * Free all resources of previous loadFontInternal operations.
	 */
	abstract void disposeInternal();


	private static class FontKey {
		private final String name;
		private final FontStyle style;
		

		private FontKey(String name, FontStyle style) {
			super();
			this.name = name.toLowerCase();
			this.style = style;
		}
		
		private FontKey(IFont font) {
			this(font.getName(),font.getStyle());
		}
		
		
		String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((style == null) ? 0 : style.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FontKey other = (FontKey) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (style != other.style)
				return false;
			return true;
		}
	}	
}
