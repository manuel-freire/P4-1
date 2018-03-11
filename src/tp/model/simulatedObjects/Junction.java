package tp.model.simulatedObjects;

import java.util.List;
import tp.model.TrafficSimulator;

public class Junction {
	List<Vehicle> vehicles;
	
	public void enterVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	public void advance() {
		if(!vehicles.isEmpty()) {
			vehicles.get(0).advanceToNextRoad();
			vehicles.remove(0);
		}
	}

}
