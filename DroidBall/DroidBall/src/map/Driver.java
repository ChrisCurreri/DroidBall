package map;
import java.awt.Color;

import controllers.*;
import gui.GameView;

/**
 * Driver for DroidBall.
 * @version 1.0
 * @author Chris Curreri
 */

public class Driver {

	public static void main(String[] args) {
		
		// Create the grid
		Grid2d grid2d = new Grid2d(15, 15);

		// Add AI Droids
		Droid targetDroid = new Droid("(^-^)", 7, 8, Color.BLUE);
		Droid hunterDroid = new Droid("(o-o)", 7, 12, Color.RED);
		
		grid2d.addDroid(targetDroid); //Add Target to map
		grid2d.addDroid(hunterDroid); //Add Hunter to map
		
		// Add wall Droids
		grid2d.addDroid(new Droid("[***]",6,10));
		grid2d.addDroid(new Droid("[***]",7,10));
		grid2d.addDroid(new Droid("[***]",8,10));
		
		grid2d.addDroid(new Droid("[***]",5,7));
		grid2d.addDroid(new Droid("[***]",6,7));
		grid2d.addDroid(new Droid("[***]",8,7));
		grid2d.addDroid(new Droid("[***]",9,7));
		
		grid2d.addDroid(new Droid("[***]",6,4));
		grid2d.addDroid(new Droid("[***]",7,4));
		grid2d.addDroid(new Droid("[***]",8,4));
		
		for(int i=2; i<=12; i++) {
			grid2d.addDroid(new Droid("[***]",4, i));
			grid2d.addDroid(new Droid("[***]",10, i));
		}
		
		for(int i=1; i<=13; i++) {
			grid2d.addDroid(new Droid("[***]",1, i));
			grid2d.addDroid(new Droid("[***]",13, i));
		}
		grid2d.addDroid(new Droid("[***]",2,1));
		grid2d.addDroid(new Droid("[***]",2,13));
		
		grid2d.addDroid(new Droid("[***]",12,1));
		grid2d.addDroid(new Droid("[***]",12,13));
		
		// Create AI controllers
		Ball ballCon = new Ball(targetDroid, grid2d);
		Hunter hunterCon = new Hunter(hunterDroid, targetDroid, grid2d);	

		// Create GUI
		GameView gameView = new GameView(grid2d.getGrid());
		
		// Loop until Hunter finds the Target
		while(hunterDroid.x != targetDroid.x || hunterDroid.y != targetDroid.y){
				waitForSeconds(1);
				ballCon.Act();
				hunterCon.Act();
		  		gameView.updateGrid(grid2d.getGrid());
		}
		gameView.updateHeader("Hunter Wins!!!");
	}
	
	/**
	 * Assistance method for delaying routines
	 * @param seconds -amount of seconds the program will wait
	 */
	private static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
