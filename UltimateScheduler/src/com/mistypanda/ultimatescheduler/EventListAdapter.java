package com.mistypanda.ultimatescheduler;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<Event> {
	private final List<Event> events;
	private final Context context;
	
	public EventListAdapter(Context context, List<Event> events){
		super(context, R.layout.eventlistview, events);
		this.context=context;
		this.events=events;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView=inflater.inflate(R.layout.eventlistview,parent, false);
		TextView eventName = (TextView) rowView.findViewById(R.id.eventname);
		TextView location = (TextView) rowView.findViewById(R.id.location);
		TextView date = (TextView) rowView.findViewById(R.id.date);
		
		eventName.setText(events.get(position).getEventName());
		location.setText(events.get(position).getLocation());
		date.setText(events.get(position).getStartDate().toDate().toString());
		

		
		return rowView;
		
	}
	
}
