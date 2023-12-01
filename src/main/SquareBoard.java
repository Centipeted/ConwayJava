package main;

import java.awt.Color;
import java.io.Serializable;

public class SquareBoard implements Board, Serializable {
	private int sideSize;
	public Cell board[][];
	public Cell newBoard[][];
	private Rules rules = new Rules(3, 3, 2, 3);
	private CanvasCreator canvas;

	public SquareBoard(int sideSize) {
		this.sideSize = sideSize;
		this.sideSize = sideSize;
		board = new Cell[sideSize][sideSize];
	    newBoard = new Cell[sideSize][sideSize];
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				this.board[i][j] = new Cell(false);
				this.newBoard[i][j] = new Cell(false);
			}
		}
		/*board[10][10].setState(true);
		board[11][10].setState(true);
		board[11][9].setState(true);
		board[12][9].setState(true);
		board[10][8].setState(true);
		
		newBoard[10][10].setState(true);
		newBoard[11][10].setState(true);
		newBoard[11][9].setState(true);
		newBoard[12][9].setState(true);
		newBoard[10][8].setState(true);*/
	}
	
	public int getSideSize() {
		return sideSize;
	}
	
	@Override
	public int rtnNeighborCnt(int xCoord, int yCoord) {
		int cnt = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
	            int tmpX = xCoord + i;
	            int tmpY = yCoord + j;

	            if (tmpX < 0){
	                tmpX = sideSize - 1;
	            }
	            if (tmpY < 0){
	                tmpY = sideSize - 1;
	            }
	            if (tmpX >= sideSize){
	                tmpX = 0;
	            }
	            if (tmpY >= sideSize){
	                tmpY = 0;
	            }
				if (board[tmpX][tmpY].getState() == true) {
					cnt++;
				}
			}
		}
		if (board[xCoord][yCoord].getState() == true) {
			cnt--;
		}
		return cnt;
	}

	@Override
	public boolean rtnCellNextState(int xCoord, int yCoord) {
		int neighborCnt = rtnNeighborCnt(xCoord, yCoord);
		if (board[xCoord][yCoord].getState() == false) {
			if (neighborCnt >= rules.getRebornMin() && neighborCnt <= rules.getRebornMax()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (neighborCnt >= rules.getDieUnderpop() && neighborCnt <= rules.getDieOverpop()) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public void calcNextStep() {
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				newBoard[i][j].setState(rtnCellNextState(i, j));
			}
		}
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				board[i][j].setState(newBoard[i][j].getState());
			}
		}
	}
	
	public void drawCells(){
		
	    for (int i = 0; i < sideSize; i++){
	        for (int j = 0; j < sideSize; j++){
	            if (newBoard[i][j].getState() == true){
	                canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
	                		(840 / sideSize), (840 / sideSize), Color.BLACK);
	            }
	            else{
	            	canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
	            			 (840 / sideSize), (840 / sideSize), Color.WHITE);
	            } 
	        }
	    }
	    canvas.repaint();
	}
	
	public void setSquareState(int xCoord, int yCoord, boolean state) {
        if (xCoord >= 0 && xCoord < sideSize && yCoord >= 0 && yCoord < sideSize) {
            board[xCoord][yCoord].setState(state);
            newBoard[xCoord][yCoord].setState(state);
            canvas.repaint();
        }
    }
	
	public boolean getState(int xCoord, int yCoord) {
		return board[xCoord][yCoord].getState();
	}
	
	public Rules getRules() {
		return rules;
	}
	
	public void setCanvas(CanvasCreator canvas) {
		this.canvas = canvas;
	}
}