package com.mistypanda.ultimatescheduler.MediaController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mistypanda.ultimatescheduler.MediaAlbum;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
/*
 * BVF
 * A thread to save a picture to the picture server and recieve a string with the name of the newly saved file
 */
public class PictureSaver extends Thread {
		
		View photoContainer;//view to add the picture to
		MediaAlbum mediaAlbum;//collection of pictures associated with this event
		Bitmap bitmap;//picture data
		Activity activity;//calling activity
		String address;//address to bind the socket to
		Socket socket = null;//new socket
	    DataOutputStream out = null;//stream needed for output
	    BufferedReader in = null;//stream needed for input
	    int eId;//eid of associated event
		
	    
	    /**
	     * 
	     * @param bitmap bitmap data of file to save
	     * @param mediaAlbum media album to add the filename to
	     * @param eId eId of the event associated with the picture
	     * @param activity calling activity
	     */
		public PictureSaver(Bitmap bitmap, MediaAlbum mediaAlbum,  int eId, Activity activity){
			this.bitmap = bitmap;
			this.mediaAlbum=mediaAlbum;
			this.activity = activity;
			this.eId = eId;
			
			SharedPreferences prefs = activity.getSharedPreferences(
				      "com.mistypanda.ultimatescheduler.HomePageActivity", Context.MODE_PRIVATE);
			
		

			// use a default value using new Date()
			address = prefs.getString("com.mistypanda.ultimatescheduler.HomePageActivity.serverAddress", "localhost"); 
		
		}
		
		
		//what happens when this thread runs
		public void run(){
			Log.d("opeining socket", "Running socket writing thread thread");
			

	     
			
	        try {
	        	
	        
	        	//open a socket
	            socket = new Socket(address, 1880);
	            
	            
	            //create output and input streams and readers 
	            out = new DataOutputStream(socket.getOutputStream());
	            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        } catch (UnknownHostException e) {
	        	 e.printStackTrace();
	           Log.d("Starting Socket", "Don't know about host");
	            System.exit(1);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        	 Log.d("Starting Socket ", "I/O on socket is bad");
	            System.exit(1);
	        }
	        
	        
	        //convert picture to byte array
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, MAX_PRIORITY, stream);
            
            byte[] byteArray = stream.toByteArray();
            
           
	        try{
	        	// send byte array to socket;
	        	out.write(byteArray);
	        	out.flush();
	        	
	        	//wait for server to send response
	        	waitOnResponse();
	        	
	        	//close streams and sockets
	        	out.close();
	 	        socket.close();
	        }
	        catch(Exception e){
	        	 e.printStackTrace();
	        	 Log.d("SocketWriting ", "Problem with transfering data between the socket");
	        }
	  
	       
			
		}
		
		//waits on response from server and stores the file name sent in file name. then adds the file to media album
		void waitOnResponse(){
			String newFile= "";
			//keep looping until it gets response from server
			while(newFile=="" || newFile==null){
				System.out.println("about to try reading from socket");
				try{
					System.out.println("Reading now");
					newFile = in.readLine();
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			
		}
			//add picture to media album in database and app.
			System.out.println("Finsihed reading. New file is : " + newFile);
			 mediaAlbum.addPic(eId, newFile);
		}
		
		
		
}
