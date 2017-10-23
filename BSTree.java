/**
 * BSTree creates a Binary Search Tree.
 * @author Robert
 *
 * @param <T> - template for any object that extends Comparable
 */
public class BSTree<T extends Comparable<T>> {
	//object node for our data
	class BSTNode {
		//variables
		public T data;
		public BSTNode left;
		public BSTNode right;
		
		//constructor
		public BSTNode(T value) {
			data = value;
			left = null;
			right = null;
		}
	}
	
	//node that is the head of the tree
	private BSTNode root;
	
	/**
	 * helper method that finds a certain value in the tree
	 * @param value - value to find
	 * @return bool - whether the value is in the tree
	 */
	public boolean find(T value) {
		return find(value, root);
	}
	
	/**
	 * finds a certain value in the tree by recursively searching the tree
	 * @param value - value to find
	 * @param node - current node to check
	 * @return bool - whether the value is in the tree
	 */
	public boolean find(T value, BSTNode node) {
		//check if node is null
		if (node == null)
			return false;
		//check if we found it
		if(node.data.compareTo(value) == 0)
			return true;
		//check if the data is to the left
		else if (node.data.compareTo(value) > 0)
			return find(value, node.left);
		//check if the data is to the right
		else 
			return find(value, node.right);
	}
	
	/**
	 * helper method to insert a certain value into the tree
	 * @param value - value to insert
	 */
	public void insert(T value) {
		//change the root
		root = insert(value, root);
	}
	
	/**
	 * inserts a certain value into the tree
	 * @param value - value to insert
	 * @param node - current node
	 * @return BSTNode - returns the new current node
	 */
	public BSTNode insert(T value, BSTNode node) {
		//checks if node is null, meaning new root
		if (node == null)
			return new BSTNode(value);
		//checks if the value is less than the current node
		if(node.data.compareTo(value) > 0)
			node.left = insert(value, node.left);
		//assumes that the value is equal to or more than the current node
		else
			node.right = insert(value, node.right);
		//returns the current node
		return node;
	}
	
	/**
	 * helper method to delete a certain value in the tree
	 * @param value - value to delete
	 */
	public void delete(T value) {
		//change the root
		root = delete(value, root);
	}
	
	/**
	 * deletes a certain value in the tree
	 * @param value - value to delete
	 * @param node - current node
	 * @return BSTNode - returns the new current node
	 */
	public BSTNode delete(T value, BSTNode node) {
		//checks if node is null
		if (node == null)
			return null;
		//checks if the value is the current node
		if (node.data.compareTo(value) == 0) {
			//checks if left is null, puts right node into current spot
			if (node.left == null) {
				return node.right;
			//checks if right is null, put left node into current spot
			} else if (node.right == null) {
				return node.left;
			//assumes both left and right exists
			} else {
				//checks if there is no left in the right node, move node up
				if (node.right.left == null) {
					node.data = node.right.data;
					node.right = node.right.right;
				//assumes there is a left in the right node, move up nodes
				} else {
					node.data = removeSmallest(node.right);
				}
			}
		//checks if the value is to the left
		} else if (value.compareTo(node.data) > 0) {
			node.left = delete(value, node.left);
		//assumes the value is to the right
		} else {
			node.right = delete(value, node.right);
		}
		//returns current node
		return node;
	}
	
	/**
	 * removes smallest node but put move it up
	 * @param node - current node
	 * @return T - value that we move up
	 */
	public T removeSmallest(BSTNode node) {
		//checks if there is no left in the left node, move up right node of left node
		if (node.left.left == null) {
			T smallest = node.left.data;
			node.left = node.left.right;
			return smallest;
		//iterate through the left nodes
		} else {
			return removeSmallest(node.left);
		}
	}
	
	/**
	 * helper method to print the tree in order
	 * @return String - output
	 */
	public String toStringInOrder() {
		return toStringInOrder(root);
	}
	
	/**
	 * creates a toString of the tree in order
	 * @param node - current node
	 * @return String - output
	 */
	private String toStringInOrder(BSTNode node) {
		//checks if node is null, print nothing
		if (node == null)
			return "";
		//checks if node is a leaf, print just the data
		if (node.left == null && node.right == null)
			return node.data.toString();
		//checks if node only has a right, print the data then print the right's data
		if (node.left == null)
			return node.data.toString() + " " + toStringInOrder(node.right);
		//checks if node only has a left, print the left's data then the current data
		if (node.right == null)
			return toStringInOrder(node.left) + " " + node.data.toString();
		//print all the left data first then the current data then the right data
		return toStringInOrder(node.left) + " " + node.data.toString() + " " + toStringInOrder(node.right);
	}
	
	/**
	 * helper method to print the tree in the order of top to left to right
	 * @return String - output
	 */
	public String toStringPreOrder() {
		return toStringPreOrder(root);
	}
	
	/**
	 * creates a toString of the tree in the order of top to left to right
	 * @param node - current node
	 * @return String - output
	 */
	private String toStringPreOrder(BSTNode node) {
		//checks if node is null, print nothing
		if (node == null)
			return "";
		//checks if node is a leaf, print just the data
		if (node.left == null && node.right == null)
			return node.data.toString();
		//checks if node only has a right, print the data then the right data
		if (node.left == null)
			return node.data.toString() + " " + toStringPreOrder(node.right);
		//checks if node only has a left, print the data then the left data
		if (node.right == null)
			return node.data.toString() + " " + toStringPreOrder(node.left);
		//print the data then the left data then the right data
		return node.data.toString() + " " + toStringPreOrder(node.left) + " " + toStringPreOrder(node.right);
	}
}
