package com.mistypanda.ultimatescheduler.DBAccess;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mistypanda.ultimatescheduler.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/** This database helper is the local copy of the exernal database. Use this DB helper
 * in order to grab data for all activities. 
 * @author Senai Mesfin
 *
 */
public class LocalSQLiteHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "events";
	private static final String ID = BaseColumns._ID;
	
	private static final String eventName = "eventName";
	private static final String eventLocation = "eventLocation";
	private static final String eventHost = "eventHost";
	private static final String eventStartDate = "eventStartDate";
	private static final String eventEndDate = "eventEndDate";
	private static final String eventInfo = "eventInfo";
	private static final String eventVersion = "eventVersion";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " 
	+ eventName + " TEXT NOT NULL, " 
	+ eventLocation + " TEXT NOT NULL, " 
	+ eventHost + " TEXT NOT NULL, " 
	+ eventStartDate + " TEXT NOT NULL," 
	+ eventEndDate + " TEXT,"
	+ eventInfo + " TEXT NOT NULL,"
	+ eventVersion + "INTEGER NOT NULL);";
	
	public LocalSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LocalSQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		    onCreate(db);
	}
	
	public void insert(Event event){
		SQLiteDatabase database = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, event.getID());
		values.put(eventName, event.getEventName());
		values.put(eventLocation, event.getLocation());
		values.put(eventHost, event.getHost());
		values.put(eventStartDate, event.getStartDate().toString());
		values.put(eventEndDate, event.getEndDate().toString());
		values.put(eventInfo, event.getInfo());
		values.put(eventVersion, event.getVersion());
		database.insertOrThrow(TABLE_NAME, null, values);
	}
	
	public void insert(List<Event> eventList){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		Event tempEvent = null;
		for(int i=0; i < eventList.size(); i++){
			values.put(ID, tempEvent.getID());
			values.put(eventName, tempEvent.getEventName());
			values.put(eventLocation, tempEvent.getLocation());
			values.put(eventHost, tempEvent.getHost());
			values.put(eventStartDate, tempEvent.getStartDate().toString());
			values.put(eventEndDate, tempEvent.getEndDate().toString());
			values.put(eventInfo, tempEvent.getInfo());
			values.put(eventVersion, tempEvent.getVersion());
		}
		database.insertOrThrow(TABLE_NAME, null, values);
	}
	
	public Event getEvent(int eventID){
		SQLiteDatabase database = this.getReadableDatabase();
		Event event = null;
		//String[] SELECT = {ID, eventName, eventLocation, eventHost, eventStartDate, eventEndDate, eventInfo, eventVersion};
		String[] SELECT = {"*"};
		Cursor cursor = database.query(TABLE_NAME, //FROM events
				SELECT, //SELECT ID, eventName, eventLocation, eventHost, eventStartDate, eventEndDate, eventInfo, eventVersion
				ID + "=?", //WHERE ID = eventID
				new String[] { String.valueOf(eventID) }, 
				null, null, null, null);
		if (cursor != null)
	        cursor.moveToFirst();
		event = new Event (Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				parseDate(cursor.getString(4)),
				parseDate(cursor.getString(5)),
				cursor.getString(6),
				Integer.parseInt(cursor.getString(7)));
		return event;
	}
	
	public List<Event> getAllEvents(){
		List<Event> eventsList = new ArrayList<Event>();
		String selectQuery = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//Loop through all rows and add to list
		if(cursor.moveToFirst()){
			do{
				Event event = new Event(Integer.parseInt(cursor.getString(0)), 
						cursor.getString(1),
						cursor.getString(2),
						cursor.getString(3),
						parseDate(cursor.getString(4)),
						parseDate(cursor.getString(5)),
						cursor.getString(6),
						Integer.parseInt(cursor.getString(7)));
				//Add event to list
				eventsList.add(event);
			}
			while(cursor.moveToNext());
		}
		//Return the list of events
		return eventsList;
	}
	
	public int getEventsCount(){
		int counter = 0;
		String countQuery = "SELECT COUNT (*) FROM " + TABLE_NAME;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		counter = Integer.parseInt(cursor.getString(0));
		//Return the count of events in the local SQLite database
		return counter;
	}
	
	/** Parses a string in the format of a MySQL DATETIME and turns it into a DateTime.
	 * @param stringDate - The date of a MySQL type DATETIME in string format.
	 * @return The date in the form of DateTime.
	 */
	private static DateTime parseDate(String stringDate) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime date = format.parseDateTime(stringDate);
		return date;
	}
	
	
	
	

}
