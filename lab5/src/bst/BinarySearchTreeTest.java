package bst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;
import java.util.Comparator;

class BinarySearchTreeTest {
	
	//First type
	private BinarySearchTree<Integer> intBinaryTree;
	private BinarySearchTree<String> stringBinaryTree;
	
	
	//Second type
	private BinarySearchTree<Integer> intBinaryTreeC;
	private BinarySearchTree<String> stringBinaryTreeC;
	

	@BeforeEach
	void setUp() throws Exception {
		intBinaryTree = new BinarySearchTree<Integer>();
		stringBinaryTree = new BinarySearchTree<String>();
		
		intBinaryTreeC = new BinarySearchTree<Integer>((i1, i2) -> i1.compareTo(i2));
		stringBinaryTreeC = new BinarySearchTree<String>((p1, p2) -> p1.compareTo(p2));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		intBinaryTree = null;
		stringBinaryTree = null;
		
		intBinaryTreeC = null;
		stringBinaryTreeC = null;
		
	}

	@Test
	void testHeight() {
		assertTrue(intBinaryTree.add(2));
		assertTrue(intBinaryTree.add(-7));
		assertTrue(intBinaryTree.add(42312));
		assertTrue(intBinaryTree.add(9));
		assertTrue(intBinaryTree.add(3));
		assertTrue(intBinaryTree.height() == 4);
		
		assertTrue(stringBinaryTree.add("Balin"));
		assertTrue(stringBinaryTree.add("Falin"));
		assertTrue(stringBinaryTree.add("Aalin"));
		assertTrue(stringBinaryTree.height() == 2);
		
		assertTrue(stringBinaryTreeC.add("Balin"));
		assertTrue(stringBinaryTreeC.add("Falin"));
		assertTrue(stringBinaryTreeC.add("Aalin"));
		assertTrue(stringBinaryTreeC.height() == 2);
		
	}
	
	
	@Test
	void testAdd() {
		assertTrue(intBinaryTree.add(3));
		assertTrue(intBinaryTree.add(2));
		assertTrue(intBinaryTree.add(1));
		assertTrue(intBinaryTree.add(9));
		assertTrue(intBinaryTree.add(4));
		
		assertFalse(intBinaryTree.add(3));
		assertFalse(intBinaryTree.add(2));
		assertFalse(intBinaryTree.add(1));
		assertFalse(intBinaryTree.add(9));
		assertFalse(intBinaryTree.add(4));
		
		assertTrue(intBinaryTree.size() == 5);
		
		assertTrue(intBinaryTreeC.add(3));
		assertTrue(intBinaryTreeC.add(2));
		assertTrue(intBinaryTreeC.add(1));
		assertTrue(intBinaryTreeC.add(9));
		assertFalse(intBinaryTreeC.add(2), "Should be false");
	}
	
	@Test
	void testClear() {
		assertTrue(intBinaryTree.add(32));
		assertTrue(intBinaryTree.add(5));
		assertTrue(intBinaryTree.add(-2));
		intBinaryTree.clear();
		testEmpty();
	}
	
	@Test
	void testEmpty() {
		assertTrue(intBinaryTree.root == null);
		assertTrue(intBinaryTree.height() == 0);
		assertTrue(intBinaryTree.size == 0);
	}

}


