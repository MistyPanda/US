package com.mistypanda.ultimatescheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public MediaAlbum(ArrayList<String> media){
		this.media=media;
	
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
	
	public void addPic(String string){
		media.add(string);
	
	}
	
	
	//MediaList
	//EventID
	
	//addMedia()
	//deleteMedia()
}
