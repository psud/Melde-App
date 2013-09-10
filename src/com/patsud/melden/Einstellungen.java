package com.patsud.melden;


import android.os.Bundle;
//import org.bostonandroid.timepreference.TimePreference;
import android.preference.PreferenceActivity;

public class Einstellungen extends PreferenceActivity{

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.prefs);
	
	
//	aboutClicked aboutClick = (aboutClicked) findPreference("aboutClick");
	
	}
}
