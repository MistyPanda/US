package com.mistypanda.ultimatescheduler;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.mistypanda.ultimatescheduler.DBAccess.*;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SavedEventsActivity extends Activity// extends ListActivity 
{
	
	List<Event> events= new ArrayList<Event>(); 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.eventlist);
	    LayoutInflater inflater = getLayoutInflater();
	    ListView listView = (ListView)findViewById(R.id.eventListView);
	   
	    
	    try{
	 	   // events = get all saved events from device
	 	    }	   

	 	    catch(Exception e){
	 	    	
	 	    	System.out.println(e.getMessage());	
	 	    }
	    
	   EventListAdapter eventAdapter = new EventListAdapter(this,events);
	   listView.setAdapter(eventAdapter);
	   final Intent viewEventIntent = new Intent(this, EventDetailsActivity.class);
	   listView.setOnItemClickListener(new OnItemClickListener() {
	    	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		  Event selectedEvent= events.get(position);
	    			viewEventIntent.putExtra("Event", selectedEvent);
	    			startActivity(viewEventIntent);
	    		  
	    	  }
	    	}
	   ); 
	   
	    
	}

	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		Event selectedEvent= events.get(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
}
