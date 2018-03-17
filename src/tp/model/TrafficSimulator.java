package tp.model;

import java.util.List;
import java.util.NoSuchElementException;

import tp.model.simulatedObjects.*;
import tp.model.events.Event;

public class TrafficSimulator {
	private List<Junction> junctions;
	private List<Road> roads;
	private List<Vehicle> vehicles;
	private List<Event> events;
	int contadorTiempo, pasosSimulacion;
	
	public void execute() {
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1; 
		while (this.contadorTiempo <= limiteTiempo) { 
			for(Event e: events) {
				if(e.getTime() <= contadorTiempo)
					e.execute(this);
			}
			for(Road r : roads)
				r.advance(this);
			for(Junction j : junctions)
				j.advance(this);
			contadorTiempo++; 
			// 5. esciribir un informe en OutputStream en caso de que no sea null 
			for(Road r : roads)
				r.generateReport(contadorTiempo);
			for(Junction j : junctions)
				j.generateReport(contadorTiempo);
			for(Vehicle v : vehicles)
				v.generateReport(contadorTiempo);
		} 
	}

	public Road getRoad(String road_id, String end_id) {
		Road road = null;
		for(Road r: roads)
			if(r.getID()==road_id&&getJunction(r.getEndJunction()).getID()==end_id)
				road = r;
		if(road == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return road;
	}

	public Junction getJunction(String id) throws NoSuchElementException {
		Junction jun = null;
		for(Junction j : junctions)
			if(j.getID() == id)
				jun = j;
		if(jun == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return jun;
	}

	public void addRoad(Road r) {
		// TODO Auto-generated method stub
		
	}
	public void addVehicle(Vehicle v) {
		// TODO Auto-generated method stub
		
	}

	public void addJunction(Junction j) {
		// TODO Auto-generated method stub
		
	}
}
