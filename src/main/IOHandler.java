package main;

import java.io.BufferedReader;
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
				if(squareBoard.newBoard[j][i].getState()) {
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

	public static SquareBoard loadGameState(String filename, CanvasCreator canvas) throws IOException {
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

		/*while ((line = br.readLine()) != null && row < sideSize) {
	        String[] values = line.split(" ");
	        for (int col = 0; col < values.length && col < sideSize; col++) {
	            boolean state = values[col].trim().equals("1");
	            System.out.println("Set square on the new board: " + values[col].equals("1"));
	            
	            
	            squareBoard.board[col][row].setState(state);
	            squareBoard.newBoard[col][row].setState(state);
	        }
	        row++;
	    }*/
		
		for(int i = 0; i < sideSize; i++) {
			line = br.readLine();
			for(int j = 0; j < sideSize; j++) {
				char bitChar = line.charAt(j);
				if(Character.getNumericValue(bitChar) == 1) {
					squareBoard.board[j][i].setState(true);
					squareBoard.newBoard[j][i].setState(true);
				}
				else {
					squareBoard.board[j][i].setState(false);
					squareBoard.newBoard[j][i].setState(false);
				}
			}
		}

		br.close();
		return squareBoard;
	}
}
