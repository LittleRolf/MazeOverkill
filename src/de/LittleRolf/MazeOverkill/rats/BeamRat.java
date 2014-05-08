package de.LittleRolf.MazeOverkill.rats;

import java.awt.Color;
import java.awt.Point;

import de.LittleRolf.MazeOverkill.data.Maze;
import de.LittleRolf.MazeOverkill.data.MazeRat;

public class BeamRat extends MazeRat {

	private int maxX = 0, maxY = 0; 
	
	public BeamRat(Point p, Maze m) {
		super(p, m);
		maxY = m.getMazeRawData().length;
		maxX = m.getMazeRawData()[1].length;
	}

	@Override
	public void performStep() {
		
		position = new Point((int)(Math.random()*maxX),(int) (Math.random()*maxY));

	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

}
