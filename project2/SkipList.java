package cmsc420_f21;

import java.util.Random;
import java.util.ArrayList;

//-------------------------------------------------------------------------
//Add your code here. You may import whatever (standard) packages you like.
//You may also create additional files, if you like, but be sure to upload
//them as well for grading.
//-------------------------------------------------------------------------

public class SkipList<Key extends Comparable<Key>> {
	// -------------------------------------------------------------------------
	// Here is the utility functions.
	// You should NOT change this. You also should NOT use any of the functions
	// in your own impelementation.
	// -------------------------------------------------------------------------
	
	private Random generator = new Random();
	
	public void setRandomSeed(int seed) {
		generator.setSeed(seed);
	}
	private int getRandomLevel(double p) {
		int level = 0;
		while (generator.nextDouble() <= p) {
			level += 1;
		}
		return level;
	}
	public void clear() {
		head = null;
	}
	
	// -------------------------------------------------------------------------
	// Here's the provided interface for the node class. 
	// You should NOT delete anything here, but you can add any 
	// additional methods and/or data members that you like. 
	// -------------------------------------------------------------------------
	
	private class Node {
		ArrayList<Node> next;
		Key key;
		int level;
		boolean is_head = false;
		Node(Key k, int level) {
			key = k;
			next = new ArrayList<Node>();
			for (int i = 0; i <= level; i++) 
				next.add(null);
			this.level = level;
		}
	}
	// -------------------------------------------------------------------------
	// Here is the public interface. You should not change the function signature.
	// But you can add any additional methods and/or data members that you like.
	// -------------------------------------------------------------------------
	private Node head;
	private int maxLevel = -1;
	
	// Constructor
	public SkipList() {
		head = null;
	}

	// Insert key k to the skip list. 
	// Invoke function getRandomLevel with input p to get the level of 
	// the newly inserted node.
	public void insert(Key k, double p) {
		int level = getRandomLevel(p);
		Node node = new Node(k, level);
		

		if (head == null) {
			// First, initialize the head of the skip list
			head = new Node(null, level);
			head.is_head = true;

			// Now, insert the first node
			// The height will always exactly be correct
			for (int i = 0; i <= level; i++)  {
				head.next.set(i, node);
			}
		}
		
		else {
			adjust(level);
			Node temp = head.next.get(level);
			insertHelper(temp, node, level);
		}
	}

	private void insertHelper(Node temp, Node node, int level) {
		


	}

	// Helper function for adjusting the level of skip list
	private void adjust(int level) {
		if (level > maxLevel) {
			int rem = level - maxLevel;
			for (int i = 0; i < rem; i++) {
				head.next.add(null);
			}
		}
	}




	// Find key k in the tree and return number of comparisons needed.
	// Return -1 if key is not found.
	public int find(Key k) {
		int level = maxLevel;
		while (level >= 0) 
	}



	// Print your skiplist content.
	// The format is <node1.key>,<node1.level>|<node2.key>,<node2.level>|....|<final_node.key>,<final_node.level>
	public String print() {
		return "";
	}
}
