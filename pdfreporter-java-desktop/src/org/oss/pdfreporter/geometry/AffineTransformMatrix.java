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

import java.awt.geom.AffineTransform;

import org.oss.pdfreporter.geometry.factory.IGeometryFactory.Rotate90;


public class AffineTransformMatrix implements IAffineTransformMatrix {
	
	private final AffineTransform delegate;
 
	AffineTransformMatrix(float m00, float m01, float m02, float m10, float m11, float m12) {
		this.delegate = new AffineTransform(m00, m10, m01,m11,m02,m12);
	}
	
	AffineTransformMatrix(float x, float y, Rotate90 angle) {
		this.delegate = AffineTransform.getQuadrantRotateInstance(toQuadrand(angle), x, y);
	}
	
	private int toQuadrand(Rotate90 angle) {
		switch(angle) {
		case DEGREE_360:
			return 0;
		case DEGREE_90:
			return 1;
		case DEGREE_180:
			return 2;
		case DEGREE_270:
			return 3;
		default:
			return 0;
		}
	}
	
	@Override
	public float getM00() {
		return (float) delegate.getScaleX();
	}

	@Override
	public float getM01() {
		return (float) delegate.getShearX();
	}

	@Override
	public float getM02() {
		return (float) delegate.getTranslateX();
	}

	@Override
	public float getM10() {
		return (float) delegate.getShearY();
	}

	@Override
	public float getM11() {
		return (float) delegate.getScaleY();
	}

	@Override
	public float getM12() {
		return (float) delegate.getTranslateY();
	}
}
