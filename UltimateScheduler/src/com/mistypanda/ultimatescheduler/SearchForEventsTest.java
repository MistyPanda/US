package com.mistypanda.ultimatescheduler;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author kahorton
 *
 */
public class SearchForEventsTest extends TestCase {

	ArrayList<Event> events;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		events = new ArrayList<Event>();
		try{
		events.add(new Event(1, "cool event number 1", "my house", "kevin", null, null, "this will be a cool party thing", 0,"pass"));
		events.add(new Event(2, "interesting event number 2", "my house", "kevin", null, null, "this will be an interesting party thing", 0,"pass"));
		events.add(new Event(3, "bitchin party number 3", "the bac", "jec", null, null, "this will be a bitchin party thing", 0,"pass"));
		events.add(new Event(4, "cool event number 4", "pengle", "jec", null, null, "this will be a pretty cool party thing", 0,"pass"));
		events.add(new Event(5, "interesting event number 5", "the bac", "senai", null, null, "this may be a pretty interesting thing", 0,"pass"));
		events.add(new Event(6, "bitchin party number 6", "pengle", "senai", null, null, "this is a bitchin party", 0,"pass"));
		events.add(new Event(7, "something gathering number 7", "my house", "kevin", null, null, "who knows what will happen", 0,"pass"));
		events.add(new Event(8, "nobody likes number 8", "the abyss", "panda", null, null, "you can't say no!", 0,"pass"));
		}catch(Exception e){}
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEventsByID() {
		ArrayList<Event> results = SearchForEvents.searchByID(events,"1");
		
		// should find events that are there
		assertTrue("There should only be one event with id=1",results.size()==1);
		// events that are found should be visible
		assertTrue("should be able to get the name of the event", 
				results.get(0).getEventName().equals("cool event number 1"));
		// should find no events that aren't there
		
		//fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEventsByName() {
		ArrayList<Event> results = SearchForEvents.searchByName(events,"event");
		
		// should find events that are there
		
		assertTrue("There should be 4 events with event in the name",results.size()==4);
		// events that are found should be visible
		assertTrue("should be able to get the name of the event", 
				results.get(0).getEventName().equals("cool event number 1"));
		// should find no events that aren't there

		results = SearchForEvents.searchByName(events,"I AM THE GREATEST");
		assertTrue("should be empty!",results.size()==0);
		
		results = SearchForEvents.searchByName(events,"interesting event number 5");
		assertTrue("should have 1 with exact event name",results.size()==1);
		
	}
	
	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEventsByLocation() {
		ArrayList<Event> results = SearchForEvents.searchByLocation(events,"c");
		
		// should find events that are there
		
		assertTrue("There should be 2 events with a c in the location",results.size()==2);
		// events that are found should be visible
		assertTrue("should be able to get the name of the event", 
				results.get(0).getLocation().equals("the bac"));
		// should find no events that aren't there

		results = SearchForEvents.searchByLocation(events,"I AM THE GREATEST");
		assertTrue("should be empty!",results.size()==0);
		
		results = SearchForEvents.searchByLocation(events,"the abyss");
		assertTrue("should have 1 with exact event name",results.size()==1);
				
		//fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEventsByHost() {
		ArrayList<Event> results = SearchForEvents.searchByHost(events,"e");
		
		// should find events that are there
		
		assertTrue("There should be 7 events with an e in the host name",results.size()==7);
		// events that are found should be visible
		assertTrue("should be able to get the name of the event", 
				results.get(0).getHost().equals("kevin"));
		// should find no events that aren't there

		results = SearchForEvents.searchByHost(events,"I AM THE GREATEST");
		assertTrue("should be empty!",results.size()==0);
		
		results = SearchForEvents.searchByHost(events,"panda");
		assertTrue("should have 1 with exact input",results.size()==1);
		
	}
	
	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEventsByInformation() {
		ArrayList<Event> results = SearchForEvents.searchByInfo(events,"thing");
		
		// should find events that are there
		
		assertTrue("There should be 5 events with a thing in the event info",results.size()==5);
		// events that are found should be visible
		assertTrue("should be able to get the name of the host of the event", 
				results.get(0).getHost().equals("kevin"));
		// should find no events that aren't there

		results = SearchForEvents.searchByInfo(events,"I AM THE GREATEST");
		assertTrue("should be empty!",results.size()==0);
		
		results = SearchForEvents.searchByInfo(events,"you can't say no!");
		assertTrue("should have 1 with exact input",results.size()==1);

	}
	

	/**
	 * Test method for {@link SearchForEvents#searchEvents(java.lang.String, java.lang.String)}.
	 */
	public void testSearchEvents() {
		ArrayList<Event> results = SearchForEvents.searchEvents(events,"party");
		
		// should find events that are there
		
		assertTrue("There should be 5 events with party anywhere",results.size()==5);
		// events that are found should be visible
		assertTrue("should be able to get the name of the host of the event", 
				results.get(0).getHost().equals("kevin"));
		// should find no events that aren't there

		results = SearchForEvents.searchEvents(events,"I AM THE GREATEST");
		assertTrue("should be empty!",results.size()==0);
		


	}


}
