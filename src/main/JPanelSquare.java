package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class JPanelSquare extends JPanel {
	private final List<Square> squares;
	
	public JPanelSquare() {
	  squares = new ArrayList<>();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<Square> squaresCopy = new ArrayList<>(squares);

		for (Square square : squaresCopy) {
			square.draw(g);
		}
	}
	
	public void clearSquares() {
		squares.clear();
	}
	
	public void addSquare(int xCoord, int yCoord, int width, int height, Color color) {
	  squares.add(new Square(xCoord, yCoord, width, height, color));
	}
	
	private static class Square {
	  private final int xCoord;
	  private final int yCoord;
	  private final int width;
	  private final int height;
	  private final Color color;
	  
	  public Square(int xCoord, int yCoord, int width, int height, Color color) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
		this.color = color;
	  }
	  
	  public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(xCoord, yCoord, width, height);
		g.fillRect(xCoord, yCoord, width, height);
	  }
	}
}