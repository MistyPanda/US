package com.mistypanda.ultimatescheduler;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * Event class of the US will contain information about the events 
 * attributes include:id, name, location, time, daterange, info, contact, phonenumber
 * methods are: getphoto, getvideo, publishevent
 * @author kahorton, Senai Mesfin
 *
 */
public class Event implements Serializable{
	/**
	 * this is the event's integer identification. it should be unique
	 */
	int ID;
	/**
	 * this is the event's name
	 */
	String EventName;
	/**
	 * this is the location where the event will take place
	 */
	String Location;
	/**
	 * this is the host of the event
	 */
	String Host;
	DateTime startDate;
	DateTime endDate;
	/**
	 * this contains information about the event
	 */
	String Info;
	/**
	 * this is the version number of the event. gets updated when things change
	 */
	int Version;
	//MediaAlbum mediaAlbum;
	/**
	 * this is the password associated with the event to allow editing privileges
	 */
	String password;
	
	
	/**
	 * Constructor for the event class. takes strings for the event name, location, host and information. 
	 * it also requires a start and end date for the event 
	 * 
	 * @param iD
	 * @param eventName
	 * @param location
	 * @param host
	 * @param startDate
	 * @param endDate
	 * @param info
	 * @param version
	 */
	public Event(int iD, String eventName, String location, String host, DateTime startDate,
			DateTime endDate, String info, int version) {
		
		this.ID = iD;
		this.EventName = eventName;
		this.Location = location;
		this.Host = host;
		this.startDate = startDate;
		this.endDate = endDate;
		this.Info = info;
		this.Version = version;
		//mediaAlbum = new MediaAlbum(null, null);
	}
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	private void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return EventName;
	}

	/** 
	 * 
	 * @param eventName the eventName to set
	 * @throws Exception 
	 */
	void setEventName(String eventName) throws Exception {
		if(validateStrings(eventName))
		EventName = eventName;
		else throw new Exception("invalid input!");
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location the location to set
	 * @throws Exception 
	 */
	void setLocation(String location) throws Exception {
		if(validateStrings(location))
			Location = location;
			else throw new Exception("invalid input!");
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return Info;
	}

	/**
	 * @param info the info to set
	 * @throws Exception 
	 */
	void setInfo(String info) throws Exception {
		if(validateStrings(info))
			Info = info;
			else throw new Exception("invalid input!");
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return Host;
	}

	/**
	 * @param host the host to set
	 * @throws Exception 
	 */
	void setHost(String host) throws Exception {
		if(validateStrings(host))
		Host = host;
		else throw new Exception("invalid input!");
		
	}
	
	public int getVersion(){
		return Version;
	}
	
	public void incrementVersion(){
		Version++;
	}

	/**
	 * this method will ensure that a string exists for the input 
	 * boolean
	 * @param input
	 * @return
	 */
	private boolean validateStrings(String input){
		String trimmedString = input.trim();
		if(trimmedString.equals("")){
			return false;
		}
		return true;
	}

	
	/**
	 * @return the startDate
	 */
	public DateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	private void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public DateTime getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	private void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}


	
	public boolean getPhoto(){	return false;	}
	public boolean getVideo(){	return false;	}
	public boolean publishEvent(){return false;	}
}
