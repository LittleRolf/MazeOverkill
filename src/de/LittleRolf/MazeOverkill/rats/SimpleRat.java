package de.LittleRolf.MazeOverkill.rats;

import java.awt.Color;
import java.awt.Point;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeField;
import de.LittleRolf.MazeOverkill.data.MazeRat;

/**
 * A sample implementation of the wall follower method for solving simple mazes
 * 
 * @author ole
 * 
 */
public class SimpleRat extends MazeRat {

	public SimpleRat(Point p, Maze m) {
		super(p, m);
	}

	@Override
	public void performStep() {
		if(checkIfTargetNearby())
			return;

		turnRight();
		if (!goForward()) { // can go right?
			turnLeft();
			if (!goForward()) { // can go straight?
				turnLeft();
				if (!goForward()) { // what about left?
					turnLeft();
					goForward(); // OK, dead end
				}
			}
		}

	}

	private boolean checkIfTargetNearby() {
		try {
			if (getRatSurrounding().get(Direction.NORTH).isTarget()) {
				dir = Direction.NORTH;
				goForward();
				return true;
			} else if (getRatSurrounding().get(Direction.EAST).isTarget()) {
				dir = Direction.EAST;
				goForward();
				return true;
			} else if (getRatSurrounding().get(Direction.SOUTH).isTarget()) {
				dir = Direction.SOUTH;
				goForward();
				return true;
			} else if (getRatSurrounding().get(Direction.WEST).isTarget()) {
				dir = Direction.WEST;
				goForward();
				return true;
			}
			
		} catch (NullPointerException e) {
			//Shit happens, on start or target field or maze border not there
		}
		return false;
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

}
