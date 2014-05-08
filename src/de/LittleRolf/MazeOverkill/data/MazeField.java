package de.LittleRolf.MazeOverkill.data;

public class MazeField {
	
	public enum FieldType{
		EMPTY, WALL;
		
	}
	
	private final boolean isTarget, isStartPoint;
	private final FieldType type;
	
	public String toString() {
		switch (type) {
		case EMPTY:
			return "E";
		case WALL:
			return "W";
		default:
			return "0";
		}
	}
	
	
	public MazeField(FieldType t) {
		type = t;
		isStartPoint=false;
		isTarget=false;
	}
	
	public MazeField(FieldType t, boolean start, boolean target) {
		type = t;
		isStartPoint=start;
		isTarget=target;
	}


	public boolean isTarget() {
		return isTarget;
	}


	public boolean isStartPoint() {
		return isStartPoint;
	}
}
