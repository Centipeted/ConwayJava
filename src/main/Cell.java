package main;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private byte state;
	
	public Cell(int state) {
		this.state = (byte)state;
	}
	
	public byte getCellState() {
		return state;
	}
	
	public void setCellState(byte state) {
		this.state = state;
	}
	
}