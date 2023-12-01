package main;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private boolean state;
	
	public Cell(boolean state) {
		this.state = state;
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
}