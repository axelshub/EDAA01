package sudoku;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {
	
	private SudokuSolver sudoku;
	

	@BeforeEach
	void setUp() throws Exception {
		sudoku = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		sudoku = null;
	}
	
	//Inga specifika test för validmetoderna då de testas genom solvetesterna
	
	
	@Test
	void testSolveEmpty() {
		//Test empty sudoku:
		int[][] emptySudoku = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setMatrix(emptySudoku);
		assertTrue(sudoku.solve(), "Expected true, empty sudoku should be solvable");
		
	}
	
	@Test
	void testSolveUnsolvable() {
		int[][] unsolvableSudoku1 = new int[][] {
			{5, 0, 0, 0, 0, 0, 0, 0, 5 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		sudoku.setMatrix(unsolvableSudoku1);
		assertFalse(sudoku.solve(), "Expected false but got true, this Sudoku should be unsolvable");
		
		int[][] unsolvableSudoku2 = new int[][] {
			{5, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{5, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		sudoku.setMatrix(unsolvableSudoku2);
		assertFalse(sudoku.solve(), "Expected false but got true, this Sudoku should be unsolvable");
		
		int[][] unsolvableSudoku3 = new int[][] {
			{5, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 5, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		sudoku.setMatrix(unsolvableSudoku3);
		assertFalse(sudoku.solve(), "Expected false but got true, this Sudoku should be unsolvable");
		
	}
	
	@Test
	void testUnsolveableAndEmptySquare() {
		int[][] sudoku1 = new int[][] {
			{1, 2, 3, 0, 0, 0, 0, 0, 0 },
			{4, 5, 6, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 7, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setMatrix(sudoku1);
		assertFalse(sudoku.solve(), "Expected false but got true, this Sudoku should be unsolvable");
		sudoku1[2][3] = 0;
		sudoku.setMatrix(sudoku1);
		assertTrue(sudoku.solve(), "Expected true but got false, this Sudoku should be solvable");
	}
	
	@Test
	void testClear() {
		int[][] sudoku1 = new int[][] {
			{5, 0, 0, 0, 0, 0, 5, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		sudoku.setMatrix(sudoku1);
		assertFalse(sudoku.solve(), "Expected false but got true, this Sudoku should be unsolvable");
		sudoku.clear();
		
		int[][] emptySudoku = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		assertArrayEquals(emptySudoku, sudoku.getMatrix(), "Sudoku is not cleared");
		assertTrue(sudoku.solve(), "Expected true but got false, cleared Sudoku should be solvable");
		
	}
	
	@Test
	void testSolvableSudoku() {
		int[][] solvableSudoku = new int[][] {
			{0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		sudoku.setMatrix(solvableSudoku);
		assertTrue(sudoku.solve(), "Expected true but got false");
		
		int[][] correctSudoku = new int[][] {
			{5, 4, 8, 1, 7, 9, 3, 6, 2 },
			{3, 7, 6, 8, 2, 4, 9, 1, 5 },
			{1, 9, 2, 5, 6, 3, 8, 7, 4 },
			{7, 8, 4, 2, 1, 6, 5, 9, 3 },
			{2, 5, 9, 3, 8, 7, 6, 4, 1 },
			{6, 3, 1, 9, 4, 5, 7, 2, 8 },
			{4, 1, 5, 6, 9, 8, 2, 3, 7 },
			{8, 6, 7, 4, 3, 2, 1, 5, 9 },
			{9, 2, 3, 7, 5, 1, 4, 8, 6 },
		};
		assertArrayEquals(correctSudoku, sudoku.getMatrix(), "The sudoku has not been solved correctly");
	}
	
	@Test
	void testWrongInput() {
		int[][] wrongSudoku = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, -1, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		assertThrows("Threw exception", IllegalArgumentException.class, () -> sudoku.setMatrix(wrongSudoku));
		
		wrongSudoku[3][3] = 10;
		
		assertThrows("Threw exception", IllegalArgumentException.class, () -> sudoku.setMatrix(wrongSudoku));
		
	}
	
	@Test
	void changeNumbers() {
		int[][] sudoku1 = new int[][] {
			{1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 2, 0, 0, 0, 0, 5, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 7, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 3, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 8 },
		};
		sudoku.setMatrix(sudoku1);
		
		assertTrue(sudoku.getNumber(1,1) == 2, "Wrong number, (1,1) should be 2");
		sudoku.setNumber(1,1,4);
		assertTrue(sudoku.getNumber(1,1) == 4, "Wrong number, (1,1) should be 4");
		sudoku.clearNumber(1,1);
		assertTrue(sudoku.getNumber(1,1) == 0, "Wrong number, (1,1) should be 0");
		
	}
	
	
}