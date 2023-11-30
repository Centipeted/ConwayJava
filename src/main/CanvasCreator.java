package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CanvasCreator extends JFrame{
	
	private JPanelSquare leftPanel;
	private JPanel rightPanel;
	private JPanel container;
	private int clickedX;
	private int clickedY;
	private SquareBoard squareBoard;
	private JButton startButton;
	private JButton stopButton;
	private JButton speedPlus;
	private JButton speedMinus;
	private JButton rebornMinPlus;
	private JButton rebornMinMinus;
	private JButton rebornMaxPlus;
	private JButton rebornMaxMinus;
	private JButton dieUnderpopPlus;
	private JButton dieUnderpopMinus;
	private JButton dieOverpopPlus;
	private JButton dieOverpopMinus;
	private JButton sizePlus;
	private JButton sizeMinus;
	private JTextField rebornMinJTextField;
	private JTextField rebornMaxJTextField;
	private JTextField dieUnderpopJTextField;
	private JTextField dieOverpopJTextField;
	private JTextField playfieldSizeJTextField;
	private boolean isStopped;
	private int simulationSpeed;
	private int change;
	private	int sqbCurrentSizeIndex;
	private static final int[] sqbArray = {
			1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 15, 20, 21, 24, 28, 30, 35, 40, 42, 56, 60, 70, 84, 105, 120, 140, 168, 210, 280, 420, 840
    };
	

	public CanvasCreator(SquareBoard sqb) {
		change = 0;
		squareBoard = sqb;
		sqbCurrentSizeIndex = 21;
		simulationSpeed = 100;
		isStopped = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway");
		setSize(1325, 916);
		setBackground(new Color(0,0,255));
		setResizable(false);
		setLayout(null);
		
		
		initializePanels();
		
		initializeButtons();
		
		sqbMouseListener();
		
		
		
	}
	
	private void initializePanels() {
		container = new JPanel();
		container.setSize(1325, 880);
		container.setLayout(null);
		container.setBackground(new Color(60, 60, 60));
		add(container);
		
		JPanel lineHider1 = new JPanel();
		lineHider1.setSize(5, 20);
		lineHider1.setBackground(new Color(30, 30, 30));
		lineHider1.setLocation(880, 0);
		container.add(lineHider1);
		JPanel lineHider2 = new JPanel();
		lineHider2.setLocation(880, 860);
		lineHider2.setSize(5, 20);
		lineHider2.setBackground(new Color(30, 30, 30));
		container.add(lineHider2);
		
		leftPanel = new JPanelSquare();
		leftPanel.setSize(880, 880);
		container.add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setSize(440, 880);
		rightPanel.setLayout(null);
		rightPanel.setBackground(new Color(30, 30, 30));
		rightPanel.setLocation(885, 0);
	}
	
	private void initializeButtons() {
		startButton = new JButton("START");
		startButton.setBounds(165, 100, 120, 30);
		startButton.addActionListener(e -> {
		    if (isStopped) {
		        startButton.setVisible(false);
		        stopButton.setVisible(true);
		    } else {
		        startButton.setVisible(true);
		        stopButton.setVisible(false);
		    }
		    isStopped = !isStopped;
		});
		
		stopButton = new JButton("STOP");
		stopButton.setBounds(165, 100, 120, 30);
		stopButton.addActionListener(e -> {
		    if (isStopped) {
		        startButton.setVisible(false);
		        stopButton.setVisible(true);
		    } else {
		        startButton.setVisible(true);
		        stopButton.setVisible(false);
		    }
		    isStopped = !isStopped;
		});
		
		startButton.setVisible(false);
		stopButton.setVisible(true);
		rightPanel.add(startButton);
		rightPanel.add(stopButton);
		container.add(rightPanel);
		
		
		speedPlus = new JButton("SPEED+");
		speedPlus.setBounds(235, 150, 120, 30);
		speedPlus.addActionListener(e -> {
		    if (simulationSpeed - 10 > 10) {
		        simulationSpeed -= 10;
		    } else {
		        simulationSpeed = 10;
		    }
		});
		
		speedMinus = new JButton("SPEED-");
		speedMinus.setBounds(85, 150, 120, 30);
		speedMinus.addActionListener(e -> {
			if(simulationSpeed + 10 < 200) {
				simulationSpeed += 10;
			}
			else {
				simulationSpeed = 200;
			}
		});
		
		rightPanel.add(speedPlus);
		rightPanel.add(speedMinus);
		
		rebornMinPlus = new JButton("RebornMin+");
		rebornMinPlus.setBounds(230, 200, 130, 30);
		rebornMinPlus.addActionListener(e -> {
			if(squareBoard.getRules().getRebornMin() < 8) {
				squareBoard.getRules().setRebornMin(squareBoard.getRules().getRebornMin() + 1);
				if(squareBoard.getRules().getRebornMin() - 1 == squareBoard.getRules().getRebornMax()) {
					squareBoard.getRules().setRebornMax(squareBoard.getRules().getRebornMax() + 1);
				}
				rebornMinJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMin()));
				rebornMaxJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMax()));
			}
		});
		
		rebornMinMinus = new JButton("RebornMin-");
		rebornMinMinus.setBounds(80, 200, 130, 30);
		rebornMinMinus.addActionListener(e -> {
			if(squareBoard.getRules().getRebornMin() > 0) {
				squareBoard.getRules().setRebornMin(squareBoard.getRules().getRebornMin() - 1);
				rebornMinJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMin()));
			}
			
		});
		
		rebornMaxPlus = new JButton("RebornMax+");
		rebornMaxPlus.setBounds(230, 250, 130, 30);
		rebornMaxPlus.addActionListener(e -> {
			if(squareBoard.getRules().getRebornMax() < 8) {
				squareBoard.getRules().setRebornMax(squareBoard.getRules().getRebornMax() + 1);
				rebornMaxJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMax()));	
			}
		});
		
		
		rebornMaxMinus = new JButton("RebornMax-");
		rebornMaxMinus.setBounds(80, 250, 130, 30);
		rebornMaxMinus.addActionListener(e -> {
			if(squareBoard.getRules().getRebornMax() > 0) {
				squareBoard.getRules().setRebornMax(squareBoard.getRules().getRebornMax() - 1);
				if(squareBoard.getRules().getRebornMax() + 1 == squareBoard.getRules().getRebornMin()) {
					squareBoard.getRules().setRebornMin(squareBoard.getRules().getRebornMin() - 1);
				}
				rebornMinJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMin()));
				rebornMaxJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMax()));
			}
		});
		
		rightPanel.add(rebornMinPlus);
		rightPanel.add(rebornMinMinus);
		rightPanel.add(rebornMaxPlus);
		rightPanel.add(rebornMaxMinus);
		
		rebornMinJTextField = new JTextField();
		rebornMinJTextField.setEditable(false);
		rebornMinJTextField.setBounds(80, 400, 30, 30);
		
		rebornMaxJTextField = new JTextField();
		rebornMaxJTextField.setEditable(false);
		rebornMaxJTextField.setBounds(150, 400, 30, 30);
		
		rightPanel.add(rebornMinJTextField);
		rightPanel.add(rebornMaxJTextField);
		
		rebornMinJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMin()));
		rebornMaxJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMax()));
		
		dieUnderpopJTextField = new JTextField();
		dieUnderpopJTextField.setEditable(false);
		dieUnderpopJTextField.setBounds(220, 400, 30, 30);
		
		dieOverpopJTextField = new JTextField();
		dieOverpopJTextField.setEditable(false);
		dieOverpopJTextField.setBounds(290, 400, 30, 30);
		
		rightPanel.add(dieUnderpopJTextField);
		rightPanel.add(dieOverpopJTextField);
		
		dieUnderpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieUnderpop()));
		dieOverpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieOverpop()));
		
		dieUnderpopPlus = new JButton("Underpop+");
		dieUnderpopPlus.setBounds(230, 300, 130, 30);
		dieUnderpopPlus.addActionListener(e -> {
			if(squareBoard.getRules().getDieUnderpop() < 8) {
				squareBoard.getRules().setDieUnderpop(squareBoard.getRules().getDieUnderpop() + 1);
				if(squareBoard.getRules().getDieUnderpop() - 1 == squareBoard.getRules().getDieOverpop()) {
					squareBoard.getRules().setDieOverpop(squareBoard.getRules().getDieOverpop() + 1);
				}
				dieUnderpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieUnderpop()));
				dieOverpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieOverpop()));
			}
		});
		
		dieUnderpopMinus = new JButton("Underpop-");
		dieUnderpopMinus.setBounds(80, 300, 130, 30);
		dieUnderpopMinus.addActionListener(e -> {
			if(squareBoard.getRules().getDieUnderpop() > 0) {
				squareBoard.getRules().setDieUnderpop(squareBoard.getRules().getDieUnderpop() - 1);
				dieUnderpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieUnderpop()));
			}
			
		});
		
		dieOverpopPlus = new JButton("Overpop+");
		dieOverpopPlus.setBounds(230, 350, 130, 30);
		dieOverpopPlus.addActionListener(e -> {
			if(squareBoard.getRules().getDieOverpop() < 8) {
				squareBoard.getRules().setDieOverpop(squareBoard.getRules().getDieOverpop() + 1);
				dieOverpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieOverpop()));	
			}
		});
		
		dieOverpopMinus = new JButton("Overpop-");
		dieOverpopMinus.setBounds(80, 350, 130, 30);
		dieOverpopMinus.addActionListener(e -> {
			if(squareBoard.getRules().getDieOverpop() > 0) {
				squareBoard.getRules().setDieOverpop(squareBoard.getRules().getDieOverpop() - 1);
				if(squareBoard.getRules().getDieOverpop() + 1 == squareBoard.getRules().getDieUnderpop()) {
					squareBoard.getRules().setDieUnderpop(squareBoard.getRules().getDieUnderpop() - 1);
				}
				dieUnderpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieUnderpop()));
				dieOverpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieOverpop()));
			}
		});
		
		rightPanel.add(dieUnderpopPlus);
		rightPanel.add(dieUnderpopMinus);
		rightPanel.add(dieOverpopPlus);
		rightPanel.add(dieOverpopMinus);
		
		playfieldSizeJTextField = new JTextField();
		playfieldSizeJTextField.setEditable(false);
		playfieldSizeJTextField.setBounds(80, 540, 30, 30);
		rightPanel.add(playfieldSizeJTextField);
		playfieldSizeJTextField.setText(" " + Integer.toString(sqbArray[sqbCurrentSizeIndex]));
		
		sizePlus = new JButton("Size+");
		sizePlus.setBounds(230, 490, 130, 30);
		sizePlus.addActionListener(e -> {
			if(sqbCurrentSizeIndex < 31) {
				sqbCurrentSizeIndex++;
				playfieldSizeJTextField.setText(" " + Integer.toString(sqbArray[sqbCurrentSizeIndex]));
				change = 1;
				
			}
		});
		
		sizeMinus = new JButton("Size-");
		sizeMinus.setBounds(80, 490, 130, 30);
		sizeMinus.addActionListener(e -> {
			if(sqbCurrentSizeIndex > 11) {
				sqbCurrentSizeIndex--;
				playfieldSizeJTextField.setText(" " + Integer.toString(sqbArray[sqbCurrentSizeIndex]));
				change = 2;
			}
		});
		
		
		
		rightPanel.add(sizePlus);
		rightPanel.add(sizeMinus);
	}
	
	
	
	private void sqbMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY() - 36;
				int squareSize = 840 / squareBoard.getRowCnt();
	
				clickedY = (mouseY - 20) / squareSize;
				clickedX = (mouseX - 20) / squareSize;
				
				if(clickedX < squareBoard.getRowCnt() && clickedY < squareBoard.getRowCnt() && mouseX >= 20 && mouseY >= 20) {
					if(squareBoard.getState(clickedX, clickedY) == false) {
						squareBoard.setSquareState(clickedX, clickedY, true);
						drawSquare((clickedX * (840 / squareBoard.getColCount()) + 20), (clickedY * (840 / squareBoard.getColCount()) + 20),
		            			 (840 / squareBoard.getRowCnt()), (840 / squareBoard.getRowCnt()), Color.BLACK);
					}
					else {
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
	
	public SquareBoard getNewSquareBoard(boolean bigger) {
		SquareBoard newBoard = new SquareBoard(sqbArray[sqbCurrentSizeIndex], sqbArray[sqbCurrentSizeIndex]);
		
			if(bigger) {
				for(int i = 0; i < squareBoard.getRowCnt() - 1; i++) {
					for(int j = 0; j < squareBoard.getRowCnt() - 1; j++) {
						newBoard.board[i][j] = squareBoard.board[i][j];
						newBoard.newBoard[i][j] = squareBoard.newBoard[i][j];
					}
				}
			}
			else {
				for(int i = 0; i < sqbArray[sqbCurrentSizeIndex] - 1; i++) {
					for(int j = 0; j < sqbArray[sqbCurrentSizeIndex] - 1; j++) {
						newBoard.board[i][j] = squareBoard.board[i][j];
						newBoard.newBoard[i][j] = squareBoard.newBoard[i][j];
					}
				}
			}
			
			squareBoard = newBoard;
			squareBoard.setCanvas(this);
		return newBoard;
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
	
	public int getSqbCurrentSizeIndex() {
		return sqbCurrentSizeIndex;
	}
	public int getChange() {
		return change;
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
	
	public void setSqbCurrentSizeIndex(int sqbCurrentSizeIndex) {
		this.sqbCurrentSizeIndex = sqbCurrentSizeIndex;
	}
	
	public void setChange(int change) {
		this.change = change;
	}
	
	
	
}