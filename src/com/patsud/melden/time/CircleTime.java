package com.patsud.melden.time;

import java.util.Calendar;

import android.util.Log;

public class CircleTime {

	public static float CalPercentage(int startSec, int endSec){
		
		float pers;
		Calendar c = Calendar.getInstance();
		int nowSec = c.get(Calendar.SECOND);
		int nowMin = c.get(Calendar.MINUTE);
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		
		float nowTotalSec;
		nowTotalSec = nowHour*60*60;
		nowTotalSec += nowMin*60;
		nowTotalSec += nowSec;
		
		int timeDiff = endSec - startSec;
		
		pers = nowTotalSec - startSec;
		pers = pers / timeDiff;
		pers = pers * 100;
		//pers = (nowTotalSec - startSec) / (endSec - startSec) * 100;
//		Log.d("pers", "startSec: "+ String.valueOf(startSec));
//		Log.d("pers", "endSec: "+ String.valueOf(endSec));
//		Log.d("pers", "nowTotalSec: "+ String.valueOf(nowTotalSec));
//		Log.d("pers", "pers: "+ String.valueOf(pers));
		return pers;
		
	}
}
