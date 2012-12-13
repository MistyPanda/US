package com.mistypanda.ultimatescheduler;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.mistypanda.ultimatescheduler.DBAccess.DBHelper;
import com.mistypanda.ultimatescheduler.DBAccess.LocalDBAdapter;



import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SavedEventsActivity extends Activity{ // extends ListActivity 

	private static String tag = "Saved Events Acticity";

	List<Event> events= new ArrayList<Event>(); 
	Cursor eventsCursor;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savedevents);
		//LayoutInflater inflater = getLayoutInflater();
		ListView listView = (ListView)findViewById(R.id.savedEventListView);

		LocalDBAdapter db = new LocalDBAdapter(this);
		try{

			db.open();

			updateDatabase();

			eventsCursor = LocalDBAdapter.getAllEvents();
		}  catch(Exception e){

			System.out.println(e.getMessage());	
		}
		try{



			events = cursorMethod(eventsCursor);
		}  catch(Exception e){

			System.out.println(e.getMessage());	
		}


		try{
			db.close();
		}	   

		catch(Exception e){

			System.out.println(e.getMessage());	
		}

		EventListAdapter eventAdapter = new EventListAdapter(this,events);
		listView.setAdapter(eventAdapter);
		final Intent viewEventIntent = new Intent(this, SavedEventDetailsActivity.class);
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

	public ArrayList<Event> cursorMethod(Cursor cursor) throws Exception{
		Event hold;
		ArrayList<Event> list = new ArrayList<Event>();
		if(cursor.moveToFirst()){
			do{
				hold = new Event(cursor.getInt(0), //ID
						cursor.getString(1), //Name
						cursor.getString(2), //Location
						cursor.getString(3), //Host
						cursor.getString(4), //Start
						cursor.getString(5), //End
						cursor.getString(6), //Info
						cursor.getInt(7), 	//Version
				"testpassword"); //password

				Log.d(tag, "Cursor Method Event: "+hold.getEventName());
				list.add(hold);

			}while(cursor.moveToNext());
		}

		return list;
	}

	/**
	 * this method will update the local database by finding 
	 * and by updating events with a newer version
	 * 
	 * void
	 * @throws Exception 
	 * 
	 */
	public void updateDatabase() throws Exception{		
		Cursor c = LocalDBAdapter.getVersions();
		if(c.moveToFirst()){
			do{
				Log.d(tag+" - Update Local DB", "Local eID: "+c.getInt(0)+", Version: "+c.getInt(1));
				updateEvent(c.getInt(0), c.getInt(1));
			}
			while(c.moveToNext());
			
		}
	}
	
	
	public void updateEvent(int localEID, int localVersion){
		try {
			Event externalEvent = DBHelper.getEvent(localEID);
			Log.d(tag+" - Update Local DB pt.2", "External Event ID: "+externalEvent.getID()+" ,Name: "+externalEvent.getEventName()+", Version: "+externalEvent.getVersion());
			if(localVersion < externalEvent.getVersion()){
				LocalDBAdapter.updateEvent(externalEvent);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
