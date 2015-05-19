/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.geometry;


public class Color implements IColor {
	
	public final java.awt.Color delegate;

	Color(java.awt.Color delegate) {
		this.delegate = delegate;
	}
	
	public Color(int r, int g, int b) {
		this.delegate = new java.awt.Color(r, g, b);
	}

	public Color(int rgb) {
		this.delegate = new java.awt.Color(rgb);
	}

	@Override
	public int getRed() {
		return delegate.getRed();
	}

	@Override
	public int getGreen() {
		return delegate.getGreen();
	}

	@Override
	public int getBlue() {
		return delegate.getBlue();
	}

	@Override
	public int getAlpha() {
		return delegate.getAlpha();
	}

	@Override
	public int getRGB() {
		return delegate.getRGB();
	}

	@Override
	public Transparency getTransparency() {
		switch (delegate.getTransparency()) {
		case java.awt.Transparency.OPAQUE:
			return Transparency.OPAQUE;
		case java.awt.Transparency.BITMASK:
			return Transparency.BITMASK;
		case java.awt.Transparency.TRANSLUCENT:
			return Transparency.TRANSLUCENT;
		default:
			return Transparency.OPAQUE;			
		}
	}

}
