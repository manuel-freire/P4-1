package es.ucm.fdi.model.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.TrafficSimulator;
/**
 * Class of the event that breaks vehicles.
 * @author Javier Navalon
 *
 */
public class BrokenVehicleEvent extends Event{
	private int duration, time;
	private List<String> vehicles;
	private String eventId;
	/**
	 * Class constructor.
	 */
	public BrokenVehicleEvent() {
		eventId="make_vehicle_faulty";
		vehicles=new ArrayList<String>();
	}
	public int getTime() {
		return time;
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
}
