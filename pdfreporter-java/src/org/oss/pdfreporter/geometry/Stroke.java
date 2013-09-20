package org.oss.pdfreporter.geometry;


public class Stroke implements IStroke {

	public final java.awt.BasicStroke delegate;

	Stroke(float width, int cap, int join, float miterlimit,
			float[] dash, float dash_phase) {
		this.delegate = new java.awt.BasicStroke(width, cap, join, miterlimit,
				dash, dash_phase);
	}

	Stroke(float width, int cap, int join) {
		this.delegate = new java.awt.BasicStroke(width, cap, join);
	}
	
	@Override
	public float getDashPhase() {
		return delegate.getDashPhase();
	}

	@Override
	public float[] getDashArray() {
		return delegate.getDashArray();
	}

	@Override
	public float getLineWidth() {
		return delegate.getLineWidth();
	}

	@Override
	public float getMiterLimit() {
		return delegate.getMiterLimit();
	}

	@Override
	public int getLineJoin() {
		return delegate.getLineJoin();
	}

	@Override
	public int getEndCap() {
		return delegate.getEndCap();
	}

}
