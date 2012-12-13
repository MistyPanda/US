package com.mistypanda.ultimatescheduler.MediaController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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





//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//import android.view.View;

public class PictureSaverServer {
  
 
     
  public static void main (String [] args){
   //Bitmap bitmap;
   
   ServerSocket serverSocket =null;
      Socket clientSocket = null;
      byte[] data=null;
      InputStream in=null;
      OutputStream out=null;
      int port = 0;
      int picNumber = 6;
   
   try{
   

	 
	   
   serverSocket = new ServerSocket(1880);
   System.out.println("blah"+ "port is blah");
   port = serverSocket.getLocalPort();
   //serverSocket.setSoTimeout(60000);
   //Log.d("blah","port is now  : " + port);
   
   
  }
   catch(Exception e){
    System.out.println(e.getMessage() + "MANN it is not accepting!");
    
   }
   while(true){
   try{
    clientSocket= serverSocket.accept();
    //Log.d("blah","port is now CONNECTED! : " + port);
    
   }
   catch(Exception e){
    e.printStackTrace();
   }
   
   
   
   
    try{
      
     in= clientSocket.getInputStream();
     out= clientSocket.getOutputStream();
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        for(int s; (s=in.read(buffer)) > 0; )
        {
          baos.write(buffer, 0, s);
            System.out.println("First4");
            if(in.available()==0){
            	Thread.sleep(1000);
            	if(in.available()==0){
            	break;
            	}     
            	}
          // if(in.available()==0){
            //	break;
          // }
        }
        
        data = baos.toByteArray();
          System.out.println("First3");
      }
    
    
    catch(Exception e){
     e.printStackTrace();
     System.out.println(e.getMessage());
    }
    
    try{
   // bitmap = BitmapFactory.decodeByteArray(data , 0,data.length);
    
      System.out.println("Second");
          File file = new File("/Library/WebServer/Documents/testfile" + picNumber +".png");
          FileOutputStream fOut = new FileOutputStream(file);
          fOut.write(data);
          System.out.println("First");
         // bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
         	PrintWriter outStream = new PrintWriter(out, true);
         
         	outStream.println("testfile"+picNumber+".png");
         
         	//System.out.println("Second");
         	
         	picNumber++;
          fOut.flush();
          //fOut.close();
    }
    catch(Exception e){
     e.printStackTrace();
     System.out.println(e.getMessage());
    }
      System.out.println("Saved picture number " + picNumber);
  }

  }
  
}


