package com.mistypanda.ultimatescheduler;

import java.util.ArrayList;

/**
 * Holder class for the events. has capability to add and remove events 
 * @author MistyPanda
 *
 */
public class EventList {
	ArrayList<Event> eventsList;
	
	/**
	 * constructor initiates with an empty event list
	 */
	public EventList(){
		eventsList = new ArrayList<Event>();
		
	}
	
	/**
	 * add a new event to the event list
	 * 
	 * void
	 * @param newEvent
	 */
	public void addEvent(Event newEvent){
		eventsList.add(newEvent);
	}
	
	/**
	 * remove an event from the event list if it's contained in the list
	 * 
	 * boolean
	 * @param rmvEvent event to be removed
	 * @return true if the event was removed
	 * see ArrayList<>.remove for more specific documentation
	 */
	public boolean removeEvent(Event rmvEvent){
		
		
		boolean removed = eventsList.remove(rmvEvent);
		return removed;
	}
}
