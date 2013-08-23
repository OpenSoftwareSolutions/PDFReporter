package org.oss.pdfreporter.geometry;

import org.oss.pdfreporter.registry.ApiRegistry;

public interface IColor {
	
	public enum Transparency {
		OPAQUE,
		BITMASK,
		TRANSLUCENT
	}
	
	/**
	 * The color white.  In the default sRGB space.
	 */
	IColor white = ApiRegistry.getGeometryFactory().newColor(255, 255, 255);
	/**
	 * The color white.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor WHITE = white;
	/**
	 * The color light gray.  In the default sRGB space.
	 */
	IColor lightGray = ApiRegistry.getGeometryFactory().newColor(192, 192, 192);
	/**
	 * The color light gray.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor LIGHT_GRAY = lightGray;
	/**
	 * The color gray.  In the default sRGB space.
	 */
	IColor gray = ApiRegistry.getGeometryFactory().newColor(128, 128, 128);
	/**
	 * The color gray.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor GRAY = gray;
	/**
	 * The color dark gray.  In the default sRGB space.
	 */
	IColor darkGray = ApiRegistry.getGeometryFactory().newColor(64, 64, 64);
	/**
	 * The color dark gray.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor DARK_GRAY = darkGray;
	/**
	 * The color black.  In the default sRGB space.
	 */
	IColor black = ApiRegistry.getGeometryFactory().newColor(0, 0, 0);
	/**
	 * The color black.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor BLACK = black;
	/**
	 * The color red.  In the default sRGB space.
	 */
	IColor red = ApiRegistry.getGeometryFactory().newColor(255, 0, 0);
	/**
	 * The color red.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor RED = red;
	/**
	 * The color pink.  In the default sRGB space.
	 */
	IColor pink = ApiRegistry.getGeometryFactory().newColor(255, 175, 175);
	/**
	 * The color pink.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor PINK = pink;
	/**
	 * The color orange.  In the default sRGB space.
	 */
	IColor orange = ApiRegistry.getGeometryFactory().newColor(255, 200, 0);
	/**
	 * The color orange.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor ORANGE = orange;
	/**
	 * The color yellow.  In the default sRGB space.
	 */
	IColor yellow = ApiRegistry.getGeometryFactory().newColor(255, 255, 0);
	/**
	 * The color yellow.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor YELLOW = yellow;
	/**
	 * The color green.  In the default sRGB space.
	 */
	IColor green = ApiRegistry.getGeometryFactory().newColor(0, 255, 0);
	/**
	 * The color green.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor GREEN = green;
	/**
	 * The color magenta.  In the default sRGB space.
	 */
	IColor magenta = ApiRegistry.getGeometryFactory().newColor(255, 0, 255);
	/**
	 * The color magenta.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor MAGENTA = magenta;
	/**
	 * The color cyan.  In the default sRGB space.
	 */
	IColor cyan = ApiRegistry.getGeometryFactory().newColor(0, 255, 255);
	/**
	 * The color cyan.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor CYAN = cyan;
	/**
	 * The color blue.  In the default sRGB space.
	 */
	IColor blue = ApiRegistry.getGeometryFactory().newColor(0, 0, 255);
	/**
	 * The color blue.  In the default sRGB space.
	 * @since 1.4
	 */
	IColor BLUE = blue;
	
	int getRed();

	int getGreen();

	int getBlue();

	int getAlpha();

	int getRGB();

	Transparency getTransparency();
	
}
