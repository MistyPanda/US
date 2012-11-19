package com.mistypanda.ultimatescheduler;

public class Event {
	private String name;
	private String location;
	private String date;
	
	public Event(String name, String location, String date){
		this.setName(name);
		this.setLocation(location);
		this.setDate(date);
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getLocation() {
		return location;
	}

	void setLocation(String location) {
		this.location = location;
	}

	String getDate() {
		return date;
	}

	void setDate(String date) {
		this.date = date;
	}
	
	
}
