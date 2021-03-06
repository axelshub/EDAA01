package sudoku;

import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import sudoku.Sudoku;

public class SudokuController {
	private Sudoku sudoku;
	private Scanner scanner;
	private int counter = 0;
	JTextField[][] textfields;
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		new SudokuController(sudoku);
		
	}
	
	public SudokuController(Sudoku sudoku) {
		this.sudoku = sudoku;
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 500, 500));
	}
	
	public boolean overwrite() {
		color();
		int[][] input = new int[9][9];
    	for(int r = 0; r < 9; r++) {
        	for(int c = 0; c < 9; c++) {
        		try {
        			String fieldInput = textfields[r][c].getText();
        			if(!fieldInput.equals("")) {
        				int intInput = Integer.parseInt(fieldInput);
        				if(intInput > 9 || intInput < 1) {
	        				throw new IllegalArgumentException();
	        			}
        			}
        		} catch (Exception e) {
	        		 JOptionPane optionPane = new JOptionPane("Wrong input, write a number between 1 and 9", JOptionPane.ERROR_MESSAGE);    
	   	       		 JDialog dialog = optionPane.createDialog("Error");
	   	       		 dialog.setAlwaysOnTop(true);
	   	       		 dialog.setVisible(true);
	   	       		 return false;
        			
        		}
        		
        		if(textfields[r][c].getText().equals("")) {
        			input[r][c] = 0;
        		} else {
        			input[r][c] = Integer.parseInt(textfields[r][c].getText());
        		}
        	}
        }
    	sudoku.setMatrix(input);
    	color();
    	return true;
	}
	
	
	private void color() {
		for(int r = 0; r < 9; r++) {
        	for(int c = 0; c < 9; c++) {
        		try {
        			int i =Integer.parseInt(textfields[r][c].getText());
        			if(i < 0 || i > 9) {
        				textfields[r][c].setForeground(Color.RED);
        			} else {
        				textfields[r][c].setForeground(Color.BLACK);
        			}
        		} catch(Exception e) {
        			if(textfields[r][c].getText().equals("")) {
        				textfields[r][c].setForeground(Color.BLACK);
        			} else {
        				textfields[r][c].setForeground(Color.RED);
        			}
        		}
        		
        	}
        }
	}
	
	private void giveUpCheck() {
		for(int r = 0; r < 9; r++) {
        	for(int c = 0; c < 9; c++) {
        		if(textfields[r][c].isEditable()) {
        			textfields[r][c].setText("");
        		}
        	}
        }
	}
	
	
	private void createWindow(Sudoku sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        JPanel gridPanel = new JPanel(new GridLayout(9, 9, 2, 2));
        gridPanel.setPreferredSize(new Dimension(300,300));
        textfields = new JTextField[9][9];
        
        JButton solveButton = new JButton("Give up");
        
        solveButton.addActionListener(event -> {
        	//Kolla igenom alla textfields och k??r solvemetoden.
  
        giveUpCheck();
        overwrite();
        	
        	if(sudoku.solve()) {
        		int[][] table = sudoku.getMatrix();
        		for(int r = 0; r < 9; r++) {
    	        	for(int c = 0; c < 9; c++) {
    	        		textfields[r][c].setText(Integer.toString(table[r][c]));
    	        	}
    	        }
        		
        	} else {
	        	 JOptionPane optionPane = new JOptionPane("Not solvable", JOptionPane.ERROR_MESSAGE);    
	       		 JDialog dialog = optionPane.createDialog("Error");
	       		 dialog.setAlwaysOnTop(true);
	       		 dialog.setVisible(true);
        	}
        });
        
        JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(event -> {
			color();
			//Rensa table
			sudoku.clear();
			//Rensa alla textfield	
			for(int r = 0; r < 9; r++) {
	        	for(int c = 0; c < 9; c++) {
	        		textfields[r][c].setText("");
	        		textfields[r][c].setEditable(true);
	        		
	        	}
			}
			
		});
		
		try {
			scanner = new Scanner(new File("sudokufile.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("Couldn't open file: sudokos");
			System.exit(1);
		}
	
		
		JButton generate = new JButton("Generate new");
		generate.addActionListener(event -> {
			if(counter >= 9) {
				try {
					scanner = new Scanner(new File("sudokufile.txt"));
				} catch(FileNotFoundException e) {
					System.out.println("Couldn't open file: sudokos");
					System.exit(1);
				}
				counter = 0;
			}
			
				String line = scanner.nextLine();
				for(int r = 0; r < 9; r++) {
					line = scanner.nextLine();
					for(int i = 0; i < line.trim().length(); i++) {
						String s = line.substring(i, i+1);
						if(s.equals("0")) {
							textfields[r][i].setText("");
							textfields[r][i].setEditable(true);
							textfields[r][i].setForeground(Color.BLACK);
						} else {
							textfields[r][i].setText(s);
							textfields[r][i].setEditable(false);
							textfields[r][i].setForeground(Color.BLUE);
						}
					}
				}
				counter++;
		});
		
		JButton check = new JButton("Check");
        frame.getRootPane().setDefaultButton(check);
		check.addActionListener(event -> {
			
			overwrite();
			if(sudoku.solve()) {
				JOptionPane optionPane = new JOptionPane("Sudoku is solvable!", JOptionPane.PLAIN_MESSAGE);    
	       		JDialog dialog = optionPane.createDialog("Great news!");
	       		dialog.setAlwaysOnTop(true);
	       	    dialog.setVisible(true);
			} else {
				JOptionPane optionPane = new JOptionPane("Sudoku is not solvable", JOptionPane.ERROR_MESSAGE);    
	       		JDialog dialog = optionPane.createDialog("Bad news!");
	       		dialog.setAlwaysOnTop(true);
	       	    dialog.setVisible(true);
			}
			
			
			for(int r = 0; r < 9; r++) {
		    	for(int c = 0; c < 9; c++) {
		    		if(textfields[r][c].getText().equals("")) {
		    			JOptionPane optionPane = new JOptionPane("Sudoku is not filled in", JOptionPane.ERROR_MESSAGE);    
			       		JDialog dialog = optionPane.createDialog("Error");
			       		dialog.setAlwaysOnTop(true);
			       	    dialog.setVisible(true);
		    			return;
		    		}
		    	}
			} 
			
			if (overwrite()) {
				if(sudoku.isAllValid()) {
					JOptionPane optionPane = new JOptionPane("Sudoku is correct!", JOptionPane.PLAIN_MESSAGE);    
		       		JDialog dialog = optionPane.createDialog("Correct Sudoku");
		       		dialog.setAlwaysOnTop(true);
		       	    dialog.setVisible(true);
				} else {
					JOptionPane optionPane = new JOptionPane("Sudoku is not correct", JOptionPane.ERROR_MESSAGE);    
		       		JDialog dialog = optionPane.createDialog("Error");
		       		dialog.setAlwaysOnTop(true);
		       	    dialog.setVisible(true);
				}
			}
			else {
				JOptionPane optionPane = new JOptionPane("Sudoku is not correct", JOptionPane.ERROR_MESSAGE);    
	       		JDialog dialog = optionPane.createDialog("Error");
	       		dialog.setAlwaysOnTop(true);
	       	    dialog.setVisible(true);
			}
			
		});
		
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(generate);
        buttonPanel.add(check);
        
        pane.add(buttonPanel, BorderLayout.SOUTH);
        pane.add(gridPanel, BorderLayout.CENTER);

        
        for(int r = 0; r < 9; r++) {
        	for(int c = 0; c < 9; c++) {
        		//Skapa ny textfield
        		JTextField tf = new JTextField();
        		textfields[r][c] = tf; 		
        		if (((r < 3 || r > 5) && (c < 3 || c > 5)) || (r > 2 && r < 6) && (c > 2 && c < 6)) {
					tf.setBackground(Color.orange);
				}
        		gridPanel.add(textfields[r][c]);
        	}
        }
            
        
        frame.pack();
        frame.setVisible(true);
        
	 }
	
}
