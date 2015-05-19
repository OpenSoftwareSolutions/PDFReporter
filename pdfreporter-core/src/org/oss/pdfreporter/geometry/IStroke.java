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
 * Temporary line stroke value object.
 * TODO (27.06.2013, Open Software Solutions, Donat) Move functionality to geometry including LineCap and LineJoin from IPage
 * @author donatmuller, 2013, last change 7:46:46 AM
 * 
 */
public interface IStroke {
    /**
     * Joins path segments by extending their outside edges until
     * they meet.
     */
    public final static int JOIN_MITER = 0;

    /**
     * Joins path segments by rounding off the corner at a radius
     * of half the line width.
     */
    public final static int JOIN_ROUND = 1;

    /**
     * Joins path segments by connecting the outer corners of their
     * wide outlines with a straight segment.
     */
    public final static int JOIN_BEVEL = 2;

    /**
     * Ends unclosed subpaths and dash segments with no added
     * decoration.
     */
    public final static int CAP_BUTT = 0;

    /**
     * Ends unclosed subpaths and dash segments with a round
     * decoration that has a radius equal to half of the width
     * of the pen.
     */
    public final static int CAP_ROUND = 1;

    /**
     * Ends unclosed subpaths and dash segments with a square
     * projection that extends beyond the end of the segment
     * to a distance equal to half of the line width.
     */
    public final static int CAP_SQUARE = 2;
    
	/**
	 * @return
	 */
	float getDashPhase();
	/**
	 * @return
	 */
	float[] getDashArray();
	/**
	 * @return
	 */
	float getLineWidth();
	/**
	 * @return
	 */
	float getMiterLimit();
	/**
	 * @return
	 */
	int getLineJoin();
	/**
	 * @return
	 */
	int getEndCap();
	
}
