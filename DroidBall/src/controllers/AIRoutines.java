package controllers;

/**
 * This class contains routines an AI controller uses when
 * performing an action.
 * @version 1.0
 * @author Chris Curreri
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

import map.Droid;
import map.Grid2d;
import map.GridNode;

public class AIRoutines {
	
	/**
	 * Calculates the AStar algorithm to find the shortest route.
	 * @param start -the start location
	 * @param finish -the end location
	 * @param gameGrid -the grid were using to search for the path
	 * @return the shortest route
	 */
	public static Stack<GridNode> CalculateAStar(Droid start, Droid finish, Grid2d gameGrid) {

		HashMap<GridNode, int[]> costs = new HashMap<GridNode, int[]>();
		HashMap<GridNode, GridNode> fromMap = new HashMap<GridNode, GridNode>();
		HashSet<GridNode> closed = new HashSet<GridNode>();
		PriorityQueue<GridNode> open = new PriorityQueue<GridNode>(new Comparator<GridNode>() {

			public int compare(GridNode nodeA, GridNode nodeB) {
				int fNodeA = costs.get(nodeA)[2];
				
				int fNodeB = costs.get(nodeB)[2];
				
				if(fNodeA != fNodeB) {
					return Double.compare(fNodeA, fNodeB);
				}else {
					return Double.compare(costs.get(nodeA)[1], costs.get(nodeB)[1]);
				}
			}
		});
		
		costs.put(start, costArray(0, getTravelCost(start,finish)));
		open.offer(start);
		
		while(!open.isEmpty()) {
			GridNode current = open.poll();
			
			if(current.equals(finish)) {
				Stack<GridNode> route = new Stack<GridNode>();
				while(current != start) {
					route.push(current);
					current = fromMap.get(current);
				}
				return route;
			}
			closed.add(current);
			
			for(GridNode neighbor : gameGrid.getNeighbors(current)) { 
				if (closed.contains(neighbor))
					continue;
				
				int tentG = costs.get(current)[0] + getTravelCost(current, neighbor);
				if(!open.contains(neighbor) || tentG < costs.get(neighbor)[0]) {
					costs.put(neighbor, costArray(tentG, getTravelCost(current,finish)));
					
					if(open.contains(neighbor))
						open.remove(neighbor);
					
					open.offer(neighbor);
					fromMap.put(neighbor, current);
				}
			}
		}
		return null;
	}
	
	/**
	 * Assistance method for calculateAStar().
	 * Creates an array containing the g, h, and f cost. 
	 * @param gCost -the g cost
	 * @param hCost -the h cost
	 * @return the cost array
	 */
	private static int[] costArray(int gCost, int hCost) {
		int[] costArray = new int[3];
		
		costArray[0] = gCost;							// g cost
		costArray[1] = hCost;							// h cost
		costArray[2] = costArray[1] + costArray[0];		// f cost = g + h
		
		return costArray;
	}

	/**
	 * Assistance method for calculateAStar().
	 * Gets the cost of travel between the two nodes
	 * @param nodeA -the starting node
	 * @param nodeB -the ending node
	 * @return the cost to traverse from nodeA to nodeB
	 */
	public static int getTravelCost(GridNode nodeA, GridNode nodeB) {
		int x = Math.abs(nodeA.x - nodeB.x);
		int y =  Math.abs(nodeA.y - nodeB.y);
		
		if(x > y) {
			return 14*y + 10*(x-y);
		}else {
			return 14*x + 10*(y-x);
		}
	}
	
	
	/**
	 * A simple routine that was used to test the Droid's movement. Works best in a loop.
	 * (Warning: this method causes Droids to destroy walls.)
	 * @param start -the start location
	 * @param finish -the end location
	 * @param gameGrid -the grid were are moving on
	 */
	public static void Wander(Droid start, Droid finish, Grid2d gameGrid) {
		int tempX = start.x;
		int tempY = start.y;
		
		if(tempX < finish.x) {
			tempX += 1;
		}else if(tempX > finish.x) {
			tempX -= 1;
		}
		
		if(tempY < finish.y) {
			tempY += 1;
		}else if(tempY > finish.y) {
			tempY -= 1;
		}
		gameGrid.moveDroid(start, tempX, tempY);
	}
}




