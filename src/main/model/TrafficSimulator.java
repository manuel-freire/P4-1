package main.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import main.model.events.Event;
import main.model.simulatedObjects.*;
/**
 * Class that sets up the simulator and runs it. Stores the list of events and executes them.
 * @author Borja Lozano
 */
public class TrafficSimulator {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Event> events;
	int actualTick, totalTicks;
	/**
	 * Class constructor.
	 * @param ticks number of ticks
	 */
	public TrafficSimulator(int ticks) {
		events=new ArrayList<Event>();
		junctions=new ArrayList<Junction>();
		roads=new ArrayList<Road>();
		vehicles=new ArrayList<Vehicle>();
		actualTick = 0;
		this.totalTicks = ticks;
	}
	/**
	 * Starts the simulation.
	 * @param ostream file outstream
	 */
	public void execute(FileOutputStream ostream) {
		int limiteTiempo = this.actualTick + totalTicks - 1; 
		while (this.actualTick <= limiteTiempo) {  
			for(int i = 0; i < events.size(); i++) {
				Event e = events.get(i);
				if(e.getTime() <= actualTick) {
					try {
						e.execute(this);
						events.remove(i);
						i--;
					} catch(Exception ex) {
						ex.printStackTrace();
						System.exit(1);
					}
				}
			} 
			for(Road r : roads) 
				r.advance(this);
			for(Junction j : junctions)
				j.advance(this);
			actualTick++;
			try {
				for(Junction j : junctions)
					ostream.write((j.generateReport(actualTick)+'\n').getBytes());
				for(Road r : roads)
					ostream.write((r.generateReport(actualTick)+'\n').getBytes());
				for(Vehicle v : vehicles)
					ostream.write((v.generateReport(actualTick)+'\n').getBytes());
			}catch(IOException e) {
				e.printStackTrace();
			}
		} 
	}
	/**
	 * Returns the road that matches the source junction and destination junction
	 * @param src_id source junction
	 * @param end_id end junction
	 * @return road that matches the parameters
	 */
	public Road getRoad(String src_id, String end_id) {
		Road road = null;
		for(Road r: roads)
			if(r.getSourceJunction().equals(src_id)&&getJunction(r.getEndJunction()).getID().equals(end_id))
				road = r;
		if(road == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return road;
	}
	/**
	 * Returns the junction that matches the id.
	 * @param id identification of the junction
	 * @return junction that matches the id
	 * @throws NoSuchElementException if the junction can't be found
	 */
	public Junction getJunction(String id) throws NoSuchElementException {
		Junction jun = null;
		for(Junction j : junctions)
			if(j.getID().equals(id))
				jun = j;
		if(jun == null)
			throw new NoSuchElementException("The junction: " + id + "could't be found.");
		else
			return jun;
	}
	/**
	 * Add a event to the simulation
	 * @param e event to be added
	 */
	public void addEvent(Event e) {
		events.add(e);
	}
	/**
	 * Add a road to the simulation
	 * @param r road to be added
	 */
	public void addRoad(Road r) {
		boolean found = false;
		roads.add(r);
		for(Junction j : junctions)
			if(j.getID().equals(r.getEndJunction())) {
				j.addRoad(r.getID());
				found = true;
			}
		if(found == false)
			throw new NoSuchElementException("No junction matches the destination of the road: " + r.getID() + ".");
				
	}
	/**
	 * Add a vehicle to the simulation
	 * @param v vehicle to be added
	 */
	public void addVehicle(Vehicle v) {
		boolean found = false;
		vehicles.add(v);
		for(Road r : roads)
			if(r.getSourceJunction().equals(v.getItinerary().get(0))) {
				r.entersVehicle(v);
				found = true;
				v.setActualRoad(r);
				break;
			}
		if(found == false)
			throw new NoSuchElementException("No road matches the first junctions of the itinerary of the vehicle: " + v.getID() + ".");
	}
	/**
	 * Add a junction to the simulation
	 * @param j junction to be added
	 */
	public void addJunction(Junction j) {
		junctions.add(j);
	}
	/**
	 * Sets the broken down duration of every desired vehicle to the one provided.
	 * @param vehicles2 vehicles to be broken
	 * @param duration duration
	 */
	public void breakVehicles(List<String> vehicles2, int duration) {
		for(String id : vehicles2)
			for(Vehicle v : vehicles)
				if(v.getID().equals(id)) {
					v.setFaultTime(duration);
				}
	}
}
