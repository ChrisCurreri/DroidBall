package map;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.*;
import gui.GameView;

/**
 * Driver for DroidBall.
 * @version 1.0
 * @author Chris Curreri
 */

public class DroidSim implements Runnable{
	private volatile Thread thread = null;
	
	private Droid targetDroid, hunterDroid;
	private Controller ballCon, hunterCon;
	private Grid2d grid2d;
	private GameView gameView;
	
	
	public DroidSim() {
		// Create the grid
		grid2d = new Grid2d(15, 15);

		// Add AI Droids
		targetDroid = new Droid("(^-^)", 7, 8, Color.BLUE);
		hunterDroid = new Droid("(o-o)", 7, 12, Color.RED);
		
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
		ballCon = new Ball(targetDroid, grid2d);
		hunterCon = new Hunter(hunterDroid, targetDroid, grid2d);	

		// Create GUI
		gameView = new GameView(grid2d.getGrid());
		
		gameView.start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// If the hunter droid did not catch the target droid, 
				// start the game.
				if(!(gameView.getHeaderText().equals("Hunter Wins!!!"))) {
					start();
				}
				
			}
		});
		
		gameView.stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
	}
	
	@Override
	public void run() {
		// booleans are false by default to prevent  potential null pointer errors
		// updateHeader to default in case simulation is unpaused
		boolean isThreadRunning = false;
		boolean isDroidCaught = false;
		gameView.updateHeader("DroidBall- AI Platform");
		
		// Loop until Hunter finds the Target or the Thread stops
		do{
			waitForSeconds(1);
			ballCon.Act();
			hunterCon.Act();
	  		gameView.updateGrid(grid2d.getGrid());
			
	  		isThreadRunning = (thread == Thread.currentThread());
	  		isDroidCaught = (hunterDroid.x == targetDroid.x && hunterDroid.y == targetDroid.y);
		} while(isThreadRunning && !isDroidCaught);
		
		if(isDroidCaught) {
			gameView.updateHeader("Hunter Wins!!!");
			stop();
		}else if(!isThreadRunning) {
			gameView.updateHeader("Paused");
		}
		
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

	
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();  // start() already calls run()
		}
	}
	
	public void stop() {
		thread = null;
	}
}
