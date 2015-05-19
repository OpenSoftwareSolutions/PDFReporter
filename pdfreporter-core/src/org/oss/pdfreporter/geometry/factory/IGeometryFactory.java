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
package org.oss.pdfreporter.geometry.factory;

import org.oss.pdfreporter.geometry.IAffineTransformMatrix;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.geometry.IDimension;
import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.geometry.IStroke;

public interface IGeometryFactory { 
	public enum Rotate90 {
		DEGREE_90,
		DEGREE_180,
		DEGREE_270,
		DEGREE_360
	}
	
	/**
	 * returns a new color.
	 * @param rgb
	 * @return
	 */
	IColor newColor(int rgb);
	/**
	 * returns a new color.
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	IColor newColor(int r, int g, int b);
	/**
	 * returns a new rectangle.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	IRectangle newRectangle(float x, float y, float width, float height);
	/**
	 * returns a new dimension.
	 * @param width
	 * @param height
	 * @return
	 */
	IDimension newDimension(float width, float height);
	/**
	 * returns a new transfomation matrix.
	 * @param m00
	 * @param m01
	 * @param m02
	 * @param m10
	 * @param m11
	 * @param m12
	 * @return
	 */
	IAffineTransformMatrix newAffineTransformMatrix(float m00, float m01, float m02, float m10, float m11, float m12);
	/**
	 * returns a transformation matrix for rotation by 90 degree.
	 * @param x
	 * @param y
	 * @param angle
	 * @return
	 */
	IAffineTransformMatrix newAffineTransformMatrix(float x, float y, Rotate90 angle);
	
	/**
	 * returns a new stroke.
	 * @param width
	 * @param cap
	 * @param join
	 * @param miterlimit
	 * @param dash
	 * @param dash_phase
	 * @return
	 */
	IStroke newStroke(float width, int cap, int join, float miterlimit, float dash[], float dash_phase);
	
	/**
	 * returns a new stroke.
	 * @param width
	 * @param cap
	 * @param join
	 * @return
	 */
	IStroke newStroke(float width, int cap, int join);
}
