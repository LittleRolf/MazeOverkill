package de.LittleRolf.MazeOverkill.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

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

	private static final int TILE_SIZE = 10;

	/**
	 * Create the panel.
	 */
	public MazePanel(Maze m) {
		setLayout(null);

		maze = m;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(TILE_SIZE * maze.getMazeRawData()[0].length,
				TILE_SIZE * maze.getMazeRawData().length);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);

		for (MazeRat rat : maze.getRats()) {
			g.setColor(rat.getColor());
			g.fillOval(rat.position.x * TILE_SIZE, rat.position.y * TILE_SIZE,
					TILE_SIZE, TILE_SIZE);

			double offX = 0, offY = 0;
			switch (rat.dir) {
			case EAST:
				offX = .5;
				break;
			case NORTH:
				offY = -.5;
				break;
			case SOUTH:
				offY = .5;
				break;
			case WEST:
				offX = -.5;
				break;
			default:

			}
			g.setColor(Color.BLACK);
			g.drawLine(rat.position.x * TILE_SIZE + TILE_SIZE / 2,
					rat.position.y * TILE_SIZE + TILE_SIZE / 2,
					(int) (rat.position.x * TILE_SIZE + TILE_SIZE / 2 + offX
							* TILE_SIZE), (int) (rat.position.y * TILE_SIZE
							+ TILE_SIZE / 2 + offY * TILE_SIZE));

			/*
			 * switch (rat.dir) { case EAST: g.drawLine(rat.position.x *
			 * TILE_SIZE + TILE_SIZE, rat.position.y * TILE_SIZE, rat.position.x
			 * * TILE_SIZE + TILE_SIZE, rat.position.y * TILE_SIZE + TILE_SIZE);
			 * break; case NORTH: g.drawLine(rat.position.x * TILE_SIZE,
			 * rat.position.y TILE_SIZE, rat.position.x * TILE_SIZE + TILE_SIZE,
			 * rat.position.y * TILE_SIZE); break; case SOUTH:
			 * g.drawLine(rat.position.x * TILE_SIZE, rat.position.y TILE_SIZE +
			 * TILE_SIZE, rat.position.x * TILE_SIZE + TILE_SIZE, rat.position.y
			 * * TILE_SIZE + TILE_SIZE); break; case WEST:
			 * g.drawLine(rat.position.x * TILE_SIZE, rat.position.y TILE_SIZE,
			 * rat.position.x * TILE_SIZE, rat.position.y TILE_SIZE +
			 * TILE_SIZE); break; default: break;
			 * 
			 * }
			 */
		}
	}

	private void drawMaze(Graphics g) {
		for (int y = 0; y < maze.getMazeRawData().length; y++) {
			MazeField[] mazeRow = maze.getMazeRawData()[y];
			for (int x = 0; x < mazeRow.length; x++) {
				MazeField mazeField = mazeRow[x];
				g.setColor((mazeField.toString().equals("E") ? Color.WHITE
						: Color.BLACK));
				g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

			}
		}
		for (Point targetPoint : maze.targetPoints) {
			g.setColor(Color.GREEN);
			g.fillRect(targetPoint.x * TILE_SIZE, targetPoint.y * TILE_SIZE,
					TILE_SIZE, TILE_SIZE);
		}
		g.setColor(Color.RED);
		g.fillRect(maze.startPoint.x * 10, maze.startPoint.y * 10, 10, 10);
	}
}
