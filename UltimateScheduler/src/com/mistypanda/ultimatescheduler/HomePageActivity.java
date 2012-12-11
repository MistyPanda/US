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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LocalDBAdapter db = new LocalDBAdapter(this);

		/**
		String eNam = "Final+Tests";
		String loc = "Placid+103";
		String host = "MistyPanda";
		String sDat = "2012-12-13+20:30:00";
		String eDat = "2012-12-13+23:00:00";
		String info = "Final+testing+and+submittions.";
		String password = "testpassword";
		int eID = 35;
		String filePath = "testphoto123";
		Event testEvent = null;
		try {
			Log.d("Database tests", "Attempting database methods.");
			//testEvent = DBHelper.getEventByEventID(3);
			//Log.d("Database tests", "Event ID: "+ testEvent.getID()+" Name: "+testEvent.getEventName());
			//db.open();
			//LocalDBAdapter.insertEvent(testEvent);
			//displayRecord(LocalDBAdapter.getEvent(3));
			//db.close();
			//DBHelper.addEvent(eNam, loc, host, sDat, eDat, info, password);
			
			DBHelper.addPhoto(eID, filePath);
			List<String>photos = DBHelper.getAllPhotos(eID);
			Log.d("Database tests", "Photo: "+photos.get(0));
			
			Toast.makeText(this, 
					"Event ID: "+testEvent.getID()+"\n"+
					"Name: "+testEvent.getEventName()+"\n"+
					"Version: "+testEvent.getVersion(), 
					Toast.LENGTH_LONG).show();
					
			
			
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
		**/
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
	
	/**
	public void update(){
		ArrayList<String> externalVersions = (ArrayList<String>) DBHelper.getEventVersions();
		Cursor localVersions = LocalDBAdapter.getVersions();
		localVersions.moveToFirst();
		for(int i=0; i < externalVersions.size(); i++){
			for(int j=0; j < localVersions.getCount(); j++){
				String hold = externalVersions.get(i);
				String[] parts = hold.split(",");
				int eID = Integer.parseInt(parts[0]);
				int version = Integer.parseInt(parts[1]);
	
				if(eID == localVersions.getInt(0)){
					if()
				}
			}
		}
	}
	*/
}