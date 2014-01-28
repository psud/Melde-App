package com.patsud.melden.deprecated;

import java.util.Calendar;

import com.patsud.melden.R;
import com.patsud.melden.R.anim;
import com.patsud.melden.R.id;
import com.patsud.melden.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HaSchreibenNormal extends Activity implements OnClickListener {

	Button[] faecher = new Button[20];
	Button neuFach;
	EditText[] aufgabe = new EditText[10];

	Button abgabeDay, abgabeMo, abgabeDi, abgabeMi, abgabeDo, abgabeFr,
			abgabeWe;
	ImageView erinnerungTrue, erinnerungFalse;
	LinearLayout erinnerungLayout;
	TextView erinnerung;
	private boolean erinnerungOn = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		FullScreenSetting();
		setContentView(R.layout.haschreibennormal);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


		InitFacher();
		SetFaecherColors();
		InitAufgabe();
		DatumInit();
		InitErinnerung();
		ListenersFach();
		ListenersAufgabe();
		GetPrefs();

	}

	

	private void FullScreenSetting() {
		// TODO Auto-generated method stub
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (getPrefs.getBoolean("showBar", true) == false)
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void DatumInit() {
		// TODO Auto-generated method stub

		abgabeDay = (Button) findViewById(R.id.haBAbgabe);
		abgabeMo = (Button) findViewById(R.id.haBAbMontag);
		abgabeDi = (Button) findViewById(R.id.haBAbDienstag);
		abgabeMi = (Button) findViewById(R.id.haBAbMittwoch);
		abgabeDo = (Button) findViewById(R.id.haBAbDonnerstag);
		abgabeFr = (Button) findViewById(R.id.haBAbFreitag);
		abgabeWe = (Button) findViewById(R.id.haBAbWochenende);

		abgabeMo.setOnClickListener(this);
		abgabeDi.setOnClickListener(this);
		abgabeMi.setOnClickListener(this);
		abgabeDo.setOnClickListener(this);
		abgabeFr.setOnClickListener(this);
		abgabeWe.setOnClickListener(this);

		setAllDatesOff();

		abgabeDay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowDateDialog();
			}
		});

	}

	private void GetPrefs() {
		// TODO Auto-generated method stub

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// Voreinstellung Erinnerung
		if (getPrefs.getBoolean("erinnerungStd", false) == false) {
			erinnerungOn = false;
			erinnerungTrue.setVisibility(View.INVISIBLE);
			erinnerungFalse.setVisibility(View.VISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#2980b9"));
			erinnerung.setText("Erinnerung Aus");
		} else {
			erinnerungOn = true;
			erinnerungTrue.setVisibility(View.VISIBLE);
			erinnerungFalse.setVisibility(View.INVISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#3498db"));
			erinnerung.setText("Erinnerung An");
		}

		// FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// if (getPrefs.getBoolean("showBar", true) == false)
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void ListenersFach() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			faecher[i].setOnClickListener(this);
		}
//		final Intent openColors = new Intent(this, FachColors.class);
//		neuFach.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				startActivity(openColors);
//			}
//		});
	}

	private void SetFaecherColors() {
		// TODO Auto-generated method stub
		// String[][] faecherName = new String[][] {
		String[] faecherName = { "Englisch", "Deutsch", "Mathe", "Geschichte",
				"PoWi", "Religion", "Physik", "Bio", "Programmieren", "Sport" };

		String[] faecherFarben = { "#f1c40f", "#e74c3c", "#3498db", "#8e44ad",
				"#f39c12", "#e67e22", "#34495e", "#27ae60", "#2980b9",
				"#1abc9c" };

		for (int i = 0; i < faecherName.length; i++) {
			faecher[i].setText(faecherName[i]);
			if (i % 2 == 0)
				faecher[i].setBackgroundColor(Color.parseColor("#3498db"));
			// faecher[i].setBackgroundColor(Color.parseColor(faecherFarben[i]));
			else
				faecher[i].setBackgroundColor(Color.parseColor("#2980b9"));
			faecher[i].setAlpha((float) 0.5);
		}
		for (int i = faecherName.length; i < 20; i++)
			faecher[i].setVisibility(View.GONE);

		neuFach.setBackgroundColor(Color.parseColor("#95a5a6"));
		neuFach.setText("Fach Hinzufügen");
	}

	private void InitFacher() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 20; i++) {
			int resId = getResources().getIdentifier("haBFach" + (i + 1), "id",
					"com.patsud.melden");
			faecher[i] = (Button) findViewById(resId);
		}
		neuFach = (Button) findViewById(R.id.haBFachNeu);

	}

	private void InitAufgabe() {
		// TODO Auto-generated method stub
		aufgabe[0] = (EditText) findViewById(R.id.haEtAufgabe1);
		aufgabe[1] = (EditText) findViewById(R.id.haEtAufgabe2);
		aufgabe[2] = (EditText) findViewById(R.id.haEtAufgabe3);
		aufgabe[3] = (EditText) findViewById(R.id.haEtAufgabe4);
		aufgabe[4] = (EditText) findViewById(R.id.haEtAufgabe5);
		aufgabe[5] = (EditText) findViewById(R.id.haEtAufgabe6);
		aufgabe[6] = (EditText) findViewById(R.id.haEtAufgabe7);
		aufgabe[7] = (EditText) findViewById(R.id.haEtAufgabe8);
		aufgabe[8] = (EditText) findViewById(R.id.haEtAufgabe9);
		aufgabe[9] = (EditText) findViewById(R.id.haEtAufgabe10);
	}

	private void InitErinnerung() {
		// TODO Auto-generated method stub
		erinnerungTrue = (ImageView) findViewById(R.id.erinnerungTrue);
		erinnerungFalse = (ImageView) findViewById(R.id.erinnerungFalse);
		erinnerungLayout = (LinearLayout) findViewById(R.id.erinnerungLayout);
		erinnerung = (TextView) findViewById(R.id.ErinnerungTv);
		erinnerungLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ErinnerungButtonChange();
			}
		});

	}

	protected void ErinnerungButtonChange() {
		// TODO Auto-generated method stub
		if (erinnerungOn) {
			erinnerungOn = false;
			erinnerung.setText("Erinnerung Aus");
			Animation animaway = AnimationUtils.loadAnimation(this,
					R.anim.animationdown);
			erinnerungTrue.startAnimation(animaway);
			erinnerungTrue.setVisibility(View.INVISIBLE);
			Animation animcome = AnimationUtils.loadAnimation(this,
					R.anim.animationfromup);
			erinnerungFalse.startAnimation(animcome);
			erinnerungFalse.setVisibility(View.VISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#2980b9"));

		} else {
			erinnerungOn = true;
			erinnerung.setText("Erinnerung An");
			Animation animaway = AnimationUtils.loadAnimation(this,
					R.anim.animationdown);
			erinnerungFalse.startAnimation(animaway);
			erinnerungTrue.startAnimation(animaway);
			erinnerungTrue.setVisibility(View.VISIBLE);
			Animation animcome = AnimationUtils.loadAnimation(this,
					R.anim.animationfromup);
			erinnerungTrue.startAnimation(animcome);
			erinnerungFalse.setVisibility(View.INVISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#3498db"));

		}

	}

	private void ListenersAufgabe() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			aufgabe[i].addTextChangedListener(new CustomTextWatcher(i));
		//	aufgabe[i].setImeActionLabel("PAT", KeyEvent.KEYCODE_ENTER);
		//	aufgabe[i].setOnEditorActionListener(onEnterListener);

		}
		

	}

	int bla = 0;
	// Listener for Enter Pressed
	// ////////////?NOT WORKING//////////
