package com.patsud.melden;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		Intent openDebuggApp = new Intent(this, InClass.class);
		startActivity(openDebuggApp);
	}


}
