package controllers;
/**
 * Interface used to be defined as a controller for a droid.
 * (either AI or manual)
 * @version 1.0
 * @author Chris Curreri
 */
import map.Droid;

public interface Controller {

	public Droid getDroid();
	
	/**
	 * Represents the action the Droid performs for each frame.
	 */
	public void Act();
}
