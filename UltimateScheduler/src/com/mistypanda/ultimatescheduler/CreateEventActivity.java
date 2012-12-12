package com.mistypanda.ultimatescheduler;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mistypanda.ultimatescheduler.DBAccess.*;

public class CreateEventActivity extends Activity {

	private int mYear, secYear;
	private int mMonth, secMonth;
	private int mDay, secDay;

	private TextView mDateDisplay;
	private Button mPickDate;
	private TextView secondDateDisplay;
	private Button secondPickDate;
	
	private TextView firstTimeDisplay;
	private Button firstPickTime;
	private TextView secondTimeDisplay;
	private Button secondPickTime;

	static final int DATE_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID2 = 0;
	
	
	
 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.createactivity);
	 
	    mDateDisplay = (TextView) findViewById(R.id.showStartDate);        
	    mPickDate = (Button) findViewById(R.id.myStartDateButton);
		
		
	    secondDateDisplay = (TextView) findViewById(R.id.showEndDate);        
	    secondPickDate = (Button) findViewById(R.id.myEndDateButton);
	    

	    mPickDate.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	        }
	    });
	    
	    secondPickDate.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID2);
	        }
	    });

	    // get the current date
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
	    mMonth = c.get(Calendar.MONTH);
	    mDay = c.get(Calendar.DAY_OF_MONTH);
	    	    
	    updateDisplay();
  
	}
	
    // display the current date
    private void updateDisplay() {
    	this.secondDateDisplay.setText(new StringBuilder()
        	// Month is 0 based so add 1
        	.append(secMonth + 1).append("-")
        	.append(secDay).append("-")
        	.append(secYear).append(" "));
    	
        this.mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    
    
	
    private DatePickerDialog.OnDateSetListener mDateSetListener =
    	    new DatePickerDialog.OnDateSetListener() {
    	        public void onDateSet(DatePicker view, int year, 
    	                              int monthOfYear, int dayOfMonth) {
    	            mYear = year;
    	            mMonth = monthOfYear;
    	            mDay = dayOfMonth;
    	            secYear = year;
    	            secMonth = monthOfYear;
    	            secDay = dayOfMonth;
    	            updateDisplay();
    	        }
    	    };
    	    
    	   
    @Override
    protected Dialog onCreateDialog(int id) {
       switch (id) {
       case DATE_DIALOG_ID:
          return new DatePickerDialog(this,
                    mDateSetListener,
                    mYear, mMonth, mDay);
       }
       return null;
    }  
	

	
	public void createClick(View view){
		LayoutInflater inflater = getLayoutInflater();
		EditText name = (EditText)findViewById(R.id.eventNameET);
		//DatePicker startDate = (DatePicker)findViewById(R.id.startDateTV);
		//DatePicker endDate = (DatePicker)findViewById(R.id.endDateTV);
		EditText host = (EditText)findViewById(R.id.eventHostET);
		EditText location = (EditText)findViewById(R.id.locationET);
		EditText description = (EditText)findViewById(R.id.descriptionET);
		
		String eNam = name.getText().toString();
		String eLoc = location.getText().toString();
		String eHost = host.getText().toString();
		String eStart = null;	/*= startDate.getText().toString();*/
		String eEnd = null;	/*= endDate.getText().toString();*/
		String descrip = description.getText().toString();
		String password = "";//TO-DO
		
		try {
			// creating the event will validate the input for the event to add
			Event candidate = new Event(0,eNam, eLoc, eHost, null, null, descrip,0, password);
			DBHelper.addEvent(eNam, eLoc, eHost, eStart, eEnd, descrip, password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			//create pop up box with error message e.getMessage();
			popupMessage("Create event Error: "+e.getMessage());
		}
		
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	//	View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		
		
	}

	/**
	 * this method will take a string message and create a popup on the device 
	 *  displaying the message using the toast class
	 * void
	 * @param message
	 */
	private void popupMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		
	}

}
