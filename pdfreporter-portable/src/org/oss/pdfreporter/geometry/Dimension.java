package org.oss.pdfreporter.geometry;


public class Dimension implements IDimension {

	private final float width, height;
	
	Dimension(float width, float height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

}
