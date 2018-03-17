package tp.model.simulatedObjects;

import java.util.List;

import tp.model.TrafficSimulator;

public class Vehicle {
	private int actualVel, maxVel, location, kilometrage,maxBreakTime;
	private List<String> itinerary;
	private Road actualRoad;
	private String id;
	private int brokenTime;
	
	/**
	 * Constructs a vehicle with its desired behaviour.
	 * @param maxVel       maximum velocity it can have
	 * @param maxBreakTime maximum time it can be broken down
	 * @param itinerary    collection of junctions it has to go through
	 * @param id           identification of the vehicle
	 */
	public Vehicle(int maxVel, int maxBreakTime, List<String> itinerary, String id) {
		this.maxVel = maxVel;
		this.itinerary = itinerary;
		this.maxBreakTime = maxBreakTime;
		this.id = id;
	}
	
	/**
	 * Returns the road that is travelling through.
	 * 
	 * @return road that the vehicle is in
	 */
	public Road getActualRoad() {
		return actualRoad;
	}
	/**
	 * Returns the velocity of the vehicle.
	 * @return velocity of the vehicle
	 */
	public int getActualVel() {
		return actualVel;
	}
	/**
	 * Returns the location relative to the road it is in.
	 * @return location of the vehicle
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * Returns the maximum time it can be broken down.
	 * @return maximum time to be broken down.
	 */
	public int getMaxBreakTime() {
		return maxBreakTime;
	}
	/**
	 * Returns the maximum velocity of the vehicle.
	 * @return maximum velocity
	 */
	public int getMaxVel() {
		return maxVel;
	}
	/**
	 * Returns the time till it is no longer out of order.
	 * @return the time till it can move again.
	 */
	public int getBrokenTime() {
		return brokenTime;
	}
	/**
	 * Returns the identification of the vehicle.
	 * @return identification
	 */
	public String getID() {
		return id;
	}
	/**
	 * Returns if the vehicle is out of order.
	 * @return whether the vehicle is broken down.
	 */
	public boolean isOutOfOrder() {
		return brokenTime>0;
	}
	
	/**
	 * Sets the time it will be out of order.
	 * @param brokenTime time to be broken down.
	 */
	public void setBrokenTime(int brokenTime) {
		this.brokenTime = brokenTime;
	}
	/**
	 * Updates the velocity of the vehicle.
	 * @param actualVel velocity of the vehicle.
	 */
	public void setActualVel(int actualVel) {
		this.actualVel = actualVel;
	}
	/**
	 * Updates the road that the vehicle is travelling through.
	 * @param actualRoad road that it is in
	 */
	public void setActualRoad(Road actualRoad) {
		this.actualRoad = actualRoad;
	}
	/**
	 * Updates the location of the vehicle in the road.
	 * @param location distance travelled in the road
	 */
	public void setLocation(int location) {
		this.location = location;
	}
	
	/**
	 * Updates the vehicle's position in the road. If it has reached the end of the road it enters the junction and exits from the road.
	 */
	public void advance() {
		if(brokenTime <= 0) {
			location += actualVel;
			if(location>actualRoad.getLength())
				location = actualRoad.getLength();
			actualRoad.getEndJunction().enterVehicle(this,actualRoad);
			actualRoad.exitsVehicle(this);
		}else
			brokenTime--;
	}
	/**
	 * Advances to the next road.
	 */
	public void advanceToNextRoad(TrafficSimulator sim) {
		actualRoad.exitsVehicle(this);
		itinerary.remove(0);
		try {
			Road r = sim.getRoad(itinerary.get(0),itinerary.get(1));
			actualRoad = r;
			r.entersVehicle(this);
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("Vehicle "+id+" should have reached its destination.");
		}
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + id + "\n time = " + time + "\n kilometrage = " + kilometrage + "\n (" + actualRoad.getID() + "," + location + ")";
	}
}
