package com.patsud.melden;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {
	
	Button inclass, homework;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		final Intent startActivity = new Intent(this,  InClass.class);
		final Intent b1Activity = new Intent(this,  InClass.class);
		final Intent b2Activity = new Intent(this,  HaSchreibenNormal.class);
		startActivity(startActivity);
		
		inclass = (Button) findViewById(R.id.button1);
		homework = (Button) findViewById(R.id.button2);
		
		inclass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(b1Activity);
			}
		});
		homework.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(b2Activity);
			}
		});
		
	}


}
