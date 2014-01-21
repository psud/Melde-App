package com.patsud.melden.customview;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

import com.patsud.melden.R;

public class PercentView extends View {

	Context c;

	public PercentView(Context context) {
		super(context);
		this.c = context;
		init();
	}

	public PercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.c = context;
		init();
	}

	public PercentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.c = context;
		init();
	}

	private Paint paint;
	private Paint bgpaint;
	private Paint p2Stroke;
	private Paint pText;
	private RectF rect;
	private SharedPreferences prefs;
	private float percentage = 5;
	private Paint circlePaint;
	private int dotsNum[] = { -1, -1 };
	private float [][] dots = new float[2][1000];
	private String[][] colorCode = new String[2][1000];
	private String[][] colorGoodness = new String[2][1000];
	private int pxCut = 75;
	private int smallCircle = 200;
	private boolean twoPlayer = false;
	private boolean showScore;

	private void init() {
		prefs = PreferenceManager.getDefaultSharedPreferences(c);
		paint = new Paint();
		paint.setColor(Color.parseColor(c.getString(R.color.main)));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		bgpaint = new Paint();
		bgpaint.setColor(Color.parseColor(c.getString(R.color.main_dark)));
		bgpaint.setAntiAlias(true);
		bgpaint.setStyle(Paint.Style.FILL);
		p2Stroke = new Paint();
		p2Stroke.setColor(Color.parseColor(c.getString(R.color.background)));
		p2Stroke.setAntiAlias(true);
		p2Stroke.setStyle(Paint.Style.STROKE);
		p2Stroke.setStrokeWidth(1);
		rect = new RectF();
		circlePaint = new Paint();
		pText = new Paint();
		pText.setColor(Color.parseColor(c.getString(R.color.white)));
		pText.setTextSize(65);
		pText.setAntiAlias(true);
		pText.setTextAlign(Align.CENTER);

		boolean scoreDefault;
		scoreDefault = c.getResources().getBoolean(R.bool.showscore_default);
		showScore = prefs.getBoolean("showScore", scoreDefault);
	}

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
		if (twoPlayer) {
			canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2,
					((this.getWidth() - pxCut) - smallCircle) / 2, p2Stroke);
		}

		if (percentage != 0) {

			for (int i = 0; i <= dotsNum[0]; i++) {
				circlePaint.setAntiAlias(true);
				circlePaint.setColor(Color.parseColor(colorCode[0][i]));
				circlePaint.setStyle(Paint.Style.FILL);
				// calculation for dots as coordinates
				float dotX = (float) (this.getWidth() / 2 + (this.getWidth() - pxCut)
						/ 2 * Math.cos((dots[0][i] * 3.6 - 90) * Math.PI / 180));
				float dotY = (float) (this.getHeight() / 2 + (this.getWidth() - pxCut)
						/ 2 * Math.sin((dots[0][i] * 3.6 - 90) * Math.PI / 180));
				canvas.drawCircle(dotX, dotY, 35, circlePaint);

				// For outer Stroke color
				// check Settings
				SharedPreferences getPrefs = PreferenceManager
						.getDefaultSharedPreferences(getContext());

				if (getPrefs.getBoolean("bewertungKreis", true)) {
					if (!colorGoodness[0][i].equalsIgnoreCase("#ffffff")) {
						if (colorCode[0][i]
								.equalsIgnoreCase(colorGoodness[0][i])) {
							circlePaint.setAntiAlias(true);
							circlePaint.setColor(Color.parseColor("#ffffff"));
							circlePaint.setStyle(Paint.Style.STROKE);
							circlePaint.setStrokeWidth(4);
							canvas.drawCircle(dotX, dotY, 32, circlePaint);
						}
						circlePaint.setAntiAlias(true);
						circlePaint.setColor(Color
								.parseColor(colorGoodness[0][i]));
						circlePaint.setStyle(Paint.Style.STROKE);
						circlePaint.setStrokeWidth(8);
						canvas.drawCircle(dotX, dotY, 35, circlePaint);
					}
				}
			}
			if (twoPlayer) {
				if (showScore) {
					int pointP1 = dotsNum[0] + 1;
					int pointP2 = dotsNum[1] + 1;
					String pointP1Str = String.valueOf(pointP1);
					String pointP2Str = String.valueOf(pointP2);
					String score;
					score = pointP1Str + " : " + pointP2Str;
					canvas.drawText(score, this.getWidth() / 2,
							this.getHeight() / 2, pText);
				}
				for (int i = 0; i <= dotsNum[1]; i++) {
					circlePaint.setAntiAlias(true);
					circlePaint.setColor(Color.parseColor(colorCode[1][i]));
					circlePaint.setStyle(Paint.Style.FILL);
					// calculation for dots as coordinates
					float dotX = (float) (this.getWidth() / 2 + (this
							.getWidth() - pxCut - smallCircle)
							/ 2
							* Math.cos((dots[1][i] * 3.6 - 90) * Math.PI / 180));
					float dotY = (float) (this.getHeight() / 2 + (this
							.getWidth() - pxCut - smallCircle)
							/ 2
							* Math.sin((dots[1][i] * 3.6 - 90) * Math.PI / 180));
					canvas.drawCircle(dotX, dotY, 35, circlePaint);

					// For outer Stroke color
					// check Settings
					// SharedPreferences getPrefs = PreferenceManager
					// .getDefaultSharedPreferences(getContext());
					//
					// if (getPrefs.getBoolean("bewertungKreis", true)) {
					// if (!colorGoodness[1][i].equalsIgnoreCase("#ffffff")) {
					// if (colorCode[1][i]
					// .equalsIgnoreCase(colorGoodness[1][i])) {
					// circlePaint.setAntiAlias(true);
					// circlePaint.setColor(Color
					// .parseColor("#ffffff"));
					// circlePaint.setStyle(Paint.Style.STROKE);
					// circlePaint.setStrokeWidth(4);
					// // circlePaint.setStyle(Paint.Style.STROKE);
					// canvas.drawCircle(dotX, dotY, 32, circlePaint);
					// }
					//
					// circlePaint.setAntiAlias(true);
					// circlePaint.setColor(Color
					// .parseColor(colorGoodness[1][i]));
					// circlePaint.setStyle(Paint.Style.STROKE);
					// circlePaint.setStrokeWidth(8);
					// // circlePaint.setStyle(Paint.Style.STROKE);
					// canvas.drawCircle(dotX, dotY, 35, circlePaint);
					// }
					// }
				}
			}
		}
	}

	public void setTwoPlayer(boolean twoPlayerIn) {
		this.twoPlayer = twoPlayerIn;
	}

	public void setPercentage(float inpercentage) {
		this.percentage = inpercentage;
		invalidate();
	}

	public void setDot(int type, int playerNum) {
		dotsNum[playerNum]++;

		switch (type) {
		case 0:
			String green;
			green = c.getString(R.color.green);
			colorCode[playerNum][dotsNum[playerNum]] = green;
			break;
		case 1:
			String yellow;
			yellow = c.getString(R.color.yellow);
			colorCode[playerNum][dotsNum[playerNum]] = yellow;
			break;
		case 2:
			String red;
			red = c.getString(R.color.red);
			colorCode[playerNum][dotsNum[playerNum]] = red;
			break;
		case 3:
			String dark;
			dark = c.getString(R.color.dark);
			colorCode[playerNum][dotsNum[playerNum]] = dark;
			break;
		}
		colorGoodness[playerNum][dotsNum[playerNum]] = "#ffffff";

		dots[playerNum][dotsNum[playerNum]] = percentage;
		invalidate();
	}

	public void setGoodness(int type, int playerNum) {
		switch (type) {
		case 0:
			String green;
			green = c.getString(R.color.green);
			colorGoodness[playerNum][dotsNum[playerNum]] = green;
			break;
		case 1:
			String yellow;
			yellow = c.getString(R.color.yellow);
			colorGoodness[playerNum][dotsNum[playerNum]] = yellow;
			break;
		case 2:
			String red;
			red = c.getString(R.color.red);
			colorGoodness[playerNum][dotsNum[playerNum]] = red;
			break;
		case 3:
			String dark;
			dark = c.getString(R.color.dark);
			colorGoodness[playerNum][dotsNum[playerNum]] = dark;
			break;
		}
		invalidate();
	}

	public void DeleteLast(int playerNum) {
		dotsNum[playerNum]--;
		invalidate();
	}

	public void ResetCircle() {
		dotsNum[0] = 0;
		dotsNum[1] = 0;
		invalidate();
	}
}