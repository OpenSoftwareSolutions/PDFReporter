package org.oss.pdfreporter.font;

/**
 * Default font metric.
 * @author donatmuller, 2013, last change 1:40:26 AM
 * 
 */
public abstract class AbstractFontMetric implements IFontMetric {

	@Override
	public float getAscent() {
		return 1.0f;
	}

	@Override
	public float getDescent() {
		return 0f;
	}

	@Override
	public float getLeading() {
		return 0.25f;
	}

}
