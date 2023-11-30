package main;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Main {
	
	private static boolean stopped = true;
	
	public static void main(String[] args) throws InterruptedException {
		SquareBoard sqb = new SquareBoard(60, 60);
		CanvasCreator draw = new CanvasCreator(sqb);
		draw.setVisible(true);
		sqb.setCanvas(draw);
		sqb.drawCells();
		
		while(true) {
			if(draw.getChange() == 1) {
				sqb = draw.getNewSquareBoard(true);
				draw.setChange(0);
			}
			else if (draw.getChange() == 2) {
				sqb = draw.getNewSquareBoard(false);
				draw.setChange(0);
			}
			draw.clearSqureList();
			draw.drawSquare(0, 0, 880, 880, new Color(30, 30, 30));
			
			if (draw.getIsStopped() == false) {
				sqb.calcNextStep();

			}
			sqb.drawCells();
			Thread.sleep(draw.getSimulationSpeed());
		}
	}

}
