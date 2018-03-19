package main.model.events;

import java.io.BufferedReader;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;

public abstract class Event {
	public abstract Event parser(String id);
	public abstract void builder(IniSection sec);
	public abstract void print();
	public abstract void execute(TrafficSimulator sim);
	public abstract int getTime();
}
