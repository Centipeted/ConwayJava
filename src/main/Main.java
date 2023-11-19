package main;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Main {
	
	private static boolean stopped = false;
	
	public static void main(String[] args) throws InterruptedException {
		CanvasCreator draw = new CanvasCreator();
		draw.setVisible(true);
		SquareBoard sqb = new SquareBoard(60, 60, draw);
		draw.setSquareBoard(sqb);
		sqb.drawCells();
		
		//draw.drawSquare(100, 100, 14, 14, Color.RED);
		while(true) {
			draw.clearSqureList();
			draw.drawSquare(0, 0, 880, 880, new Color(30, 30, 30));
			//System.out.println("draw.getClickedX(): " + draw.getClickedX() + ", draw.getClickedY(): " + draw.getClickedY());
			if (draw.getClickedX() == 60 && draw.getClickedY() == 0) {
				if (stopped == false) {
					stopped = true;
				}
				else {
					stopped = false;
				}
				draw.setClickedX(61);
				draw.setClickedY(1);
			}
			if (stopped == false) {
					sqb.calcNextStep();
				}
			sqb.drawCells();
			Thread.sleep(100);
		}
		
	}

}
