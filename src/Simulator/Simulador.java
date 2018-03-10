package Simulator;

import java.util.List;
import Map.*;
import Vehicles.*;

public class Simulador {
	private List<Cruce> cruces;
	private List<Carretera> carreteras;
	private List<Vehiculo> vehiculos;
	private List<Evento> eventos;
	
	private int limiteTiempo, contadorTiempo, pasosSimulacion;
	
	class Evento{
		class Tiempo{
			
		}
		
		Tiempo tiempo;
	}

	public void ejecuta() {
		limiteTiempo = this.contadorTiempo + pasosSimulacion - 1; 
		while (this.contadorTiempo <= limiteTiempo) { 
			// 1. ejecutar los eventos correspondientes a ese tiempo 
			// 2. invocar al método avanzar de las carreteras 
			// 3. invocar al método avanzar de los cruces 
			// 4. this.contadorTiempo++; 
			// 5. esciribir un informe en OutputStream 
			// en caso de que no sea null 
		}
	}
	public void insertaEvento() {
		
	}
	private void generaInforme() {
		for(Cruce c : cruces)
			c.generaInforme();
		for(Carretera c : carreteras)
			c.generaInforme();
		for(Vehiculo v : vehiculos)
			v.generaInforme();
	}
}
