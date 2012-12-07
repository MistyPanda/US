package com.mistypanda.ultimatescheduler.activity;

import com.mistypanda.ultimatescheduler.Event;
import com.mistypanda.ultimatescheduler.R;
import com.mistypanda.ultimatescheduler.R.id;
import com.mistypanda.ultimatescheduler.R.layout;

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
	   name.setText(event.getEventName());
	   TextView location = (TextView) findViewById(R.id.location);
	   location.setText(event.getLocation());
	   TextView host = (TextView) findViewById(R.id.Host);
	   host.setText(event.getHost());
	   TextView startDate = (TextView) findViewById(R.id.StartDate);
	   startDate.setText(event.getStartDate().toString());
	   TextView endDate = (TextView) findViewById(R.id.EndDate);
	   endDate.setText(event.getLocation());
	   
	   TextView info = (TextView) findViewById(R.id.Info);
	   info.setText(event.getInfo());
	}
	


}
