package com.patsud.melden;

import java.util.Calendar;

import android.R.bool;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HaSchreibenNormal extends Activity implements OnClickListener {

	Button[] faecher = new Button[20];
	EditText[] aufgabe = new EditText[10];

	Button abgabeDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.haschreibennormal);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		InitFacher();
		SetFaecherColors();
		InitAufgabe();
		InitRest();
		ListenersFach();
		ListenersAufgabe();

	}
 
	private void ListenersFach() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			faecher[i].setOnClickListener(this);
		}
	}

	private void SetFaecherColors() {
		// TODO Auto-generated method stub
		// String[][] faecherName = new String[][] {
		String[] faecherName = { "Englisch", "Deutsch", "Mathe", "Geschichte",
				"PoWi", "Religion", "Physik", "Bio", "Programmieren", "Sport" };

		// 2d ArrayTest
	/*	int[] faecherFarben = { R.drawable.backgraufach,
				R.drawable.backrotfach, R.drawable.backblaufach,
				R.drawable.backlilafach, R.drawable.backgelbfach,
				R.drawable.backgraufach, R.drawable.backschwarzfach,
				R.drawable.backgruenfach, R.drawable.backgraufach,
				R.drawable.backgraufach };
*/
		String[] faecherFarben = {"#bdc3c7",
				"#e74c3c", "#3498db",
				"#8e44ad", "#f1c40f",
				"#e67e22","#2c3e50",
				"#27ae60","#34495e",
				"#16a085" };
		// { "grau", "red",fae "blue", "lila",
		// "PoWi", "Religion", "Physik", "Chemie", "Bio", "Programmieren",
		// "Sport" },};
		for (int i = 0; i < faecherName.length; i++) {
			faecher[i].setText(faecherName[i]);
		//[i].setBackgroundResource(faecherFarben[i]);
			faecher[i].setBackgroundColor(Color.parseColor(faecherFarben[i
			                                                             ]));
			faecher[i].setPaintFlags(Paint.ANTI_ALIAS_FLAG);
		}
		for (int i = faecherName.length; i < 20; i++)
			faecher[i].setVisibility(View.GONE);
	}

	private void InitFacher() {
		// TODO Auto-generated method stub
		/*
		 * faecher[0] = (Button) findViewById(R.id.haBFach1); faecher[1] =
		 * (Button) findViewById(R.id.haBFach2); faecher[2] = (Button)
		 * findViewById(R.id.haBFach3); faecher[3] = (Button)
		 * findViewById(R.id.haBFach4); faecher[4] = (Button)
		 * findViewById(R.id.haBFach5); faecher[5] = (Button)
		 * findViewById(R.id.haBFach6); faecher[6] = (Button)
		 * findViewById(R.id.haBFach7); faecher[7] = (Button)
		 * findViewById(R.id.haBFach8); faecher[8] = (Button)
		 * findViewById(R.id.haBFach9); faecher[9] = (Button)
		 * findViewById(R.id.haBFach10); faecher[10] = (Button)
		 * findViewById(R.id.haBFach11); faecher[11] = (Button)
		 * findViewById(R.id.haBFach12); faecher[12] = (Button)
		 * findViewById(R.id.haBFach13); faecher[13] = (Button)
		 * findViewById(R.id.haBFach14); faecher[14] = (Button)
		 * findViewById(R.id.haBFach15); faecher[15] = (Button)
		 * findViewById(R.id.haBFach16); faecher[16] = (Button)
		 * findViewById(R.id.haBFach17); faecher[17] = (Button)
		 * findViewById(R.id.haBFach18); faecher[18] = (Button)
		 * findViewById(R.id.haBFach19); faecher[19] = (Button)
		 * findViewById(R.id.haBFach20);
		 */

		for (int i = 0; i < 20; i++) {
			int resId = getResources().getIdentifier("haBFach" + (i + 1), "id",
					"com.patsud.melden");
			faecher[i] = (Button) findViewById(resId);
		}
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
		// TODO Auto-generated method stub
		// faecher[0].setText(Integer.toString(v.getId()));
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
		faecher[thisone].setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
		// faecher[thisone].setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

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

		if (firstlauf){
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
				.append("Abgabe: ")
				.append(mDay).append(".").append(mMonth + 1).append(".")
				.append(mYear).append(" ")
	//			.append("\n" + weekday));
				);
	//	if (today.get(Calendar.DAY_OF_MONTH)==mDay&&today.get(Calendar.MONTH)==mMonth&&today.get(Calendar.YEAR)==mYear)
	//		abgabeDay.setText("Abgabetag: Heute");
	//	else if (today.get(Calendar.DAY_OF_MONTH)+1==mDay&&today.get(Calendar.MONTH)==mMonth&&today.get(Calendar.YEAR)==mYear)
	//		abgabeDay.setText("Abgabetag: Morgen");
	}

	/////////??NOT FUNKTIONING
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


