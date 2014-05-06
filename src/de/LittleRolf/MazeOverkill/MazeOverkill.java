package de.LittleRolf.MazeOverkill;

import java.util.Arrays;

import de.LittleRolf.MazeOverkill.data.Maze;

public class MazeOverkill {

	public static void main(String[] args) {
		Maze m = new Maze("maze.txt");
		System.out.println(Arrays.deepToString(m.getMazeRawData()));
		m.setMaxSimulationStep(10);
		m.startSimulation();
	}

}
