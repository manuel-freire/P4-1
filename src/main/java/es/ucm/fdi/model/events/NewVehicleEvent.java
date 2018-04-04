package es.ucm.fdi.model.events;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import es.ucm.fdi.model.TrafficSimulator;
import es.ucm.fdi.model.advancedObjects.Bike;
import es.ucm.fdi.model.advancedObjects.Car;
import es.ucm.fdi.model.simulatedObjects.Vehicle;
import es.ucm.fdi.ini.*;
/**
 * Class of the event that creates a new vehicle.
 * @author Javier Navalon
 *
 */
public class NewVehicleEvent extends Event{
	private String id, type;
	private int max_speed, resistance, 
	max_fault_duration;
	private double faultProbability;
	private ArrayList<String> it;
	private String eventId;
	private int time;
	private long seed;
	
	public int getTime() {
		return time;
	}
	public NewVehicleEvent() {
		eventId="new_vehicle";
		it=new ArrayList<String>();
	}
	public void execute(TrafficSimulator sim) {
		if(type == null){
			Vehicle v = new Vehicle(max_speed, it,id);
			sim.addVehicle(v);
		} else if (type.equals("bike")) {
			Bike b = new Bike(it,id,max_speed);
			sim.addVehicle(b);
		} else if(type.equals("car")) {
			Car c = new Car(id, it, max_speed,max_fault_duration, resistance,seed,faultProbability);
			sim.addVehicle(c);
		} else 
			throw new NoSuchElementException("The type of vehicle " + type + " doesn't exist");
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewVehicleEvent();
	} else {
		return null;
		}
	}
	public void builder(IniSection sec) {
		this.time=Integer.parseInt(sec.getValue("time"));
		this.type=sec.getValue("type");
		this.id=sec.getValue("id");
		String[]iti=sec.getValue("itinerary").split(",");
		for (int i=0; i<iti.length; i++) {
			this.it.add(iti[i]);
		}
		this.max_speed=Integer.parseInt(sec.getValue("max_speed"));
		if(type!=null)
			if(type.equals("car")){
				this.faultProbability=Double.parseDouble(sec.getValue("fault_probability"));
				this.resistance=Integer.parseInt(sec.getValue("resistance"));
				this.max_fault_duration=Integer.parseInt(sec.getValue("max_fault_duration"));
				this.seed=Long.parseLong(sec.getValue("seed"));
			}
	}
}
