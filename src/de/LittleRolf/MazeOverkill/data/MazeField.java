package de.LittleRolf.MazeOverkill.data;

public class MazeField {
	
	public enum FieldType {
		EMPTY, WALL, 
	}
	
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
	}
}
