package Simulator;

import java.util.ArrayList;

public abstract class Vehiculo {
	String ID;
	double velMaxima, velActual, localizacion, km, tiempoAveria, kilometraje;
	boolean haLlegado;
	ArrayList<Cruce> itinerario = new ArrayList<>();
	Carretera carretera;
	
	public boolean averiado() {
		return tiempoAveria > 0;
	}
	public void setTiempoAveria(double n) {
		tiempoAveria = n;
	}
	public void setVelocidadActual(double n) {
		velActual = n;
	}
	public boolean avanza() {
		if(averiado()) {
			tiempoAveria--;
		}else {
			if(carretera.getLongitud()<localizacion + velActual) {
				kilometraje += carretera.getLongitud()-localizacion;
				localizacion = carretera.getLongitud();
			}else {
				kilometraje += velActual;
				localizacion += velActual;
			}
			
		}
		return carretera.getLongitud() == localizacion;
	}
	public void moverASiguienteCarretera() {
		
	}
	public String generaInforme() {
		String informe = "[vehicle_report]\n" + 
				"id = " + this.ID + "\n" + 
				"time = " + "\n" + 
				"speed = " + ((tiempoAveria>0 || haLlegado) ? 0 : velActual) + "\n" + 
				"kilometrage = "+ kilometraje +"\n" + 
				"faulty =" + tiempoAveria +"\n" + 
				"location = ("+carretera.getID()+","+localizacion+")";
		return informe;
	}
	public Cruce getSigCruce() {
		return this.itinerario.get(0);
	}
}
