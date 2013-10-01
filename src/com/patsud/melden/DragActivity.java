package com.patsud.melden;

/*
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
 }*/

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testview);
		findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
		findViewById(R.id.topright).setOnDragListener(new MyDragListener());
		findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
		findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());

	}

	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
			view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	class MyDragListener implements OnDragListener {
		Drawable enterShape = getResources().getDrawable(
				R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// Do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				v.setBackgroundDrawable(enterShape);
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				v.setBackgroundDrawable(normalShape);
				break;
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				v.setBackgroundDrawable(normalShape);
			default:
				break;
			}
			return true;
		}
	}
}