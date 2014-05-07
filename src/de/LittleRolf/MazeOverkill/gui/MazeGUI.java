package de.LittleRolf.MazeOverkill.gui;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeRat;

public class MazeGUI {

	private JFrame frame;
	private MazePanel mazePanel;
	private Maze maze;

	/**
	 * Create the application.
	 */
	public MazeGUI(Maze m) {
		maze = m;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mazePanel = new MazePanel(maze);
		mazePanel.setBounds(10, 10, 590, 340);
		frame.add(mazePanel);

		(new SimulationThread()).start();

	}

	private class SimulationThread extends Thread {

		public void run() {
			int counter = 0;
			while (!maze.allRatsOnTarget()
					&& counter < maze.getMaxSimulationStep()) {
				for (MazeRat rat : maze.getRats()) {
					if (!maze.isRatOnTarget(rat))
						rat.performStep();
				}
				mazePanel.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counter++;
			}

			System.out.println("Finished simulation!");
			if (maze.allRatsOnTarget()) {
				System.out.println("Rats finished! It took " + counter
						+ " steps");
			} else {
				System.out.println("Rats failed");
			}
		}

	}

	public void setVisible(boolean b) {
		frame.setVisible(b);

	}

}
