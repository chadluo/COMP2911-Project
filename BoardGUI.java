import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class BoardGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	Cell[][] board;
	private Cell nothingCell;
	private Cell chosenCell;
	private SudokuGenerator generator;
	private SudokuBoard sudokuBoard; 
	
	public BoardGUI() {
		board = new Cell[9][9];
		nothingCell = new Cell(-1, -1);
		chosenCell = nothingCell;
		generator = new SudokuGenerator(1);
		generator.GenerateSolvableSudoku();
		sudokuBoard = generator.getBoard();
		sudokuBoard.printBoard();
		System.out.print("\n");
		JPanel squares = createSquares();	
		add(squares);
		JPanel buttons = createNumButtons();
		add(buttons);		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width / 2 - 175), (d.height / 2 - 275));
		populateBoard(sudokuBoard);
		setVisible(true);
	}
	
	public BoardGUI(SudokuBoard solution) {
		board = new Cell[9][9];
		nothingCell = new Cell(-1, -1);
		chosenCell = nothingCell;
		sudokuBoard = solution;
		sudokuBoard.printBoard();
		System.out.print("\n");
		JPanel squares = createSquares();		
		add(squares);	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width / 2 - 175), (d.height / 2 - 275));
		populateBoard(sudokuBoard);
		setVisible(true);
	}
	
	public JPanel createSquares() {
		JPanel squares = new JPanel();
		squares.setLayout(new GridLayout(9, 9, 1, 1));
		
		for(int col = 0 ; col < 9; col++) {
			for(int row = 0; row < 9; row++) {
				board[col][row] = new Cell(col, row);
				squares.add(board[col][row]);
				board[col][row].addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						chosenCell.setBackground(Color.WHITE);
						chosenCell = (Cell) e.getSource();
						chosenCell.setBackground(Color.CYAN);
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}
					
				});
			}
		}
		
		return squares;
	}
	
	public JPanel createSquaresSolution() {
		JPanel squares = new JPanel();
		squares.setLayout(new GridLayout(9, 9, 1, 1));
		return squares;
	}
	
	public JPanel createNumButtons() {
		JPanel numbers = new JPanel();
		numbers.setLayout(new GridLayout(1, 1, 1, 1));
		numbers.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		String keyLabels = "123456789";
	    for (int i = 0; i < keyLabels.length(); i++) {
	    	final String label = keyLabels.substring(i, i + 1);
	        final JButton keyButton = new JButton(label);
	        numbers.add(keyButton);
	        keyButton.addActionListener(new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent event) {
	        		if(chosenCell.getEditable()) {
	        			chosenCell.setBackground(Color.WHITE);
	        			chosenCell.setText(keyButton.getText());
						chosenCell.setEditable(false);
						sudokuBoard.setNumber(chosenCell.getCol(), chosenCell.getRow(), Integer.parseInt(keyButton.getText()));
	        		}
	            }
	        });
	    }
	    
		return numbers;
	}
	
	public void deleteCell() {
		chosenCell.setBackground(Color.WHITE);
		chosenCell.setText("");
		chosenCell.setEditable(true);
		chosenCell = nothingCell;
	}
	
	public void populateCell (int x, int y, int number) {
		if(board[x][y].getEditable() == true) {
			String num = Integer.toString(number);
			board[x][y].setText(num);
		}
	}
	
	public void populateBoard (SudokuBoard sudoku) {
		for (int col = 0; col<9; col++) {
			for (int row = 0; row<9; row++){
				//want to out
				String number = Integer.toString(sudoku.getNumber(col, row));
				board[col][row].setText(number);
			}
		}
	}
	
	public SudokuBoard getSolution () {
		return generator.solution;
	}
}
