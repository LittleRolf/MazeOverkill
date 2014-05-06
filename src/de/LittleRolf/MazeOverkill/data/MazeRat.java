package de.LittleRolf.MazeOverkill.data;

import java.awt.Point;

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
	private boolean canGoForward() {
		return maze.canRatGoForward();
	}

	/**
	 * Makes the rat go forward one step if possible
	 * 
	 * @return Whether the move succeeded or was blocked by a wall
	 */
	private boolean goForward() {
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
	private void turnRight() {
		dir = dir.getRight();
	}

	/**
	 * Makes the rat turn left 90°
	 */
	private void turnLeft() {
		dir = dir.getLeft();
	}

	public abstract void performStep();

}
