package org.oss.pdfreporter.android;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class TrapezoidDrawable extends Drawable{
	
	private final float mLeftYRatio;
	private final Paint mPaint;
	private final Path mPath;
	
	public TrapezoidDrawable(int color, float leftRatio) {
		mLeftYRatio = leftRatio;
		mPaint = new Paint();
		mPaint.setColor(color);
		mPaint.setStyle(Style.FILL);
		mPaint.setAntiAlias(true);
		mPath = new Path();
	}
	
	@Override
	public void draw(Canvas canvas) {
		Rect b = getBounds();
		mPath.reset();
		mPath.moveTo(b.left, b.top);
		mPath.lineTo(b.left, b.bottom*mLeftYRatio);
		mPath.lineTo(b.right, b.bottom);
		mPath.lineTo(b.right, b.top);
		mPath.close();
		canvas.drawPath(mPath, mPaint);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSPARENT;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}

}
