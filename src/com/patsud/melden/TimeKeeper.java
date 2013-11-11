package com.patsud.melden;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;
import android.util.Printer;

public class TimeKeeper {

	private String startTime, endTime;

	public int TimeStart() {

		CalHour();

		Log.d("Time", "Start: "+startTime);
		return MakeInSec(startTime);
	}

	public int TimeEnd() {
		Log.d("Time", "End: "+endTime);
		return MakeInSec(endTime);
	}

	private int MakeInSec(String time) {
		// TODO Auto-generated method stub
		int sec = Integer.parseInt(time.substring(0, 2)) * 60;
		sec += Integer.parseInt(time.substring(3));
		sec = sec * 60;
		return sec;
	}

	private void CalHour() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		//switch (c.get(Calendar.DAY_OF_WEEK)) {
		//case Calendar.MONDAY:

			// Check if in First Hour 
			Date x = GetNowTime().getTime();
			if (x.after(TimeToDate("07:55:00").getTime())
					&& x.before(TimeToDate("09:30:00").getTime())) {
				startTime = "08:00";
				endTime = "09:30";
			} else if (x.after(TimeToDate("09:40:00").getTime())
					&& x.before(TimeToDate("11:15:00").getTime())) {
				startTime = "09:45";
				endTime = "11:15";
			} else if (x.after(TimeToDate("11:25:00").getTime())
					&& x.before(TimeToDate("13:00:00").getTime())) {
				startTime = "11:30";
				endTime = "13:00";
			} else if (x.after(TimeToDate("13:25:00").getTime())
					&& x.before(TimeToDate("15:00:00").getTime())) {
				startTime = "13:30";
				endTime = "15:00";
			} 
				//else if (x.after(TimeToDate("15:00:00").getTime())
			//		&& x.before(TimeToDate("20:00:00").getTime())) {
			//	startTime = "15:00";
			//	endTime = "20:00";
			//}
			else {
				DebugStartEnd();
			}
		//	break;
		//}
	}

	
	private Calendar TimeToDate(String inTime) {
		// TODO Auto-generated method stub
		Date outTime = null;
		try {
			outTime = new SimpleDateFormat("HH:mm:ss").parse(inTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar outC = Calendar.getInstance();
		outC.setTime(outTime);
		outC.add(Calendar.DATE, 1);
		
		 
		return outC;
	}

	private Calendar GetNowTime() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		String minute = Integer.toString(c.get(Calendar.MINUTE));
		if (minute.length() == 1)
			minute = "0" + minute;
		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		if (hour.length() == 1)
			hour = "0" + hour;
		int day = c.get(Calendar.DAY_OF_WEEK);
		StringBuffer total = new StringBuffer();
		total.append(hour).append(":").append(minute).append(":00");
		Date end1D = null;
		try {
			end1D = new SimpleDateFormat("HH:mm:ss").parse(total.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar outC = Calendar.getInstance();
		outC.setTime(end1D);
		outC.add(Calendar.DATE, 1);

		return outC;
	}
	
	private void DebugStartEnd() {
		// TODO Auto-generated method stub
		Log.d("MeldeMessage", "Time in Debug mode");
		Calendar c = Calendar.getInstance();
		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		if (hour.length() == 1)
			hour = "0" + hour;
		startTime = hour +":00";
		String hourPlus = Integer.toString(c.get(Calendar.HOUR_OF_DAY)+1);
		if (hourPlus.length() == 1)
			hourPlus = "0" + hourPlus;
		endTime = hourPlus + ":00";
	}


}
