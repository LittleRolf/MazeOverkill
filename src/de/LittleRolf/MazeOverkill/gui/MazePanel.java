package de.LittleRolf.MazeOverkill.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeField;
import de.LittleRolf.MazeOverkill.data.MazeRat;

public class MazePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636281095934554811L;

	private Maze maze;

	/**
	 * Create the panel.
	 */
	public MazePanel(Maze m) {
		setLayout(null);
		
		maze = m;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
		
		for (MazeRat rat : maze.getRats()) {
			g.setColor(rat.getColor());
			g.fillOval(rat.position.x*10+1, rat.position.y*10+1, 7, 7);
			
			switch (rat.dir) {
			case EAST:
				g.drawLine(rat.position.x*10+10, rat.position.y*10, rat.position.x*10+10, rat.position.y*10+10);
				break;
			case NORTH:
				g.drawLine(rat.position.x*10, rat.position.y*10, rat.position.x*10+10, rat.position.y*10);
				break;
			case SOUTH:
				g.drawLine(rat.position.x*10, rat.position.y*10+10, rat.position.x*10+10, rat.position.y*10+10);
				break;
			case WEST:
				g.drawLine(rat.position.x*10, rat.position.y*10, rat.position.x*10, rat.position.y*10+10);
				break;
			default:
				break;

			}
		}
	}

	private void drawMaze(Graphics g) {
		for (int y = 0; y < maze.getMazeRawData().length; y++) {
			MazeField[] mazeRow = maze.getMazeRawData()[y];
			for (int x = 0; x < mazeRow.length; x++) {
				MazeField mazeField = mazeRow[x];
				g.setColor((mazeField.toString().equals("E") ? Color.WHITE
						: Color.BLACK));
				g.fillRect(x*10, y*10, 10, 10);

			}
		}
		g.setColor(Color.GREEN);
		g.fillRect(maze.targetPoint.x*10, maze.targetPoint.y*10,10,10);
		g.setColor(Color.RED);
		g.fillRect(maze.startPoint.x*10, maze.startPoint.y*10,10,10);
	}
}
