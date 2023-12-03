package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		SquareBoard sqb = new SquareBoard(60);
		CanvasCreator draw = new CanvasCreator(sqb);
		draw.setSqb(sqb);
		draw.setVisible(true);
		sqb.setCanvas(draw);
		sqb.drawCells();
		int previousSize = draw.getSqbCurrentSizeIndex();
		
		while(true) {
			if(draw.getChangeId() == 1) {
				if(previousSize <= draw.getSqbCurrentSizeIndex()) {
					sqb = draw.getResizedSquareBoard(true);
				}
				else {
					sqb = draw.getResizedSquareBoard(false);
				}
				previousSize = draw.getSqbCurrentSizeIndex();
				draw.setChangeId(0);
			}
			else if(draw.getChangeId() == 2) {
				sqb = draw.loadGame();
				draw.setSqb(sqb);
				for (int i = 0; i < draw.sqbArray.length; i++) {
		            if (draw.sqbArray[i] == sqb.getSideSize()) {
		                draw.setSqbCurrentSizeIndex(i);
		            }
		        }
				previousSize = draw.getSqbCurrentSizeIndex();
				draw.refreshAllTextfields();
				
				draw.setChangeId(0);
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
