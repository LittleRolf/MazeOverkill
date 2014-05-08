package de.LittleRolf.MazeOverkill.data;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import de.LittleRolf.MazeOverkill.data.MazeRat.Direction;
import de.LittleRolf.MazeOverkill.rats.SimpleRat;

;

public class Maze {

	private int maxSimulationStep = 100000;

	private MazeField[][] maze;

	public Point startPoint;
	public List<Point> targetPoints = new ArrayList<Point>();

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

		maze = new MazeField[img.getHeight()][img.getWidth()];

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				if (c.getGreen() > 100 && c.getBlue() <= 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY,
							false, true);
					targetPoints.add(new Point(x, y));
				} else if (c.getRed() > 100 && c.getBlue() <= 100) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL, true,
							false);
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
		Reflections reflections = new Reflections("de.LittleRolf.MazeOverkill");

		Set<Class<? extends MazeRat>> subTypes = reflections
				.getSubTypesOf(MazeRat.class);
		for (Class<? extends MazeRat> clazz : subTypes) {

			try {
				rats.add(clazz.getConstructor(Point.class, Maze.class)
						.newInstance(startPoint.clone(), this));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

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
		boolean onATarget = false;
		for (Point targetPoint : targetPoints)
			if (rat.position.equals(targetPoint))
				onATarget = true;
		return onATarget;
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

	public HashMap<Direction, MazeField> getRatSurrounding(MazeRat rat) {
		HashMap<Direction, MazeField> surround = new HashMap<Direction, MazeField>();
		try {
			surround.put(Direction.NORTH,
					maze[rat.position.y - 1][rat.position.x]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			surround.put(Direction.EAST,
					maze[rat.position.y][rat.position.x + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			surround.put(Direction.SOUTH,
					maze[rat.position.y + 1][rat.position.x]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			surround.put(Direction.WEST,
					maze[rat.position.y][rat.position.x - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return surround;

	}

	public void startSimulation() {
		int counter = 0;
		while (!allRatsOnTarget() && counter < maxSimulationStep) {
			for (MazeRat rat : rats) {
				rat.performStep();
			}

			counter++;
		}

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
					maze[y][x] = new MazeField(MazeField.FieldType.WALL, true,
							false);
					startPoint = new Point(x, y);
				} else if (line.substring(x, x + 1).equals("T")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY,
							false, true);
					targetPoints.add(new Point(x, y));
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
