package com.mistypanda.ultimatescheduler;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mistypanda.ultimatescheduler.DBAccess.*;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class EventsActivity extends ListActivity {
	List<Event> events; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    try {
			setUpEvents();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
		}
	    
	    try{
	   //events = (Event[])DBHelper.getAllEvents().toArray();
	   
	   events = DBHelper.getAllEvents();
	    }
	    catch(Exception e){
	    	System.out.println(e.getMessage());	
	    }
	   
	    System.out.println(events.get(1).getEventName());
	   EventListAdapter eventAdapter = new EventListAdapter(this,events);
	    setListAdapter(eventAdapter);
	    // TODO Auto-generated method stub
	     
	    
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		Event selectedEvent= events.get(position);
		//Intent viewEventIntent = new Intent(this, EventActivity.class);
		//add event to intent.
		//call intent.
		//how to retrieve event from intent?
		//String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	private void setUpEvents(){
		
	}
	
	
}
