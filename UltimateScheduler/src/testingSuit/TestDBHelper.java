package testingSuit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.content.res.Resources;
import android.net.ParseException;
import android.util.Log;

import com.mistypanda.ultimatescheduler.Event;


/**
 * This class is used to test database functions and connections
 * @author Senai Mesfin
 */
public class TestDBHelper {
	
	private static String tag = "TestDBHelper Debug";
	
	/** This method accesses the main external database and retrieves an event by ID.
	 * @param eventID - The int ID for a specific event.
	 * @return Returns the newly created event.
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static Event getEvent(int eventID) throws InterruptedException, ExecutionException, TimeoutException{
		Event event = null;
		FetchTask task = new FetchTask();
		
		// FIGURE OUT HOW TO ACCESS STRING RESOURCE
		//task.execute(R.string.hello_world);
		//getString(R.string.hello_world);
		//Resources.getSystem().getString(R.string.get_event);
		
		// Start background thread to access the database through php
		String getEvent = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/getEvent.php?id="+eventID);
		Log.d(tag, "Attempted URL: "+getEvent);
		task.execute(getEvent);
		task.get(5000, TimeUnit.MILLISECONDS);
		String result = task.getData();
		
		// Parse result into manageable variables and create new event
		try{
			JSONArray dataArray = new JSONArray(result);
			JSONObject json_data=null;
			
			for(int i=0; i<dataArray.length(); i++){
				json_data = dataArray.getJSONObject(i);
				
				int eID = json_data.getInt("eID");
				String eventName = json_data.getString("eName");
				String location = json_data.getString("eLocation");
				String host = json_data.getString("eHost");
				String startDate = json_data.getString("eStartDate");
				String endDate = json_data.getString("eEndDate");
				String info = json_data.getString("eInfo");
				int version = json_data.getInt("eVersion");
				String pass = json_data.getString("password");
				
				event = new Event(eID, eventName, location, host, startDate, endDate, info, version, pass);
			}
		}
		catch(JSONException je){
			System.out.println(je.toString());
		}
		catch(ParseException pe){
			System.out.println("Date string is not in the correct format: " + pe.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return event;
	}
	
	/**
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static List<Event> getAllEvents() throws InterruptedException, ExecutionException, TimeoutException{
		List<Event> eventList = new ArrayList<Event>();
		FetchTask task = new FetchTask();
		Event event = null;
		
		// Start background thread to access the database through php
		String getAllEvents = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/getAllEvents.php");
		Log.d(tag, "Attempted URL: "+getAllEvents);
		task.execute(getAllEvents);
		task.get(30000, TimeUnit.MILLISECONDS);
		String result = task.getData();
		
		// Parse results into manageable variables and events. Then add them to event list.
		try{
			JSONArray dataArray = new JSONArray(result);
			JSONObject json_data = null;
			
			for(int i=0; i<dataArray.length(); i++){
				json_data = dataArray.getJSONObject(i);
				
				int eID = json_data.getInt("eID");
				String eventName = json_data.getString("eName");
				String location = json_data.getString("eLocation");
				String host = json_data.getString("eHost");
				String startDate = json_data.getString("eStartDate");
				String endDate = json_data.getString("eEndDate");
				String info = json_data.getString("eInfo");
				int version = json_data.getInt("eVersion");
				String pass = json_data.getString("password");
				
				event = new Event(eID, eventName, location, host, startDate, endDate, info, version, pass);
				eventList.add(event);
			}
		}
		catch(JSONException je){
			System.out.println(je.toString());
		}
		catch(ParseException pe){
			System.out.println("Date string is not in the correct format: " + pe.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Return the list of all events in the external database.
		return eventList;
	}
	
	/**
	 * @param eNam
	 * @param loc
	 * @param host
	 * @param sDat
	 * @param eDat
	 * @param info
	 * @param password
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void addEvent(String eNam, String loc, String host, String sDat,
			String eDat, String info, String password) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		
		// Start background thread to access the database through php
		String addEvent = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/addEvent.php?name="+eNam
		+"&loc="+loc+"&host="+host+"&start="+sDat+"&end="+eDat+"&info="+info+"&pass="+password);
		Log.d(tag, "Attempted URL: "+addEvent);
		task.execute(addEvent);
	}

	/**
	 * @param eID
	 * @param password
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void deleteEvent(int eID, String password) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String deleteEvent = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/deleteEvent.php?id="+eID+"&pass="+password);
		Log.d(tag, "Attempted URL: "+deleteEvent);
		task.execute(deleteEvent);
	}
	
	/**
	 * @param eID
	 * @param eNam
	 * @param loc
	 * @param host
	 * @param sDat
	 * @param eDat
	 * @param info
	 * @param password
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void updateEvent(int eID, String eNam, String loc, String host, String sDat,
			String eDat, String info, String password) throws InterruptedException, ExecutionException, TimeoutException{
		
		FetchTask task = new FetchTask();
		String updateEvent = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/updateEvent.php?id="+eID+"&name="+eNam
		+"&loc="+loc+"&host="+host+"&start="+sDat+"&end="+eDat+"&info="+info+"&pass="+password);
		Log.d(tag, "Attempted URL: "+updateEvent);
		task.execute(updateEvent);
	}
	
	/**
	 * @param eID
	 * @param filePath
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void addPhoto(int eID, String filePath) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String addPhoto = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/addPhoto.php?id="+eID+"&path="+filePath);
		Log.d(tag, "Attempted URL: "+addPhoto);
		task.execute(addPhoto);
	}
	
	/**
	 * @param eID
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static List<String> getAllPhotos(int eID) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String getAllPhotos = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/getAllPhotos.php?id="+eID);
		Log.d(tag, "Attempted URL: "+getAllPhotos);
		task.execute(getAllPhotos);
		task.get(5000, TimeUnit.MILLISECONDS);
		String result = task.getData();
		
		List<String>  photoPath = new ArrayList<String>();
		try {
			JSONArray dataArray = new JSONArray(result);
			JSONObject json_data = null;
			
			for(int i=0; i<dataArray.length(); i++){
				json_data = dataArray.getJSONObject(i);
				photoPath.add(json_data.getString("filepath"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photoPath;
	}
	
	/**
	 * @param eID
	 * @param filepath
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void deletePhoto(int eID, String filepath) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String deletePhoto = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/deletePhoto.php?id="+eID+"&path="+filepath);
		Log.d(tag, "Attempted URL: "+deletePhoto);
		task.execute(deletePhoto);
	}
	
	/**
	 * @param eID
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public static void deleteAllPhotos(int eID) throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String deleteAllPhotos = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/deletePhoto.php?id="+eID);
		Log.d(tag, "Attempted URL: "+deleteAllPhotos);
		task.execute(deleteAllPhotos);
	}
	
	/**
	 * @return
	 * @throws TimeoutException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static List<String> getEventVersions() throws InterruptedException, ExecutionException, TimeoutException{
		FetchTask task = new FetchTask();
		String getEventVersions = processSpaces("http://www.users.csbsju.edu/~symesfin/mistypanda/test/getEventVersions.php");
		Log.d(tag, "Attempted URL: "+ getEventVersions);
		task.execute(getEventVersions);
		task.get(5000, TimeUnit.MILLISECONDS);
		String result = task.getData();
		List<String> versions = new ArrayList<String>();
		
		// Parse results into manageable variables and events. Then add them to event list.
		try{
			JSONArray dataArray = new JSONArray(result);
			JSONObject json_data = null;
			
			for(int i=0; i<dataArray.length(); i++){
				json_data = dataArray.getJSONObject(i);
				String idVersion = json_data.getInt("eID")+","+json_data.getInt("eVersion");
				versions.add(idVersion);
			}
		}
		catch(JSONException je){
			System.out.println(je.toString());
		}
		catch(ParseException pe){
			System.out.println("Date string is not in the correct format: " + pe.toString());
		}
		
		return versions;
	}
	
	/** Parses a string in the format of a MySQL DATETIME and turns it into a DateTime.
	 * @param stringDate - The date of a MySQL type DATETIME in string format.
	 * @return The date in the form of DateTime.
	 */
	public static DateTime parseDate(String stringDate) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime date = format.parseDateTime(stringDate);
		return date;
	}
	
     /** This method will take in any string input and replace all ' ' characters with the sequence "+"
     * First it will locate the spaces in the string
     * then it will replace spaces with the representation of space in php language  
     * String
     * @param input
     * @return the string after being processed with '+' wherever there were ' ' in the original string
     */
    public static String processSpaces(String input){
        String output = "";
        String space = "+";
        
        String[] words = input.split(" ");
        output = words[0];
        for(int i=1;i< words.length;i++){
            output = output.concat(space+words[i]);
        }
        return output;      
    }

	
}
