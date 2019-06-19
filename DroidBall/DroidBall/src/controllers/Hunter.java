package controllers;

/**
 * This class is the AI controller for the Hunter Droid.
 * The hunter job is to catch the ball.
 * @version 1.0
 * @author Chris Curreri
 */

import java.util.LinkedList;

import map.Droid;
import map.Grid2d;
import map.GridNode;

public class Hunter implements Controller{

	private Droid start, target;
	private Grid2d gameGrid;
	
	public Hunter(Droid start, Droid target,  Grid2d gameGrid) {
		this.start = start;
		this.target = target;
		this.gameGrid = gameGrid;
	}
	
	@Override
	public Droid getDroid() {
		return start;
	}
	
	@Override
	public void Act() {
		// For every action the hunter calculates the path and gets the first move it should make.
		LinkedList<GridNode> route = AIRoutines.CalculateAStar(start, target, gameGrid);
		gameGrid.moveDroid(start, route.getFirst().x, route.getFirst().y);
		System.out.println("Hunter Coords: (" + start.x + "," +  start.y + ")");
		System.out.println(route);
	}
}
