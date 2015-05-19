/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.geometry;

public class Stroke implements IStroke {

	private final float dashPhase;
	private final float[] dashArray;
	private final float lineWidth;
	private final float miterLimit;
	private final int lineJoin;
	private final int endCap;
	
	
    public Stroke(float width, int cap, int join, float miterlimit,
		       float dash[], float dash_phase) {
    	this.lineWidth = width;
    	this.endCap = cap;
    	this.lineJoin = join;
    	this.miterLimit = miterlimit;
    	this.dashArray = dash;
    	this.dashPhase = dash_phase;
    }
    
    public Stroke(float width, int cap, int join) {
    	this(width, cap, join, 10.0f, null, 0.0f);
    }
	
	public float getDashPhase() {
		return dashPhase;
	}
	public float[] getDashArray() {
		return dashArray;
	}
	public float getLineWidth() {
		return lineWidth;
	}
	public float getMiterLimit() {
		return miterLimit;
	}
	public int getLineJoin() {
		return lineJoin;
	}
	public int getEndCap() {
		return endCap;
	}

	
	
	
}
