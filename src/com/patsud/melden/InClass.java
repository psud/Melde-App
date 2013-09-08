package com.patsud.melden;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class InClass extends Activity implements OnClickListener {

	Button rundeDran, dran, gemeldet, gemeldetDran;
	TextView timeRemaining, malGemeldet, malDran;
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

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		// Left
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
			meldDran++; meld++;
			SomeChange();
			break;
		case R.id.bEinstellungen:

			// Intent openPrefs = new Intent(this)
			// break;
		}
	}

	private void SomeChange() {
		// TODO Auto-generated method stub
		malGemeldet.setText(Integer.toString(meld)+"x\ngemeldet");
		malDran.setText(Integer.toString(meldDran)+"x\ndavon dran");
	}
}