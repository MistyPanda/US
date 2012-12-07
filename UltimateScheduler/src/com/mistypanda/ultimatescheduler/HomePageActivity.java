package com.mistypanda.ultimatescheduler;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.homepage);
	
	    // TODO Auto-generated method stub
	}

	public void eventsClick(View view){
		Intent intent=new Intent(this, EventsActivity.class);
		startActivity(intent);
		
	}
}