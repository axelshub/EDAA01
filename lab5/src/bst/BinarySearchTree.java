package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
  
  public static void main(String[] args) {
	  BinarySearchTree<Integer> testBinaryTree = new BinarySearchTree<Integer>();
	  BSTVisualizer v = new BSTVisualizer("TEST", 400, 400);
	  
	  //Skev form
	  testBinaryTree.add(2);
	  testBinaryTree.add(555);
	  testBinaryTree.add(5);
	  testBinaryTree.add(-2);
	  testBinaryTree.add(7);
	  testBinaryTree.add(1);
	  testBinaryTree.add(2);
	  testBinaryTree.add(3);
	  testBinaryTree.add(100);
	  testBinaryTree.add(99);
	  testBinaryTree.add(98);
	 
	  
	  //Optimal form
//	  testBinaryTree.add(10);
//	  testBinaryTree.add(3);
//	  testBinaryTree.add(15);
//	  testBinaryTree.add(2);
//	  testBinaryTree.add(4);
//	  testBinaryTree.add(14);
//	  testBinaryTree.add(16);
	  
	  testBinaryTree.rebuild();
	  v.drawTree(testBinaryTree);
  }
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	
	public boolean add(E x) {
		if (root == null) { //If the tree is empty
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(root, x);
		}
	}
	
	private boolean add(BinaryNode<E> n, E x) {
		if (n.element == x) { //Specified element found
			return false;
		} else if (comparator.compare(x, n.element) < 0) {
			if(n.left == null) { //Specified element doesn't exist in tree
				n.left = new BinaryNode(x);
				size++;	
			} else {
				return add(n.left, x);
			}
		} else {
			if(n.right == null) { //Specified element doesn't exist in tree
				n.right = new BinaryNode(x);
				size++;
			} else {
				return add(n.right, x);
			}
		}
		return true;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else {
			int leftHeight = height(n.left);
			int rightHeight = height(n.right);
			
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		clear();
		buildTree(sorted, 0, sorted.size()-1);
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) { //Base case: no more nodes to add on this side
			return null;
		} else {
			//Creates a new node based on the middle of given index from the list
			int mid = first + (last - first)/2;
			BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
			add(node.element);
			
			//Left subtree
			node.left = buildTree(sorted, first, mid - 1);

			//Right subtree
			node.right = buildTree(sorted, mid + 1, last);	
			return node;
			
		}
	}
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}


