package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import main.model.TrafficSimulator;
import main.model.events.*;

public class TestNewJunctionEvent {
	@Test
	public void testExecuteAndBuild() {
		/*Event e = new NewJunctionEvent();
		try {
			e.builder(new BufferedReader(new FileReader("EventTest")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TrafficSimulator sim = new TrafficSimulator(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add("vehicle");
		sim.addEvent(new NewJunctionEvent("junction"));
		try {
			assertEquals(sim.getJunctions().get(0).getID(), "junction");
		} catch (IndexOutOfBoundsException ex ) {
			ex.printStackTrace();
		}*/
	}

}
