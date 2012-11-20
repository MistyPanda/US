package com.mistypanda.ultimatescheduler.DBAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

/** This class creates a new thread to run in the background and 
 * access the external main database.
 * @author Senai Mesfin
 *
 */
class FetchTask  extends AsyncTask<String, String, String>{

	private String jsonData;
	
	@Override
	protected String doInBackground(String... arg) {
		
		String data;
		URL url = null;
		URLConnection urlConnection = null;
		InputStreamReader inputStream = null;
		BufferedReader buffer = null;
		
		try{
			
			// Create the URL object that points at the default file index.html
			
			url = new URL(arg[0]);
			urlConnection = url.openConnection();
			inputStream = new InputStreamReader(urlConnection.getInputStream());
			buffer = new BufferedReader(inputStream);
			
			StringBuilder sb = new StringBuilder("");
			while(true){
				data = buffer.readLine();
				if(data != null){
					sb.append(data);
				}
				else{
					break;
				}
			}
			jsonData = sb.toString();
			return jsonData;
		}
		catch(MalformedURLException urle){
			System.out.println("Check URL: " + urle.toString());
			return "";
		}
		catch(IOException ioe){
			System.out.println(ioe.toString());
			return "";
		}
	}
	
	protected void onPostExecute(String result){
		super.onPostExecute(result);
	}
	
	public String getData(){
		return jsonData;
	}
	
	public void setData(String data){
		this.jsonData = data;
	}

	
}
