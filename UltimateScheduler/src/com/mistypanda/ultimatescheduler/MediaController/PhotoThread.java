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

public class PhotoThread extends Thread {
		private Bitmap picture;
		boolean done = false;
		String photoAddress;
		View photoContainer;
		Activity activity;
		public PhotoThread(View photoContainer, String photoAddress, Activity activity){
			super();
			this.photoAddress= photoAddress;
			this.photoContainer= photoContainer;
			this.activity = activity;
		}
		public Bitmap getPicture (){
			
			return picture;
		}
		
		public boolean isDone(){
		return done;
		}
	
		
		
		public void run(){
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		        StrictMode.setThreadPolicy(policy); 
			System.out.println("Running from thread");
			try{
			URL link = new URL(photoAddress);
			HttpURLConnection http = (HttpURLConnection)link.openConnection();
			
			http.setDoInput(true);
			http.connect();
			InputStream input = http.getInputStream();
			picture = BitmapFactory.decodeStream(input);
			//need to fix the adding of the picture to the view
			
			
			
			activity.runOnUiThread(new Runnable() {
			     public void run() {

			//stuff that updates ui

			 
			ImageView view = new ImageView(photoContainer.getContext().getApplicationContext());
			view.setLayoutParams(new TableRow.LayoutParams(500,500));
			view.setImageBitmap(picture);
			((LinearLayout) photoContainer).addView(view);
			     }
			  			});
			
			
			
			done = true;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}

			
			
		}
}
