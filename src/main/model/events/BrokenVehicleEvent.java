package main.model.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;

public class BrokenVehicleEvent extends Event{
	private int duration, time;
	private List<String> vehicles;
	private String eventId;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public BrokenVehicleEvent() {
		eventId="make_vehicle_faulty";
		vehicles=new ArrayList<String>();
	}
	public void execute(TrafficSimulator sim) {
		sim.breakVehicles(vehicles,duration);
	}
	public Event parser(String id){
		if (eventId.equals(id))
			return new BrokenVehicleEvent();
		else
			return null;
	}
	public void builder(IniSection sec) {
		vehicles  = Arrays.asList(sec.getValue("vehicles").split(","));
		this.time=Integer.parseInt(sec.getValue("time"));
		this.duration=Integer.parseInt(sec.getValue("duration"));
	}
	public void print() {  //Only for testing purposes
		System.out.println(eventId);
		System.out.print("Duration, "); System.out.println(duration);
		System.out.print("Time, "); System.out.println(time);
		for (int i=0; i<vehicles.size(); i++) {
			System.out.println(vehicles.get(i));
		}
	}
}
