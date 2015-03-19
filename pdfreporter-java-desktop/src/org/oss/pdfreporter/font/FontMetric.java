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

import java.awt.FontMetrics;

public class FontMetric implements IFontMetric {

	private final FontMetrics delegate;
	
	public FontMetric(FontMetrics delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int measureText(String text, int width, boolean wordwrap) {
		int remainingWidth = width;
		int idx=0;
		int lastWordPos = 0;
		while (remainingWidth>0 && idx<text.length()) {
			char ch = text.charAt(idx);
			if (ch<=' ') {
				lastWordPos = idx + 1;
			}
			remainingWidth-=delegate.charWidth(ch);
			idx++;
		}
		if(remainingWidth<0) idx--;
		int result = !wordwrap || text.length()==idx ? idx : lastWordPos;
		return result;
	}

	@Override
	public int getWidth(String text) {
		return delegate.stringWidth(text);
	}

	@Override
	public float getAscent() {
		return delegate.getAscent();
	}

	@Override
	public float getDescent() {
		return delegate.getLeading();
	}

	@Override
	public float getLeading() {
		return delegate.getDescent();
	}

}
