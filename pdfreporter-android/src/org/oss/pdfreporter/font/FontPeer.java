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

import com.lowagie.text.pdf.BaseFont;

public class FontPeer implements IFontPeer {

	private final BaseFont delegate;
	private final IFontMetric fontMetric;
	
	FontPeer(BaseFont delegate) {
		super();
		this.delegate = delegate;
		this.fontMetric = new FontMetric(delegate);
	}

	@Override
	public IFontMetric getMetric() {
		return fontMetric;
	}

	@Override
	public Object getPeer() {
		return delegate;
	}
	
	private static class FontMetric extends AbstractFontMetric implements IFontMetric {
		private final BaseFont delegate;
		
		private FontMetric(BaseFont delegate) {
			super();
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
				remainingWidth-=delegate.getWidth(ch);
				idx++;
			}
			if(remainingWidth<0) idx--;
			int result = !wordwrap || text.length()==idx ? idx : lastWordPos;
			return result;
		}

		@Override
		public int getWidth(String text) {
			return delegate.getWidth(text);
		}
		
	}

}
