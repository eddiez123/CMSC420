package cmsc420_f21;


// YOU SHOULD NOT MODIFY THIS FILE

import java.util.Scanner;
/**
 * Command handler. This reads a single command line, processes the command (by
 * invoking the appropriate method(s) on the data structure), and returns the
 * result as a string.
 */

public class CommandHandler {

	Treap<Integer> treap = new Treap<Integer>();
	SkipList<Integer> skiplist = new SkipList<Integer>();
	/**
	 * Process a single command and return the string output. Each command begins
	 * with a command followed by a list of arguments. The arguments are separated
	 * by colons (":").
	 * 
	 * @param inputLine The input line with the command and parameters.
	 * @return A string summary of the command's execution/result.
	 */
	public String processSkipList(Scanner line) {
		String cmd = (line.hasNext() ? line.next() : ""); // next command
		String output = new String();
		
		try {
			if (cmd.compareTo("insert") == 0) {
				Integer key = line.nextInt();
				Double prob = line.nextDouble();
				skiplist.insert(key, prob);
			}
			else if (cmd.compareTo("find") == 0) {
				Integer key = line.nextInt();
				output += String.valueOf(skiplist.find(key)) + System.lineSeparator();
			}
			else if (cmd.compareTo("print") == 0) {
				output += skiplist.print() + System.lineSeparator();
			}
			else if (cmd.compareTo("clear") == 0) {
				skiplist.clear();
			}
			else if (cmd.compareTo("seed") == 0) {
				Integer seed = line.nextInt();
				skiplist.setRandomSeed(seed);
			}
			else {
				if (cmd.compareTo("") == 0)
					System.err.println("Error: Empty command line (Ignored)");
				else
					System.err.println("Error: Invalid command - \"" + cmd + "\" (Ignored)");
			}
			
		} catch (Exception | Error e) {
			output = "Unexpected error occurred while running your program: " + e.getMessage() + System.lineSeparator();
			System.err.println("Unexpected error occurred while running your program: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		
		return output;
	}
	
	public String processTreap(Scanner line) {
		String cmd = (line.hasNext() ? line.next() : ""); // next command
		String output = new String();
		// -----------------------------------------------------
		// INSERT key
		// - insert the given key to treap
		// -----------------------------------------------------
		try {
			if (cmd.compareTo("insert") == 0) {
				Integer key = line.nextInt();
				treap.insert(key);
				
			}
			else if (cmd.compareTo("find") == 0) {
				Integer key = line.nextInt();
				output += String.valueOf(treap.find(key)) + System.lineSeparator();
			}
			else if (cmd.compareTo("check") == 0) {
				boolean is_treap = treap.isTreap();
				if (is_treap)
					output += "treap sanity check succeeded" + System.lineSeparator();
				else
					output += "treap sanity check failed" + System.lineSeparator();
			}
			else if (cmd.compareTo("clear") == 0) {
				treap.clear();
			}
			else if (cmd.compareTo("seed") == 0) {
				Integer seed = line.nextInt();
				treap.setRandomSeed(seed);
			}
			else if (cmd.compareTo("bound") == 0) {
				Integer bound = line.nextInt();
				treap.setRngUpperbound(bound);
			}
			else {
				if (cmd.compareTo("") == 0)
					System.err.println("Error: Empty command line (Ignored)");
				else
					System.err.println("Error: Invalid command - \"" + cmd + "\" (Ignored)");
			}
			
		} catch (Exception | Error e) {
			output = "Unexpected error occurred while running your program: " + e.getMessage() + System.lineSeparator();
			System.err.println("Unexpected error occurred while running your program: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		
		return output;
	}
	public String processCommand(String inputLine) {
		Scanner line = new Scanner(inputLine);
		line.useDelimiter(":"); // use ":" to separate arguments
		String output = new String(); // for storing summary output
		String ds = (line.hasNext() ? line.next() : ""); // next command
		
		try {
			// Determine which data structue to operate on
			if (ds.compareTo("treap") == 0) {
				output += processTreap(line);
			}
			else if (ds.compareTo("skiplist") == 0) {
				output += processSkipList(line);
			}
			else {
				if (ds.compareTo("") == 0)
					System.err.println("Error: Empty data structure line (Ignored)");
				else
					System.err.println("Error: Invalid data structure - \"" + ds + "\" (Ignored)");
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
