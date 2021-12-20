package cmsc420_f21;

import java.util.Random;

//-------------------------------------------------------------------------
//Add your code here. You may import whatever (standard) packages you like.
//You may also create additional files, if you like, but be sure to upload
//them as well for grading.
//-------------------------------------------------------------------------

public class Treap<Key extends Comparable<Key>> {

	// -------------------------------------------------------------------------
	// Here is the utility functions.
	// You should NOT change this. You also should NOT use any of the functions
	// in your own impelementation.
	// -------------------------------------------------------------------------

	private Random generator = new Random();
	private int rng_upperbound = 0;

	public void setRandomSeed(int seed) {
		generator = new Random(seed);
	}

	public void setRngUpperbound(int u) {
		rng_upperbound = u;
	}

	private int getNextPriority() {
		return generator.nextInt(rng_upperbound);
	}

	public void clear() {
		root = null;
	}

	// -------------------------------------------------------------------------
	// Here's the provided interface for the node class.
	// You should NOT delete anything here, but you can add any
	// additional methods and/or data members that you like.
	// -------------------------------------------------------------------------

	private class Node {
		Node left, right;
		Key key;
		int priority;

		Node(Key k) {
			this.left = null;
			this.right = null;
			this.key = k;
			this.priority = getNextPriority();
		}
	}

	private Node root;

	// -------------------------------------------------------------------------
	// Here are functions for sanity check of your treap. Do NOT modify this.
	// -------------------------------------------------------------------------
	private boolean keyCheckHelper(Node root) {
		if (root == null)
			return true;
		boolean check_left_tree = keyCheckHelper(root.left);
		boolean check_right_tree = keyCheckHelper(root.right);
		boolean check = true;
		if (root.left != null)
			check = check && (root.left.key.compareTo(root.key) <= 0);
		if (root.right != null)
			check = check && (root.right.key.compareTo(root.key) > 0);
		return check && check_left_tree && check_right_tree;
	}

	private boolean priorityCheckHelper(Node root) {
		if (root == null)
			return true;
		boolean check_left_tree = priorityCheckHelper(root.left);
		boolean check_right_tree = priorityCheckHelper(root.right);
		boolean check = true;
		if (root.left != null)
			check = check && (root.left.priority >= root.priority);
		if (root.right != null)
			check = check && (root.right.priority >= root.priority);
		return check && check_left_tree && check_right_tree;
	}

	public boolean isTreap() {
		if (root == null)
			return false;
		boolean keyCheck = keyCheckHelper(root);
		boolean priorityCheck = priorityCheckHelper(root);
		return keyCheck && priorityCheck;
	}

	// -------------------------------------------------------------------------
	// Here is the public interface. You should fill in the implementation.
	// You can also add any additional methods and/or data members that you like.
	// -------------------------------------------------------------------------

	// Constructor
	public Treap() {
		root = null;
	}

	// Insert key k to the tree.
	public void insert(Key k) {


		// Use helper function to track the descending nodes
		root= insertHelper(root, k);

	}

	private Node insertHelper(Node p, Key k) {
		if (p == null) {
			p = new Node(k);
		}
		if (p.key.compareTo(k) > 0) p.left = insertHelper(p.left, k);
		else if (p.key.compareTo(k) < 0) p.right = insertHelper(p.right, k); 
		return restructure(p);
	}


	// Find key k in the tree and return number of comparisons needed.
	// Return -1 if key is not found.
	public int find(Key k) {
		int counter = 0;
		return findHelper(root, k, counter);
	}

	private int findHelper(Node p, Key k, int x) {
		if (p == null) return -1;
		
		if (p.key.compareTo(k) == 0) {
			return x + 1;
		} 
		if (p.key.compareTo(k) > 0) {
			return findHelper(p.left, k, x + 1);
		}
		return findHelper(p.right, k, x + 1);

	}

	// METHODS PROVIDED IN LECTURE
	int getPriority(Node p) { return (p == null ? rng_upperbound : p.priority); }

	Node lowestPriority(Node p) { // lowest priority of p, p.left, p.right
		Node q = p;
		if (getPriority(p.left) < getPriority(q)) q = p.left;
		if (getPriority(p.right) < getPriority(q)) q = p.right;
		return q;
	}

	Node restructure(Node p) { // restore priority at p
		if (p == null) return p; // nothing to do
		Node q = lowestPriority(p); // get child to rotate
		if (q == p.left) p = rotateRight(p); // rotate as needed
		else if (q == p.right) p = rotateLeft(p);
		return p; // return updated subtree
	}

	Node rotateRight(Node p) // right single rotation
	{
		Node q = p.left;
		p.left = q.right; // swap inner child
		q.right = p; // bring q above p
		return q; // q replaces p
	}

	Node rotateLeft(Node p) // right single rotation
	{
		Node q = p.right;
		p.right = q.left; // swap inner child
		q.left = p; // bring q above p
		return q; // q replaces p
	}
}
