package main.model.advancedObjects;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Car extends Vehicle {
	private int resistance, max_fault_duration, since_fault;
	private double faulty_probability;
	private Random rand; 
	
	public Car(String id, ArrayList<String> itinerary, int max_speed, int max_fault_duration, int fault_probability, int resistance, int seed, double faulty_probability) {
		super(max_speed,max_fault_duration,itinerary,id);
		this.faulty_probability = faulty_probability;
		this.max_fault_duration = max_fault_duration;
		this.resistance = resistance;
		this.since_fault = 0;
		if(seed!=0)
			rand = new Random(seed);
		else
			rand = new Random(System.currentTimeMillis());
	}
	public void advance(TrafficSimulator sim) {
			if(!isOutOfOrder() && since_fault >= resistance && rand.nextDouble() < faulty_probability)
					setBrokenTime(rand.nextInt(getMaxBreakTime()+1));
			else {
				if(getBrokenTime() <= 0) {
					setLocation(getLocation() + getActualVel());
					if(getLocation()>getActualRoad().getLength())
						setLocation(getActualRoad().getLength());
					try {
						sim.getJunction(getActualRoad().getEndJunction()).enterVehicle(this,getActualRoad().getID());
					}catch(NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					getActualRoad().exitsVehicle(this);
				}else
					setBrokenTime(getBrokenTime() - 1);
			}
	}
}
