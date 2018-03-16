package tp.model.events;

import java.io.BufferedReader;

import tp.model.TrafficSimulator;

public abstract class Event {
	protected String eventId;
	private int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public abstract Event parser(String id);
	public abstract void builder(BufferedReader reader);
	public abstract void print();
	public abstract void execute(TrafficSimulator sim);
}
