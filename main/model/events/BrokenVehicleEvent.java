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
	/**
	 * Returns the time of the event
	 */
	public int getTime() {
		return time;
	}
	/**
	 * Sets the time of the event
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * Initialize the id of the event for the parser and
	 * the list of vehicles
	 */
	public BrokenVehicleEvent() {
		eventId="make_vehicle_faulty";
		vehicles=new ArrayList<String>();
	}
	/**
	 * Executes the event by breaking the vehicles on the
	 * list the duration specified
	 */
	public void execute(TrafficSimulator sim) {
		sim.breakVehicles(vehicles,duration);
	}
	/**
	 * Returns the vehicle if the provided id is correct
	 * @param id A String with the id of the event type
	 */
	public Event parser(String id){
		if (eventId.equals(id))
			return new BrokenVehicleEvent();
		else
			return null;
	}
	/**
	 * Creates the event from an IniSection
	 * @param sec The information of an Ini file section
	 */
	public void builder(IniSection sec) {
		vehicles  = Arrays.asList(sec.getValue("vehicles").split(","));
		this.time=Integer.parseInt(sec.getValue("time"));
		this.duration=Integer.parseInt(sec.getValue("duration"));
	}
}
