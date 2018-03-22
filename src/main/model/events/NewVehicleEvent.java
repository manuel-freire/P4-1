package main.model.events;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;
import main.model.advancedObjects.Bike;
import main.model.advancedObjects.Car;
import main.model.simulatedObjects.Vehicle;

public class NewVehicleEvent extends Event{
	private String id, type;
	private int max_speed, max_breakTime, resistance, 
	max_fault_duration;
	private double faultProbability;
	private ArrayList<String> it;
	private String eventId;
	private int time;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
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
			Bike b = new Bike(it,id,max_speed,max_fault_duration);
			sim.addVehicle(b);
		} else if(type.equals("car")) {
			Car c = new Car(id, it, max_speed,max_fault_duration, resistance,0,faultProbability);
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
		if (type=="bike") {
			this.max_fault_duration=Integer.parseInt(sec.getValue("max_fault_duration"));
		} else if(type=="car"){
			this.faultProbability=Double.parseDouble(sec.getValue("fault_probability"));
			this.resistance=Integer.parseInt(sec.getValue("resistance"));
			this.max_fault_duration=Integer.parseInt(sec.getValue("max_fault_duration"));
		}
	}
	public void print() {  //Only for testing purposes
		System.out.println("---");
		System.out.println("VEHICLE");
		System.out.println("Itinerary:");
		for (int i=0; i<it.size(); i++) {
			System.out.print(it.get(i)+ ",");
		}
		System.out.println("");
		System.out.println("max_speed "+max_speed);
		System.out.println("max_fault_duration "+max_fault_duration);
		if (type=="bike") {
			System.out.println("bike");
			System.out.println("id "+id);

		} else {
			System.out.println("car");
			System.out.println("id "+id);
			System.out.println("resistance  "+resistance);
			System.out.println("fault_probability  "+faultProbability);
			
		}
	}
}
