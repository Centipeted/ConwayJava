package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CanvasCreator extends JFrame implements Serializable{
	
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
	private JButton saveGame;
	private JButton loadGame;
	private JButton colorfulTurnOn;
	private JButton colorfulTurnOff;
	private JButton fadeLengthPlus;
	private JButton fadeLengthMinus;
	private JTextField fadeLength;
	private JTextField rebornMinJTextField;
	private JTextField rebornMaxJTextField;
	private JTextField dieUnderpopJTextField;
	private JTextField dieOverpopJTextField;
	private JComboBox playfiledSizeJComboBox;
	private JComboBox saveIdJComboBox;
	private boolean isStopped;
	private boolean isColorful;
	private int simulationSpeed;
	private int changeId;
	private	int sqbCurrentSizeIndex;
	private int saveId;
	public static final int[] sqbArray = {
			5, 6, 7, 8, 10, 12, 15, 20, 24, 28, 30, 35, 40, 56, 60, 70, 84, 105, 120};
	private static final int[] saveArray = {1, 2, 3, 4, 5, 6};
	

	public CanvasCreator(SquareBoard sqb) {
		changeId = 0;
		this.squareBoard = sqb;
		sqbCurrentSizeIndex = 14;
		simulationSpeed = 100;
		isStopped = false;
		isColorful = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway");
		setSize(1325, 916);
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
		
		
		speedPlus = new JButton("Speed+");
		speedPlus.setBounds(235, 150, 120, 30);
		speedPlus.addActionListener(e -> {
		    if (simulationSpeed - 10 > 10) {
		        simulationSpeed -= 10;
		    } else {
		        simulationSpeed = 10;
		    }
		});
		
		speedMinus = new JButton("Speed-");
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
		
		sizePlus = new JButton("Size+");
		sizePlus.setBounds(230, 450, 130, 30);
		sizePlus.addActionListener(e -> {
			if(sqbCurrentSizeIndex < 18) {
				sqbCurrentSizeIndex++;
				changeId = 1;
			    playfiledSizeJComboBox.setSelectedItem(sqbArray[sqbCurrentSizeIndex]);
			}
		});
		
		sizeMinus = new JButton("Size-");
		sizeMinus.setBounds(80, 450, 130, 30);
		sizeMinus.addActionListener(e -> {
			if(sqbCurrentSizeIndex > 0) {
				sqbCurrentSizeIndex--;
				changeId = 1;
			    playfiledSizeJComboBox.setSelectedItem(sqbArray[sqbCurrentSizeIndex]);
			}
		});
		
		rightPanel.add(sizePlus);
		rightPanel.add(sizeMinus);
		
		playfiledSizeJComboBox = new JComboBox(toObjectArray(sqbArray));
		playfiledSizeJComboBox.setBounds(80, 500, 50, 30);
		playfiledSizeJComboBox.addActionListener(e -> {
	        int size = (int) playfiledSizeJComboBox.getSelectedItem();
	        for (int i = 0; i < sqbArray.length; i++) {
	            if (sqbArray[i] == size) {
	                sqbCurrentSizeIndex = i;
	            }
	        }
	        squareBoard.setCanvas(this);
	        changeId = 1;
	    });
		rightPanel.add(playfiledSizeJComboBox);
	    playfiledSizeJComboBox.setSelectedItem(sqbArray[sqbCurrentSizeIndex]);
	    
	    saveGame = new JButton("Save game");
	    saveGame.setBounds(80, 550, 130, 30);
	    saveGame.addActionListener(e -> {
			try {
				saveGame();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	    
	    loadGame = new JButton("Load game");
	    loadGame.setBounds(230, 550, 130, 30);
	    loadGame.addActionListener(e -> {
			changeId = 2;
		});
	    
	    rightPanel.add(saveGame);
	    rightPanel.add(loadGame);
	    
	    saveIdJComboBox = new JComboBox(toObjectArray(saveArray));
	    saveIdJComboBox.setBounds(80, 600, 50, 30);
	    saveIdJComboBox.addActionListener(e -> {
	    	saveId = (int) saveIdJComboBox.getSelectedItem();
	    });
	    saveIdJComboBox.setSelectedItem(saveArray[0]);
	    rightPanel.add(saveIdJComboBox);
	    
	    colorfulTurnOn = new JButton("Colorful ON");
	    colorfulTurnOn.setBounds(80, 650, 130, 30);
	    colorfulTurnOn.addActionListener(e -> {
		    if (isColorful) {
		    	colorfulTurnOn.setVisible(false);
		    	colorfulTurnOff.setVisible(true);
		    } else {
		    	colorfulTurnOn.setVisible(true);
		        colorfulTurnOff.setVisible(false);
		    }
		    isColorful = !isColorful;
		});
	    
	    colorfulTurnOff = new JButton("Colorful OFF");
	    colorfulTurnOff.setBounds(80, 650, 130, 30);
	    colorfulTurnOff.addActionListener(e -> {
		    if (isColorful) {
		    	colorfulTurnOn.setVisible(false);
		    	colorfulTurnOff.setVisible(true);
		    } else {
		    	colorfulTurnOn.setVisible(true);
		        colorfulTurnOff.setVisible(false);
		    }
		    isColorful = !isColorful;
		});
	    
	    colorfulTurnOn.setVisible(false);
	    
	    rightPanel.add(colorfulTurnOn);
	    rightPanel.add(colorfulTurnOff);
	    
	    fadeLengthPlus = new JButton("FadeLength+");
	    fadeLengthPlus.setBounds(230, 700, 130, 30);
	    fadeLengthPlus.addActionListener(e -> {
	    	if(squareBoard.getFadeLength() < 255) {
				squareBoard.setFadeLength(squareBoard.getFadeLength() + 1);
				if(squareBoard.getFadeLength() < 10)
					fadeLength.setText(" " + Integer.toString(squareBoard.getFadeLength()));
				else
					fadeLength.setText(Integer.toString(squareBoard.getFadeLength()));
			}
		});
	    fadeLengthMinus = new JButton("FadeLength-");
	    fadeLengthMinus.setBounds(80, 700, 130, 30);
	    fadeLengthMinus.addActionListener(e -> {
	    	if(squareBoard.getFadeLength() > 0) {
				squareBoard.setFadeLength(squareBoard.getFadeLength() - 1);
				if(squareBoard.getFadeLength() < 10)
					fadeLength.setText(" " + Integer.toString(squareBoard.getFadeLength()));
				else
					fadeLength.setText(Integer.toString(squareBoard.getFadeLength()));
			}
		});
	    
	    colorfulTurnOn.setVisible(false);
	    
	    fadeLength = new JTextField();
	    fadeLength.setBounds(230, 650, 30, 30);
	    
	    fadeLength.setText(" " + Integer.toString(squareBoard.getFadeLength()));
	    
	    rightPanel.add(colorfulTurnOn);
	    rightPanel.add(colorfulTurnOff);
	    rightPanel.add(fadeLengthPlus);
	    rightPanel.add(fadeLengthMinus);
	    rightPanel.add(fadeLength);
	}
	
	private static Integer[] toObjectArray(int[] array) {
        Integer[] objectArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            objectArray[i] = array[i];
        }
        return objectArray;
    }
	
	private void sqbMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY() - 36;
				int squareSize = 840 / squareBoard.getSideSize();
	
				clickedY = (mouseY - 20) / squareSize;
				clickedX = (mouseX - 20) / squareSize;
				
				if(clickedX < squareBoard.getSideSize() && clickedY < squareBoard.getSideSize() && mouseX >= 20 && mouseY >= 20) {
					if(squareBoard.getSquareState(clickedX, clickedY) == 1) {
						squareBoard.setSquareState(clickedX, clickedY, (byte)0);
						drawSquare((clickedX * (840 / squareBoard.getSideSize()) + 20), (clickedY * (840 / squareBoard.getSideSize()) + 20),
		            			 (840 / squareBoard.getSideSize()), (840 / squareBoard.getSideSize()), Color.BLACK);
					}
					else {
						squareBoard.setSquareState(clickedX, clickedY, (byte)1);
						
						drawSquare( clickedX * (840 / squareBoard.getSideSize()) + 20, clickedY * (840 / squareBoard.getSideSize()) + 20,
		            			 (840 / squareBoard.getSideSize()), (840 / squareBoard.getSideSize()), Color.WHITE);
						
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
	
	public SquareBoard getResizedSquareBoard(boolean bigger) {
		SquareBoard newBoard = new SquareBoard(sqbArray[sqbCurrentSizeIndex]);
		
			if(bigger) {
				for(int i = 0; i < squareBoard.getSideSize() - 1; i++) {
					for(int j = 0; j < squareBoard.getSideSize() - 1; j++) {
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
			
			newBoard.getRules().setRebornMin(squareBoard.getRules().getRebornMin());
			newBoard.getRules().setRebornMax(squareBoard.getRules().getRebornMax());
			newBoard.getRules().setDieUnderpop(squareBoard.getRules().getDieUnderpop());
			newBoard.getRules().setDieOverpop(squareBoard.getRules().getDieOverpop());
			newBoard.setFadeLength(squareBoard.getFadeLength());
			
			
			squareBoard = newBoard;
			squareBoard.setCanvas(this);
		return newBoard;
	}
	
	public void saveGame() throws IOException {
		IOHandler.saveGameState("savedGameState"+ saveId + ".txt", squareBoard);
	}
	
	public SquareBoard loadGame() throws IOException {
		return IOHandler.loadGameState("savedGameState"+ saveId + ".txt", this, squareBoard);
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
	public int getChangeId() {
		return changeId;
	}
	
	public boolean getIsColorful() {
		return isColorful;
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
	
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	
	public void setSqb(SquareBoard sqb) {
		this.squareBoard = sqb;
	}
	
	public void setIsColorful(boolean isColorful) {
		this.isColorful = isColorful;
	}
	
	public void refreshAllTextfields() {
		rebornMinJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMin()));
		rebornMaxJTextField.setText("  " + Integer.toString(squareBoard.getRules().getRebornMax()));
		dieUnderpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieUnderpop()));
		dieOverpopJTextField.setText("  " + Integer.toString(squareBoard.getRules().getDieOverpop()));
		playfiledSizeJComboBox.setSelectedItem(sqbArray[sqbCurrentSizeIndex]);
	}
	
}