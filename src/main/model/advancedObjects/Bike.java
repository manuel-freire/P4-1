package main.model.advancedObjects;

import java.util.ArrayList;

import main.model.simulatedObjects.Vehicle;
/**
 * Bike class. Extends Vehicle.
 * @author Borja Lozano
 */
public class Bike extends Vehicle {	
	/**
	 * Class constructor.
	 * @param itinerary          collection of junction identifications to go through
	 * @param id                 identification of the vehicle
	 * @param max_speed          maximum speed of the vehicle
	 */
	public Bike(ArrayList<String> itinerary, String id, int max_speed) {
		super(max_speed,itinerary,id);
	}
	/**
	 * Sets the faulty timer of the vehicle to the desired number. If it is higher than the maximum allowed it is set to this maximum.
	 * @param brokenTime fault time
	 */
	public void setFaultTime(int brokenTime) {
		if(this.getActualVel() > this.getMaxVel()/2)
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
}
