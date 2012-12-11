package com.mistypanda.ultimatescheduler;
import java.util.ArrayList;

/**
 * 
 */


/**
 * This is a helper class full of different search methods for finding events
 * all of the methods are static for use inside activities while keeping the complexity here and making testing simpler
 * @author kahorton
 *
 */
public class SearchForEvents {



	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in the ID attribute
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchByID(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(target.equals(e.getID()+"")){
				hits.add(e);
			}
		}
		return hits;
	}
	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in the EventName attribute
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchByName(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(e.getEventName().contains(target)){
				hits.add(e);
			}
		}
		return hits;
	}
	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in the Location attribute
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchByLocation(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(e.getLocation().contains(target)){
				hits.add(e);
			}
		}
		return hits;
	}
	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in the Host attribute
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchByHost(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(e.getHost().contains(target)){
				hits.add(e);
			}
		}
		return hits;
	}

	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in the Host attribute
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchByInfo(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(e.getInfo().contains(target)){
				hits.add(e);
			}
		}
		return hits;
	}
	
	/**
	 * this method will take a target string as well as the collection of events 
	 * the events and find all matches of the string in any attributes
	 * it will return the collection of all matches
	 * 
	 * ArrayList<Event>
	 * @param events the collection of all events
	 * @param target the target string segment for the search to find 
	 * 			 
	 * @return the collection of all events that have a match
	 */
	public static ArrayList<Event> searchEvents(ArrayList<Event> events, String target){
		ArrayList<Event> hits = new ArrayList<Event>();
		for(Event e: events){
			if(e.getEventName().contains(target)){
				hits.add(e);
			}else if(e.getLocation().contains(target)){
				hits.add(e);
			}else if(e.getHost().contains(target)){
				hits.add(e);
			}else if(e.getInfo().contains(target)){
				hits.add(e);
			}
		}
		return hits;
	}


}
