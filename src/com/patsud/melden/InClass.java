package com.patsud.melden;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class InClass extends Activity implements OnClickListener, NoCopySpan {

	Button rundeDran, dran, gemeldet, gemeldetDran;
	TextView clock, timeRemaining, malGemeldet, malDran;
	Button bFertig, bEinstellung, bHa;

	WakeLock wL;

	int meldDran, meld, nDran, rundDran;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// FULL SCREEN
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Keep Screen On
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");

		wL.acquire();

		setContentView(R.layout.inclass);

		Initialize();
		InitialiseListeners();

		GetTimeChanged();

	}
	private void GetTimeChanged() {
		// TODO Auto-generated method stub
		clock.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				CalEndTime();
			}
		});
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		// Left
		clock = (TextView) findViewById(R.id.digClock);
		timeRemaining = (TextView) findViewById(R.id.tvRemaining);
		malGemeldet = (TextView) findViewById(R.id.tvMalGemeldet);
		malDran = (TextView) findViewById(R.id.tvMalDran);
		bFertig = (Button) findViewById(R.id.bFertig);
		bEinstellung = (Button) findViewById(R.id.bEinstellungen);
		bHa = (Button) findViewById(R.id.bHaAufschreiben);

		// Right
		rundeDran = (Button) findViewById(R.id.bRundeDran);
		dran = (Button) findViewById(R.id.bDran);
		gemeldet = (Button) findViewById(R.id.bGemeldet);
		gemeldetDran = (Button) findViewById(R.id.bMeldDran);

	}

	private void InitialiseListeners() {
		// TODO Auto-generated method stub 
		rundeDran.setOnClickListener(this);
		dran.setOnClickListener(this);
		gemeldet.setOnClickListener(this);
		gemeldetDran.setOnClickListener(this);
		bFertig.setOnClickListener(this);
		bEinstellung.setOnClickListener(this);
		bHa.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bRundeDran:
			timeRemaining.setBackgroundColor(Color.DKGRAY);
			CalEndTime();
			break;
		case R.id.bDran:
			timeRemaining.setBackgroundColor(Color.RED);
			break;
		case R.id.bGemeldet:
			timeRemaining.setBackgroundColor(Color.YELLOW);
			meld++;
			SomeChange();
			break;
		case R.id.bMeldDran:
			timeRemaining.setBackgroundColor(Color.GREEN);
			meldDran++;
			meld++;
			SomeChange();
			break;
		case R.id.bEinstellungen:
			Intent openPrefs = new Intent(this, Einstellungen.class);
			startActivity(openPrefs);
			// break;
		}
	}

	int remainingMin;

	private void CalEndTime() {
		// TODO Auto-generated method stub
		int nowMinInt;
		String nowHour = clock.getText().toString().substring(0, 2);
		String nowMin = clock.getText().toString().substring(3, 5);
		final Calendar c = Calendar.getInstance();
		nowMinInt = c.get(Calendar.MINUTE);
		remainingMin = 60 - nowMinInt;
		timeRemaining.setText(Integer.toString(remainingMin)
				+ " min\nremaining");

		RemainingColor();
	}

	private void RemainingColor() {
		// TODO Auto-generated method stub
		if (remainingMin > 30)
			timeRemaining.setBackgroundColor(Color.WHITE);
		else if (remainingMin > 15)
			timeRemaining.setBackgroundColor(Color.YELLOW);
		else if (remainingMin > 5)
			timeRemaining.setBackgroundColor(Color.rgb(0, 200, 0));
		else if (remainingMin > 0)
			timeRemaining.setBackgroundColor(Color.GREEN);
		else if (remainingMin == 0)
			timeRemaining.setBackgroundColor(Color.WHITE);
	}

	private void SomeChange() {
		// TODO Auto-generated method stub
		malGemeldet.setText(Integer.toString(meld) + "x\ngemeldet");
		malDran.setText(Integer.toString(meldDran) + "x\ndavon dran");
	}
}