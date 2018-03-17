package tp.control;

import tp.model.TrafficSimulator;

public class Controller {
	private String output, input;
	private TrafficSimulator simulator;
	private EventBuilder evBuilder;
	
	public Controller(String input, String output, int ticks) {
		evBuilder = new EventBuilder(input);
	}
}
