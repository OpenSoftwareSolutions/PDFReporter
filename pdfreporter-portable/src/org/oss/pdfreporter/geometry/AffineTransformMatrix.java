/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.geometry;

import org.oss.pdfreporter.geometry.factory.IGeometryFactory.Rotate90;


public class AffineTransformMatrix implements IAffineTransformMatrix {

	private final float m[][];
	
	AffineTransformMatrix(float m00, float m01, float m02, float m10, float m11, float m12) {
		this.m = new float[2][3];
		m[0][0] = m00;
		m[0][1] = m01;
		m[0][2] = m02;
		m[1][0] = m10;
		m[1][1] = m11;
		m[1][2] = m12;
	}
	
	@Override
	public float getM00() {
		return m[0][0];
	}

	@Override
	public float getM01() {
		return m[0][1];
	}

	@Override
	public float getM02() {
		return m[0][2];
	}

	@Override
	public float getM10() {
		return m[1][0];
	}

	@Override
	public float getM11() {
		return m[1][1];
	}

	@Override
	public float getM12() {
		return m[1][2];
	}
	
	static AffineTransformMatrix getInstance(float x, float y, Rotate90 angle) {
		float m00 = 1;
		float m01 = 0;
		float m02 = x;
		float m10 = 0;
		float m11 = 1;
		float m12 = y;
		float M0 = m00;
		switch (angle) {
		case DEGREE_90: 
			m00 = m01;
			m01 = -M0;
			M0 = m10;
			m10 = m11;
			m11 = -M0;			
		    m02 = -y * m01 + m02;
		    m12 = -x * m10 + m12;
		    break;
		case DEGREE_180: 
			m00 = -m00;
			m11 = -m11;			
		    m02 = -x * m00 + m02;
		    m12 = -y * m11 + m12;
		    break;
		case DEGREE_270: 
			m00 = -m01;
			m01 = M0;
			M0 = m10;
			m10 = -m11;
			m11 = M0;
		    m02 = -y * m01 + m02;
		    m12 = -x * m10 + m12;
		    break;
		case DEGREE_360:
		}
		return new AffineTransformMatrix(m00,m01,m02,m10,m11,m12);
	}
}
