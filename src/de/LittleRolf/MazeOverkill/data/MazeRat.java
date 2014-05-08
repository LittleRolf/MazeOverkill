package de.LittleRolf.MazeOverkill.data;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

public abstract class MazeRat {

	public enum Direction {
		NORTH, EAST, SOUTH, WEST;

		public Direction getRight() {
			return values()[(ordinal() + 1) % values().length];
		}

		public Direction getLeft() {
			return values()[(this.ordinal() + values().length - 1)
					% values().length];
		}
	}

	public Point position;
	public Direction dir = Direction.NORTH;

	private Maze maze;

	public MazeRat(Point p, Maze m) {
		position = p;
		maze = m;
	}
	
	/**
	 * Checks whether the rat can move forward
	 * @return guess what...
	 */
	public boolean canGoForward() {
		return maze.canRatGoForward(this);
	}
	
	public HashMap<Direction,MazeField> getRatSurrounding() {
		return maze.getRatSurrounding(this);
		
	}

	/**
	 * Makes the rat go forward one step if possible
	 * 
	 * @return Whether the move succeeded or was blocked by a wall
	 */
	public boolean goForward() {
		if (!canGoForward())
			return false;

		switch (dir) {
		case EAST:
			position.x += 1;
			break;
		case NORTH:
			position.y -= 1;
			break;
		case SOUTH:
			position.y += 1;
			break;
		case WEST:
			position.x -= 1;
			break;
		default:
			return false;

		}
		return true;

	}

	/**
	 * Makes the rat turn right 90°
	 */
	public void turnRight() {
		dir = dir.getRight();
	}

	/**
	 * Makes the rat turn left 90°
	 */
	public void turnLeft() {
		dir = dir.getLeft();
	}

	public abstract void performStep();
	
	public abstract Color getColor();

}
