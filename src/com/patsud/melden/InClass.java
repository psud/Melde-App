package com.patsud.melden;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InClass extends Activity implements OnClickListener {

	Button rundeDran, dran, gemeldet, gemeldetDran;
	TextView clock, notifText;
	Button notifCheck, notifCross;
	RelativeLayout notifBoxL;

	Button bFertig, bEinstellung, bHa;
	LinearLayout bewertungLayout, leftLayout, rightLayout;
	Button bewGut, bewOk, bewSchlecht, bewFrage, bewSpringen, bewLosch;
	ImageView mitteBild;

	WakeLock wL;
	private int batteryLevel;

	PercentView percentage;

	private int meldDran, meld, nDran, rundDran;

	private int startTime, endTime, secondTime;
	private String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		CheckSettings();

		setContentView(R.layout.inclass);

		Initialize();

		InitialiseListeners();

		GetStartFinishTime();

		AnimateCircle();

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
		
		View rootView = getWindow().getDecorView();
		rootView.setSystemUiVisibility(View.STATUS_BAR_HIDDEN); //
		
		// Get User Name
		userName = getPrefs.getString("name", "Batman");
		userName = userName.substring(0, 1).toUpperCase()
				+ userName.substring(1);

	}

	private void GetTimeChanged() {
		// TODO Auto-generated method stub
		clock.addTextChangedListener(new TextWatcher() {

			SharedPreferences getPrefs = PreferenceManager
					.getDefaultSharedPreferences(getBaseContext());

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				UpdateCircle();
			}
		});
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		// Left
		clock = (TextView) findViewById(R.id.digClock);
		// timeRemaining = (TextView) findViewById(R.id.tvRemaining);
		// malGemeldet = (TextView) findViewById(R.id.tvMalGemeldet);
		// malDran = (TextView) findViewById(R.id.tvMalDran);
		bFertig = (Button) findViewById(R.id.bFertig);
		bEinstellung = (Button) findViewById(R.id.bEinstellungen);
		bHa = (Button) findViewById(R.id.bHaAufschreiben);

		// Right
		// normal
		rightLayout = (LinearLayout) findViewById(R.id.llayoutRightNormal);
		rundeDran = (Button) findViewById(R.id.bRundeDran);
		dran = (Button) findViewById(R.id.bDran);
		gemeldet = (Button) findViewById(R.id.bGemeldet);
		gemeldetDran = (Button) findViewById(R.id.bMeldDran);
		// bewertet
		bewertungLayout = (LinearLayout) findViewById(R.id.lLayoutRightBewertung);
		bewertungLayout.setVisibility(View.GONE);
		bewGut = (Button) findViewById(R.id.bAfGut);
		bewOk = (Button) findViewById(R.id.bAfOk);
		bewSchlecht = (Button) findViewById(R.id.bAfSchlecht);
		bewFrage = (Button) findViewById(R.id.bAfFrage);
		bewSpringen = (Button) findViewById(R.id.bAfSkip);
		bewLosch = (Button) findViewById(R.id.bAfLosch);
		// Bottom Notification
		notifText = (TextView) findViewById(R.id.notifText);
		notifCheck = (Button) findViewById(R.id.notifButtonCheck);
		notifCross = (Button) findViewById(R.id.notifButtonFalse);
		notifBoxL = (RelativeLayout) findViewById(R.id.notifLayout);
		// downLayout = (LinearLayout) findViewById(R.id.llButDown);
		// animation bewertung
		leftLayout = (LinearLayout) findViewById(R.id.llLeftBoxes);
		//leftInfoLayout = (LinearLayout) findViewById(R.id.llLeftInfo);

		// Middle Image
		percentage = (PercentView) findViewById(R.id.percentview);

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

		// BewertungListeners
		bewGut.setOnClickListener(this);
		bewOk.setOnClickListener(this);
		bewSchlecht.setOnClickListener(this);
		bewFrage.setOnClickListener(this);
		bewSpringen.setOnClickListener(this);
		bewLosch.setOnClickListener(this);

		clock.setOnClickListener(this);
		notifCross.setOnClickListener(this);
		notifCheck.setOnClickListener(this);

		/*
		 * bFertig.setOnLongClickListener(new View.OnLongClickListener() {
		 * 
		 * @Override public boolean onLongClick(View v) { // TODO Auto-generated
		 * method stub ShowNotifBox("Fertig", "fertig", false, false,false);
		 * return false; } });
		 *///leftLayout.setOnTouchListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bRundeDran:
			PutInSQL("4", "00");
			ShowDownBewertung();
			percentage.setDot(3);
			break;
		case R.id.bDran:
			PutInSQL("3", "00");
			ShowDownBewertung();
			percentage.setDot(2);
			break;
		case R.id.bGemeldet:
			meld++;
			SomeChange();
			PutInSQL("2", "00");
			percentage.setDot(1);
			break;
		case R.id.bMeldDran:
			meldDran++;
			meld++;
			SomeChange();
			PutInSQL("1", "00");
			ShowDownBewertung();
			percentage.setDot(0);
			break;
		case R.id.bEinstellungen:
			Intent openPrefs = new Intent(this, Einstellungen.class);
			startActivity(openPrefs);
			break;
		case R.id.bFertig:
			Intent openFertig = new Intent(this, Fertig.class);
			startActivity(openFertig);
			break;
		case R.id.bHaAufschreiben:
			Intent openCircle = new Intent(this, HaSchreibenNormal.class);
			Bundle bndlanimation = ActivityOptions.makeCustomAnimation(
					getApplicationContext(), R.anim.activityout,
					R.anim.activityin).toBundle();
			startActivity(openCircle, bndlanimation);
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
		case R.id.bAfSkip:
			CloseBewertung();
			CancelAutoMovementBewertung();
			break;
		case R.id.bAfLosch:
			percentage.DeleteLast();
			CloseBewertung();
			CancelAutoMovementBewertung();
			break;
		case R.id.digClock:

			ShowNotifBox(clockClicked(), "date", false, true, false);
			break;
		case R.id.notifButtonFalse:
			DissapearNotifBox();
			CancelAutoMovementNotif();
			break;
		case R.id.notifButtonCheck:
			ProcessTrueNotif();
			Log.d("Debug", "Got pressed drirketly");
			break;
		}
	}

