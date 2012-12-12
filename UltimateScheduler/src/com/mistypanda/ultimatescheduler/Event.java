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
	 * will throw an exception with a relevent error message for the first possible conflict 
	 * 
	 * @param iD
	 * @param eventName
	 * @param location
	 * @param host
	 * @param startDate
	 * @param endDate
	 * @param info
	 * @param version
	 * @throws Exception 
	 */
	public Event(int iD, String eventName, String location, String host, DateTime startDate,
			DateTime endDate, String info, int version, String password) throws Exception {
		
		this.ID = iD;
		
		// these need to be valid input
			setEventName(eventName);
			setLocation(location);
			setHost(host);
			setInfo(info);
			setPassword(password);
		
		this.startDate = startDate;
		this.endDate = endDate;
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
	 * Tests for valid input and sets the event name
	 * will throw an exception with a relevent error message for the first possible conflict
	 * @param eventName the eventName to set
	 * @throws Exception 
	 */
	void setEventName(String eventName) throws Exception {
		if(validateStrings(eventName))
		EventName = eventName;
		else throw new Exception("Event name invalid input! cannot be an empty string!");
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * Tests for valid input and sets the event location
	 * will throw an exception with a relevent error message for the first possible conflict
	 * @param location the location to set
	 * @throws Exception 
	 */
	void setLocation(String location) throws Exception {
		if(validateStrings(location))
			Location = location;
			else throw new Exception("Location invalid input! cannot be an empty string");
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return Info;
	}

	/**
	 * Tests for valid input and sets the event information
	 * will throw an exception with a relevent error message for the first possible conflict
	 * @param info the info to set
	 * @throws Exception 
	 */
	void setInfo(String info) throws Exception {
		if(validateStrings(info))
			Info = info;
			else throw new Exception("Event Information invalid input! cannot be an empty string!");
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return Host;
	}

	/**
	 * Tests for valid input and sets the event host
	 * will throw an exception with a relevent error message for the first possible conflict
	 * @param host the host to set
	 * @throws Exception 
	 */
	void setHost(String host) throws Exception {
		if(validateStrings(host))
		Host = host;
		else throw new Exception("Event Host invalid input! cannot be an empty string");
		
	}
	/**
	 * 
	 * String
	 * @return
	 */
	String getPassword(){
		return password;
	}
	
	/**
	 * Tests for valid input and sets the event password for editing purposes
	 * will throw an exception with a relevent error message for the first possible conflict
	 * void
	 * @param pass
	 * @throws Exception 
	 */
	void setPassword(String pass) throws Exception {
		if(!validateStrings(pass)){
			throw new Exception("Password invalid input! password cannot be an empty string");
		}
		else if(!(pass.length()<=25)){
			throw new Exception("Password invalid input! password must be less than 26 characters");	
		}
		else{// valid information
			password = pass;
		}
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
