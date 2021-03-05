package sudoku;

public class Sudoku implements SudokuSolver {

	private int[][] table;

	public Sudoku() {
		table = new int[9][9];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		// TODO Auto-generated method stub
		table[r][c] = nbr;

	}

	@Override
	public int getNumber(int r, int c) {
		// TODO Auto-generated method stub
		return table[r][c];
	}

	@Override
	public void clearNumber(int r, int c) {
		// TODO Auto-generated method stub
		table[r][c] = 0;
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		// TODO Auto-generated method stub

		// Case 1: Lodrätt
		for (int row = 0; row < table.length; row++) {
			if (table[row][c] == nbr && row != r) {
				return false;
			}
		}

		// Case 2: Vågrätt
		for (int col = 0; col < table[r].length; col++) {
			if (table[r][col] == nbr && col != c) {
				return false;
			}
		}

		// Case 3: Grupp 1-9
		// Kolla vilken grupp
		int rowGroup = r / 3;
		int colGroup = c / 3;

		for (int row = 3 * rowGroup; row < (3 * rowGroup + 3); row++) {
			for (int col = 3 * colGroup; col < (3 * colGroup + 3); col++) {
				if (table[row][col] == nbr && (row != r && col != c)) {
					return false;
				}
			}
		}

		return true;

	}

	@Override
	public boolean isAllValid() {
		for (int r = 0; r < table.length; r++) {
			for (int c = 0; c < table[r].length; c++) {
				if(table[r][c] != 0) {
					if (!isValid(r, c, table[r][c])) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		// Kalla på rekursiva solve-metoden
		if (isAllValid()) {
			return solve(0, 0);
		}
		return false;
	}

	private boolean solve(int r, int c) {
		//Rekursiv lösning
		//Returnera true om gick att lösa, annars:
		if(c > 8) { //Byter rad när man når sista kolumn
			c = 0;
			r++;
		}
		
		if (r > 8) { //Basfall
			return true;
		} 
		
		if(table[r][c] != (int)table[r][c]) {
			throw new IllegalArgumentException();
		}
		
		if(table[r][c] != 0) {
			if(!isValid(r, c, table[r][c])) {
				return false;
			}
			return solve(r, c+1);
		} 
		
		for(int i = 1; i <= 9; i++) {
			if(isValid(r, c, i)) {
				table[r][c] = i;
				if(solve(r, c+1)) {
					return true;
				} else {
					table[r][c] = 0;
				}
			}	
		}
		
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int r = 0; r < table.length; r++) {
			for (int c = 0; c < table[r].length; c++) {
				table[r][c] = 0;
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		int[][] copy = new int[table.length][table[0].length];
		for(int r = 0; r < copy.length; r++) {
			for(int c = 0; c < copy[r].length; c++) {
				copy[r][c] = table[r][c];
			}
		}
		return copy;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		// TODO Auto-generated method stub
		
		for(int r = 0; r < nbrs.length; r++) {
			for(int c = 0; c < nbrs[r].length; c++) {
				if(nbrs[r][c] < 0 || nbrs[r][c] > 9) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		table = nbrs;

	}
}
