package com.patsud.melden;

import android.app.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class HaSchreibenNormal extends Activity {

	Button[] faecher = new Button[20];
	EditText[] aufgabe = new EditText[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.haschreibennormal);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		InitFacher();
		InitAufgabe();
		InitRest();

		ListenersAufgabe();
	}

	private void InitFacher() {
		// TODO Auto-generated method stub

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

	}

	private void ListenersAufgabe() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++){
			aufgabe[i].addTextChangedListener(new CustomTextWatcher(i));
			aufgabe[i].setImeActionLabel("PAT", KeyEvent.KEYCODE_ENTER);
			aufgabe[i].setOnEditorActionListener(onEnterListener);
		
		}
	
	}
int bla = 0;
	// Listener for Enter Pressed
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

}
