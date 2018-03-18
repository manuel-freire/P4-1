package main.control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.events.Event;

public class Controller {
	private String output;
	private TrafficSimulator simulator;
	private EventBuilder evBuilder;
	/**
	 * Class constructor
	 * @param input path to output file
	 * @param output path to input file
	 * @param ticks number of iterations for the simulation
	 */
	public Controller(String input, String output, int ticks) {
		evBuilder = new EventBuilder(input);
		ArrayList<Event> eventList = evBuilder.Builder();
		simulator = new TrafficSimulator(ticks);
		for(Event e:eventList)
			simulator.addEvent(e);
	}
	/**
	 * Checks the path to the output files and starts the simulation.
	 */
	public void run() {
		try {
			FileOutputStream ostream = new FileOutputStream(output);
			this.simulator.execute(ostream);
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldnt find the output file.");
		}
	}
}
