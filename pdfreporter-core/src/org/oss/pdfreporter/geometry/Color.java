package org.oss.pdfreporter.geometry;



public class Color implements IColor {
	
	private int value;

	Color(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
	}
	
    Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int rgb) {
        value = 0xff000000 | rgb;
    }
   

	@Override
	public int getRed() {
		return (getRGB() >> 16) & 0xFF;
	}

	@Override
	public int getGreen() {
		return (getRGB() >> 8) & 0xFF;
	}

	@Override
	public int getBlue() {
		return (getRGB() >> 0) & 0xFF;
	}

	@Override
	public int getAlpha() {
	       return (getRGB() >> 24) & 0xff;
	}

	@Override
	public int getRGB() {
		return value;
	}

	@Override
	public Transparency getTransparency() {
	       int alpha = getAlpha();
	        if (alpha == 0xff) {
	            return Transparency.OPAQUE;
	        }
	        else if (alpha == 0) {
	            return Transparency.BITMASK;
	        }
	        else {
	            return Transparency.TRANSLUCENT;
	        }
	}

	@Override
	public String toString() {
		return "Color [getRed()=" + getRed() + ", getGreen()=" + getGreen()
				+ ", getBlue()=" + getBlue() + ", getAlpha()=" + getAlpha()
				+ "]";
	}


	
}
