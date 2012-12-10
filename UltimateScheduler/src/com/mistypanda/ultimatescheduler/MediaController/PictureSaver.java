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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

public class PictureSaver extends Thread {
		View photoContainer;
		MediaAlbum mediaAlbum;
		int numThreads;
		Bitmap bitmap;
		Activity activity;
		ArrayList<PhotoThread> threads;
		   Socket socket = null;
	        DataOutputStream out = null;
	        DataInputStream in = null;
	
		
		public PictureSaver(Bitmap bitmap, MediaAlbum mediaAlbum){
			this.bitmap = bitmap;
			this.mediaAlbum=mediaAlbum;
			this.activity = activity;
		
		}
		
		public void run(){
			Log.d("opeining socket", "Running socket writing thread thread");
			

	     

	        try {
	        	//InetAddress address = InetAddress.getByName("152.65.35.115");
	            socket = new Socket("152.65.35.166", 1880);
	            out = new DataOutputStream(socket.getOutputStream());
	            in = new DataInputStream(
	                                        socket.getInputStream());
	        } catch (UnknownHostException e) {
	        	 e.printStackTrace();
	           Log.d("blah ", "Don't know about host: taranis.");
	            System.exit(1);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        	 Log.d("blah ", "Don't know about host: taranis2.");
	            System.exit(1);
	        }
	        //convert picture to byte array
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            
           // send byte array to socket;
	        try{
	        	
	        	out.write(byteArray);
	        
	        	
	        	out.flush();
	        	
	        	waitOnResponse();
	        	out.close();
	 	        socket.close();
	        }
	        catch(Exception e){
	        	 e.printStackTrace();
	        	 Log.d("blah ", "Don't know about host: taranis3.");
	        }
	        
	        
	        
	        
	        
			
			
			//check if threads are done.			

			
	       
			
		}
		
		void waitOnResponse(){
			String newFile= "";
			while(newFile.equals("")==true){
				
				try{
					newFile = in.readUTF();
				}
				catch(Exception e){
				
				}
			
		}
			 mediaAlbum.addPic(newFile);
		}
		
		
		
}
