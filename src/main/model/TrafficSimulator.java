package main.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import main.model.events.Event;
import main.model.simulatedObjects.*;

public class TrafficSimulator {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Event> events;
	int actualTick, totalTicks;
	
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
						System.out.println(ex.getMessage());
						ex.printStackTrace();
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
					ostream.write(j.generateReport(actualTick).getBytes());
				for(Road r : roads)
					ostream.write(r.generateReport(actualTick).getBytes());
				for(Vehicle v : vehicles)
					ostream.write(v.generateReport(actualTick).getBytes());
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		} 
	}
	/**
	 * Returns the road that matches the source junction and destination junction
	 * @param road_id
	 * @param end_id
	 * @return road that matches the parameters
	 */
	public Road getRoad(String road_id, String end_id) {
		Road road = null;
		for(Road r: roads)
			if(r.getID().equals(road_id)&&getJunction(r.getEndJunction()).getID().equals(end_id))
				road = r;
		if(road == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return road;
	}
	/**
	 * Returns the junction that matches the id.
	 * @param id
	 * @return junction that matches the id
	 * @throws NoSuchElementException
	 */
	public Junction getJunction(String id) throws NoSuchElementException {
		Junction jun = null;
		for(Junction j : junctions)
			if(j.getID().equals(id))
				jun = j;
		if(jun == null)
			throw new NoSuchElementException("The junction does not exist.");
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
			throw new NoSuchElementException("No junction matches the destination of the road.");
				
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
			}
		if(found == false)
			throw new NoSuchElementException("No road matches the vehicle start.");
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
	 * @param vehicles_id 
	 * @param duration 
	 */
	public void breakVehicles(ArrayList<String> vehicles_id, int duration) {
		for(String id : vehicles_id)
			for(Vehicle v : vehicles)
				if(v.getID().equals(id)) {
					v.setFaultTime(duration);
					vehicles_id.remove(id);
				}
	}
	
	// Testing
	public ArrayList<Junction> getJunctions() {
		return junctions;
	}
	public ArrayList<Road> getRoads() {
		return roads;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public ArrayList<Event> getEvents() {
		return events;
	}
	public int getActualTick() {
		return actualTick;
	}
	public int getTotalTicks() {
		return totalTicks;
	}
	//Testing
	public void printJunctions() {
		for (int i=0; i<junctions.size(); i++) {
			junctions.get(i).print();
		}
	}
	public void printRoads() {
		for (int i=0; i<roads.size(); i++) {
			roads.get(i).print();
		}
	}
	public void printVehicles() {
		for (int i=0; i<vehicles.size(); i++) {
			vehicles.get(i).print();
		}
	}
}
