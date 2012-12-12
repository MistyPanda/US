package com.mistypanda.ultimatescheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.mistypanda.ultimatescheduler.DBAccess.DBHelper;

/**
 * This class holds all media items for a given event.
 * @author Senai Mesfin
 * 
 */
public class MediaAlbum implements Serializable{
	ArrayList<String> media ;
	
	public MediaAlbum(String [] media){
	this.media = new ArrayList<String>(Arrays.asList(media));	
	
	}
	
	public MediaAlbum(List<String> media){
		this.media= new ArrayList<String>(media);
	
	}
	
	public int getNumPhotos(){
		return media.size();
	}
	
	public ArrayList<String> getPhotos(){
		return media;
	}
	
	public String getPhoto(int i){
		return media.get(i);
	
		
	}
	
	public void addPic(int eId, String string){
		media.add(string);
		try {
			DBHelper.addPhoto(eId, string);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	
	
	//MediaList
	//EventID
	
	//addMedia()
	//deleteMedia()
}
