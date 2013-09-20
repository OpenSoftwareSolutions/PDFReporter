package org.oss.pdfreporter.geometry;

import org.oss.pdfreporter.geometry.factory.IGeometryFactory;
import org.oss.pdfreporter.registry.ApiRegistry;


public class GeometryFactory implements IGeometryFactory {

	public static void registerFactory() {
		ApiRegistry.register(new GeometryFactory());
	}
	
	private GeometryFactory() {
		super();
		// protected
	}
	
	@Override
	public IColor newColor(int rgb) {
		return new Color(rgb);
	}

	@Override
	public IColor newColor(int r, int g, int b) {
		return new Color(r,g,b);
	}

	@Override
	public IRectangle newRectangle(float x, float y, float width, float height) {
		return new Rectangle(x,y,width,height);
	}

	@Override
	public IDimension newDimension(float width, float height) {
		return new Dimension(width, height);
	}
	
	@Override
	public IAffineTransformMatrix newAffineTransformMatrix(float m00,
			float m01, float m02, float m10, float m11, float m12) {
		return new AffineTransformMatrix(m00, m01, m02, m10, m11, m12);
	}
	
	@Override
	public IAffineTransformMatrix newAffineTransformMatrix(float x, float y,
			Rotate90 angle) {
		return new AffineTransformMatrix(x, y, angle);
	}

	@Override
	public IStroke newStroke(float width, int cap, int join,
			float miterlimit, float[] dash, float dash_phase) {
		return new Stroke(width, cap, join, miterlimit, dash, dash_phase);
	}

	@Override
	public IStroke newStroke(float width, int cap, int join) {
		return new Stroke(width, cap, join);
	}


}
