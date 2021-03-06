package de.LittleRolf.MazeOverkill;

import java.awt.EventQueue;
import java.io.File;

import javax.imageio.ImageIO;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.gui.MazeGUI;

public class MazeOverkill {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maze m;
					m = new Maze(ImageIO.read(new File("Maze3.jpg")));

					MazeGUI window = new MazeGUI(m);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
