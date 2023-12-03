package main;

public interface Board {

	public int rtnNeighborCnt(int xCoord, int yCoord);
	
    public byte rtnCellNextState(int xCoord, int yCoord);
    
    public void calcNextStep();

}
