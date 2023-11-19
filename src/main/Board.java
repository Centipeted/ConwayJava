package main;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public interface Board {

	public int rtnNeighborCnt(int xCoord, int yCoord);
	
    public boolean rtnCellNextState(int xCoord, int yCoord);
    
    public void calcNextStep();

}
