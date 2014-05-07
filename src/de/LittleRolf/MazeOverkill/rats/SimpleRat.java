package de.LittleRolf.MazeOverkill.rats;

import java.awt.Color;
import java.awt.Point;

import de.LittleRolf.MazeOverkill.data.Maze;
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
		System.out.println(p);
	}

	@Override
	public void performStep() {
		
		turnRight();
		if (!goForward()) { //can go right?
			turnLeft();
			if (!goForward()) { //can go straight?
				turnLeft();
				if(!goForward()) { //what about left?
					turnLeft();
					goForward(); //OK, dead end
				}
			}
		}
		System.out.println("Simple rat: "+position + "   " + dir);

	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

}
