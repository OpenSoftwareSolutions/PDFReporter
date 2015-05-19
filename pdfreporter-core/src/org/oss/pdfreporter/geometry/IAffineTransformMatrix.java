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

/**
 * A matrix to do a 2D transformation of coordinates.
 * The coordinates are transformed as follows:
 * <pre>
 *	[ x']   [  m00  m01  m02  ] [ x ]   [ m00*x + m01*y + m02 ]
 *	[ y'] = [  m10  m11  m12  ] [ y ] = [ m10*x + m11*y + m12 ]
 *	[ 1 ]   [   0    0    1   ] [ 1 ]   [           1         ]
 * </pre>
 * 
 * Translation:
 * <pre>
 *	[ x']   [   1    0    tx  ] [ x ]   [ x + tx]
 *	[ y'] = [   0    1    ty  ] [ y ] = [ y + ty]
 *	[ 1 ]   [   0    0    1   ] [ 1 ]   [   1   ]
 * </pre>
 * 
 * Scaling:
 * <pre>
 *	[ x']   [   sx   0    0   ] [ x ]   [ x * sx]
 *	[ y'] = [   0    sy   0   ] [ y ] = [ y * sy]
 *	[ 1 ]   [   0    0    1   ] [ 1 ]   [   1   ]
 * </pre>
 * 
 * Rotation:
 * <pre>
 *	[ x']   [  cos -sin   0   ] [ x ]   [ x * cos - y * sin]
 *	[ y'] = [  sin  cos   0   ] [ y ] = [ x * sin + y * cos]
 *	[ 1 ]   [   0    0    1   ] [ 1 ]   [         1        ]
 * </pre>
 * 
 * @author donatmuller, 2013, last change 6:49:34 AM
 * 
 */
public interface IAffineTransformMatrix {
	float getM00();
	float getM01();
	float getM02();
	float getM10();
	float getM11();
	float getM12();
}
