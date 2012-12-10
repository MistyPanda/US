package com.mistypanda.ultimatescheduler;

import com.mistypanda.ultimatescheduler.MediaController.PhotoFactory;
import com.mistypanda.ultimatescheduler.MediaController.PictureSaver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventDetailsActivity extends Activity {
	private static final int CAMERA_PIC_REQUEST = 3344;
	/** Called when the activity is first created. */
	MediaAlbum mediaAlbum;
	PhotoFactory photoFactory;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //create media album and then call the threads to add pictures
	    mediaAlbum = new MediaAlbum(new String[]{"testfile.png"});
	    
	    
	    //you stopped here mofo.
	    
	    
	    
	    
	    Intent intent = getIntent();
	    Event event = (Event) intent.getSerializableExtra("Event");
	   
	   setContentView(R.layout.eventdetails);
	   
	   TextView name = (TextView) findViewById(R.id.eventNameDetails);
	   name.setText(event.getEventName());
	   TextView location = (TextView) findViewById(R.id.location);
	   location.setText(event.getLocation());
	   TextView host = (TextView) findViewById(R.id.Host);
	   host.setText(event.getHost());
	   TextView startDate = (TextView) findViewById(R.id.StartDate);
	   startDate.setText(event.getStartDate().toString());
	   TextView endDate = (TextView) findViewById(R.id.EndDate);
	   endDate.setText(event.getLocation());
	   
	   TextView info = (TextView) findViewById(R.id.Info);
	   info.setText(event.getInfo());
	   
	   getPictures();
	   
	}
	
	 void getPictures(){
		 View view = (View)findViewById(R.id.images);
		  photoFactory = new PhotoFactory(view, mediaAlbum, this);
		  photoFactory.start();
		  
	}
	public void onAddPictureClick(View view){
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 	
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST); 
		  
			
			//View view = (View)findViewById(R.id.images);
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    //first test for camera
		
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	        StrictMode.setThreadPolicy(policy); 
	        
		Bitmap bitmap;
		
	    if (requestCode == CAMERA_PIC_REQUEST) {  
	        // do somethinig  
			LinearLayout view = (LinearLayout)findViewById(R.id.images);
			Bundle extras = data.getExtras();
		    bitmap = (Bitmap) extras.get("data");
		    
		    //write to socket
		    PictureSaver pictureSaver = new PictureSaver(bitmap, mediaAlbum);
		    pictureSaver.run();
		    
		    //then add to actual view;
		    ImageView imageView = new ImageView(view.getContext().getApplicationContext());
			imageView.setImageBitmap(bitmap);
			((LinearLayout) view).addView(imageView);
	    } 
		
	}  
	
	
	
	


}
