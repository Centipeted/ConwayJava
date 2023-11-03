package main;

import java.awt.Color;
import javax.swing.JFrame;

public class CanvasCreator extends JFrame {
	
	private final JPanelSquare panel;

	public CanvasCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway");
		setSize(1290, 916);
		setResizable(false);
		panel = new JPanelSquare();
		add(panel);
	}

	public void drawSquare(int xCoord, int yCoord, int width, int height, Color color) {
		panel.addSquare(xCoord, yCoord, width, height, color);
	}
	
	public void clearSqureList() {
		panel.clearSquares();
	}
	
	public void repaint() {
		panel.repaint();
	}
}