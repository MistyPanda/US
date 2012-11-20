package com.mistypanda.ultimatescheduler;

/**
 * Class that defines and identifies a piece of media (i.e. photo, video)
 * @author Senai Mesfin
 *
 */
public class Media {

	//MediaID
	//EventID
	
	String mediaID, eventID;
	
	public String getMediaID(){
		return this.mediaID;
	}
	
	public String getEventID(){
		return this.eventID;
	}
	
	public void setMediaID(String mediaID){
		this.mediaID = mediaID;
	}
	
	public void setEventID(String eventID){
		this.eventID = eventID;
	}
	
}
