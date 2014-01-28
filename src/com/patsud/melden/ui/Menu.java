package com.patsud.melden.ui;

import com.patsud.melden.Einstellungen;
import com.patsud.melden.R;
import com.patsud.melden.R.id;
import com.patsud.melden.R.layout;
import com.patsud.melden.deprecated.HaSchreibenNormal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

	Button inclass, homework, test, hwView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		MuteDevice();
		final Intent startActivity = new Intent(this, BattleStart.class);
		final Intent b1Activity = new Intent(this, InClass.class);
		final Intent b2Activity = new Intent(this, HaSchreibenNormal.class);
		final Intent b3Activity = new Intent(this, BattleStart.class);
		final Intent b4Activity = new Intent(this, Einstellungen.class);
	//	startActivity(startActivity);

		inclass = (Button) findViewById(R.id.button1);
		homework = (Button) findViewById(R.id.button2);
		test = (Button) findViewById(R.id.button3);
		hwView = (Button) findViewById(R.id.button4);

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
				String component = "com.gabrielittner.timetable/com.gabrielittner.timetable.ui.MainActivity";
				Intent iHomework = new Intent();
				iHomework.setAction(component);
				iHomework.putExtra("navigation", 3);
//					iHomework.putExtra("addtask", true);
			    iHomework.setComponent(ComponentName.unflattenFromString(component));
			    startActivity(iHomework);

			
			}
			
		});
		test.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(b3Activity);
			}
		});
		hwView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(b4Activity);
			}
		});

	}

	private void MuteDevice() {
		// TODO Auto-generated method stub
		if (true){
			AudioManager audioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			
		}
	}

}
