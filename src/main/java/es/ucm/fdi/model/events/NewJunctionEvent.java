package main.model.events;

import java.util.NoSuchElementException;

import main.ini.*;
import main.model.TrafficSimulator;
import main.model.advancedObjects.MostCrowded;
import main.model.advancedObjects.RoundRobin;
import main.model.simulatedObjects.Junction;
/**
 * Class of the event that creates a new junction.
 * @author Javier Navalon
 *
 */
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
	public NewJunctionEvent() {
		eventId="new_junction";
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewJunctionEvent();
	} else {
		return null;
		}
	}
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
