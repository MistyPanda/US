package com.mistypanda.ultimatescheduler.MediaController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;


import com.mistypanda.ultimatescheduler.MediaAlbum;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class PictureSaverServer {
		
		Bitmap bitmap;
		ArrayList<PhotoThread> threads;
		ServerSocket serverSocket;
	    Socket clientSocket;
	    byte[] data;
	    InputStream in;
	    
		public void main (String [] args){
			
			try{
			serverSocket = new ServerSocket(2555);
			clientSocket= serverSocket.accept();
			
		}
			catch(Exception e){
				System.out.println(e.getMessage());
				
			}
			
			
			
			
			 try{
				 	
				 in= clientSocket.getInputStream();
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    byte buffer[] = new byte[1024];
				    for(int s; (s=in.read(buffer)) != -1; )
				    {
				      baos.write(buffer, 0, s);
				    }
				    data = baos.toByteArray();
				  }
			 
			 
			 catch(Exception e){
				 
				 System.out.println(e.getMessage());
			 }
			 
			 try{
			 bitmap = BitmapFactory.decodeByteArray(data , 0,data.length);
			 
			   
			       File file = new File("testfile.png");
			       FileOutputStream fOut = new FileOutputStream(file);

			       bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
			       fOut.flush();
			       fOut.close();
			 }
			 catch(Exception e){
				 System.out.println(e.getMessage());
			 }
			 
		}
		
	
		
}
