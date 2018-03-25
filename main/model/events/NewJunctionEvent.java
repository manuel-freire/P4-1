package main.model.events;

import java.util.NoSuchElementException;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;
import main.model.advancedObjects.MostCrowded;
import main.model.advancedObjects.RoundRobin;
import main.model.simulatedObjects.Junction;

public class NewJunctionEvent extends Event {
	private String junctionId;
	private String eventId;
	private int time;
	private String type;
	private int max_time_slice;
	private int min_time_slice;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public NewJunctionEvent() {
		eventId="new_junction";
	}
	/**
	 * Returns the vehicle if the provided id is correct
	 * @param id A String with the id of the event type
	 */
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewJunctionEvent();
	} else {
		return null;
		}
	}
	/**
	 * Executes the event by adding a new junction to the
	 * simulator
	 */
	public void execute(TrafficSimulator sim) {
		Junction j;
		if(type==null)
			j = new Junction(junctionId);
		else if(type.equals("rr"))
			j = new RoundRobin(junctionId, max_time_slice, min_time_slice);
		else if(type.equals("mc"))
			j = new MostCrowded(junctionId);
		else
			throw new NoSuchElementException("The type of junction provided does not exist.");
		sim.addJunction(j);
	}
	/**
	 * Creates the event from an IniSection
	 * @param sec The information of an ini file section
	 */
	public void builder(IniSection sec) {
		junctionId=sec.getValue("id");
		this.time=Integer.parseInt(sec.getValue("time"));
		this.type = sec.getValue("type");
		if(type!=null)
			if(type.equals("rr")) {
				this.max_time_slice = Integer.parseInt(sec.getValue("max_time_slice"));
				this.min_time_slice = Integer.parseInt(sec.getValue("min_time_slice"));	
			}
	}
}
