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

public class EventsActivity extends Activity// extends ListActivity 
{
	
	List<Event> events= new ArrayList<Event>(); 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	   //events = (Event[])DBHelper.getAllEvents().toArray();
		setContentView(R.layout.eventlist);
	    LayoutInflater inflater = getLayoutInflater();
	    //LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.eventlist,null);
	    ListView listView = (ListView)findViewById(R.id.eventListView);
	   
	    try{
	 	   events = DBHelper.getAllEvents();
	 	    	//events.add(DBHelper.getEventByEventID(1));
	 	    }	   





	 	    catch(Exception e){
	 	    	System.out.println(e.getMessage());	
	 	    }
	    
	    //System.out.println(events.get(0).getLocation());
	   EventListAdapter eventAdapter = new EventListAdapter(this,events);
	    listView.setAdapter(eventAdapter);
	    final Intent viewEventIntent = new Intent(this, EventDetailsActivity.class);
	   listView.setOnItemClickListener(new OnItemClickListener() {
	    	  public void onItemClick(AdapterView<?> parent, View view,
	    	    int position, long id) {
	    		  Event selectedEvent= events.get(position);
	    			
	    			viewEventIntent.putExtra("Event", selectedEvent);
	    			startActivity(viewEventIntent);
	    		  
	    	  }
	    	}); 
	    // TODO Auto-generated method stub
	     
	    
	}
	public void onSearchClick(View view){
		Intent intent = new Intent(this, SearchEventActivity.class);
	
		intent.putExtra("EventList", new ArrayList<Event>(events));
		
		startActivity(intent);
	}
	//@Override
	public void addEventClick(View view){
		Intent intent = new Intent(this, CreateEventActivity.class);
		startActivity(intent);
		
	}
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		Event selectedEvent= events.get(position);
		//Intent viewEventIntent = new Intent(this, EventActivity.class);
		//viewEventIntent.putExtra("Event", selectedEvent);
		//startActivity(viewEventIntent);
		//add event to intent.
		//call intent.
		//how to retrieve event from intent?
		//String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	private void setUpEvents(){
		
	}
	
	
}
