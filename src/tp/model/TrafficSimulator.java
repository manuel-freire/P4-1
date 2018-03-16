package tp.model;

import java.util.List;

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
				r.advance();
			for(Junction j : junctions)
				j.advance();
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

	public void addVehicle(Vehicle v) {
		// TODO Auto-generated method stub
		
	}
}
