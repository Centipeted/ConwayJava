package main;

import java.awt.Color;

public class SquareBoard implements Board {
	private int rowCnt;
	private int colCnt;
	private Cell board[][];
	private Cell newBoard[][];
	private Rules rules = new Rules(3, 3, 2, 3);
	private CanvasCreator canvas;

	public SquareBoard(int rowCnt, int colCnt, CanvasCreator canvas) {
		this.rowCnt = rowCnt;
		this.colCnt = colCnt;
		this.canvas = canvas;
		board = new Cell[rowCnt][colCnt];
	    newBoard = new Cell[rowCnt][colCnt];
		for (int i = 0; i < this.rowCnt; i++) {
			for (int j = 0; j < this.colCnt; j++) {
				this.board[i][j] = new Cell(false);
				this.newBoard[i][j] = new Cell(false);
			}
		}
		board[10][10].setState(true);
		board[11][10].setState(true);
		board[11][9].setState(true);
		board[12][9].setState(true);
		board[10][8].setState(true);
		
		//System.out.println(newBoard[0][0].getState());
	}	
	
	@Override
	public int rtnNeighborCnt(int xCoord, int yCoord) {
		int cnt = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
	            int tmpX = xCoord + i;
	            int tmpY = yCoord + j;

	            if (tmpX < 0){
	                tmpX = colCnt - 1;
	            }
	            if (tmpY < 0){
	                tmpY = rowCnt - 1;
	            }
	            if (tmpX >= colCnt){
	                tmpX = 0;
	            }
	            if (tmpY >= rowCnt){
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
		for (int i = 0; i < this.rowCnt; i++) {
			for (int j = 0; j < this.colCnt; j++) {
				newBoard[i][j].setState(rtnCellNextState(i, j));
			}
		}
		for (int i = 0; i < this.rowCnt; i++) {
			for (int j = 0; j < this.colCnt; j++) {
				board[i][j].setState(newBoard[i][j].getState());
			}
		}
	}
	
	void drawCells(){
		
		//System.out.println("drawcells start");
	    for (int i = 0; i < rowCnt; i++){
	        for (int j = 0; j < colCnt; j++){
	            if (newBoard[i][j].getState() == true){
	            	//System.out.println(i + ". " + j + ". is true");
	            	//System.out.println("square values are: " + i * (840 / colCnt) + ", " + j * (840 / colCnt) + ", "
	            	//+ (840 / rowCnt) + ", " + (840 / rowCnt + ", " + Color.BLACK));
	                canvas.drawSquare( i * (840 / colCnt) + 20, j * (840 / colCnt) + 20,
	                		(840 / rowCnt), (840 / rowCnt), Color.BLACK);
	            }
	            else{
	            	//System.out.println(i + ". " + j + ". is false");
	            	//System.out.println("square values are: " + i * (840 / colCnt) + ", " + j * (840 / colCnt) + ", "
	            	//+ (840 / rowCnt) + ", " + (840 / rowCnt) + ", " + Color.WHITE);
	            	canvas.drawSquare( i * (840 / colCnt) + 20, j * (840 / colCnt) + 20,
	            			 (840 / rowCnt), (840 / rowCnt), Color.WHITE);
	            } 
	        }
	    }
	    canvas.repaint();	    
	}
}
