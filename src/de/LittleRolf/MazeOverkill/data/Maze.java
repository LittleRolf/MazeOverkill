package de.LittleRolf.MazeOverkill.data;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import de.LittleRolf.MazeOverkill.rats.SimpleRat;
import sun.misc.PerformanceLogger;

public class Maze {

	private int maxSimulationStep = 100000;

	private MazeField[][] maze;

	private Point startPoint, targetPoint;

	private MazeRat rat;

	public Maze(int size) {
		maze = new MazeField[size][size];
		Arrays.fill(maze, new MazeField(MazeField.FieldType.EMPTY));

	}

	public Maze(int sizeX, int sizeY) {
		maze = new MazeField[sizeY][sizeX];
		Arrays.fill(maze, new MazeField(MazeField.FieldType.EMPTY));
	}

	public Maze(String file) {

		int sizeX = 0, sizeY = 0;
		ArrayList<String> temp = new ArrayList<String>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				temp.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sizeY = temp.size();
		sizeX = temp.get(0).length();
		maze = new MazeField[sizeY][sizeX];
		parseInputToMaze(temp);
		initRat();

	}

	public Maze(BufferedImage img) {
		System.out.println("creating maze with size " + img.getHeight() + " x "
				+ img.getWidth());
		maze = new MazeField[img.getHeight()][img.getWidth()];

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				if (c.getGreen() > 100 && c.getBlue() <= 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
					targetPoint = new Point(x, y);
				} else if (c.getRed() > 100 && c.getBlue() <= 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL);
					startPoint = new Point(x, y);
				} else if (c.getBlue() >= 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
				} else if (c.getBlue() < 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL);
				}
			}
		}

		initRat();
	}

	private void initRat() {
		System.out.println(targetPoint);
		System.out.println(startPoint);

		rat = new SimpleRat(startPoint, this);

	}

	public MazeField[][] getMazeRawData() {
		return maze;
	}

	public boolean canRatGoForward() {
		int offX = 0, offY = 0;
		switch (rat.dir) {
		case EAST:
			offX = 1;
			break;
		case NORTH:
			offY = -1;
			break;
		case SOUTH:
			offY = 1;
			break;
		case WEST:
			offX = -1;
			break;
		default:
			return false;

		}
		return (maze[rat.position.y + offY][rat.position.x + offX].toString()
				.equals("E"));

	}

	public boolean isRatOnTarget() {
		return rat.position.equals(targetPoint);
	}

	public void startSimulation() {
		int counter = 0;
		while (!isRatOnTarget() && counter < maxSimulationStep) {
			rat.performStep();
			counter++;
		}

		System.out.println("Finished simulation!");
		if (isRatOnTarget()) {
			System.out.println("Rat finished");
		} else {
			System.out.println("Rat failed");
		}
	}

	private void parseInputToMaze(ArrayList<String> temp) {
		for (int y = 0; y < temp.size(); y++) {
			String line = temp.get(y);
			for (int x = 0; x < line.length(); x++) {
				if (line.substring(x, x + 1).equals("E")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
				} else if (line.substring(x, x + 1).equals("W")) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL);
				} else if (line.substring(x, x + 1).equals("S")) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL);
					startPoint = new Point(x, y);
				} else if (line.substring(x, x + 1).equals("T")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
					targetPoint = new Point(x, y);
				}
			}
		}

	}

	public int getMaxSimulationStep() {
		return maxSimulationStep;
	}

	public void setMaxSimulationStep(int maxSimulationStep) {
		this.maxSimulationStep = maxSimulationStep;
	}

}
