package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOHandler {
	public static void saveGameState(String filePath, SquareBoard squareBoard) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(squareBoard.getSideSize() + " " + squareBoard.getRules().getRebornMin() + " " + squareBoard.getRules().getRebornMax() + " " + 
				squareBoard.getRules().getDieUnderpop() + " " + squareBoard.getRules().getDieOverpop());
		for (int i = 0; i <= squareBoard.getSideSize() - 1; i++) {
			for (int j = 0; j <= squareBoard.getSideSize() - 1; j++) {
				if(squareBoard.newBoard[j][i].getCellState() == 1) {
					pw.print(1);
				}
				else {
					pw.print(0);
				}
			}
			pw.print("\n");
		}
		pw.close();
	}

	public static SquareBoard loadGameState(String filename, CanvasCreator canvas, SquareBoard sq) throws IOException {
		File file = new File(filename);
		if (file.exists()) {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			String[] header = line.split(" ");
			int sideSize = Integer.parseInt(header[0]);
			int rebornMin = Integer.parseInt(header[1]);
			int rebornMax = Integer.parseInt(header[2]);
			int dieUnderpop = Integer.parseInt(header[3]);
			int dieOverpop = Integer.parseInt(header[4]);
	 
			SquareBoard squareBoard = new SquareBoard(sideSize);
			squareBoard.getRules().setRebornMin(rebornMin);
			squareBoard.getRules().setRebornMax(rebornMax);
			squareBoard.getRules().setDieUnderpop(dieUnderpop);
			squareBoard.getRules().setDieOverpop(dieOverpop);
			squareBoard.setCanvas(canvas);
			
			for(int i = 0; i < sideSize; i++) {
				line = br.readLine();
				for(int j = 0; j < sideSize; j++) {
					char bitChar = line.charAt(j);
					if(Character.getNumericValue(bitChar) == 1) {
						squareBoard.board[j][i].setCellState((byte)1);
						squareBoard.newBoard[j][i].setCellState((byte)1);
					}
					else {
						squareBoard.board[j][i].setCellState((byte)0);
						squareBoard.newBoard[j][i].setCellState((byte)0);
					}
				}
			}
	
			br.close();
			return squareBoard;
		}
		else {
			System.out.println("Save file not found");
			return sq;
		}
	}
}
