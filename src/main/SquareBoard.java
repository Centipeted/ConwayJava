package main;

import java.awt.Color;
import java.io.Serializable;

import javax.sound.midi.Soundbank;

public class SquareBoard implements Board, Serializable {
	private int sideSize;
	private int fadeLength;
	public Cell board[][];
	public Cell newBoard[][];
	private Rules rules = new Rules(3, 3, 2, 3);
	private CanvasCreator canvas;

	public SquareBoard(int sideSize) {
		this.fadeLength = 10;
		this.sideSize = sideSize;
		this.sideSize = sideSize;
		board = new Cell[sideSize][sideSize];
		newBoard = new Cell[sideSize][sideSize];
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				this.board[i][j] = new Cell(0);
				this.newBoard[i][j] = new Cell(0);
			}
		}
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
				if (board[tmpX][tmpY].getCellState() == 1) {
					cnt++;
				}
			}
		}
		if (board[xCoord][yCoord].getCellState() == 1) {
			cnt--;
		}
		return cnt;
	}

	@Override
	public byte rtnCellNextState(int xCoord, int yCoord) {
		int neighborCnt = rtnNeighborCnt(xCoord, yCoord);
		if (board[xCoord][yCoord].getCellState() == 0) {
			if (neighborCnt >= rules.getRebornMin() && neighborCnt <= rules.getRebornMax()) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else if (board[xCoord][yCoord].getCellState() == 1) {
			if (neighborCnt >= rules.getDieUnderpop() && neighborCnt <= rules.getDieOverpop()) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			if (neighborCnt >= rules.getRebornMin() && neighborCnt <= rules.getRebornMax()) {
				return 1;
			}
			else if (board[xCoord][yCoord].getCellState() < 255){
				return (byte)(board[xCoord][yCoord].getCellState() + 1);
			}
			return 0;
		}
	}

	@Override
	public void calcNextStep() {
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				newBoard[i][j].setCellState(rtnCellNextState(i, j));
			}
		}
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				board[i][j].setCellState((byte)(newBoard[i][j].getCellState()));
			}
		}
	}
	
	private int redValue(int i, int j, int greenValue){
		int a = newBoard[i][j].getCellState() >= 0 ? newBoard[i][j].getCellState() : newBoard[i][j].getCellState() + 256;
		if (greenValue > 0){
			return 255;
		}
		else
			if((510 - ((510 / fadeLength) * (a - 1))) > 0)
				return (510 - ((510 / fadeLength) * (a - 1)));
			else
				return 0;
	}
	
	private int greenValue(int i ,int j){
		int a = newBoard[i][j].getCellState() >= 0 ? newBoard[i][j].getCellState() : newBoard[i][j].getCellState() + 256; 
		if ((255 - (510 / fadeLength * (a - 1))) > 0) {
			return (byte)(255 - ((510 / fadeLength) * (a - 1)));
		}
		else {
			return 0;
		}
	}
	
	
	
	public void drawCells(){
		for (int i = 0; i < sideSize; i++) {
			for (int j = 0; j < sideSize; j++) {
				if (newBoard[i][j].getCellState() == 1) {
					if (canvas.getIsColorful()) {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
								(840 / sideSize), (840 / sideSize), Color.YELLOW);
					}
					else {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
								(840 / sideSize), (840 / sideSize), Color.BLACK);
					}
				}
				else if (newBoard[i][j].getCellState() == 0) {
					if (canvas.getIsColorful()) {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
								(840 / sideSize), (840 / sideSize), Color.BLACK);
					}
					else {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
								(840 / sideSize), (840 / sideSize), Color.WHITE);
					}
				}
				else {
					int a = greenValue(i, j) >= 0 ? greenValue(i, j) : greenValue(i, j) + 256;
					int b = redValue(i, j, a) >= 0 ? redValue(i, j, a) : redValue(i, j, a) + 256;
					if (canvas.getIsColorful()) {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
							(840 / sideSize), (840 / sideSize), new Color(b, a, 0));
					}
					else {
						canvas.drawSquare( i * (840 / sideSize) + 20, j * (840 / sideSize) + 20,
								(840 / sideSize), (840 / sideSize), Color.WHITE);
					}
				}
			}
		}
		canvas.repaint();
	}
	
	public void setSquareState(int xCoord, int yCoord, byte state) {
		if (xCoord >= 0 && xCoord < sideSize && yCoord >= 0 && yCoord < sideSize) {
			board[xCoord][yCoord].setCellState(state);
			newBoard[xCoord][yCoord].setCellState(state);
			canvas.repaint();
		}
	}
	
	public byte getSquareState(int xCoord, int yCoord) {
		return board[xCoord][yCoord].getCellState();
	}
	
	public Rules getRules() {
		return rules;
	}
	
	public int getFadeLength() {
		return fadeLength;
	}
	
	public void setFadeLength(int fadeLength) {
		this.fadeLength = fadeLength;
	}
	
	public void setCanvas(CanvasCreator canvas) {
		this.canvas = canvas;
	}
}