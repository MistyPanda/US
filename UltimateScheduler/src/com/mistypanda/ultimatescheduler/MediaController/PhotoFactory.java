package com.mistypanda.ultimatescheduler.MediaController;




import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import com.mistypanda.ultimatescheduler.MediaAlbum;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

/*
 * A controller thread responsible for launching a thread for each picture that exists for an event
 * 
 */

public class PhotoFactory extends Thread {
		View photoContainer; //passed in image view 
		MediaAlbum mediaAlbum;//media album (list of picture file names)
		int numThreads;//number of threads to launch
		ArrayList<PhotoThread> threads;//array of threads
		Activity activity;//calling activity 
		String address;//address of server to pull pictures from
		
		/**
		 * checks if all threads have completed transfering pictures
		 * @return
		 */
		public boolean isThreadsDone(){
			int done =0;
			for(int i=0;i<numThreads;i++){
				if(threads.get(i).isDone()){
					done++;
				}
				
			}
			if (done==numThreads){
				return true;
			}
			return false;
		}
		
		/**
		 * creates a new instance of photo factory
		 * @param photoContainer view that contains all pictures for specified event
		 * @param mediaAlbum structure containing strings of all pictures
		 * @param activity calling activity
		 */
		public PhotoFactory(View photoContainer, MediaAlbum mediaAlbum, Activity activity){
			//initialize variables
			this.photoContainer = photoContainer;
			this.mediaAlbum=mediaAlbum;
			this.numThreads=mediaAlbum.getNumPhotos();
			this.activity = activity;
			
			//get shared preferences. this is what gets the IP address that is set from activites.
			SharedPreferences prefs = activity.getSharedPreferences(
				      "com.mistypanda.ultimatescheduler.HomePageActivity", Context.MODE_PRIVATE);
			
		

			// get actual address parameter from shared preferences
			address = prefs.getString("com.mistypanda.ultimatescheduler.HomePageActivity.serverAddress", "localhost"); 
		
		}
		
		//what happens when the thread executes
		public void run(){
			
			System.out.println("Running factory thread");
			
			//Initialize an array of threads to run
			 threads = new ArrayList<PhotoThread>();
			for(int i=0;i<mediaAlbum.getNumPhotos();i++){
				//create and initialize a thread for each picture that uses the address from settings
				threads.add(new PhotoThread(photoContainer, "http://"+address+"/"+ mediaAlbum.getPhoto(i), activity ) );
				threads.get(i).start();
			}
			
			//check if threads are done.			

			
			
		}
		
}
