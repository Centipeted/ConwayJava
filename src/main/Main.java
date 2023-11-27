package main;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Main {
	
	private static boolean stopped = true;
	
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
			if (draw.getIsStopped() == false) {
				sqb.calcNextStep();

			}
			sqb.drawCells();
			Thread.sleep(draw.getSimulationSpeed());
		}
	}

}
