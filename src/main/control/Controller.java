package main.control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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
		this.output=output;
		ArrayList<Event> eventList = evBuilder.Builder();
		simulator = new TrafficSimulator(ticks);
		for (int i=0;i<eventList.size(); i++) {
			simulator.addEvent(eventList.get(i));
		}
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
			e.printStackTrace();
			System.exit(1);
		}catch(NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}
