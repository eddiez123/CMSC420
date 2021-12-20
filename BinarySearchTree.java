package cmsc420_p1;
import java.util.ArrayList;
import java.util.Collections;

//-------------------------------------------------------------------------
//Add your code here. You may import whatever (standard) packages you like.
//You may also create additional files, if you like, but be sure to upload
//them as well for grading.
//-------------------------------------------------------------------------


public class BinarySearchTree <Key extends Comparable<Key>> {
	
	// -------------------------------------------------------------------------
	// Here is the public interface. You should not change this, but you
	// can add any additional methods and/or data members that you like.
	// -------------------------------------------------------------------------


    private class Node {
        Key key;           // key of the Node
        Node left, right;  // left and right subtrees

        public Node(Key key) {
            this.key = key;
            this.left=null;
            this.right=null;
            
        }
        // DO NOT MODFIY THIS FUNCTION
     	public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(this.key.toString());
            buffer.append('\n');
            
            if(this.right==null && this.left==null) {
            	return;
            }
            
            if (this.right!=null) {
            	Node next=this.right;
            	next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            }
            else {
            	buffer.append(childrenPrefix + "├── ");
            	buffer.append("");
            	buffer.append('\n');
            }
            
            
            if (this.left!=null) {
            	Node next=this.left;
            	next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            	
            }   
            else {
            	buffer.append(childrenPrefix + "└──");
            	buffer.append("");
            	buffer.append('\n');
            }
    	}
    }
    
	Node root;             // root of BST
    
	public BinarySearchTree() {
		root=null;
	} 						// Constructor

	
	// DO NOT MODFIY THIS FUNCTION
	public void insert(Key key) {
 
        root = put(root, key);    
    }
	
	// DO NOT MODFIY THIS FUNCTION
    private Node put(Node x, Key key) {
        if (x == null) return new Node(key);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key);
        else if (cmp > 0) x.right = put(x.right, key);
 
        return x;
    } //Note that in BST we don't allow duplicate keys. 
    
    // DO NOT MODFIY THIS FUNCTION
 	public String toString() {
 		if (root == null) return "Empty BST";
 		
        StringBuilder buffer = new StringBuilder(100);
        root.print(buffer, "", "");
      
 
 	   return buffer.toString();
 	} // Print the content of the current BST
 	
     // Helper function for looping through the BST
    public ArrayList<Key> find_keys_between_range_helper(Key k1, Key k2, Node temp, ArrayList<Key> array) {
        if (temp.left != null) {
            find_keys_between_range_helper(k1, k2, temp.left, array);
        }
        if (temp.right != null) {
            find_keys_between_range_helper(k1, k2, temp.right, array);
        }
        if (temp.key.compareTo(k1) >= 0 && temp.key.compareTo(k2) <= 0) array.add(temp.key);
        return array;

    }
    
 	
 	public ArrayList<Key> find_keys_between_range(Key k1, Key k2) { 

		ArrayList<Key> array = new ArrayList<Key>();

        Node head;
        if (root != null) head = root;
        else return array;

        ArrayList<Key> retArray = find_keys_between_range_helper(k1, k2, head, array);
        Collections.sort(retArray);
        return retArray;


// 		Collections.sort(Put the name of the ArrayList you are going to return here);
	} // Note: You need to return ArrayList sorted in ascending order. You can use the commented command (Collections.sort()) or any other method to sort your resulting ArrayList. 
 	 
 	
}



