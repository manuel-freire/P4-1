package main.model.advancedObjects;

import java.util.List;
import java.util.Random;

import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Bike extends Vehicle {	
	public Bike(List<String> itinerary, String id, int max_speed, int max_fault_duration) {
		super(max_speed,max_fault_duration,itinerary,id);
	}
	public void setBrokenTime(int brokenTime) {
		if(this.getActualVel() > this.getMaxVel()/2)
			this.brokenTime = brokenTime;
	}
}
