package de.LittleRolf.MazeOverkill.data;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
	
	private MazeField[][] maze;
	
	private Point startPoint, targetPoint;
	
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
	        while((line = br.readLine()) != null) {
	        	temp.add(line);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		sizeY = temp.size();
		sizeX = temp.get(0).length();
		maze = new MazeField[sizeY][sizeX];
		parseInputToMaze(temp);
        
	}

	public MazeField[][] getMazeRawData() {
		return maze;
	}
	
	private void parseInputToMaze(ArrayList<String> temp) {
		for (int y = 0; y< temp.size();y++) {
			String line = temp.get(y);
			for (int x = 0; x < line.length(); x++) {
				if(line.substring(x, x+1).equals("E")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
				} else if (line.substring(x, x+1).equals("W")) {
					maze[y][x] = new MazeField(MazeField.FieldType.WALL);
				} else if (line.substring(x, x+1).equals("S")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
					startPoint = new Point(x,y);
				} else if (line.substring(x, x+1).equals("T")) {
					maze[y][x] = new MazeField(MazeField.FieldType.EMPTY);
					targetPoint = new Point(x,y);
				}
			}
		}
		
	}

	
	
}
