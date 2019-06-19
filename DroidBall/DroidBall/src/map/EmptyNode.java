package map;

/**
 * An EmptyNode is a GridNode that inherits its default color and
 * dispay's its coordinates on the grid.
 * @version 1.0
 * @author Chris Curreri
 */

public class EmptyNode extends GridNode {
	
	public EmptyNode(int x, int y) {
		super(x,y);
	}
	
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
