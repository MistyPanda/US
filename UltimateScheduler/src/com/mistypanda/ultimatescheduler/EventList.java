package com.mistypanda.ultimatescheduler;

public class EventList {
import java.util.ArrayList;

public class EventList {
	ArrayList<Event> eventsList;
	
	public EventList(){
		eventsList = new ArrayList<Event>();
		
	}
	
	public void addEvent(Event newEvent){
		eventsList.add(newEvent);
	}
	
	public boolean removeEvent(Event rmvEvent){
		
		
		boolean removed = eventsList.remove(rmvEvent);
		return removed;
	}
}
