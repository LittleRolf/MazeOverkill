package de.LittleRolf.MazeOverkill;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import de.LittleRolf.MazeOverkill.data.Maze;

public class MazeOverkill {

	public static void main(String[] args) {

		Maze m;
		try {
			m = new Maze(ImageIO.read(new File("Maze1.jpg")));
			System.out.println(Arrays.deepToString(m.getMazeRawData()));
			m.setMaxSimulationStep(10000);
			m.startSimulation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
