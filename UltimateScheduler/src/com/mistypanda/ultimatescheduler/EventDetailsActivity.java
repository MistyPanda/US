package com.mistypanda.ultimatescheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class EventDetailsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    Intent intent = getIntent();
	    Event event = (Event) intent.getSerializableExtra("Event");
	   
	   setContentView(R.layout.eventdetails);
	   
	   TextView name = (TextView) findViewById(R.id.eventNameDetails);
	   TextView location = (TextView) findViewById(R.id.location);
	   TextView host = (TextView) findViewById(R.id.Host);
	   TextView StartDate = (TextView) findViewById(R.id.StartDate);
	   TextView EndDate = (TextView) findViewById(R.id.EndDate);
	   TextView Info = (TextView) findViewById(R.id.Info);
	}
	


}
