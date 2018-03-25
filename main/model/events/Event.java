package main.model.events;

import main.model.TrafficSimulator;
import main.ini.*;

public abstract class Event {
	public abstract Event parser(String id);
	public abstract void builder(IniSection sec);
	public abstract void execute(TrafficSimulator sim);
	public abstract int getTime();
}
