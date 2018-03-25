package main.model.simulatedObjects;

import java.util.List;

import main.model.TrafficSimulator;
/**
 * Vehicle class.
 * @author Borja Lozano
 */
public class Vehicle {
	private int actualVel;
	protected int maxVel;
	private int location;
	private int kilometrage;
	private List<String> itinerary;
	private Road actualRoad;
	private boolean arrived;
	protected String id;
	protected int brokenTime;
	private boolean waiting;
	
	/**
	 * Constructs a vehicle with its desired behaviour.
	 * @param maxVel       maximum velocity it can have
	 * @param itinerary    collection of junctions it has to go through
	 * @param id           identification of the vehicle
	 */
	public Vehicle(int maxVel, List<String> itinerary, String id) {
		this.maxVel = maxVel;
		this.setItinerary(itinerary);
		this.id=id;
		this.setArrived(false);
		this.location =0;
		this.setWaiting(false);
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
		return getId();
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
	 * @param fault_time time to be broken down.
	 */
	public void setFaultTime(int fault_time) {
		this.brokenTime = fault_time;
	}
	/**
	 * Updates the velocity of the vehicle.
	 * @param actualVel velocity of the vehicle.
	 */
	public void setActualVel(int actualVel) {
		this.actualVel = Math.min(this.maxVel, actualVel);
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
	 * @param sim simulation
	 */
	public void advance(TrafficSimulator sim) {
		if(brokenTime <= 0) {
			int km = location;
			location += actualVel;
			if(location>=actualRoad.getLength()) {
				location = actualRoad.getLength();
				actualVel = 0;
				if(!isWaiting()) {
					setWaiting(true);
					sim.getJunction(actualRoad.getEndJunction()).enterVehicle(this,actualRoad.getID());
				}
			}
			kilometrage += location-km;
		}else {
			brokenTime--;
			this.actualVel = 0;
		}
	}
	/**
	 * Advances to the next road.
	 * @param sim simulation
	 */
	public void advanceToNextRoad(TrafficSimulator sim) {
		getItinerary().remove(0);
		this.actualVel = 0;
		this.location = 0;
		actualRoad.exitVehicle(this);
		if(getItinerary().size() < 2) {
			assert(isArrived()==true);
			setArrived(true);
		}
		else {
			Road r = sim.getRoad(getItinerary().get(0),getItinerary().get(1));
			actualRoad = r;
			r.entersVehicle(this);
			setWaiting(false);
		}
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		String loc = isArrived() ? "arrived" : "(" + actualRoad.getID() + "," + location + ")";
		return "[vehicle_report]\nid = " + getId() + "\ntime = " + time + "\nspeed = " + this.actualVel +  "\nkilometrage = " + getKilometrage() + "\nfaulty = " + getBrokenTime() + "\nlocation = " + loc + "\n";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public List<String> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<String> itinerary) {
		this.itinerary = itinerary;
	}

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public boolean isArrived() {
		return arrived;
	}

	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}
}
