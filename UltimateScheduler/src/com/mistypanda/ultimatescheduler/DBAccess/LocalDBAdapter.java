package com.mistypanda.ultimatescheduler.DBAccess;

import java.util.List;

import com.mistypanda.ultimatescheduler.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LocalDBAdapter {

	private static final String DATABASE_NAME = "LittlePanda.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "events";

	// The index (key) column name for use in where clauses.
	private static final String KEY_ID = "id";
	private static final String eventName = "name";
	private static final String eventLocation = "location";
	private static final String eventHost = "eventHost";
	private static final String eventStartDate = "startDate";
	private static final String eventEndDate = "endDate";
	private static final String eventInfo = "info";
	private static final String eventVersion = "version";

	private static String Tag = "LocalDBAdapter";
	
	// SQL Statement to create a new database.	
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( "+KEY_ID+" INTEGER PRIMARY KEY, "+
	eventName+" TEXT NOT NULL, "+
	eventLocation + " TEXT NOT NULL, " +
	eventHost + " TEXT NOT NULL, " +
	eventStartDate + " TEXT NOT NULL, " +
	eventEndDate + " TEXT, " +
	eventInfo + " TEXT NOT NULL, " +
	eventVersion + " INTEGER NOT NULL);";


	private final Context context;
	private LocalDBHelper DBHelper;
	private SQLiteDatabase db;

	public LocalDBAdapter(Context ctx){
		this.context = ctx;
		DBHelper = new LocalDBHelper(context);
	}


	private static class LocalDBHelper extends SQLiteOpenHelper{
		LocalDBHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(LocalDBAdapter.class.getName(),
					"Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	//Opens the database
	public LocalDBAdapter open() throws SQLException{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//Closes the database
	public void close(){
		DBHelper.close();
	}
	
	public long insertEvent(Event event) throws SQLException{
		ContentValues values = new ContentValues();
		values.put(KEY_ID, event.getID());
		values.put(eventName, event.getEventName());
		values.put(eventLocation, event.getLocation());
		values.put(eventHost, event.getHost());
		values.put(eventStartDate, event.getStartDate().toString());
		values.put(eventEndDate, event.getEndDate().toString());
		values.put(eventInfo, event.getInfo());
		values.put(eventVersion, event.getVersion());
		Log.d(Tag, "Event version number: "+ event.getVersion());
		return db.insert(TABLE_NAME, null, values);
	}
	
	public long insertAllEvents(List<Event> eventList) throws SQLException{
		ContentValues values = new ContentValues();
		Event tempEvent = null;
		long count = 0;
		for(int i=0; i < eventList.size(); i++){
			tempEvent = eventList.get(i);
			values.put(KEY_ID, tempEvent.getID());
			values.put(eventName, tempEvent.getEventName());
			values.put(eventLocation, tempEvent.getLocation());
			values.put(eventHost, tempEvent.getHost());
			values.put(eventStartDate, tempEvent.getStartDate().toString());
			values.put(eventEndDate, tempEvent.getEndDate().toString());
			values.put(eventInfo, tempEvent.getInfo());
			values.put(eventVersion, tempEvent.getVersion());
			db.insert(TABLE_NAME, null, values);
			count++;
		}
		return count;
	}

	public boolean deleteEvent(long eventID) throws SQLException{
		return(db.delete(TABLE_NAME, KEY_ID + " = " + eventID, null) > 0);
	}
	
	public Cursor getEvent(long eventID){		
		Cursor cursor =
			db.query(true, TABLE_NAME, new String[] {KEY_ID, eventName,
					eventLocation, eventHost, eventStartDate, eventEndDate, eventInfo, eventVersion},
			KEY_ID + "=" + eventID, null, null, null, null, null);
			if (cursor != null) {
			cursor.moveToFirst();
			}
			return cursor;
	}
	
	public Cursor getAllEvents(){
		return db.query(TABLE_NAME, new String[] {KEY_ID, eventName,
				eventLocation, eventHost, eventStartDate, eventEndDate, eventInfo, eventVersion}, null, null, null, null,null);
	}

	//Return the count of events in the local SQLite database
	public int getEventsCount(){
		int counter = 0;
		String countQuery = "SELECT COUNT (*) FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(countQuery, null);
		counter = Integer.parseInt(cursor.getString(0));
		return counter;
	}

	
}


