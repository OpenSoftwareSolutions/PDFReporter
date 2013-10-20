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

import java.io.IOException;
import java.util.logging.Logger;

public class FontProxy implements IFont {
	private final static Logger logger = Logger.getLogger(FontProxy.class.getName());
	private final AbstractFontManager fontManager;
	private final String filePath;
	private final String encoding;
	private final boolean embed;
	private final String name;
	private final FontStyle style;
	private IFontPeer delegate = null;
	
	FontProxy(AbstractFontManager fontManager, String filePath, String encoding, boolean embed, String name, FontStyle style) {
		super();
		this.fontManager = fontManager;
		this.filePath = filePath;
		this.encoding = encoding;
		this.embed = embed;
		this.name = name;
		this.style = style;
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
		return filePath;
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
		return encoding;
	}
	
	private void load() {
		try {
			if (delegate == null) {
				String name = fontManager.loadFontInternal(filePath, encoding, embed);
				this.delegate = fontManager.getFontInternal(name);
				logger.fine("Load TTF font: " + name + ", from: " + filePath + ", encoding: " + encoding + ", embed: " + embed);
			}
		} catch (IOException e) {
			throw new RuntimeException("Exception while loading font resource :" + filePath);
		}
	}

	@Override
	public IFontManager getFontManager() {
		return fontManager;
	}
}
