package cmsc420_p1;



import java.util.ArrayList;

//YOU SHOULD NOT MODIFY THIS FILE

import java.util.Scanner;
/**
* Command handler. This reads a single command line, processes the command (by
* invoking the appropriate method(s) on the data structure), and returns the
* result as a string.
*/

public class CommandHandler {


	
	BinarySearchTree<Integer> bst;
	
	public CommandHandler(BinarySearchTree<Integer> input_bst) {
		this.bst=input_bst;
		
	}

	
	/**
	 * Process a single command and return the string output. Each command begins
	 * with a command followed by a list of arguments. The arguments are separated
	 * by colons (":").
	 * 
	 * @param inputLine The input line with the command and parameters.
	 * @return A string summary of the command's execution/result.
	 */
	public String processCommand(String inputLine) {
		Scanner line = new Scanner(inputLine);
		line.useDelimiter(":"); // use ":" to separate arguments
		String output = new String(); // for storing summary output
		String cmd = (line.hasNext() ? line.next() : ""); // next command
		try {
			
			// -----------------------------------------------------
			// insert key
			// - append the given key to the binary search tree
			// -----------------------------------------------------
			if (cmd.compareTo("insert") == 0) {
				Integer key = line.nextInt();
				bst.insert(key);
			}
			// -----------------------------------------------------
			// print
			// - print the content of binary search tree
			// -----------------------------------------------------
			else if (cmd.compareTo("print") == 0) {
				output += bst.toString() + System.lineSeparator();
			}
			
			// -----------------------------------------------------
			// printkeybetween k1 k2
			// - calculate and print the intersection of current and target linked list
			// -----------------------------------------------------
			else if (cmd.compareTo("printkeybetween") == 0) {
				Integer k1 = line.nextInt();
				Integer k2 = line.nextInt();
				ArrayList<Integer> new_list = bst.find_keys_between_range(k1,k2);
				output += new_list.toString() + System.lineSeparator();
			}
			// -----------------------------------------------------
			// Unrecognized command
			// -----------------------------------------------------
			else {
				if (cmd.compareTo("") == 0)
					System.err.println("Error: Empty command line (Ignored)");
				else
					System.err.println("Error: Invalid command - \"" + cmd + "\" (Ignored)");
			}
			line.close();
		} catch (Exception | Error e) {
			output = "Unexpected error occurred while running your program: " + e.getMessage() + System.lineSeparator();
			System.err.println("Unexpected error occurred while running your program: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		return output; // return summary output
	}
}
