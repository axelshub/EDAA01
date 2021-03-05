package sudoku;

public interface SudokuSolver {
	/**
	 * Sets the number nbr in box r, c.
	 * @param r The row
	 * @param c The column
	 * @param nbr The number to insert in box r, c
	 */
	public void setNumber(int r, int c, int nbr);
	
	/**
	 * Returns the number in box r,c. If the box i empty 0 is returned.
	 * 
	 * @param r The row
	 * @param c The column
	 * @return the number in box r,c or 0 if the box is empty.
	 */
	public int getNumber(int r, int c);
	
	
	/**
	 * Tömmer rutan r,c. IllegalArgumentException om fel värde på r eller c
	 * @param r	The row
	 * @param c The column	
	 */
	public void clearNumber(int r, int c);
	
	/**
	 * Kontrollerar om värdet nbr i rutan r,c är ok enligt reglerna.
	 * IllegalArgumentException om fel värde på r, c eller nbr
	 * @return true if valid
	 * @param r	The row
	 * @param c The column	
	 * @param nbr The number controlled
	 */
	public boolean isValid(int r, int c, int nbr);


	/**
	 * Kontrollerar att alla ifyllda siffrorna uppfyller reglerna.
	 * @return true if all is valid
	 */
	public boolean isAllValid();
		
	/**
	 * Försöker lösa sudokut och returnerar true om det var läsbart, annars false.
	 * @return true if the sudoku was solved
	 */
	public boolean solve();
		
	/**
	 * Tömmer alla rutorna i sudokut
	 */
	public void clear();
		
	/**
	 * Returns the numbers in the grid. An empty box i represented
	 * by the value 0.
	 * 
	 * @return the numbers in the grid
	 */
	public int[][] getMatrix();

	/**
	 * Fills the grid with the numbers in nbrs.
	 * 
	 * @param nbrs the matrix with the numbers to insert
	 * @throws IllegalArgumentException
	 *             if nbrs have wrong dimension or containing values not in [0..9] 
	 */
	public void setMatrix(int[][] nbrs);
		
	
	/**
	 * Returns the dimension of the grid
	 * @return the dimension of the grid
	 */
	public default int getDimension() {
		return 9;
	}

}