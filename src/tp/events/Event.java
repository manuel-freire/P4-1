package tp.control.events;

import java.io.BufferedReader;

import tp.model.TrafficSimulator;

public abstract class Event {
	protected String eventId;
	public abstract Event parser(String id);
	public abstract void builder(BufferedReader reader);
	public abstract void print();
	public abstract void execute(TrafficSimulator sim);
}
