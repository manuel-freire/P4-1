package tp;

import java.util.ArrayList;

public class Carretera {	
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
	int longitud, maxVel;
	String ID;
	Cruce fin; // ini;
	
	public void entraVehiculo(Vehiculo vehiculo) {
		listaVehiculos.add(vehiculo);
	}
	public void saleVehiculo(Vehiculo vehiculo) {
		fin.entraVehiculo(vehiculo);
		listaVehiculos.remove(vehiculo);
	}
	public void avanza() {
		int factorReduccion = 0;
		double velocidadBase = Math.min(maxVel, Math.floorDiv(maxVel,Math.max(listaVehiculos.size(), 1)));
		for(int i = listaVehiculos.size()-1; i >= 0 ; i-- ) {
			listaVehiculos.get(i).setVelocidadActual(velocidadBase/factorReduccion);
			listaVehiculos.get(i).avanza();
			factorReduccion += (listaVehiculos.get(i).averiado()) ? 1 : 0;
		}
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
}
