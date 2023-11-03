package main;

import java.awt.Color;
import java.awt.color.ColorSpace;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		CanvasCreator draw = new CanvasCreator();
		draw.setVisible(true);
		SquareBoard sqb = new SquareBoard(60, 60, draw);
		sqb.drawCells();
		
		//draw.drawSquare(100, 100, 14, 14, Color.RED);
		while(true) {
			draw.clearSqureList();
			draw.drawSquare(0, 0, 1290, 880, new Color(30, 30, 30));
			sqb.calcNextStep();
			sqb.drawCells();
			Thread.sleep(100);
		}
		
	}

}
