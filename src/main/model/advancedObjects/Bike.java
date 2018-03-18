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
	public Bike(List<String> itinerary, String id, int max_speed, int max_fault_duration) {
		super(max_speed,max_fault_duration,itinerary,id);
	}
	/**
	 * Sets the faulty timer of the vehicle to the desired number. If it is higher than the maximum allowed it is set to this maximum.
	 */
	public void setFaultTime(int brokenTime) {
		if(this.getActualVel() > this.getMaxVel()/2)
			if(this.getMaxBreakTime()<brokenTime)
				this.brokenTime = this.getMaxBreakTime();
			else
				this.brokenTime = brokenTime;
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + getId() + "\n time = " + time + "\n kilometrage = " + getKilometrage() + "\n (" + getActualRoad().getID() + "," + getLocation() + ")" + "/n type = bike";
	}
}
