package map;

/**
 * A GridNode is an object that has a color and x & y coordinates.
 * GridNode's are what makes up the grid as EmptyNodes and Droids.
 * Its default color is light grey.
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.Color;

public class GridNode {

	public final Color color;
	public int x, y;

	public GridNode(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public GridNode(int x, int y) {
		this.color = Color.LIGHT_GRAY;
		this.x = x;
		this.y = y;
	} 

	protected void setCords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Null";
	}

	
	
}
