package com.mistypanda.ultimatescheduler;

import java.util.Calendar;

import com.mistypanda.ultimatescheduler.DBAccess.DBHelper;
import com.mistypanda.ultimatescheduler.DBAccess.LocalDBAdapter;
import com.mistypanda.ultimatescheduler.MediaController.PhotoFactory;
import com.mistypanda.ultimatescheduler.MediaController.PictureSaver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class SavedEventDetailsActivity extends Activity {
	private static final int CAMERA_PIC_REQUEST = 3344;
	/** Called when the activity is first created. */
	MediaAlbum mediaAlbum;
	PhotoFactory photoFactory;
	Event event;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //create media album and then call the threads to add pictures
	   
	    
	    
	    //you stopped here mofo.
	    
	    
	    
	    
	    Intent intent = getIntent();
	    event = (Event) intent.getSerializableExtra("Event");
	    try{
	    mediaAlbum = new MediaAlbum(DBHelper.getAllPhotos(event.getID()));
	    }
	    catch(Exception e){
	    	System.out.println(e.getMessage());
	    	
	    }
	   setContentView(R.layout.savedeventdetails);
	   
	   TextView name = (TextView) findViewById(R.id.savedeventNameDetails);
	   name.setText(event.getEventName());
	   TextView location = (TextView) findViewById(R.id.savedlocation);
	   location.setText(event.getLocation());
	   TextView host = (TextView) findViewById(R.id.savedHost);
	   host.setText(event.getHost());
	   TextView startDate = (TextView) findViewById(R.id.savedStartDate);
	   startDate.setText(event.getStartDate().toString());
	   TextView endDate = (TextView) findViewById(R.id.savedEndDate);
	   endDate.setText(event.getLocation());
	   
	   TextView info = (TextView) findViewById(R.id.savedInfo);
	   info.setText(event.getInfo());
	   
	   getPictures();
	   
	}
	
	 void getPictures(){
		 View view = (View)findViewById(R.id.savedimages);
		  photoFactory = new PhotoFactory(view, mediaAlbum, this);
		  photoFactory.start();
		  
	}
	public void onAddPictureClick(View view){
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 	
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST); 
		  
			
			//View view = (View)findViewById(R.id.images);
		
	}
	public void onEditEventClick(View view){
		
		//make popup to prompt user for password
		
		//add notification to ask user about adding events
		//AlertDialog alert = new AlertDialog(getContext());
		
		
		final Intent intent = new Intent(this, EditEventActivity.class);
		final Activity passedActivity = this;
		final EditText passwordView = new EditText(this);
		passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
 
			// set title
			alertDialogBuilder.setTitle("");
			
			// set dialog message
			alertDialogBuilder
				.setMessage("Please enter password for this event")
				.setView(passwordView)
				.setCancelable(true)
				.setPositiveButton("Accept",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						String password = passwordView.getText().toString();
						if(password.equals(event.getPassword())){
							//
							intent.putExtra("Event", event);
							startActivity(intent);
							Toast.makeText(passedActivity, "Right Password", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(passedActivity, "Wrong Password", Toast.LENGTH_SHORT).show();
						}
						
						
						
						
					}
				  })
				;
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
	}
	
	public void onDeleteClick(View view){
		LocalDBAdapter db = new LocalDBAdapter(this);
		try{
			db.open();
			System.out.println("Here2");
		   LocalDBAdapter.deleteEvent(event.getID());
			System.out.println("Here3");
			db.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Intent intent = new Intent(this, SavedEventsActivity.class);
		startActivity(intent);
		this.onDestroy();
	
	}
	public void onSaveEventClick(View view){
		//add event to database
		
		
		//add notification to ask user about adding events
		//AlertDialog alert = new AlertDialog(getContext());
		System.out.println("Here1");
	
		LocalDBAdapter db = new LocalDBAdapter(this);
		try{
			db.open();
			System.out.println("Here2");
			LocalDBAdapter.insertEvent(event);
			System.out.println("Here3");
			db.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("Here4");
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
 
			// set title
			alertDialogBuilder.setTitle("");
			
			// set dialog message
			alertDialogBuilder
				.setMessage("Would you like to save this event to your calendar?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						
						Calendar beginTime = Calendar.getInstance();
						beginTime.setTime(DBHelper.parseDate(event.getStartDate()).toDate());

						Calendar endTime = Calendar.getInstance();
						endTime.setTime(DBHelper.parseDate(event.getEndDate()).toDate());
						Intent intent = new Intent(Intent.ACTION_INSERT)
						        .setData(Events.CONTENT_URI)
						        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
						        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
						        .putExtra(Events.TITLE, event.getEventName())
						        .putExtra(Events.DESCRIPTION, event.getInfo())
						        .putExtra(Events.EVENT_LOCATION, event.getLocation())
						        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
						        .putExtra(Intent.EXTRA_EMAIL, "bvferguson@csbsju.edu");
						startActivity(intent);
						
						
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
		
		
		
	}
	
private void editEvent(){
		
	
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    //first test for camera
		
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	        StrictMode.setThreadPolicy(policy); 
	        
		Bitmap bitmap;
		
	    if (requestCode == CAMERA_PIC_REQUEST) {  
	       if(resultCode== RESULT_OK) {// do somethinig  
			LinearLayout view = (LinearLayout)findViewById(R.id.images);
			Bundle extras = data.getExtras();
		    bitmap = (Bitmap) extras.get("data");
		    
		    //write to socket
		    PictureSaver pictureSaver = new PictureSaver(bitmap, mediaAlbum, event.getID(),this);
		    pictureSaver.run();
		    
		    //then add to actual view;
		    ImageView imageView = new ImageView(this);
		    imageView.setLayoutParams(new TableRow.LayoutParams(500,500));
			imageView.setImageBitmap(bitmap);
			((LinearLayout) view).addView(imageView);
	    }
	    } 
		
	}  
	
	
	
	


}
