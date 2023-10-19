package main;

public class SquareBoard implements Board {
	private int rowCnt;
	private int colCnt;
	private Cell board[][];
	private Cell newBoard[][];
	private Rules rules = new Rules(3, 3, 2, 3);

	public SquareBoard(int rowCnt, int colCnt) {
		this.rowCnt = rowCnt;
		this.colCnt = colCnt;
		for (int i = 0; i < this.rowCnt; i++) {
			for (int j = 0; j < this.colCnt; j++) {
				board[i][j].setState(false);
			}
		}
	}	
	
	@Override
	public int rtnNeighborCnt(int xCoord, int yCoord) {
		int cnt = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = 0; j < colCnt; j++) {
				if (board[xCoord + i][yCoord + j].getState() == true) {
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
	
	



}
