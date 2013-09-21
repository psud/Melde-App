package com.patsud.melden;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Fertig extends Activity
{

	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fertig);
		
		SqlHandler info = new SqlHandler(this);
		info.open();
		String data = info.getData();
		info.close();
		
		String wholeData = null;
		tv = (TextView) findViewById(R.id.textView1);
		tv.setText(data);
		
	}
}
