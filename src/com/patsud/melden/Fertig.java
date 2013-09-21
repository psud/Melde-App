package com.patsud.melden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Fertig extends Activity {

	TextView tv;
	Button email;
	String allText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fertig);

		SqlHandler info = new SqlHandler(this);
		info.open();
		allText = info.getData();
		info.close();

		String wholeData = null;
		tv = (TextView) findViewById(R.id.textView1);
		tv.setText(allText);

		email = (Button) findViewById(R.id.bEmailSQL);
		email.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				String emailAdress[] = { "app@patsud.com" };
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
						emailAdress);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"Meine Leistung Heute");
				emailIntent.setType("plain/text");
				emailIntent
						.putExtra(android.content.Intent.EXTRA_TEXT, allText);
				startActivity(emailIntent);
			}
		});

	}
}
