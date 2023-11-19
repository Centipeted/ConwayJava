package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CanvasCreator extends JFrame {
	
	private final JPanelSquare leftPanel = new JPanelSquare();
	private final JPanelSquare rightPanel;
	private int clickedX;
	private int clickedY;
	private SquareBoard squareBoard;
	private JButton button;
	private JLabel label;

	public CanvasCreator() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway");
		setSize(1320, 916);
		setResizable(false);
		
		//leftPanel = new JPanelSquare();
		leftPanel.setSize(880, 880);
		leftPanel.setLayout(new GridBagLayout());
		add(leftPanel);
		
		rightPanel = new JPanelSquare();
		rightPanel.setSize(440, 880);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(new Color(30, 30, 30));
		//rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		//button = new JButton("START/STOP");
		//label = new JLabel("nothing");
		add(rightPanel);
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 0;
        gbc.weighty = 1;
        //leftPanel.add(label, gbc);
        gbc.weighty = 0;
       // rightPanel.add(button, BorderLayout.NORTH);
        add(rightPanel, BorderLayout.NORTH);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY() - 36;
				int squareSize = 840 / 60;

				clickedY = (mouseY - 20) / squareSize;
				clickedX = (mouseX - 20) / squareSize;
				
				if(squareBoard.getState(clickedX, clickedY) == false) {
					squareBoard.setSquareState(clickedX, clickedY, true);
					drawSquare((clickedX * (840 / squareBoard.getColCount()) + 20), (clickedY * (840 / squareBoard.getColCount()) + 20),
	            			 (840 / squareBoard.getRowCnt()), (840 / squareBoard.getRowCnt()), Color.BLACK);
				}
				else {
					System.out.println("setting to false");
					squareBoard.setSquareState(clickedX, clickedY, false);
					drawSquare( clickedX * (840 / squareBoard.getColCount()) + 20, clickedY * (840 / squareBoard.getColCount()) + 20,
	            			 (840 / squareBoard.getRowCnt()), (840 / squareBoard.getRowCnt()), Color.WHITE);
					
				}
				repaint();
				
			}
			
		});
	}
	
	public void setSquareBoard(SquareBoard squareBoard) {
		this.squareBoard = squareBoard;
	}

	public void drawSquare(int xCoord, int yCoord, int width, int height, Color color) {
		leftPanel.addSquare(xCoord, yCoord, width, height, color);
	}
	
	public void clearSqureList() {
		leftPanel.clearSquares();
	}
	
	public void repaint() {
		leftPanel.repaint();
	}

	public int getClickedX() {
		return clickedX;
	}

	public int getClickedY() {
		return clickedY;
	}
	
	public void setClickedX(int x) {
		clickedX = x;
	}
	
	public void setClickedY(int y) {
		clickedX = y;
	}
	
	
}