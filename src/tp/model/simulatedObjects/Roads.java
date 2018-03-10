package tp.model.simulatedObjects;

import java.util.ArrayList;
import tp.model.simulatedObjects.Vehicles;

public class Roads {
	ArrayList<Vehicles> listvehicles = new ArrayList<>();
	int length, maxVel;
	String ID;
	Junctions fin; // ini;
	
	public void entravehicles(Vehicles vehicles) {
		listvehicles.add(vehicles);
	}
	public void salevehicles(Vehicles vehicles) {
		fin.enterVehicle(vehicles);
		listvehicles.remove(vehicles);
	}
	public void avanza() {
		int factorReduccion = 0;
		double velocidadBase = Math.min(maxVel, Math.floorDiv(maxVel,Math.max(listvehicles.size(), 1)));
		for(int i = listvehicles.size()-1; i >= 0 ; i-- ) {
			listvehicles.get(i).setActualVelocity(velocidadBase/factorReduccion);
			listvehicles.get(i).advance();
			factorReduccion += (listvehicles.get(i).isOutOfOrder()) ? 1 : 0;
		}
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void generateReport() {
		// TODO Auto-generated method stub
		
	}
}
