package map;

/**
 * This class is responsible for the grid. It has routines that return
 * aspects of the grid such as the grid itself and the surrounding 
 * nodes of a GridNode. It also has routines that add droids and moves
 * droids to a specific location.
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Grid2d {
	
	public int width, height;
	
	private final GridNode[][] grid;
	
	public Grid2d(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new GridNode[width][height];
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[i].length; j++) {
				grid[i][j] = new EmptyNode(i, j);
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Grid Routines
	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the 2D grid.
	 * @return the grid
	 */
	public GridNode[][] getGrid(){
		return grid;
	}
	
	/**
	 * Gets the surrounding node of a specific node. 
	 * @param gridNode -the node whose neighbors 
	 * are being returned
	 * @return the node's neighbors (in the form of a HashSet)
	 */
	public Set<GridNode> getNeighbors(GridNode gridNode){
		Set<GridNode> neighbors = new HashSet<GridNode>();
		int x = gridNode.x;
		int y = gridNode.y;
		
		for (int i=x-1; i<=x+1; i++) {
			for(int j=y-1; j<=y+1; j++) {
				if((i==x && j==y) || i<0 || j<0 || j>=grid.length || i>= grid[j].length)
					continue;
				
				if(grid[i][j].color == Color.BLACK) //if the grid node is a wall then continue
					continue;
					
				neighbors.add(grid[i][j]);	
			}
		}
		return neighbors;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// Droid Actions
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds a droid to the grid.
	 * @param droid -the droid added
	 */
	public void addDroid(Droid droid) {
		try {
			grid[droid.x][droid.y] = droid;
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Droid " + droid.toString() +  
					" coords are out of bounds: (" + droid.x + "," + droid.y + ")");
		}
	}
	
	/**
	 * Moves a droid to another location on the grid.
	 * @param droid -the droid being moved
	 * @param x -the new x coordinate
	 * @param y -the new y coordinate
	 */
	public void moveDroid(Droid droid, int x, int y) {
		grid[droid.x][droid.y] = new EmptyNode(droid.x, droid.y);
		droid.setCords(x, y);
		addDroid(droid);
	}
	
	@Override
	public String toString() {
		String output = "";
		for(int i=0; i<grid[0].length; i++){
			StringJoiner sj = new StringJoiner(" | ");
	        for(int j=0; j<grid.length; j++){
	        	sj.add(grid[j][grid[0].length - i -1].toString());
	        }
	        output += " |" + sj.toString() + " | \n";
	    }
		return output;
	}
}
