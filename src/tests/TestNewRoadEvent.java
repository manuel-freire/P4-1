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

public class TestNewRoadEvent {
	@Test
	public void testExecuteAndBuild() {
		Event e = new NewRoadEvent();
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
		sim.addEvent(e);
		assertEquals(sim.getRoad("road", "dest").getSourceJunction(), "src");
	}

}
