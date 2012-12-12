package com.mistypanda.ultimatescheduler;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mistypanda.ultimatescheduler.DBAccess.*;

public class CreateEventActivity extends Activity {

	private int mYear;
	private int secYear;
	private int mMonth;
	private int secMonth;
	private int mDay;
	private int secDay;
	private int mHour;
	private int mMinute;
	private int sMinute;
	private int sHour;
	
	private TextView mDateDisplay;
	private Button mPickDate;
	
	private TextView secondDateDisplay;
	private Button secondPickDate;
	
	private TextView firstTimeDisplay;
	private Button firstPickTime;
	private TextView secondTimeDisplay;
	private Button secondPickTime;

	static final int DATE_DIALOG_START = 0;
	static final int DATE_DIALOG_ID2 = 1;
	static final int TIME_DIALOG = 2;
	static final int TIME_DIALOG2 = 3;
	
	
	
 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.createactivity);
	    
	    secondTimeDisplay = (TextView) findViewById(R.id.showEndTime);        
	    secondPickTime = (Button) findViewById(R.id.endTime);
	    
	    secondPickTime.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
	            showDialog(TIME_DIALOG2);
	        }
	    });
	    
	    updateDisplayTime2();
	    
	    firstTimeDisplay = (TextView) findViewById(R.id.showStartTime);        
	    firstPickTime = (Button) findViewById(R.id.startTime);
	    
	    firstPickTime.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
	            showDialog(TIME_DIALOG);
	        }
	    });
	    
	    updateDisplayTime();
	    
	    mDateDisplay = (TextView) findViewById(R.id.showStartDate);        
	    mPickDate = (Button) findViewById(R.id.myStartDateButton);
		
	    mPickDate.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
	            showDialog(DATE_DIALOG_START);
	        }
	    });
	    
	    // get the current date
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
	    mMonth = c.get(Calendar.MONTH);
	    mDay = c.get(Calendar.DAY_OF_MONTH);
	    
	    updateDisplay();
	    
	    
	    /*
	     * Second Date Picker OnClickListener, and getting the current date to display as the text view initially.
	     */
	    secondDateDisplay = (TextView) findViewById(R.id.showEndDate);        
	    secondPickDate = (Button) findViewById(R.id.myEndDateButton);
	    
	    secondPickDate.setOnClickListener(new View.OnClickListener() {
	        @SuppressWarnings("deprecation")
			public void onClick(View c) {
	            showDialog(DATE_DIALOG_ID2);
	        }
	    });

	    // get the current date
	    final Calendar c2 = Calendar.getInstance();
	    secYear = c2.get(Calendar.YEAR);
	    secMonth = c2.get(Calendar.MONTH);
	    secDay = c2.get(Calendar.DAY_OF_MONTH);

	    
	    updateDisplaySec();
	   
	    
  
	}
	
    // display the current date
    private void updateDisplay() {
        this.mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    
    private void updateDisplaySec() {
    	this.secondDateDisplay.setText(new StringBuilder()
        	// Month is 0 based so add 1
        	.append(secMonth + 1).append("-")
        	.append(secDay).append("-")
        	.append(secYear).append(" "));
    }
    
    private void updateDisplayTime() {
    	this.firstTimeDisplay.setText(new StringBuilder()
    		.append(mHour).append(":").append(mMinute)
    		);
    }
    
    private void updateDisplayTime2() {
    	this.secondTimeDisplay.setText(new StringBuilder()
    		.append(sHour).append(":").append(sMinute)
    		);
    }
    
	
    private DatePickerDialog.OnDateSetListener mDateSetListener =
    	    new DatePickerDialog.OnDateSetListener() {
    	        public void onDateSet(DatePicker view, int year, 
    	                              int monthOfYear, int dayOfMonth) {
    	            mYear = year;
    	            mMonth = monthOfYear;
    	            mDay = dayOfMonth;
    	            updateDisplay();
    	        }
    	    };
    	    
	private DatePickerDialog.OnDateSetListener secDateSetListener =
		    new DatePickerDialog.OnDateSetListener() {
		        public void onDateSet(DatePicker viewSec, int yearSec, 
		                              int monthOfYearSec, int dayOfMonthSec) {
		    
		            secYear = yearSec;
		            secMonth = monthOfYearSec;
		            secDay = dayOfMonthSec;
		            updateDisplaySec();
		        }
		    };
		    
    private TimePickerDialog.OnTimeSetListener mTimeListener =
    	    new TimePickerDialog.OnTimeSetListener() {
    	        public void onTimeSet(TimePicker view, int hour, int minute) {
    	            mHour = hour;
    	            mMinute = minute;
    	            updateDisplayTime();
    	        }
    	    };
    	    
    	    
    private TimePickerDialog.OnTimeSetListener sTimeListener =
    	    new TimePickerDialog.OnTimeSetListener() {
    	        public void onTimeSet(TimePicker view, int hour, int minute) {
    	            sHour = hour;
    	            sMinute = minute;
    	            updateDisplayTime2();
    	        }
    	    };
    	   
    @Override
    protected Dialog onCreateDialog(int id) {
       switch (id) {
       case DATE_DIALOG_START:
          return new DatePickerDialog(this,
                    mDateSetListener,
                    mYear, mMonth, mDay);
       		case DATE_DIALOG_ID2:
       			return new DatePickerDialog(this, secDateSetListener,
       					secYear, secMonth, secDay);
       		case TIME_DIALOG:
       			return new TimePickerDialog(this, mTimeListener, mHour, mMinute, true);
       		case TIME_DIALOG2:
       			return new TimePickerDialog(this, sTimeListener, sHour, sMinute, true);
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
			popupMessage(e.getMessage());
		}
		
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	//	View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		
		
	}

	/**
	 * this method will take a string message and create a popup on the device displaying the message
	 * void
	 * @param message
	 */
	private void popupMessage(String message) {
		// TODO Auto-generated method stub
		
	}

}
