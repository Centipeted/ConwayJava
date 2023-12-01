package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {
	
	private static boolean stopped = true;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		SquareBoard sqb = new SquareBoard(60);
		CanvasCreator draw = new CanvasCreator(sqb);
		draw.setSqb(sqb);
		draw.setVisible(true);
		sqb.setCanvas(draw);
		sqb.drawCells();
		int previousSize = draw.getSqbCurrentSizeIndex();
		
		while(true) {
			if(draw.getChange() == 1) {
				System.out.println("Change == 1 detected");
				if(previousSize <= draw.getSqbCurrentSizeIndex()) {
					sqb = draw.getResizedSquareBoard(true);
				}
				else {
					sqb = draw.getResizedSquareBoard(false);
				}
				System.out.println("Change 1: Setting sqbCurrentSizeIndex from " + previousSize + " to " + draw.getSqbCurrentSizeIndex());
				previousSize = draw.getSqbCurrentSizeIndex();
				draw.setChange(0);
			}
			else if(draw.getChange() == 2) {
				System.out.println("Change == 2 detected");
				sqb = draw.loadGame();
				draw.setSqb(sqb);
				for (int i = 0; i < draw.sqbArray.length; i++) {
		            if (draw.sqbArray[i] == sqb.getSideSize()) {
		                draw.setSqbCurrentSizeIndex(i);
		            }
		        }
				previousSize = draw.getSqbCurrentSizeIndex();
				draw.refreshAllTextfields();
				
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
