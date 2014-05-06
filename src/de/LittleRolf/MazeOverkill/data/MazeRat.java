package de.LittleRolf.MazeOverkill.data;

import java.awt.Point;

public class MazeRat {

	public enum Direction {
		NORTH,EAST,SOUTH,WEST
	}
	public Point position;
	public Direction dir = Direction.NORTH;
	
	private Maze maze;
	
	public MazeRat(Point p, Maze m) {
		position = p;
		maze = m;
	}
	
	public void performStep() {
		System.out.println(maze.canGoForward());
	}
	
}
