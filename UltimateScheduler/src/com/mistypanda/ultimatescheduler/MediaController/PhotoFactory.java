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

public class PhotoFactory extends Thread {
		View photoContainer;
		MediaAlbum mediaAlbum;
		int numThreads;
		ArrayList<PhotoThread> threads;
		Activity activity;
		String address;
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
		
		public PhotoFactory(View photoContainer, MediaAlbum mediaAlbum, Activity activity){
			this.photoContainer = photoContainer;
			this.mediaAlbum=mediaAlbum;
			this.numThreads=mediaAlbum.getNumPhotos();
			this.activity = activity;
			SharedPreferences prefs = activity.getSharedPreferences(
				      "com.mistypanda.ultimatescheduler.HomePageActivity", Context.MODE_PRIVATE);
			
		

			// use a default value using new Date()
			address = prefs.getString("com.mistypanda.ultimatescheduler.HomePageActivity.serverAddress", "localhost"); 
		
		}
		
		public void run(){
			System.out.println("Running factory thread");
			 threads = new ArrayList<PhotoThread>();
			for(int i=0;i<mediaAlbum.getNumPhotos();i++){
				//create and initialize a thread for each picture
				threads.add(new PhotoThread(photoContainer, "http://"+address+"/"+ mediaAlbum.getPhoto(i), activity ) );
				threads.get(i).start();
			}
			
			//check if threads are done.			

			
			
		}
		
}
