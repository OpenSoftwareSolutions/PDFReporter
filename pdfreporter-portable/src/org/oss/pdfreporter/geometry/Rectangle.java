package org.oss.pdfreporter.geometry;


public class Rectangle implements IRectangle {

	private final float x, y, width, height;
	
	Rectangle(float x, float y, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public IRectangle getBounds() {
		return this;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Rectangle [x=" + x + ", y=" + y + ", width=" + width
				+ ", height=" + height + "]";
	}

}
