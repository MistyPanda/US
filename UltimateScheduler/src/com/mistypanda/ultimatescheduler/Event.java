package com.mistypanda.ultimatescheduler;

/**
 * Event class of the US will contain information about the events 
 * attributes include:id, name, location, time, daterange, info, contact, phonenumber
 * methods are: getphoto, getvideo, publishevent
 * @author kahorton
 *
 */
public class Event {
	int ID;
	String EventName;
	String Location;
	String startDate;
	String endDate;
	String Info;
	String Host;
	MediaAlbum mediaAlbum;
	
	/**
	 * 
	 */
	public Event(int iD, String eventName, String location, String startDate,
			String endDate, String info, String host) {
		
		ID = iD;
		EventName = eventName;
		Location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		Info = info;
		Host = host;
		mediaAlbum = new MediaAlbum();
	}
	
	/**
	 * @return the iD
	 */
	private int getID() {
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
	private String getEventName() {
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
	private String getLocation() {
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
	private String getInfo() {
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
	private String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	private void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	private String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	private void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the host
	 */
	private String getHost() {
		return Host;
	}

	/**
	 * @param host the host to set
	 */
	private void setHost(String host) {
		Host = host;
	}

	public boolean getPhoto(){	return false;	}
	public boolean getVideo(){	return false;	}
	public boolean publishEvent(){return false;	}
}
