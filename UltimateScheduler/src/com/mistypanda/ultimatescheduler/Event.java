package com.mistypanda.ultimatescheduler;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * Event class of the US will contain information about the events 
 * attributes include:id, name, location, time, daterange, info, contact, phonenumber
 * methods are: getphoto, getvideo, publishevent
 * @author kahorton, Senai Mesfin
 *
 */
public class Event implements Serializable{
	int ID;
	String EventName;
	String Location;
	String Host;
	DateTime startDate;
	DateTime endDate;
	String Info;
	int Version;
	MediaAlbum mediaAlbum;
	
	
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
		mediaAlbum = new MediaAlbum();
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
	 * @param eventName the eventName to set
	 */
	private void setEventName(String eventName) {
		EventName = eventName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location the location to set
	 */
	private void setLocation(String location) {
		Location = location;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return Info;
	}

	/**
	 * @param info the info to set
	 */
	private void setInfo(String info) {
		Info = info;
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

	/**
	 * @return the host
	 */
	public String getHost() {
		return Host;
	}

	/**
	 * @param host the host to set
	 */
	private void setHost(String host) {
		Host = host;
	}
	
	public int getVersion(){
		return Version;
	}
	
	public void setVersion(int version){
		Version = version;
	}

	public boolean getPhoto(){	return false;	}
	public boolean getVideo(){	return false;	}
	public boolean publishEvent(){return false;	}
}
