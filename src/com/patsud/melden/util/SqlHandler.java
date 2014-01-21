package com.patsud.melden.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHandler {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_TIME = "meld_time";
	public static final String KEY_KIND = "meld_kind";
	public static final String KEY_GOODNESS = "meld_goodness";
	

	private static final String DATABASE_NAME = "databaseMeld";
	private static final String DATABASE_TABLE = "meldTable";
	private static final int DATABASE_VERSION = 1;
	
	
	private dbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	//send to inclass
	public static int KEY_ROWNUM = 0;
	
	
	private static class dbHelper extends SQLiteOpenHelper{

		public dbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_TIME + " TEXT NOT NULL, " + KEY_KIND + " TEXT NOT NULL, "+
					KEY_GOODNESS + " INTEGER);" );
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}

	
	//Constructer
	public SqlHandler(Context c){
		ourContext = c;
	}
	
	public SqlHandler open() throws SQLException{
		ourHelper = new dbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String time, String kind, String goodness) {
		// TODO Auto-generated method stub
		//Get info for INCLASS
		Cursor c = ourDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
		KEY_ROWNUM= c.getCount()+1;
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_TIME, time);
		cv.put(KEY_KIND, kind);
		cv.put(KEY_GOODNESS, goodness);
 		return ourDatabase.insert(DATABASE_TABLE, null, cv);
 		
 		
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_TIME, KEY_KIND, KEY_GOODNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iTime = c.getColumnIndex(KEY_TIME);
		int iKind = c.getColumnIndex(KEY_KIND);
		int iGoodness = c.getColumnIndex(KEY_GOODNESS);

		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow)+ " --- " + c.getString(iTime) + " --- " + c.getString(iKind)+ " --- "+ c.getString(iGoodness)+ "\n";
		}

		result += "\n1. Spalte: Row ID\n2.Spalte: Datum und Zeit" +
				"\n3.Spalte: Wie gemeldet: 1=Gemeldet+Dran, 2=Gemeldet, 3=nur Dran, 4=Runde Dran" +
				"\n4.Spalte: Qualität des beitrags: 0=keine Angabe, 1= Gut, 2= Ok, 3=Schlecht, 4= Frage";
		
		return result;
	}

	public void updateEntry(int goodness) {
		// TODO Auto-generated method stub
		
		//Get Last Row num
		Cursor c = ourDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
		long longRow = Long.valueOf(c.getCount());
		
		ContentValues cvUpdate = new ContentValues();
		String goodnessStr = Integer.toString(goodness);
		cvUpdate.put(KEY_GOODNESS, goodnessStr);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "="+ longRow, null);
	}
	
	
	
}
