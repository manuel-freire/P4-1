package main.model.advancedObjects;

import java.util.List;
import java.util.Random;

import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Bike extends Vehicle {
	private double breakProb;
	private Random rand; 
	
	public Bike(List<Road> itinerary, int seed, String id) {
		super(0,0,itinerary,id);
		rand = new Random(seed);
	}
	public void advanceBike() {
		if(!isOutOfOrder())
			if(getActualVel() < getMaxVel()/2) {
				if(rand.nextDouble() < breakProb)
					setBrokenTime(rand.nextInt(getMaxBreakTime()));
				else
					advance();
			}else
				advance();
	}
}
