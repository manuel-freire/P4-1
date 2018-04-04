package main.model.events;

import main.model.TrafficSimulator;
import main.ini.*;
/**
 * Abstract class of an event.
 * @author Javier Navalon
 *
 */
public abstract class Event {
	/**
	 * If the id provided is this type of event returns a new event of this kind.
	 * 
	 * @param id identification
	 * @return a new event
	 */
	public abstract Event parser(String id);
	/**
	 * Builds the event.
	 * 
	 * @param sec IniSection
	 */
	public abstract void builder(IniSection sec);
	/**
	 * Executes the event. Breaks down the list of vehicles.
	 * 
	 * @param sim simulator
	 */
	public abstract void execute(TrafficSimulator sim);
	/**
	 * Returns the time of the event.
	 * 
	 * @return time time
	 */
	public abstract int getTime();
}
