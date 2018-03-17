package tp.control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import tp.model.TrafficSimulator;
import tp.model.events.Event;

public class Controller {
	private String output;
	private TrafficSimulator simulator;
	private EventBuilder evBuilder;
	
	public Controller(String input, String output, int ticks) {
		evBuilder = new EventBuilder(input);
		ArrayList<Event> eventList = evBuilder.Builder();
		simulator = new TrafficSimulator(ticks);
		for(Event e:eventList)
			simulator.addEvent(e);
	}
	
	public void run() {
		try {
			FileOutputStream ostream = new FileOutputStream(output);
			this.simulator.execute(ostream);
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldnt find the output file");
		}
	}
}