/*	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("Debug", "GOES IN");
		switch (v.getId()) {
		// the button. I set bFertig.setOnTouchListener(this); in onCreate
		
		case R.id.llLeftBoxes:
			//Log.d("Debug", "Pressed Down");
			
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// everything works up to here
				//Log.d("Debug", "PRESSED");
		//		final Animation animRight = AnimationUtils.loadAnimation(this,
			//			R.anim.slidelefttoright);
				waitTimerNotif = new CountDownTimer(500, 500) {
					@Override
					public void onTick(long arg0) {
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						// here im checking if the button still is pressed
						if (bFertig.isPressed()) {
							// It never goes into here
							//leftInfoLayout.setVisibility(View.VISIBLE);
							//leftInfoLayout.startAnimation(animRight);
							 ShowNotifBox("Fertig", "fertig", false, false,
							 false);
							
							Log.d("Debug", "HELD LONG");
						}
					}
				}.start();
			}
			if (event.getAction() == MotionEvent.ACTION_UP) {
				// DissapearNotifBox();
			//	leftInfoLayout.setVisibility(View.GONE);
			//	Animation animLeft = AnimationUtils.loadAnimation(this,
			//			R.anim.slideawaytoleft);
			//	leftInfoLayout.setAnimation(animLeft);
			//	Log.d("Debug", "MotionEvent.ACTION_UP");
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				Log.d("Debug", "eventY: "+Integer.toString((int) event.getY())+" - vYBot: "+Integer.toString(v.getBottom()/2));
			//	if ( event.getX() > v.getRight()
				//		|| event.getY() < 0|| event.getY() > v.getBottom()/2
				//		){
					Log.d("Debug", "ACTION_MOVE AWATY");
					DissapearNotifBox();
				//}

			}
			break;
		}
		return false;
	}*/

	private CountDownTimer waitTimerNotif;
	private String notifType = null;

	private void ShowNotifBox(String text, String type, boolean showCheck,
			boolean autoAway, boolean longl) {
		// TODO Auto-generated method stub

		notifText.setText(text);
		notifType = type;
		if (showCheck)
			notifCheck.setVisibility(View.VISIBLE);
		else
			notifCheck.setVisibility(View.GONE);
		notifBoxL.setVisibility(View.VISIBLE);
		Animation animUp = AnimationUtils.loadAnimation(this,
				R.anim.animdowntotop);
		notifBoxL.startAnimation(animUp);
		int waitTime;
		if (longl)
			waitTime = 10000;
		else
			waitTime = 4000;
		if (autoAway) {
			waitTimerNotif = new CountDownTimer(waitTime, 500) {
				@Override
				public void onTick(long arg0) {
				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					DissapearNotifBox();
				}
			}.start();
		}
	}

	protected void DissapearNotifBox() {
		// TODO Auto-generated method stub
		if (notifBoxL.isShown()) {
			Animation animDown = AnimationUtils.loadAnimation(this,
					R.anim.animtoptodown);
			notifBoxL.startAnimation(animDown);
			notifBoxL.setVisibility(View.GONE);
		}
	}

	private void CancelAutoMovementNotif() {
		// TODO Auto-generated method stub
		if (waitTimerNotif != null) {
			waitTimerNotif.cancel();
			waitTimerNotif = null;
		}
	}

	private String clockClicked() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		String thedate = "";
		thedate = WeekDayText(c.get(Calendar.DAY_OF_WEEK) - 1);

		thedate += " den ";
		thedate += Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + "."
				+ Integer.toString(c.get(Calendar.MONTH) + 1) + "."
				+ Integer.toString(c.get(Calendar.YEAR));

		int nowSec = (Integer.parseInt(clock.getText().toString()
				.substring(0, 2)) * 60 + Integer.parseInt(clock.getText()
				.toString().substring(3, 5))) * 60;
		int remainingSec = endTime - nowSec;
		remainingSec = remainingSec / 60;
		thedate += "\nNoch " + Integer.toString(remainingSec)
				+ " Minuten bis zum Ende";

		return thedate;
	}

	private String WeekDayText(int weekInt) {
		// TODO Auto-generated method stub
		String weekDay = null;
		switch (weekInt) {
		case 1:
			weekDay = "Montag";
			break;
		case 2:
			weekDay = "Dienstag";
			break;
		case 3:
			weekDay = "Mittwoch";
			break;
		case 4:
			weekDay = "Donnerstag";
			break;
		case 5:
			weekDay = "Freitag";
			break;
		case 6:
			weekDay = "Samstag";
			break;
		case 7:
			weekDay = "Sonntag";
			break;
		}
		return weekDay;
	}

	private void ShowDownBewertung() {
		// TODO Auto-generated method stub
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		if (getPrefs.getBoolean("bewertung", true))
			AnimateBewwertungen();
	}

	private CountDownTimer waitTimer;

	private void AnimateBewwertungen() {
		// TODO Auto-generated method stub
		bewertungLayout.setVisibility(View.VISIBLE);
		Animation animLeft = AnimationUtils.loadAnimation(this,
				R.anim.animationleft);
		bewertungLayout.startAnimation(animLeft);

		waitTimer = new CountDownTimer(10000, 500) {
			@Override
			public void onTick(long arg0) {
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				CloseBewertung();
			}
		}.start();
	}

	private void AnimateCircle() {
		// TODO Auto-generated method stub
		Animation animBig = AnimationUtils.loadAnimation(this,
				R.anim.animationscalebig);
		percentage.startAnimation(animBig);
	}

	private void AddGoodness(int goodness) {
		// TODO Auto-generated method stub
		// String s = sql
		// Get Last Row num

		CancelAutoMovementBewertung();
		percentage.setGoodness(goodness - 1);

		int totalRows = SqlHandler.KEY_ROWNUM;
		long longRow = Long.valueOf(totalRows);

		SqlHandler handler = new SqlHandler(this);
		handler.open();
		handler.updateEntry(goodness);
		handler.close();

		// hide buttons
		CloseBewertung();
	}

	private void CancelAutoMovementBewertung() {
		// TODO Auto-generated method stub
		if (waitTimer != null) {
			waitTimer.cancel();
			waitTimer = null;
		}
	}

	private void CloseBewertung() {
		// TODO Auto-generated method stub
		// hide buttons
		bewertungLayout.setVisibility(View.GONE);
		Animation animRight = AnimationUtils.loadAnimation(this,
				R.anim.animationright);
		bewertungLayout.startAnimation(animRight);
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

	private int remainingMin;

	protected void UpdateCircle() {
		// TODO Auto-generated method stub

		float percentage = CalPercentage();

		this.percentage.setPercentage(percentage);
	}

	private void GetStartFinishTime() {
		TimeKeeper time = new TimeKeeper();
		startTime = time.TimeStart();
		endTime = time.TimeEnd();
		secondTime = time.TimeSecond();
	}

	private boolean showNextHourNotif = false;

	private float CalPercentage() {
		// TODO Auto-generated method stub
		float pers = 0;

		Calendar c = Calendar.getInstance();
		int seconds = c.get(Calendar.SECOND);

		float nowTotalMin = (Integer.parseInt(clock.getText().toString()
				.substring(0, 2)) * 60 + Integer.parseInt(clock.getText()
				.toString().substring(3, 5)))
				* 60 + seconds;

		if (nowTotalMin == endTime - 1005) {

			ShowNotifBox(
					"Nur noch " + Integer.toString(endTime - (int) nowTotalMin)
							+ " Sekunden\nLos! Einmal Melden schaffst du noch "
							+ userName, "melden", false, true, true);
		} else if (nowTotalMin > endTime - 1005 && nowTotalMin < endTime - 995) {
			notifText.setText("Nur noch "
					+ Integer.toString(endTime - (int) nowTotalMin)
					+ " Sekunden\nLos! Einmal Melden schaffst du noch "
					+ userName);
		}

		Log.d("Debug",
				"Now: " + Float.toString(nowTotalMin) + " --- Start: "
						+ Integer.toString(endTime) + " --- End: "
						+ Integer.toString(startTime) + " --- Diff: "
						+ Float.toString(endTime - nowTotalMin)
						+ " --- secondTime: " + Integer.toString(secondTime));
		if (endTime < nowTotalMin && showNextHourNotif == false) {
			ShowNotifBox("Neue Stunde Anfangen?", "neuestunde", true, false,
					false);
			Log.d("Debug", "GOT IT DONE");
			showNextHourNotif = true;
		}

		pers = ((nowTotalMin - startTime) / secondTime) * 100;
		// Log.d("Debug", "ProzentKreis: " + Float.toString(pers));
		return pers;
	}

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

		remainingMin = endminInt;

		RemainingColor();
	}

	private void ProcessTrueNotif() {
		// TODO Auto-generated method stub
		if (notifType.equalsIgnoreCase("neuestunde")) {
			percentage.ResetCircle();
			GetStartFinishTime();
		}

	}

	private void RemainingColor() {

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// Setting show background Color
		/*
		 * if (getPrefs.getBoolean("showRemainingColor", true)) { // TODO
		 * Auto-generated method stub if (remainingMin > 30)
		 * timeRemaining.setBackgroundColor(Color.WHITE); else if (remainingMin
		 * > 15) timeRemaining.setBackgroundColor(Color.YELLOW); else if
		 * (remainingMin > 5) timeRemaining.setBackgroundColor(Color.rgb(0, 200,
		 * 0)); else if (remainingMin > 0)
		 * timeRemaining.setBackgroundColor(Color.GREEN); else if (remainingMin
		 * == 0) timeRemaining.setBackgroundColor(Color.WHITE); } else
		 * timeRemaining.setBackgroundColor(Color.WHITE);
		 */
	}

	private void SomeChange() {
		// TODO Auto-generated method stub
		// malGemeldet.setText(Integer.toString(meld) + "x\ngemeldet");
		// malDran.setText(Integer.toString(meldDran) + "x\ndavon dran");
	}

	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context c, Intent i) {
			int level = i.getIntExtra("level", 0);
			batteryLevel = level;
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		boolean doneSth = false;
		if (bewertungLayout.isShown()) {
			bewertungLayout.setVisibility(View.GONE);
			Animation animRight = AnimationUtils.loadAnimation(this,
					R.anim.animationright);
			bewertungLayout.startAnimation(animRight);
			doneSth = true;
		}
		if (notifBoxL.isShown()) {
			DissapearNotifBox();
			doneSth = true;
		}
		if (!doneSth)
			super.onBackPressed();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		View rootView = getWindow().getDecorView();
		rootView.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		super.onResume();
	}

}
