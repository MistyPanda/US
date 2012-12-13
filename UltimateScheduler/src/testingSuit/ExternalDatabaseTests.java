package testingSuit;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.mistypanda.ultimatescheduler.Event;

import junit.framework.TestCase;

public class ExternalDatabaseTests extends TestCase {

	Event e1;
	Event e2;
	Event e3;

	protected void setUp(){
		try{
			e1 = new Event(1, "Test Event 1", "Test Location", "Test Host", 
					"2012-12-15 20:30:00", "2012-12-23 10:30:00", "Test information. Blah blahblabh", 0, "testpassword");
			e2 = new Event(1, "Test Event 2", "Test Location", "Test Host", 
					"2013-03-15 02:30:00", "2014-11-23 01:30:00", "Test information. Blah blahblabh", 0, "testpassword");
			e3 = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testAddEvent(){
		try {
			TestDBHelper.addEvent(e1.getEventName(), e1.getLocation(), e1.getHost(), e1.getStartDate(), e1.getEndDate(), e1.getInfo(), "testpassword");
			assertEquals("Event added should be Test Event 1", "Test Event 1", TestDBHelper.getEvent(1).getEventName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testDeleteEvent(){
		try {
			TestDBHelper.deleteEvent(e1.getID(), "testpassword");
			TestDBHelper.getEvent(1);
			fail("No event to pull from database.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
