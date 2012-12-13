package com.mistypanda.ultimatescheduler;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

import com.mistypanda.ultimatescheduler.DBAccess.*;

public class EditEventActivity extends Activity{

	private Event event;

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
	private int sSecond;
	private int mSecond;

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

	EditText name, host, location, description, pword;
	TextView startDate, endDate, startTime, endTime;


	/*
	 *  Called when the activity is first created. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.editeventactivity);
		Intent intent = getIntent();
		event=(Event)intent.getSerializableExtra("Event");

		name = (EditText)findViewById(R.id.editeventNameET);
		host = (EditText)findViewById(R.id.editeventHostET);
		location = (EditText)findViewById(R.id.editlocationET);
		description = (EditText)findViewById(R.id.editdescriptionET);
		// pword = (EditText)findViewById(R.id.editenterPassword);

		startDate = (TextView)findViewById(R.id.showStartDate);
		endDate = (TextView)findViewById(R.id.showEndDate);
		startTime = (TextView)findViewById(R.id.showStartTime);
		endTime = (TextView)findViewById(R.id.showEndTime);

		name.setText(event.getEventName());
		host.setText(event.getHost());
		location.setText(event.getLocation());
		description.setText(event.getInfo());
		//pword.setText(event.getPassword());

		/*
		 *  Set secondTime listener and set them to the appropriate buttons and textviews
		 */
		secondTimeDisplay = (TextView) findViewById(R.id.showEndTime);        
		secondPickTime = (Button) findViewById(R.id.endTime);

		secondPickTime.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(TIME_DIALOG2);
			}
		});

		final Calendar c0 = Calendar.getInstance();
		sHour = c0.get(Calendar.HOUR);
		sMinute = c0.get(Calendar.MINUTE);
		sSecond = c0.get(Calendar.SECOND);

		updateDisplayTime2();


		/*
		 *  Set firstTime listener and set them to the appropriate buttons and textviews
		 */
		firstTimeDisplay = (TextView) findViewById(R.id.showStartTime);        
		firstPickTime = (Button) findViewById(R.id.startTime);

		firstPickTime.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(TIME_DIALOG);
			}
		});



		final Calendar c1 = Calendar.getInstance();
		mHour = c1.get(Calendar.HOUR);
		mMinute = c1.get(Calendar.MINUTE);
		mSecond = c1.get(Calendar.SECOND);


		updateDisplayTime();

		/*
		 *  Set startDate listener and set them to the appropriate buttons and textviews
		 */
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
				.append(mYear).append("-")
				.append(mMonth + 1).append("-")
				.append(mDay).append(" "));
	}

	private void updateDisplaySec() {
		this.secondDateDisplay.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(secYear).append("-")
		.append(secMonth + 1).append("-")
		.append(secDay).append(" "));
	}

	private void updateDisplayTime() {
		this.firstTimeDisplay.setText(new StringBuilder()
		.append(addZero(mHour)).append(":").append(addZero(mMinute)).append(":").append(mSecond)
		);
	}

	private void updateDisplayTime2() {
		this.secondTimeDisplay.setText(new StringBuilder()
		.append(addZero(sHour)).append(":").append(addZero(sMinute)).append(":").append(sSecond)
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

	public void onDeleteClick(View view){
		try {
			DBHelper.deleteEvent(event.getID(), event.getPassword());
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
		Intent intent=new Intent(this, EventsActivity.class);
		startActivity(intent);
		this.onDestroy();
	}

	public void createClick(View view){

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		LayoutInflater inflater = getLayoutInflater();


		String eNam = name.getText().toString();
		String eLoc = location.getText().toString();
		String eHost = host.getText().toString();
		String eStart = startDate.getText().toString();
		String eEnd = endDate.getText().toString();
		String descrip = isEmpty(description.getText().toString());
		String password = event.getPassword();
		String sTime = startTime.getText().toString();
		String eTime = endTime.getText().toString();

		String startDateTime = eStart + sTime;
		String endDateTime = eEnd + eTime;


		try {
			// creating the event will validate the input for the event to add
			Event candidate = new Event(0,eNam, eLoc, eHost, null, null, descrip,0, password);
			Log.d("Edit Event Acticity","ID: "+event.getID()+ ", Name: "+eNam+", Loc: "+eLoc+", Host: "+eHost+
					", Info: "+descrip+", Start: "+startDateTime+", End: "+endDateTime+", Password: "+password);
			/**
			 * @param eID
			 * @param eNam
			 * @param loc
			 * @param host
			 * @param sDat
			 * @param eDat
			 * @param info
			 * @param password
			 **/

			DBHelper.updateEvent(event.getID(),eNam, eLoc, eHost, startDateTime, endDateTime, descrip, password);

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
			popupMessage("Edit event Error: "+e.getMessage());
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
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();

	}

	private String isEmpty(String input){
		if(input.isEmpty()){
			return "No Description";
		} else {
			return input;
		}
	}

	private String addZero(int input){
		if(input < 10){

			return "0"+input;
		} else {
			return ""+input;
		}
	}


}
