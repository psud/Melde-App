package com.patsud.melden;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
//import org.bostonandroid.timepreference.TimePreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Button;
import android.widget.Toast;

public class Einstellungen extends PreferenceActivity {

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		final EditTextPreference namePref = (EditTextPreference) findPreference("name");
		String name = getPrefs.getString("name", "Batman");
		namePref.setTitle("Dein Name: " + name);

		findPreference("openAbout");

		PreferenceScreen aboutMe = (PreferenceScreen) findPreference("openAbout");

		aboutMe.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub

				// dialog with ok button
				final AlertDialog.Builder aboutDialog = new AlertDialog.Builder(
						context);
				// title
				aboutDialog.setTitle("About");
				// message
				aboutDialog
						.setMessage(
								"Created and Designed by Patrick Sudhaus\nwww.patsud.com\n\n" +
								"Great Android Tutorials:\nwww.thenewbosten.org\n\n" +
								"")
						.setCancelable(true).setPositiveButton("Ok", null);

				// create dialog
				AlertDialog alertDialog = aboutDialog.create();

				// show dialog
				alertDialog.show();

				return false;
			}
		});

		namePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub
				SharedPreferences laPrefs = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				String name = laPrefs.getString("name", "Batman");
				namePref.setTitle("Dein Name: " + name);
				return false;
			}
		});

	}
}


