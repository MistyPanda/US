package com.mistypanda.ultimatescheduler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class CreateEventActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.createactivity);
	    // TODO Auto-generated method stub
	}
	
	public void createClick(View view){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		
		
	}

}
