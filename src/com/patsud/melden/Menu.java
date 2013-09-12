package com.patsud.melden;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {
	
	Button inclass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		final Intent openDebuggApp = new Intent(this, InClass.class);
		startActivity(openDebuggApp);
		
		inclass = (Button) findViewById(R.id.button1);
		
		inclass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(openDebuggApp);
			}
		});
		
	}


}
