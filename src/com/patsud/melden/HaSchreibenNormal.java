package com.patsud.melden;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
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

	Button abgabeDay;
	ImageView erinnerungTrue, erinnerungFalse;
	LinearLayout erinnerungLayout;
	TextView erinnerung;
	private boolean erinnerungOn = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(R.layout.actionbarha);
		setContentView(R.layout.haschreibennormal);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		InitFacher();
		SetFaecherColors();
		InitAufgabe();	
		InitRest();
		ListenersFach();
		ListenersAufgabe();
		GetPrefs();

	}

	private void GetPrefs() {
		// TODO Auto-generated method stub

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// Voreinstellung Erinnerung
		if (getPrefs.getBoolean("erinnerungStd", false)==false) {
			erinnerungOn = false;
			erinnerungTrue.setVisibility(View.INVISIBLE);
			erinnerungFalse.setVisibility(View.VISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#e74c3c"));
			erinnerung.setText("Erinnerung Aus");
		} else {
			erinnerungOn = true;
			erinnerungTrue.setVisibility(View.VISIBLE);
			erinnerungFalse.setVisibility(View.INVISIBLE);
			erinnerungLayout.setBackgroundColor(Color.parseColor("#27ae60"));
			erinnerung.setText("Erinnerung An");
		}
	}

	private void ListenersFach() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			faecher[i].setOnClickListener(this);
		}
		final Intent openColors = new Intent(this,  FachColors.class);
		neuFach.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(openColors);
			}
		});
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
			faecher[i].setBackgroundColor(Color.parseColor(faecherFarben[i]));
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

	private void InitRest() {
		// TODO Auto-generated method stub
		abgabeDay = (Button) findViewById(R.id.haBAbgabe);
		abgabeDay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowDateDialog();
			}
		});

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
			erinnerungLayout.setBackgroundColor(Color.parseColor("#e74c3c"));

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
			erinnerungLayout.setBackgroundColor(Color.parseColor("#27ae60"));

		}

	}

	private void ListenersAufgabe() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			aufgabe[i].addTextChangedListener(new CustomTextWatcher(i));
			aufgabe[i].setImeActionLabel("PAT", KeyEvent.KEYCODE_ENTER);
			aufgabe[i].setOnEditorActionListener(onEnterListener);

		}

	}

	int bla = 0;
	// Listener for Enter Pressed
	// ////////////?NOT WORKING//////////
	OnEditorActionListener onEnterListener = new OnEditorActionListener() {
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
		int thisone = 0;
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
		}

		SetFaecherColors();
		faecher[thisone].setAlpha(1);

	}

	// ///////////////////////////////////// DATE DIALOG
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mWeekDay;
	static final int DATE_DIALOG_ID = 1;
	static boolean firstlauf = true;

	private void ShowDateDialog() {
		// TODO Auto-generated method stub

		if (firstlauf) {
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			mWeekDay = c.get(Calendar.DAY_OF_WEEK);
			firstlauf = false;
		}
		showDialog(DATE_DIALOG_ID);

		updateDisplay();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {

		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {

		case DATE_DIALOG_ID:
			((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);

			break;
		}
	}

	private void updateDisplay() {
		String weekday = GetWeekday();
		final Calendar today = Calendar.getInstance();

		abgabeDay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append("Abgabe: ").append(mDay).append(".").append(mMonth + 1)
				.append(".").append(mYear).append(" ")
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
	private String GetWeekday() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		String thedate = "";

		switch (mWeekDay) {
		case 1:
			thedate = "Montag";
			break;
		case 2:
			thedate = "Dienstag";
			break;
		case 3:
			thedate = "Mittwoch";
			break;
		case 4:
			thedate = "Donnerstag";
			break;
		case 5:
			thedate = "Freitag";
			break;
		case 6:
			thedate = "Samstag";
			break;
		case 7:
			thedate = "Sonntag";
			break;
		}
		return thedate;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			abgabeDay.setText(Integer.toString(mDay));
			updateDisplay();
		}

	};

}



