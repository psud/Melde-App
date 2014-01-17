package com.patsud.melden;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class BattleStart extends Activity {



	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		CheckSettings();

		setContentView(R.layout.battlestart);

		Intent i = new Intent(this, BattleMode.class);
		startActivity(i);

	}

	private void CheckSettings() {
		// TODO Auto-generated method stub
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		// Hide Action Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Hide Notification Bar
		boolean showBar = prefs.getBoolean("showBar", true);
		if (!showBar)
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Keep Screen On
		boolean screenStayOn = prefs.getBoolean("screenStayOn", false);
		if (screenStayOn) {
			PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
			WakeLock wL;
			// WindowManager wM =
			// WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
			wL.acquire();
		}
	}

}
