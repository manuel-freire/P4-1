package main.model.advancedObjects;

import java.util.List;
import java.util.Random;

import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Bike extends Vehicle {	
	/**
	 * Class constructor.
	 * @param itinerary          collection of junction identifications to go through
	 * @param id                 identification of the vehicle
	 * @param max_speed          maximum speed of the vehicle
	 * @param max_fault_duration maximum time that the vehicle can be out of order
	 */
	private int max_fault_duration;
	public Bike(List<String> itinerary, String id, int max_speed, int max_fault_duration) {
		super(max_speed,itinerary,id);
		this.max_fault_duration = max_fault_duration;
	}
	/**
	 * Sets the faulty timer of the vehicle to the desired number. If it is higher than the maximum allowed it is set to this maximum.
	 */
	public void setFaultTime(int brokenTime) {
		if(this.getActualVel() > this.getMaxVel()/2)
			if(this.max_fault_duration<brokenTime)
				this.brokenTime = this.max_fault_duration;
			else
				this.brokenTime = brokenTime;
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		String loc = isArrived() ? "arrived" : "(" + getActualRoad().getID() + "," + getLocation() + ")";
		return "[vehicle_report]\nid = " + getId() + "\ntime = " + time + "\ntype = bike\nspeed = " + this.getActualVel() + "\nkilometrage = " + getKilometrage() + "\nfaulty = " + getBrokenTime() + "\nlocation = " + loc + "\n";
	}
	public void print() {
		System.out.println("bike");
		System.out.println("id "+ id);
		System.out.println("Itinerary:");
		for (int i=0; i<getItinerary().size(); i++) {
			System.out.print(getItinerary().get(i)+ ",");
		}
		System.out.println("max_speed "+maxVel);
		System.out.println("max_fault_duration "+max_fault_duration);
	}
}
