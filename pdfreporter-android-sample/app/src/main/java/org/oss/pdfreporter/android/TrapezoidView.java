package org.oss.pdfreporter.android;

import org.oss.pdfreporter.android.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TrapezoidView extends View{

	private final float mLeftYRatio;
	private final Paint mPaint;
	private final Path mPath;
	
	public TrapezoidView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setColor(getResources().getColor(R.color.bg_light));
		mPaint.setStyle(Style.FILL);
		mPaint.setAntiAlias(true);
		mPath = new Path();
		mLeftYRatio = (float)347/453;
	}
		
	@Override
	protected void onDraw(Canvas canvas) {
		mPath.reset();
		mPath.moveTo(0, 0);
		mPath.lineTo(0, getHeight()*mLeftYRatio);
		mPath.lineTo(getWidth(), getHeight());
		mPath.lineTo(getWidth(), 0);
		mPath.close();
		canvas.drawPath(mPath, mPaint);
	}

}
