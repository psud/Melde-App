package com.patsud.melden;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class HaView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	//	View view = LayoutInflater.from(this).inflate(R.layout.haview, null);
	//	LinearLayout bottomLayout = (LinearLayout) view
	//			.findViewById(R.id.llExampleHaDisplay2);
	//	Button b = new Button(this);
	//	b.setText("I don't do anything, but I was added dynamically. :)");

	//	((ViewGroup) view).addView(b);
	//	((ViewGroup) view).removeView(bottomLayout);
		 setContentView(R.layout.haview);
	
		
		///LinearLayout box = new LinearLayout(this);
		//box.setOrientation(LinearLayout.VERTICAL);
		//box.setBackgroundResource(R.drawable.border);
		// ((ViewGroup)box).addView(b);
	//	((ViewGroup) view).addView(box);
		
		
		
		
	}

}
