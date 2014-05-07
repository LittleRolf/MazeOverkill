package de.LittleRolf.MazeOverkill.data;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import de.LittleRolf.MazeOverkill.rats.*;

;

public class Maze {

	private int maxSimulationStep = 100000;

	private MazeField[][] maze;

	public Point startPoint, targetPoint;

	private ArrayList<MazeRat> rats = new ArrayList<MazeRat>();

	private int sizeX = 0, sizeY = 0;

	public Maze(String file) {

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

		// rats.add(new BeamRat(startPoint, this));
		rats.add(new SimpleRat((Point) startPoint.clone(), this));

	}

	public MazeField[][] getMazeRawData() {
		return maze;
	}

	public ArrayList<MazeRat> getRats() {
		return rats;
	}

	public boolean canRatGoForward(MazeRat rat) {
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
		try {
			return (maze[rat.position.y + offY][rat.position.x + offX]
					.toString().equals("E"));
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}

	}

	public boolean isRatOnTarget(MazeRat rat) {
		return rat.position.equals(targetPoint);
	}

	public boolean allRatsOnTarget() {
		boolean res = true;
		for (MazeRat rat : rats) {
			if (!isRatOnTarget(rat)) {
				res = false;
			}
		}
		return res;
	}

	public void startSimulation() {
		int counter = 0;
		while (!allRatsOnTarget() && counter < maxSimulationStep) {
			for (MazeRat rat : rats) {
				rat.performStep();
			}

			counter++;
		}

		System.out.println("Finished simulation!");
		if (allRatsOnTarget()) {
			System.out.println("Rats finished! It took " + counter + " steps");
		} else {
			System.out.println("Rats failed");
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
