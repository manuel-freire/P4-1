package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import main.model.TrafficSimulator;
import main.model.events.*;
import main.model.simulatedObjects.Vehicle;

public class TestNewVehicleEvent {
	@Test
	public void testExecuteAndBuild() {
		Event e = new NewVehicleEvent();
		try {
			e.builder(new BufferedReader(new FileReader("EventTest")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TrafficSimulator sim = new TrafficSimulator(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add("vehicle");
		sim.addEvent(new NewJunctionEvent("src"));
		sim.addEvent(new NewJunctionEvent("dest"));
		sim.addEvent(new NewRoadEvent("id","src","dest",0,0));
		ArrayList<String> it = new ArrayList<String>();
		it.add("src");
		it.add("dest");
		sim.addEvent(new NewVehicleEvent("id", 0, 0, list));
		try {
		assertEquals(sim.getVehicles().get(0).getActualRoad().getID(),"id");
		}catch(IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testBuilder() {
		fail("Not yet implemented");
	}

}
