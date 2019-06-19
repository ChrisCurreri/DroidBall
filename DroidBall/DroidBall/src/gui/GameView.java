package gui;

/**
 * GameView for DroidBall. Responsible for displaying the grid 
 * in a GUI interface.
 * @version 1.0
 * @author Chris Curreri
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import map.GridNode;

public class GameView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel, headerPanel;

	public GameView(GridNode[][] grid) {
	
		setTitle("DroidBall");
		setLayout(new BorderLayout());
		
		add(gridPanel = gridPanel(grid), BorderLayout.CENTER);
		add(headerPanel = headerPanel("DroidBall- AI Platform",SwingConstants.CENTER), BorderLayout.NORTH);
		add(startPanel(), BorderLayout.SOUTH);
		
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
	
	/**
	 * Panel containing the start menu options. The start menu
	 * consists of the start, stop, and quit button respectively.
	 * @return the start panel
	 */
	private JPanel startPanel() {
		JPanel startPanel = new JPanel(new GridLayout(1,3));
		startPanel.add(StartButton());
		startPanel.add(new JButton("Stop"));
		startPanel.add(QuitButton());
		
		return startPanel;
	}
	
	/**
	 * The functionality of the start button.
	 * @return the start button
	 */
	private JButton StartButton() {
		JButton startBtn = new JButton("Start");
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO add start button functionality
				//beginGame();
			}
		});
		return startBtn;
	}
	
	/**
	 * The quit button. This button activates a JOptionPane in case the 
	 * user does not want to quit. If they press yes, the program
	 * terminates.
	 * @return the quit button
	 */
	private JButton QuitButton() {
		JButton quitBtn = new JButton("Quit");
		
		quitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(new Frame(), "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				
				if(i == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		return quitBtn;
	}
}
