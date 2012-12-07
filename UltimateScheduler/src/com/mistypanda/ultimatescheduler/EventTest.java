package com.mistypanda.ultimatescheduler;




import junit.framework.TestCase;


/**
 * @author kahorton
 *
 */
public class EventTest extends TestCase {
	Event event;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		event = new Event(0, "name", "location", "host", null, null, "information", 0);
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testName(){
		//test 
		try{
			event.setEventName("");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Name should still equal old value", event.getEventName().equals("name"));
		}
		try{
			event.setEventName("   ");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Name should still equal old value", event.getEventName().equals("name"));
		}
		try{
			event.setEventName("newName");
			assertTrue("Name should equal the new value", event.getEventName().equals("newName"));
		}catch(Exception e){
			fail("should have worked");
		}
	}
	
	public void testLocation(){
		try{
			event.setLocation("");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Location should still equal old value", event.getLocation().equals("location"));
		}
		try{
			event.setLocation("   ");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Location should still equal old value", event.getLocation().equals("location"));
		}
		try{
			event.setLocation("newLocation");
			assertTrue("Location should equal the new value", event.getLocation().equals("newLocation"));
		}catch(Exception e){
			fail("shouldnt get here");
		}
	}
	
	public void testHost(){
		try{
			event.setHost("");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Host should still equal old value", event.getHost().equals("host"));
		}
		try{
			event.setHost("   ");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Host should still equal old value", event.getHost().equals("host"));
		}
		try{
			event.setHost("newHost");
			assertTrue("Host should equal the new value", event.getHost().equals("newHost"));
		}catch(Exception e){
			fail("shouldnt get here");
		}
	}
	
	public void testInfo(){
		try{
			event.setInfo("");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Info should still equal old value", event.getInfo().equals("information"));
		}
		try{
			event.setInfo("   ");
			fail("accepted an empty string! This is not good!");
		}catch(Exception e){
			assertTrue("Info should still equal old value", event.getInfo().equals("information"));
		}
		try{
			event.setInfo("newInfo");
			assertTrue("Info should equal the new value", event.getInfo().equals("newInfo"));
		}catch(Exception e){
			fail("shouldnt get here");
		}
	}
		
}
