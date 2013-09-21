package com.patsud.melden;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
	
	private boolean mShowText = false;
	//private int mTextPos = TEXTPOS_LEFT;

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CircleView, 0, 0);

		try {
			mShowText = a.getBoolean(R.styleable.CircleView_showText, false);
		//	mTextPos = a.getInteger(R.styleable.CircleView_labelPosition, 0);
		} finally {
			a.recycle();
		}

	}
	
	public boolean isShowText(){
		return mShowText;
	}
	
	public void setShowText (boolean showText){
		mShowText = showText;
		invalidate();
		requestLayout();
	}
}