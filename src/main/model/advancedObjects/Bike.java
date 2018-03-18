package main.model.advancedObjects;

import java.util.List;
import java.util.Random;

import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Bike extends Vehicle {	
	public Bike(List<String> itinerary, String id, int max_speed, int max_fault_duration) {
		super(max_speed,max_fault_duration,itinerary,id);
	}
	public void setFaultTime(int brokenTime) {
		if(this.getActualVel() > this.getMaxVel()/2)
			this.brokenTime = brokenTime;
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + getId() + "\n time = " + time + "\n kilometrage = " + getKilometrage() + "\n (" + getActualRoad().getID() + "," + getLocation() + ")" + "/n type = bike";
	}
}
