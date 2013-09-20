package org.oss.pdfreporter.geometry;

import java.awt.geom.Rectangle2D;

public class Rectangle implements IRectangle {
	private final Rectangle2D delegate;

	Rectangle(float x, float y, float width, float height) {
		this.delegate = new Rectangle2D.Float(x, y, width, height);
	}
	
	@Override
	public IRectangle getBounds() {
		return this;
	}

	@Override
	public float getX() {
		return (float) delegate.getX();
	}

	@Override
	public float getY() {
		return (float) delegate.getY();
	}

	@Override
	public float getWidth() {
		return (float) delegate.getWidth();
	}

	@Override
	public float getHeight() {
		return (float) delegate.getHeight();
	}

}
