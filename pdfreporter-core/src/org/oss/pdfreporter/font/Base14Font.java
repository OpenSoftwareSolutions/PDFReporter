/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;



public class Base14Font implements IFont {
	private final static Logger logger = Logger.getLogger(Base14Font.class.getName());
	private final AbstractFontManager fontManager;
	private final String pdfName;
	private final String name;
	private final FontStyle style;
	private IFontPeer delegate = null;

	private Base14Font(AbstractFontManager fontManager, String pdfName, String name, FontStyle style) {
		super();
		this.fontManager = fontManager;
		this.pdfName = pdfName;
		this.name = name;
		this.style = style;
	}

	static List<IFont> getList(AbstractFontManager fontManager) {
		List<IFont> builtInFonts = new ArrayList<IFont>();
		builtInFonts.add(new Base14Font(fontManager, COURIER, COURIER, FontStyle.PLAIN));
		builtInFonts.add(new Base14Font(fontManager, "Courier-Bold", COURIER, FontStyle.BOLD));
		builtInFonts.add(new Base14Font(fontManager, "Courier-Oblique", COURIER, FontStyle.OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, "Courier-BoldOblique", COURIER, FontStyle.BOLD_OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, HELVETICA, HELVETICA, FontStyle.PLAIN));
		builtInFonts.add(new Base14Font(fontManager, "Helvetica-Bold", HELVETICA, FontStyle.BOLD));
		builtInFonts.add(new Base14Font(fontManager, "Helvetica-Oblique", HELVETICA, FontStyle.OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, "Helvetica-BoldOblique", HELVETICA, FontStyle.BOLD_OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, TIMES_ROMAN, TIMES_ROMAN, FontStyle.PLAIN));
		builtInFonts.add(new Base14Font(fontManager, "Times-Bold", TIMES_ROMAN, FontStyle.BOLD));
		builtInFonts.add(new Base14Font(fontManager, "Times-Italic", TIMES_ROMAN, FontStyle.OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, "Times-BoldItalic", TIMES_ROMAN, FontStyle.BOLD_OBLIQUE));
		builtInFonts.add(new Base14Font(fontManager, SYMBOL, SYMBOL, FontStyle.PLAIN));
		builtInFonts.add(new Base14Font(fontManager, ZAPF_DINGBATS, ZAPF_DINGBATS, FontStyle.PLAIN));
		return builtInFonts;
	}

	static IFont findFont(AbstractFontManager fontManager, String name, FontStyle style) {
        if (name.equalsIgnoreCase("DialogInput") || name.equalsIgnoreCase("Monospaced") || name.equalsIgnoreCase(COURIER)) {
        	return fontManager.getFont(COURIER, style);
        } else if (name.equalsIgnoreCase("Serif") || name.equalsIgnoreCase("TimesRoman")) {
           	return fontManager.getFont(TIMES_ROMAN, style);
        } else {
           	return fontManager.getFont(HELVETICA, style);
        }
	}
	
	static Map<String,String> getLogicalFontNames() {
		 Map<String,String> logicalFonts = new HashMap<String, String>();
		 logicalFonts.put("DialogInput", COURIER);
		 logicalFonts.put("Monospaced", COURIER);
		 logicalFonts.put("Serif", TIMES_ROMAN);
		 logicalFonts.put("TimesRoman", TIMES_ROMAN);
		 logicalFonts.put("SansSerif", HELVETICA);
		 return logicalFonts;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getSize() {
		return 1.0f;
	}
	

	@Override
	public FontStyle getStyle() {
		return style;
	}

	@Override
	public FontDecoration getDecoration() {
		return FontDecoration.NONE;
	}

	@Override
	public IFontMetric getMetric() {
		return getDelegate().getMetric();
	}

	@Override
	public String getResourcePath() {
		return null;
	}

	@Override
	public Object getPeer() {
		return getDelegate().getPeer();
	}
	
	private IFontPeer getDelegate() {
		load();
		return delegate;
	}
	
	@Override
	public String getEncoding() {
		return "UTF-8";
	}
	
	private void load() {
		if (delegate == null) {
			logger.fine("Load Base14 font: " + pdfName);
			this.delegate = fontManager.getFontInternal(pdfName);
		}
	}

	@Override
	public IFontManager getFontManager() {
		return fontManager;
	}
}
