package com.mistypanda.ultimatescheduler.MediaController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

/*
 * BVF
 * A picture thread that takes a address of a pictures, loads it and adds it to a view
 */
public class PhotoThread extends Thread {
		private Bitmap picture;//bitmap to store pictures
		boolean done = false;//is this thread done?
		String photoAddress;//address of photo to be pulled
		View photoContainer;//view to add photo to
		Activity activity;//calling activity
		
		/**
		 * 
		 * @param photoContainer view to add photo to
		 * @param photoAddress address of picture server
		 * @param activity calling activity
		 */
		public PhotoThread(View photoContainer, String photoAddress, Activity activity){
			super();
			//intialize variables
			this.photoAddress= photoAddress;
			this.photoContainer= photoContainer;
			this.activity = activity;
		}
		
		//return the picture that this thread loaded
		public Bitmap getPicture (){
			
			return picture;
		}
		
		//is this thread done?
		public boolean isDone(){
		return done;
		}
	
		
		//what happens when this thread runs
		public void run(){
			//makes sure that this thread runs seperate from the main thread. needed for the android system
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		     StrictMode.setThreadPolicy(policy); 
		        
		        
			System.out.println("Running from thread");
			
			//load the photo adres in a url
			try{
			URL link = new URL(photoAddress);
			//open http a connection
			HttpURLConnection http = (HttpURLConnection)link.openConnection();
			http.setDoInput(true);
			http.connect();
			
			//create a input stream based on the http connection
			InputStream input = http.getInputStream();
			//load the picture
			picture = BitmapFactory.decodeStream(input);
			//need to fix the adding of the picture to the view
			
			
			//updating the ui can only be done via a thread attached to the calling activity, thus:
			activity.runOnUiThread(new Runnable() {
			     public void run() {

			//stuff that updates ui
			ImageView view = new ImageView(photoContainer.getContext().getApplicationContext());
			//makes the image bigger
			view.setLayoutParams(new TableRow.LayoutParams(500,500));
			
			//add picture to the view
			view.setImageBitmap(picture);
			//add view to main view contaner
			((LinearLayout) photoContainer).addView(view);
			     }
			  			});
			
			
			//thread is done
			done = true;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}

			
			
		}
}
