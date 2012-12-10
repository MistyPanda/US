package com.mistypanda.ultimatescheduler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mistypanda.ultimatescheduler.DBAccess.*;

public class CreateEventActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.createactivity);
	    // TODO Auto-generated method stub
	}
	
	public void createClick(View view){
		LayoutInflater inflater = getLayoutInflater();
		EditText name = (EditText)findViewById(R.id.createEventName);
		EditText startDate = (EditText)findViewById(R.id.createEventStartDate);
		EditText endDate = (EditText)findViewById(R.id.createEventEndDate);
		EditText host = (EditText)findViewById(R.id.createEventHost);
		EditText location = (EditText)findViewById(R.id.createEventLocation);
		EditText info = (EditText)findViewById(R.id.createEventInfo);
		
		String eNam = name.getText().toString();
		String eLoc = location.getText().toString();
		String eHost = host.getText().toString();
		String eStart	= startDate.getText().toString();
		String eEnd	= endDate.getText().toString();
		String descrip = info.getText().toString();
		
		
		//check strings;
		if(eLoc.replace(" ", "").isEmpty()){
			//call method to allert user;
			
		}
		
		if(eHost.replace(" ", "").isEmpty()){
			//call method to allert user;
			
		}
		
		if(eStart.replace(" ", "").isEmpty()){
			//call method to allert user;
			
		}
		
		if(eEnd.replace(" ", "").isEmpty()){
			//call method to allert user;
			
		}
		
		if(descrip.replace(" ", "").isEmpty()){
			//call method to allert user;
			
		}
		try {
			DBHelper.addEvent(eNam, eLoc, eHost, eStart, eEnd, descrip);
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
		
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	//	View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		
		
	}

}
