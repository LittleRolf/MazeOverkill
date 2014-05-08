package de.LittleRolf.MazeOverkill.rats;

import java.awt.Color;
import java.awt.Point;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeRat;

public class ControllableRat extends MazeRat {

	public ControllableRat(Point p, Maze m) {
		super(p, m);
		
	}

	@Override
	public void performStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

}
