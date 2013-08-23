package org.oss.pdfreporter.font;

public class ScaledFontMetric implements IFontMetric {

	private final IFontMetric baseFontMetric;
	private float fontSize;
	
	ScaledFontMetric(IFontMetric baseFontMetric, float fontSize) {
		super();
		this.baseFontMetric = baseFontMetric;
		this.fontSize = fontSize;
	}

	@Override
	public int measureText(String text, int width, boolean wordwrap) {
		return baseFontMetric.measureText(text, width, wordwrap);
	}

	@Override
	public int getWidth(String text) {
		return baseFontMetric.getWidth(text);
	}

	@Override
	public float getAscent() {
		return baseFontMetric.getAscent() * fontSize;
	}

	@Override
	public float getDescent() {
		return baseFontMetric.getDescent() * fontSize;
	}

	@Override
	public float getLeading() {
		return baseFontMetric.getLeading() * fontSize;
	}
	
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

}
