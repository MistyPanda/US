package com.mistypanda.ultimatescheduler;

import java.util.ArrayList;
import java.util.Arrays;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;

public class SearchEventActivity extends Activity// extends ListActivity 
{
	ListView listView;
	ArrayList<Event> events= new ArrayList<Event>(); 
	 ArrayAdapter<CharSequence> adapter;
	 Spinner spinner;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Intent intent = getIntent();
	    events = (ArrayList<Event>) intent.getSerializableExtra("EventList");
	   //events = (Event[])DBHelper.getAllEvents().toArray();
		setContentView(R.layout.searchevents);
	    LayoutInflater inflater = getLayoutInflater();
	    //LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.eventlist,null);
	     listView = (ListView)findViewById(R.id.searchListView);
	   
	    
	    //set the variable for the drop downmenu (spinner)
	     spinner = (Spinner) findViewById(R.id.searchby);
	    adapter = ArrayAdapter.createFromResource(this, R.array.searchoptions, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    
	    
	    
	    //System.out.println(events.get(0).getLocation());
	  
	    // TODO Auto-generated method stub
	     
	    
	}
	
	//@Override
	public void searchClick(View view){
		String selected = spinner.getSelectedItem().toString();
		String searchField = ((EditText)findViewById(R.id.searchField)).getText().toString();
		final List<Event> result;
		if (selected.equals("Default(all)")==true){
			result = SearchForEvents.searchEvents(events, searchField); 
			}
		else if (selected.equals("By Name")==true){
			
			result = SearchForEvents.searchByName(events, searchField); 
		
		}
		
		else if (selected.equals("By Location")==true){
			result = SearchForEvents.searchByLocation(events, searchField); 
			}
		else if (selected.equals("By Host")==true){
			result = SearchForEvents.searchByHost(events, searchField); 
			}
		else // (selected.equals("By Details")==true)
			{
			result = SearchForEvents.searchByInfo(events, searchField); 
			}
		
	//	System.out.println("first event " + result.get(0).getEventName());
		
		EventListAdapter eventAdapter = new EventListAdapter(this,result);
		
		 listView.setAdapter(eventAdapter);
		    final Intent viewEventIntent = new Intent(this, EventDetailsActivity.class);
		   listView.setOnItemClickListener(new OnItemClickListener() {
		    	  public void onItemClick(AdapterView<?> parent, View view,
		    	    int position, long id) {
		    		  Event selectedEvent= result.get(position);
		    			
		    			viewEventIntent.putExtra("Event", selectedEvent);
		    			startActivity(viewEventIntent);
		    		  
		    	  }
		    	}); 
	
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