/*	OnEditorActionListener onEnterListener = new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			boolean handled = false;
			if (actionId == KeyEvent.KEYCODE_ENTER) {
				// Handle pressing "Enter" key here
				bla++;
				aufgabe[0].setText(Integer.toString(bla));
				handled = true;
			}
			return handled;
		}
	};
*/
	// Class
	private class CustomTextWatcher implements TextWatcher {
		private int one;

		public CustomTextWatcher(int e) {
			one = e;
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (aufgabe[one].length() == 0 && aufgabe[one + 1].length() == 0)
				aufgabe[one + 1].setVisibility(View.GONE);
			if (aufgabe[one].length() > 0)
				aufgabe[one + 1].setVisibility(View.VISIBLE);
		}

		public void afterTextChanged(Editable s) {

		}
	}

	@Override
	public void onClick(View v) {
		int thisone = -1;
		switch (v.getId()) {
		case R.id.haBFach1:
			thisone = 0;
			break;
		case R.id.haBFach2:
			thisone = 1;
			break;
		case R.id.haBFach3:
			thisone = 2;
			break;
		case R.id.haBFach4:
			thisone = 3;
			break;
		case R.id.haBFach5:
			thisone = 4;
			break;
		case R.id.haBFach6:
			thisone = 5;
			break;
		case R.id.haBFach7:
			thisone = 6;
			break;
		case R.id.haBFach8:
			thisone = 7;
			break;
		case R.id.haBFach9:
			thisone = 8;
			break;
		case R.id.haBFach10:
			thisone = 9;
			break;
		case R.id.haBFach11:
			thisone = 10;
			break;
		case R.id.haBFach12:
			thisone = 11;
			break;

		case R.id.haBAbMontag:
			setAllDatesOff();
			calDay(2);
			abgabeMo.setAlpha(1);
			break;

		case R.id.haBAbDienstag:
			setAllDatesOff();
			calDay(3);
			abgabeDi.setAlpha(1);
			break;

		case R.id.haBAbMittwoch:
			setAllDatesOff();
			calDay(4);
			abgabeMi.setAlpha(1);
			break;

		case R.id.haBAbDonnerstag:
			setAllDatesOff();
			calDay(5);
			abgabeDo.setAlpha(1);
			break;

		case R.id.haBAbFreitag:
			setAllDatesOff();
			calDay(6);
			abgabeFr.setAlpha(1);
			break;
		case R.id.haBAbWochenende:
			setAllDatesOff();
			calDay(8);
			abgabeWe.setAlpha(1);
			break;
		}

		if (thisone != -1) {
			SetFaecherColors();
			faecher[thisone].setAlpha(1);
		}
	}

	private void calDay(int dd) {
		// TODO Auto-generated method stub

		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		int mWeekDay = c.get(Calendar.DAY_OF_WEEK);

		int dayToday = mWeekDay;
		if (dd < dayToday + 1)
			dd += 7;
		int plusDays = dd - dayToday;
		// abgabeDay.setText(Integer.toString(plusDays));

		abgabeDay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append("Abgabe: ").append(mDay + plusDays).append(".")
				.append(mMonth + 1).append(".").append(mYear).append(" "));

	}

	private void setAllDatesOff() {
		// TODO Auto-generated method stub
		abgabeMo.setAlpha((float) 0.5);
		abgabeDi.setAlpha((float) 0.5);
		abgabeMi.setAlpha((float) 0.5);
		abgabeDo.setAlpha((float) 0.5);
		abgabeFr.setAlpha((float) 0.5);
		abgabeWe.setAlpha((float) 0.5);
	}

	// ///////////////////////////////////// DATE DIALOG
	private int mYearDia;
	private int mMonthDia;
	private int mDayDia;
	static final int DATE_DIALOG_ID = 1;
	static boolean firstlauf = true;

	private void ShowDateDialog() {
		// TODO Auto-generated method stub

		if (firstlauf) {
			final Calendar c = Calendar.getInstance();
			mYearDia = c.get(Calendar.YEAR);
			mMonthDia = c.get(Calendar.MONTH);
			mDayDia = c.get(Calendar.DAY_OF_MONTH);
			firstlauf = false;
		}
		showDialog(DATE_DIALOG_ID);

		updateDisplay();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {

		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYearDia,
					mMonthDia, mDayDia);
		}
		return null;
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {

		case DATE_DIALOG_ID:
			((DatePickerDialog) dialog)
					.updateDate(mYearDia, mMonthDia, mDayDia);

			break;
		}
	}

	private void updateDisplay() {

		final Calendar today = Calendar.getInstance();

		abgabeDay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append("Abgabe: ").append(mDayDia).append(".")
				.append(mMonthDia + 1).append(".").append(mYearDia).append(" ")
		// .append("\n" + weekday));
				);
		// if
		// (today.get(Calendar.DAY_OF_MONTH)==mDay&&today.get(Calendar.MONTH)==mMonth&&today.get(Calendar.YEAR)==mYear)
		// abgabeDay.setText("Abgabetag: Heute");
		// else if
		// (today.get(Calendar.DAY_OF_MONTH)+1==mDay&&today.get(Calendar.MONTH)==mMonth&&today.get(Calendar.YEAR)==mYear)
		// abgabeDay.setText("Abgabetag: Morgen");
	}

	// ///////??NOT FUNKTIONING

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYearDia = year;
			mMonthDia = monthOfYear;
			mDayDia = dayOfMonth;
			abgabeDay.setText(Integer.toString(mDayDia));
			updateDisplay();
		}

	};

	// /back button pressed
	//@Override
	//public void onBackPressed() {
		// TODO Auto-generated method stub
	//	Intent bactoClass = new Intent(this, InClass.class);
	//	Bundle bndlanimation = ActivityOptions.makeCustomAnimation(
	//			getApplicationContext(), R.anim.homeworkdismiss,
	//			R.anim.slidelefttoright).toBundle();
	//	startActivity(bactoClass, bndlanimation);
		

	//}

}
