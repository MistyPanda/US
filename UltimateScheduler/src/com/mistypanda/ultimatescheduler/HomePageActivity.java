package com.mistypanda.ultimatescheduler;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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

		//Local Database check
		try {
			String destPath = "/data/data/" + getPackageName() +
			"/databases/LittlePanda.db";
			File f = new File(destPath);
			if (!f.exists()) {
				CopyDB( getBaseContext().getAssets().open("LittlePanda.db"),
						new FileOutputStream(destPath));
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
	public void onSettingsClick(View view){
		Intent intent = new Intent(this, SettingsActivity.class);
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
	
}