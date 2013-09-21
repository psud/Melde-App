package com.patsud.melden;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;

public class InClass extends Activity implements OnClickListener, NoCopySpan {

	Button rundeDran, dran, gemeldet, gemeldetDran;
	TextView clock, timeRemaining, malGemeldet, malDran;
	Button bFertig, bEinstellung, bHa;
	LinearLayout downLayout;
	Button downGut, downOk, downSchlecht, downFrage;

	WakeLock wL;
	int batteryLevel;

	int meldDran, meld, nDran, rundDran;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		CheckSettings();

		setContentView(R.layout.inclass);

		Initialize();
		InitialiseListeners();

		GetTimeChanged();

		registerReceiver(mBatInfoReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));

	}

	private void CheckSettings() {
		// TODO Auto-generated method stub
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// FULL SCREEN
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (getPrefs.getBoolean("showBar", true) == false)
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Keep Screen On
		if (getPrefs.getBoolean("screenStayOn", false)) {
			PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
			wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
			wL.acquire();
		}

	}

	int showWhat = 0;

	private void GetTimeChanged() {
		// TODO Auto-generated method stub
		clock.addTextChangedListener(new TextWatcher() {

			SharedPreferences getPrefs = PreferenceManager
					.getDefaultSharedPreferences(getBaseContext());

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

				if (batteryLevel < 21
						&& getPrefs.getBoolean("showBattery", true)) {
					// if ( getPrefs.getBoolean("showBattery", true)){
					showWhat++;
					if (showWhat % 4 == 0 || showWhat % 4 == 1) {
						CalEndTime();
						timeRemaining.setBackgroundColor(Color.WHITE);
					} else {
						timeRemaining.setText("Battery Level: "
								+ Integer.toString(batteryLevel) + "%");
						if (batteryLevel < 5)
							timeRemaining.setBackgroundColor(Color.RED);
						else if (batteryLevel < 15)
							timeRemaining.setBackgroundColor(Color.YELLOW);
						else if (batteryLevel < 20)
							timeRemaining.setBackgroundColor(Color.LTGRAY);
					}

				} else if (getPrefs.getBoolean("showBatteryDebug", true)) {
					showWhat++;
					if (showWhat % 4 == 0 || showWhat % 4 == 1) {
						timeRemaining.setBackgroundColor(Color.WHITE);
						CalEndTime();
					} else {
						timeRemaining.setText("Battery Level: "
								+ Integer.toString(batteryLevel) + "%");
						if (batteryLevel < 5)
							timeRemaining.setBackgroundColor(Color.RED);
						else if (batteryLevel < 15)
							timeRemaining.setBackgroundColor(Color.YELLOW);
						else if (batteryLevel < 101)
							timeRemaining.setBackgroundColor(Color.LTGRAY);
					}
				}

				else
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

		// Bottom
		downLayout = (LinearLayout) findViewById(R.id.llButDown);
		downGut = (Button) findViewById(R.id.bAfGut);
		downOk = (Button) findViewById(R.id.bAfOk);
		downSchlecht = (Button) findViewById(R.id.bAfSchlecht);
		downFrage = (Button) findViewById(R.id.bAfFrage);

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

		// BottomListeners
		downGut.setOnClickListener(this);
		downOk.setOnClickListener(this);
		downSchlecht.setOnClickListener(this);
		downFrage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bRundeDran:
			PutInSQL("4", "00");
			ShowDownBewertung();
			break;
		case R.id.bDran:
			PutInSQL("3", "00");
			ShowDownBewertung();

			break;
		case R.id.bGemeldet:
			meld++;
			SomeChange();
			PutInSQL("2", "00");
			break;
		case R.id.bMeldDran:
			meldDran++;
			meld++;
			SomeChange();
			PutInSQL("1", "00");
			ShowDownBewertung();
			break;
		case R.id.bEinstellungen:
			Intent openPrefs = new Intent(this, Einstellungen.class);
			startActivity(openPrefs);
			break;
		case R.id.bFertig:
			Intent openFertig = new Intent(this, Fertig.class);
			startActivity(openFertig);
			break;
		// cases for down Buttons
		case R.id.bAfGut:
			AddGoodness(1);
			break;
		case R.id.bAfOk:
			AddGoodness(2);
			break;
		case R.id.bAfSchlecht:
			AddGoodness(3);
			break;
		case R.id.bAfFrage:
			AddGoodness(4);
			break;
		}
	}

	private void ShowDownBewertung() {
		// TODO Auto-generated method stub
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		int howBewert = Integer.parseInt(getPrefs.getString("bewertung", "2"));
	//	int da = getPrefs.getLong(key, defValue)
		if (howBewert == 2)
			downLayout.setVisibility(1);
	}

	private void AddGoodness(int goodness) {
		// TODO Auto-generated method stub
		// String s = sql
		// Get Last Row num
		int totalRows = SqlHandler.KEY_ROWNUM;
		long longRow = Long.valueOf(totalRows);

		SqlHandler handler = new SqlHandler(this);
		handler.open();
		handler.updateEntry(goodness);
		handler.close();

		// hide buttons
		downLayout.setVisibility(View.INVISIBLE);

		FeedbackClick(goodness);

	}

	private void FeedbackClick(int goodness) {
		// TODO Auto-generated method stub

	}

	private void PutInSQL(String kind, String goodness) {
		// TODO Auto-generated method stub
		try {
			// Get Date and Time
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd.MM.yy HH:mm:ss");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			String dateStr = dateFormat.format(cal.getTime());

			SqlHandler entry = new SqlHandler(this);
			entry.open();
			entry.createEntry(dateStr, kind, goodness);
			entry.close();
		} catch (Exception e) {

		}
	}

	int remainingMin;

	private void CalEndTime() {

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// Get Endtime
		String endtime = getPrefs.getString("endtime", "12:00");

		// TODO Auto-generated method stub
		String endMin = endtime.substring(3, 5);
		String endHour = endtime.substring(0, 2);
		String nowHour = clock.getText().toString().substring(0, 2);
		String nowMin = clock.getText().toString().substring(3, 5);

		int endminInt = Integer.parseInt(endMin);
		int endHourInt = Integer.parseInt(endHour);

		endminInt = endminInt - Integer.parseInt(nowMin);
		endHourInt = endHourInt - Integer.parseInt(nowHour);
		endminInt = endminInt + endHourInt * 60;

		timeRemaining
				.setText((Integer.toString(endminInt) + " min\nremaining"));

		remainingMin = endminInt;

		RemainingColor();
	}

	private void RemainingColor() {

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// Setting show background Color
		if (getPrefs.getBoolean("showRemainingColor", true)) {
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
		} else
			timeRemaining.setBackgroundColor(Color.WHITE);
	}

	private void SomeChange() {
		// TODO Auto-generated method stub
		malGemeldet.setText(Integer.toString(meld) + "x\ngemeldet");
		malDran.setText(Integer.toString(meldDran) + "x\ndavon dran");
	}

	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context c, Intent i) {
			int level = i.getIntExtra("level", 0);
			batteryLevel = level;
		}
	};
}