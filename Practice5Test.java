
public class Practice5Test {
	//changed this so make BSTree of type String
	protected BSTree<String> tree;


	public Practice5Test() {
		//changed this too bc of above reason
		tree = new BSTree<String>();
	}


	public boolean insertRemoveTest() {
		tree.insert("First");    // Insert a value into the tree
		if (tree.find("First")) {    // If that works, insert another value into the tree
			tree.insert("Second");
			if (tree.find("First") && tree.find("Second")) { // Both should be present now.
				tree.delete("First");  // Delete one of the values and try to retrieve it...
				if (tree.find("First") || ! tree.find("Second"))
					return false;
				tree.delete("Second"); // The tree should be empty now
				return true;
			}
		}
		return false;
	}


	public boolean insertMultipleTest() {
		// Insert copies of data into the tree. See whether the tree can keep track of the copies.
		tree.insert("copy");
		tree.insert("copy2");
		tree.insert("copy");
		if (tree.find("copy")) {
			tree.delete("copy");
			if (tree.find("copy")) {
				// Empty the tree before returning...
				tree.delete("copy2");
				tree.delete("copy");
				return true;
			}
		}
		return false;
	}


	public boolean treeInOrderTest() {
		// Checks whether the tree keeps data in the correct order
		String [] values = {"Turing", "Knuth", "Dijstra", "Hopper", "Lee", "Codd", "Lovelace", "Thompson", "Matsumoto", "Allen", "Wilson"};
		for (String value : values) {
			tree.insert(value);
		}
		// Here, we expect alphabetic order, specifically: 
		// Allen Codd Dijstra Hopper Knuth Lee Lovelace Matsumoto Thompson Turing Wilson
		if (tree.toStringInOrder().equals("Allen Codd Dijstra Hopper Knuth Lee Lovelace Matsumoto Thompson Turing Wilson"))
			return true;
		//added this to check if i did it right
		System.out.println("Order:     " + tree.toStringInOrder() + "|");
		System.out.println("Expecting: " + "Allen Codd Dijstra Hopper Knuth Lee Lovelace Matsumoto Thompson Turing Wilson|");
		return false;
	}
	
	
	public boolean treePreOrderTest() {
		// Checks whether the tree keeps data in the correct order
		if (tree.toStringPreOrder().equals("Turing Knuth Dijstra Codd Allen Hopper Lee Lovelace Thompson Matsumoto Wilson"))
				return true;
		System.out.println("Order:     " + tree.toStringPreOrder() + "|");
		System.out.println("Expecting: " + "Turing Knuth Dijstra Codd Allen Hopper Lee Lovelace Thompson Matsumoto Wilson|");
		return false;
	}
	
	public void runTests() {
		int grade = 0;
		
		// Test 1: Basic insert & delete
		try {
			if (insertRemoveTest()) {
				grade += 25;
				System.out.println("[25%] = passed basic insert / remove tests.");
			} else {
				throw new Exception("Fails.");
			}
		} catch (Exception e) {
			System.out.println("[   ] = failed basic insert / remove tests.");
		}
		
		// Test 2: inserting (and removing) multiple copies
		try {
			if (insertMultipleTest()) {
				grade += 25;
				System.out.println("[25%] = the tree can handle multiple copies of the same data.");
			} else {
				throw new Exception("Fails.");
			}
		} catch (Exception e) {
			System.out.println("[   ] = tree cannot handle multiple copies of the same data.");
		}
		// Test 3: check that the (in-order) ordering of the tree is correct
		try {
			if (treeInOrderTest()) {
				grade += 25;
				System.out.println("[25%] = the tree appears to store data correct in-order order.");
			} else {
				throw new Exception("Fails.");
			}
		} catch (Exception e) {
			//changed this bc of a typo
			System.out.println("[   ] = the tree does not appears to store data correct in-order order.");
		}
		
		// Test 4: check that the (pre-ordering) ordering of the tree is correct
		try {
			if (treePreOrderTest()) {
				grade += 25;
				System.out.println("[25%] = the tree appears to store data correct pre-order order.");
			} else {
				throw new Exception("Fails.");
			}
		} catch (Exception e) {
			System.out.println("[   ] = the tree does not appears to store data correct pre-order order.");
		}
		
		System.out.println("----------------------------------------");
		System.out.println("Starting point for this assignment: " + grade + "%.");
	}


	public static void main(String[] args) {
		Practice5Test test = new Practice5Test();
		test.runTests();
	}

}
