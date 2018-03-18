package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import main.model.TrafficSimulator;
import main.model.events.BrokenVehicleEvent;
import main.model.events.Event;
import main.model.simulatedObjects.Vehicle;

public class TestBrokenVehicleEvent {
	@Test
	public void testExecuteAndBuild() {
		Event e = new BrokenVehicleEvent();
		try {
			e.builder(new BufferedReader(new FileReader("EventTest")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TrafficSimulator sim = new TrafficSimulator(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add("vehicle");
		sim.addEvent(e);
		sim.addVehicle(new Vehicle(0, 5, null, "vehicle"));
		try {
			sim.execute(new FileOutputStream("file"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		assertEquals(sim.getActualTick(), 0);
		assertEquals(sim.getVehicles().get(0).getBrokenTime(), 4);
	}
}
