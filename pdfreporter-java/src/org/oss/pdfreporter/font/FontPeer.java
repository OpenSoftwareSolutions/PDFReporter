package org.oss.pdfreporter.font;

import java.awt.Font;
import java.awt.FontMetrics;


public class FontPeer implements IFontPeer {

	private final Font delegate;
	private final FontMetrics fontMetric;
	
	FontPeer(Font delegate, FontMetrics metric) {
		super();
		this.delegate = delegate;
		this.fontMetric= metric;
	}

	@Override
	public IFontMetric getMetric() {
		return new FontMetric(fontMetric);
	}

	@Override
	public Object getPeer() {
		return delegate;
	}
}
