package com.patsud.melden;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BattleMode extends Activity implements OnClickListener {

	// Main Buttons both sides
	private Button p1Runde, p1Dran, p1Gem, p1GemDran;
	private Button p2Runde, p2Dran, p2Gem, p2GemDran;
	// Bewertung button both sides
	private Button p1Gut, p1Okay, p1Schlecht, p1Frage, p1Skip, p1Delete;
	private Button p2Gut, p2Okay, p2Schlecht, p2Frage, p2Skip, p2Delete;
	// Layout Bewertungsn
	private LinearLayout p1Layout, p1Bewertung;
	private LinearLayout p2Layout, p2Bewertung;

	// Circle
	private PercentView circle;

	// Notification
	private RelativeLayout notifLayout;
	private TextView notifText;
	private Button notifTrue, notifFalse;

	private SharedPreferences prefs;

	private int startTime, endTime, secondTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CheckSettings();

		setContentView(R.layout.battlemode);

		Initialize();

		InitialiseListeners();

		GetStartFinishTime();

		AnimateCircle();

		InitUpdateCircle();

		// registerReceiver(mBatInfoReceiver, new IntentFilter(
		// Intent.ACTION_BATTERY_CHANGED));

	}

	private void InitUpdateCircle() {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateCircle();
			}
		}, 0, 1000);// Update text every second
	}

	protected void UpdateCircle() {
		// TODO Auto-generated method stub

	}

	private void AnimateCircle() {
		Animation animBig = AnimationUtils.loadAnimation(this,
				R.anim.animationscalebig);
		circle.startAnimation(animBig);
	}

	private void GetStartFinishTime() {
		// TODO Auto-generated method stub
		TimeKeeper time = new TimeKeeper();
		startTime = time.TimeStart();
		endTime = time.TimeEnd();
		secondTime = time.TimeSecond();
	}

	private void CheckSettings() {
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

	private void Initialize() {
		// Main Buttons Player 1
		p1Runde = (Button) findViewById(R.id.battle1Runde);
		p1Dran = (Button) findViewById(R.id.battle1NurDran);
		p1Gem = (Button) findViewById(R.id.battle1Gemeldet);
		p1GemDran = (Button) findViewById(R.id.battle1GemeldetDran);

		// Main Buttons Player 2
		p2Runde = (Button) findViewById(R.id.battle2Runde);
		p2Dran = (Button) findViewById(R.id.battle2NurDran);
		p2Gem = (Button) findViewById(R.id.battle2Gemeldet);
		p2GemDran = (Button) findViewById(R.id.battle2GemeldetDran);

		// Bewertung Player 1
		p1Gut = (Button) findViewById(R.id.battle1BewGut);
		p1Okay = (Button) findViewById(R.id.battle1BewOk);
		p1Schlecht = (Button) findViewById(R.id.battle1BewSchlecht);
		p1Frage = (Button) findViewById(R.id.battle1BewFrage);
		p1Delete = (Button) findViewById(R.id.battle1BewLosch);
		p1Skip = (Button) findViewById(R.id.battle1BewSkip);

		// Bewertung Player 2
		p2Gut = (Button) findViewById(R.id.battle2BewGut);
		p2Okay = (Button) findViewById(R.id.battle2BewOk);
		p2Schlecht = (Button) findViewById(R.id.battle2BewSchlecht);
		p2Frage = (Button) findViewById(R.id.battle2BewFrage);
		p2Delete = (Button) findViewById(R.id.battle2BewLosch);
		p2Skip = (Button) findViewById(R.id.battle2BewSkip);

		// Layout Bewertung Player 1
		p1Layout = (LinearLayout) findViewById(R.id.battle1Layout);
		p1Bewertung = (LinearLayout) findViewById(R.id.battle1LBewertung);

		// Layout Bewertung Player 2
		p2Layout = (LinearLayout) findViewById(R.id.battle2Layout);
		p2Bewertung = (LinearLayout) findViewById(R.id.battle2LBewertung);

		// Circle
		circle = (PercentView) findViewById(R.id.battleCircle);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private void InitialiseListeners() {
		// Main Buttons Player 1
		p1Runde.setOnClickListener(this);
		p1Dran.setOnClickListener(this);
		p1Gem.setOnClickListener(this);
		p1GemDran.setOnClickListener(this);

		// Main Buttons Player 2
		p2Runde.setOnClickListener(this);
		p2Dran.setOnClickListener(this);
		p2Gem.setOnClickListener(this);
		p2GemDran.setOnClickListener(this);

		// Bewertung Player 1
		p1Gut.setOnClickListener(this);
		p1Okay.setOnClickListener(this);
		p1Schlecht.setOnClickListener(this);
		p1Frage.setOnClickListener(this);
		p1Delete.setOnClickListener(this);
		p1Skip.setOnClickListener(this);

		// Bewertung Player 2
		p2Gut.setOnClickListener(this);
		p2Okay.setOnClickListener(this);
		p2Schlecht.setOnClickListener(this);
		p2Frage.setOnClickListener(this);
		p2Delete.setOnClickListener(this);
		p2Skip.setOnClickListener(this);
	}

}
