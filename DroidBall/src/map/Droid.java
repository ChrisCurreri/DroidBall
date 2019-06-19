package map;

/**
 * An Droid is a GridNode that displays its symbol on the grid.
 * Droids can be controlled by a controller that tells it where to
 * go and it's behavior. A droid's default color is black. Droids
 * should only be black if they are walls (droids that have no 
 * controller). Otherwise some routines will not work properly.
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.Color;

public class Droid extends GridNode{
	
	private final String symbol;
	
		public Droid(String symbol, int x, int y, Color color) {
			super(color,x,y);
			this.symbol = symbol;
		}
		
		public Droid(String symbol, int x, int y) {
			super(Color.BLACK,x,y);
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}
}
