package de.LittleRolf.MazeOverkill.rats;

import java.awt.Point;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeRat;

/**
 * A sample implementation of the wall follower method for solving simple mazes
 * @author ole
 *
 */
public class SimpleRat extends MazeRat {

	public SimpleRat(Point p, Maze m) {
		super(p, m);
	}

	@Override
	public void performStep() {
		if(canGoForward()) {
			goForward();
		} else {
			turnRight();
		}

	}

}
