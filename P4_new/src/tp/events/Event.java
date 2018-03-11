package tp.events;

import java.io.BufferedReader;

public abstract class Event {
	protected String eventId;
	abstract void execute();
	public abstract Event parser(String id);
	public abstract void builder(BufferedReader reader);
}
