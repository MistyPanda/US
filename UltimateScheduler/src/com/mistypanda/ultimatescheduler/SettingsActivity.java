package com.mistypanda.ultimatescheduler;

import java.util.Calendar;

import com.mistypanda.ultimatescheduler.DBAccess.DBHelper;
import com.mistypanda.ultimatescheduler.DBAccess.LocalDBAdapter;
import com.mistypanda.ultimatescheduler.MediaController.PhotoFactory;
import com.mistypanda.ultimatescheduler.MediaController.PictureSaver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

public class SettingsActivity extends Activity {
	private static final int CAMERA_PIC_REQUEST = 3344;
	/** Called when the activity is first created. */
	MediaAlbum mediaAlbum;
	PhotoFactory photoFactory;
	Event event;
	SharedPreferences prefs;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //create media album and then call the threads to add pictures
	   
	    
	   prefs = this.getSharedPreferences(
			      "com.mistypanda.ultimatescheduler.HomePageActivity", Context.MODE_PRIVATE);
	   
	    
	    
	    setContentView(R.layout.settings);
	   String string =  prefs.getString("com.mistypanda.ultimatescheduler.HomePageActivity.serverAddress", "localhost");
	    ((EditText)findViewById(R.id.serverAddress)).setText(string);
	 
	}
	
	public void onSave(View view){
		
		
		
		String serverAddress = ((EditText)findViewById(R.id.serverAddress)).getText().toString();
		prefs.edit().putString("com.mistypanda.ultimatescheduler.HomePageActivity.serverAddress", serverAddress).commit();
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
		
	}
	
	
	
	


}
