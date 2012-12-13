package com.mistypanda.ultimatescheduler;





import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.mistypanda.ultimatescheduler.Event;
import com.mistypanda.ultimatescheduler.R;
import com.mistypanda.ultimatescheduler.DBAccess.DBHelper;
import com.mistypanda.ultimatescheduler.DBAccess.LocalDBAdapter;

public class HomePageActivity extends Activity {
	
	private String local = "Local Database - Home"	;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

		try {
			String destPath = "/data/data/" + getPackageName() +
			"/databases/LittlePanda.db";
			File f = new File(destPath);
			if (!f.exists()) {
				CopyDB( getBaseContext().getAssets().open("LittlePanda.db"),
						new FileOutputStream(destPath));
				//addAllEvents();
			}
		} catch (FileNotFoundException e) {
			Log.w(local, "Local Database not found; Attempting to create new local database.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LocalDBAdapter db = new LocalDBAdapter(this);
		db.open();
		db.close();

	    super.onCreate(savedInstanceState);
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
	    setContentView(R.layout.homepage);
	
	    // TODO Auto-generated method stub
	}

	public void eventsClick(View view){
		Intent intent=new Intent(this, EventsActivity.class);
		startActivity(intent);

	}
	
	public void savedEventsClick(View view){
		Intent intent = new Intent(this, SavedEventsActivity.class);
		startActivity(intent);
	}
	

	public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
		//---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	public void displayRecord(Cursor c){
		Toast.makeText(this,
				"Event ID: " + c.getString(0) + "\n" +
				"Name: " + c.getString(1) + "\n" ,
				Toast.LENGTH_SHORT).show();
	}
	
	/**
	public void addAllEvents(){
		try {
			List<Event> allEvents = DBHelper.getAllEvents();
			Long count = LocalDBAdapter.insertAllEvents(allEvents);
			if(count > 0){
				Log.d("Local DB", "Local db updated; "+count+" Events added."	);
			}
			else{
				Log.d("Local DB", "No events added to local db.");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/*
	
	/**
	 * this method will update the local database by finding 
	 * and by updating events with a newer version
	 * 
	 * void
	 * @throws Exception 
	 * 
	 */
	public void updateDatabase() throws Exception{		
		//get local event id's/ versions
		ArrayList<String> list = new ArrayList<String>(cursorMethod(LocalDBAdapter.getAllEvents()));
		//loop through local events
		String id, version;
		for(String event: list){
			id = event.split(".")[0];
			version = event.split(".")[1];			
			// compare local and global versions
			if(Integer.parseInt(version)<=DBHelper.getEvent(Integer.parseInt(id)).getVersion())
				Toast.makeText(this, "Event ID "+id+" updated", Toast.LENGTH_LONG).show();
			//LocalDBAdapter.updateEvent();
			// update if global is newer version	
		}
	}

	/**
	 * converts a cursor from the local database into an Arraylist strings 
	 * a period separates the ID and version number
	 * ArrayList<String>
	 * @param cursor
	 * @return the collection of id, version pairs
	 * @throws Exception
	 */
	public ArrayList<String> cursorMethod(Cursor cursor) throws Exception{
		String hold ="";
		ArrayList<String> list = new ArrayList<String>();
		if(cursor.moveToFirst()){
			do{
				hold = cursor.getInt(0)+"."+ //ID
						cursor.getInt(7); 	//Version
				list.add(hold);
				
			}while(cursor.moveToNext());
		}
		
		return list;
	}
}