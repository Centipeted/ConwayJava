package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CanvasCreator extends JFrame{
	
	private final JPanelSquare leftPanel;
	private JPanel rightPanel;
	private JPanel container;
	private int clickedX;
	private int clickedY;
	private SquareBoard squareBoard;
	private JButton startButton;
	private JButton stopButton;
	private JButton speedPlus;
	private JButton speedMinus;
	private boolean isStopped;
	private int simulationSpeed;
	

	public CanvasCreator() {
		simulationSpeed = 100;
		isStopped = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway");
		setSize(1330, 916);
		setBackground(new Color(0,0,255));
		setResizable(false);
		setLayout(null);
		
		container = new JPanel();
		container.setSize(1330, 880);
		container.setLayout(null);
		container.setBackground(new Color(60, 60, 60));
		add(container);
		
		JPanel a = new JPanel();
		a.setSize(880, 880);
		a.setBackground(new Color(255, 0, 100));
		//container.add(a);
		
		leftPanel = new JPanelSquare();
		leftPanel.setSize(880, 880);
		container.add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setSize(440, 880);
		rightPanel.setLayout(null);
		rightPanel.setBackground(new Color(30, 30, 30));
		rightPanel.setLocation(890, 0);
		
		StartStopButtonActionListener startStopB = new StartStopButtonActionListener();
		
		startButton = new JButton("START");
		startButton.setBounds(165, 100, 120, 30);
		startButton.addActionListener(startStopB);
		
		stopButton = new JButton("STOP");
		stopButton.setBounds(165, 100, 120, 30);
		stopButton.addActionListener(startStopB);
		
		startButton.setVisible(false);
		stopButton.setVisible(true);
		rightPanel.add(startButton);
		rightPanel.add(stopButton);
		container.add(rightPanel);
		
		SpeedPlusButtonActionListener speedPlusB = new SpeedPlusButtonActionListener();
		SpeedMinusButtonActionListener speedMinusB = new SpeedMinusButtonActionListener();
		
		speedPlus = new JButton("SPEED+");
		speedPlus.setBounds(85, 150, 120, 30);
		speedPlus.addActionListener(speedPlusB);
		
		speedMinus = new JButton("SPEED-");
		speedMinus.setBounds(235, 150, 120, 30);
		speedMinus.addActionListener(speedMinusB);
		
		rightPanel.add(speedPlus);
		rightPanel.add(speedMinus);
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY() - 36;
				int squareSize = 840 / squareBoard.getRowCnt();

				clickedY = (mouseY - 20) / squareSize;
				clickedX = (mouseX - 20) / squareSize;
				
				System.out.println("mouse:" + mouseX + " " + mouseY);
				System.out.println("clicked: " + clickedX + " " +clickedY);
				if(clickedX < squareBoard.getRowCnt() && clickedY < squareBoard.getRowCnt() && mouseX >= 20 && mouseY >= 20) {
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
	
	public boolean getIsStopped() {
		return isStopped;
	}
	
	public int getSimulationSpeed() {
		return simulationSpeed;
	}
	
	public void setClickedX(int x) {
		clickedX = x;
	}
	
	public void setClickedY(int y) {
		clickedX = y;
	}
	
	public void setIsStopped() {
		isStopped = !isStopped;
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		if(isStopped) {
			startButton.setVisible(false);
			stopButton.setVisible(true);
		}
		else {
			startButton.setVisible(true);
			stopButton.setVisible(false);
		}
		isStopped = !isStopped;
		
	}*/
	private class StartStopButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isStopped) {
				startButton.setVisible(false);
				stopButton.setVisible(true);
			}
			else {
				startButton.setVisible(true);
				stopButton.setVisible(false);
			}
			isStopped = !isStopped;
			
		}
		
	}
	
	private class SpeedPlusButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(simulationSpeed - 10 > 10) {
				simulationSpeed -= 10;
			}
			else {
				simulationSpeed = 10;
			}
		}
		
	}
	
	private class SpeedMinusButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(simulationSpeed + 10 < 200) {
				simulationSpeed += 10;
			}
			else {
				simulationSpeed = 200;
			}
		}
		
	}
	
	
	
}