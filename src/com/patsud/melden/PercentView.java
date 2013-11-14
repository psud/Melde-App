package com.patsud.melden;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

public class PercentView extends View {

	public PercentView(Context context) {
		super(context);
		init();
	}

	public PercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PercentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.parseColor("#3498db"));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		bgpaint = new Paint();
		bgpaint.setColor(Color.parseColor("#2980b9"));
		bgpaint.setAntiAlias(true);
		bgpaint.setStyle(Paint.Style.FILL);
		rect = new RectF();
		circlePaint = new Paint();

	}

	Paint paint;
	Paint bgpaint;
	RectF rect;
	float percentage = 5;
	Paint circlePaint;
	float[] dots = new float[1000];
	int dotsNum = -1;
	String[] colorCode = new String[1000];
	String[] colorGoodness = new String[1000];
	int pxCut = 75;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw background circle anyway

		int left = pxCut / 2;
		int width = getWidth() - pxCut;
		int top = pxCut / 2;
		rect.set(left, top, left + width, top + width);
		canvas.drawArc(rect, -90, 360, true, bgpaint);
		canvas.drawArc(rect, -90, (float) (3.6 * percentage), true, paint);

		if (percentage != 0) {

			for (int i = 0; i <= dotsNum; i++) {
				circlePaint.setAntiAlias(true);
				circlePaint.setColor(Color.parseColor(colorCode[i]));
				circlePaint.setStyle(Paint.Style.FILL);
				// calculation for dots as coordinates
				float dotX = (float) (this.getWidth() / 2 + (this.getWidth() - pxCut)
						/ 2 * Math.cos((dots[i] * 3.6 - 90) * Math.PI / 180));
				float dotY = (float) (this.getHeight() / 2 + (this.getWidth() - pxCut)
						/ 2 * Math.sin((dots[i] * 3.6 - 90) * Math.PI / 180));
				canvas.drawCircle(dotX, dotY, 35, circlePaint);

				// For outer Stroke color
				// check Settings
				SharedPreferences getPrefs = PreferenceManager
						.getDefaultSharedPreferences(getContext());

				if (getPrefs.getBoolean("bewertungKreis", true)) {
					if (!colorGoodness[i].equalsIgnoreCase("#ffffff")) {
						if (colorCode[i].equalsIgnoreCase(colorGoodness[i])){
						circlePaint.setAntiAlias(true);
						circlePaint.setColor(Color.parseColor("#ffffff"));
						circlePaint.setStyle(Paint.Style.STROKE);
						circlePaint.setStrokeWidth(4);
						// circlePaint.setStyle(Paint.Style.STROKE);
						canvas.drawCircle(dotX, dotY, 32, circlePaint);
						}

						circlePaint.setAntiAlias(true);
						circlePaint
								.setColor(Color.parseColor(colorGoodness[i]));
						circlePaint.setStyle(Paint.Style.STROKE);
						circlePaint.setStrokeWidth(8);
						// circlePaint.setStyle(Paint.Style.STROKE);
						canvas.drawCircle(dotX, dotY, 35, circlePaint);
					}
				}
			}
		}
	}

	public void setPercentage(float inpercentage) {
		this.percentage = inpercentage;
		invalidate();
	}

	public void setDot(int type) {
		dotsNum++;

		switch (type) {
		case 0:
			colorCode[dotsNum] = "#27ae60";
			break;
		case 1:
			colorCode[dotsNum] = "#f1c40f";
			break;
		case 2:
			colorCode[dotsNum] = "#e74c3c";
			break;
		case 3:
			colorCode[dotsNum] = "#34495e";
			break;
			 
		}
		colorGoodness[dotsNum] = "#ffffff";

		dots[dotsNum] = percentage;
		invalidate();
	}

	public void setGoodness(int type) {
		switch (type) {
		case 0:
			colorGoodness[dotsNum] = "#27ae60";
			break;
		case 1:
			colorGoodness[dotsNum] = "#f1c40f";
			break;
		case 2:
			colorGoodness[dotsNum] = "#e74c3c";
			break;
		case 3:
			colorGoodness[dotsNum] = "#34495e";
			break;
		}
		invalidate();
	}
	
	public void DeleteLast(){
		dotsNum--;
		invalidate();
	}
	public void ResetCircle(){
		dotsNum = 0;
		invalidate();
	}
}