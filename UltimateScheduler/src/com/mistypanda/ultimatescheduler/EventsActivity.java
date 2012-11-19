package com.mistypanda.ultimatescheduler;


import android.app.ListActivity;

import android.os.Bundle;


public class EventsActivity extends ListActivity {
	Event [] events = new Event[3];
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    
	    
	    for (int i=0;i<3;i++){
	    	events[i] = new Event("Eventss " + i, "location " + i , i + "/10/1020");
	    }

	    EventListAdapter eventAdapter = new EventListAdapter(this,events);
	    setListAdapter(eventAdapter);
	    // TODO Auto-generated method stub
	    
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		Event selectedEvent= event[position];
		Intent viewEventIntent = new Intent(this,ViewEvent.class);
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	
}
