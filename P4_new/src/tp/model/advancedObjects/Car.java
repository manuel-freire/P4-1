package tp.model.advancedObjects;

import java.util.List;
import java.util.Random;
import tp.model.simulatedObjects.Road;
import tp.model.simulatedObjects.Vehicle;

public class Car extends Vehicle {
	private int resistanceKm, sinceBreak;
	private double breakProb;
	private Random rand; 
	
	public Car(List<Road> itinerary, int seed, String id) {
		super(0,0,itinerary,id);
		rand = new Random(seed);
	}
	public void advanceCar() {
		if(!isOutOfOrder())
			if(sinceBreak >= resistanceKm) {
				if(rand.nextDouble() < breakProb)
					setBrokenTime(rand.nextInt(getMaxBreakTime()));
				else {
					int loc = getLocation();
					advance();
					sinceBreak += getLocation()-loc;
				}
			}else {
				int loc = getLocation();
				advance();
				sinceBreak += getLocation()-loc;
			}
	}
}
