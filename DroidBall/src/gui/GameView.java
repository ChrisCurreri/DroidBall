package gui;

/**
 * GameView for DroidBall. Responsible for displaying the grid 
 * in a GUI interface.
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import map.GridNode;

public class GameView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel, headerPanel, buttonPanel;
	
	public JButton start = new JButton("Start");
	public JButton stop = new JButton("Stop");

	public GameView(GridNode[][] grid) {
	
		setTitle("DroidBall");
		setLayout(new BorderLayout());
		
		add(gridPanel = gridPanel(grid), BorderLayout.CENTER);
		add(headerPanel = headerPanel("DroidBall- AI Platform", SwingConstants.CENTER), BorderLayout.NORTH);
		add(buttonPanel = buttonPanel(new JButton[]{start, stop}), BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,1000,800);
		setVisible(true);
	}

	////////////////////////////////////////////////////////////////////////
	// GUI Actions
	////////////////////////////////////////////////////////////////////////
	
	public void updateGrid(GridNode[][] grid) {
		remove(gridPanel);
		add(gridPanel = gridPanel(grid), BorderLayout.CENTER);
		revalidate();
	}
	
	public void updateHeader(String text) {
		((JLabel) headerPanel.getComponent(0)).setText(text);
	}
	
	public String getHeaderText() {
		return ((JLabel) headerPanel.getComponent(0)).getText();
	}
	
	
	////////////////////////////////////////////////////////////////////////
	// GUI Components
	////////////////////////////////////////////////////////////////////////
	
	/**
	 * Creates the panel for the 2D grid.
	 * @param grid -the grid being displayed
	 * @return panel that contains the grid display
	 */
	private JPanel gridPanel(GridNode[][] grid) {
		
		JPanel gridPanel = new JPanel(new GridLayout(grid[0].length, grid.length));
		
		for(int i=0; i<grid[0].length; i++) {
			for(int j=0; j<grid.length; j++) {
				gridPanel.add(nodeLabel(grid[j][grid[0].length - i - 1]));
			}
		}
		return gridPanel;
	}
	
	/**
	 * Creates the JLabel for individual GridNodes
	 * @param node -the node that will be displayed
	 * @return panel displaying the node
	 */
	private JLabel nodeLabel(GridNode node) {
		JLabel nodePanel = new JLabel(node.toString(), SwingConstants.CENTER);
		nodePanel.setForeground(node.color);
		nodePanel.setBackground(Color.WHITE);
		nodePanel.setOpaque(true);
	
		return nodePanel;
	}
	
	/**
	 * Creates the panel responsible for the header display.
	 * @param text -the header's text
	 * @param alignment -the header's alignment
	 * @return panel containing the header
	 */
	private JPanel headerPanel(String text, int alignment) {
		JPanel headerPanel = new JPanel();
		headerPanel.add(new JLabel(text,alignment));
		return headerPanel;
	}
	
	private JPanel buttonPanel(JButton buttons[]) {
		JPanel buttonPanel = new JPanel(new GridLayout());
		
		for(int i=0; i<buttons.length; i++) {
			buttonPanel.add(buttons[i]);
		}
		return buttonPanel;
	}
}