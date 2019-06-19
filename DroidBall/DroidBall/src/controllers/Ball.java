package controllers;

/**
 * This class is the AI controller for the Ball Droid.
 * The ball travels to a random location represented by
 * the target droid ((o))
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

import map.Droid;
import map.Grid2d;
import map.GridNode;

public class Ball implements Controller{

	private Droid ballDroid, targetDroid;
	private Grid2d gameGrid;
	
	private int locX, locY;
	private Random random = new Random();
	private LinkedList<GridNode> route = new LinkedList<GridNode>();
	
	public Ball(Droid ballDroid, Grid2d gameGrid) {
		this.ballDroid = ballDroid;
		this.gameGrid = gameGrid;
	}
	
	@Override
	public Droid getDroid() {
		return ballDroid;
	}
	
	@Override
	public void Act() {
		// If the route is empty then create a Droid with a random location
		// and caculate the route.
		if(route.isEmpty()) {	
			do {
				locX = random.nextInt(gameGrid.width);
				locY = random.nextInt(gameGrid.height);
			}while(gameGrid.getGrid()[locX][locY].color != Color.LIGHT_GRAY);
			
			gameGrid.addDroid(targetDroid = new Droid ("((o))", locX, locY, Color.GREEN));
			route = AIRoutines.CalculateAStar(ballDroid, targetDroid, gameGrid);
		}
		// Remove the first GridNode in the route and make it the new droid location
		GridNode temp = route.removeFirst();
		gameGrid.moveDroid(ballDroid, temp.x, temp.y);
	}
}