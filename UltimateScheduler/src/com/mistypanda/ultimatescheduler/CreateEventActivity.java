package com.mistypanda.ultimatescheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
		
		
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	//	View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		
		
	}

}
