package main;

public interface Board {

	public int rtnNeighborCnt(int xCoord, int yCoord);
	
    public boolean rtnCellNextState(int xCoord, int yCoord);
    
    public void calcNextStep();


    
}
