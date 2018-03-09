package tp;

import java.util.ArrayList;	
	
public class Cruce {
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
	boolean green;
	
	public boolean isGreen() {
		return green;
	}
	public void setGreen(boolean green) {
		this.green = green;
	}
	public void entraVehiculo(Vehiculo vehiculo) {
		listaVehiculos.add(vehiculo);
	}
	public void saleVehiculo(Vehiculo vehiculo) {
		if(!listaVehiculos.isEmpty()) {
			Vehiculo v = listaVehiculos.get(0);
			v.getSigCruce().entraVehiculo(v);
		}
	}
}
