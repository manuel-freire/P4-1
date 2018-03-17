package tp.control;

import java.util.ArrayList;

import tp.model.TrafficSimulator;
import tp.model.events.Event;

public class Controller {
	private String output, input;
	private TrafficSimulator simulator;
	private EventBuilder evBuilder;
	
	public Controller(String input, String output, int ticks) {
		evBuilder = new EventBuilder(input);
		ArrayList<Event> eventList = evBuilder.Builder();
		simulator = new TrafficSimulator(ticks);
		for(Event e:eventList)
			simulator.addEvent(e);
	}
}
