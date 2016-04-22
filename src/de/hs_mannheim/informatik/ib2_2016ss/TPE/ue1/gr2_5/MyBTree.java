package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue1.gr2_5;

import static gdi.MakeItSimple.*;
import java.util.*;

public class MyBTree implements BTree {

	protected int order;
	protected Node root = null;

	/**
	 * @param order of the BTree
	 */
	public MyBTree(int order) {
		//Order must be larger than 0
		if(order >= 1) {
			this.order = order;
		}
		else {
			throw new GDIException("Order must be greater than 0");
		}
	}

	/**
	 * @description: Returns the root node
	 * @return node root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @description: Inserts an Integer o in the BTree
	 * @returns: true if o was inserted, false if o already exists
	 */
	public boolean insert(Integer insertValue) {
		if(!contains(insertValue)) {
			if(!isEmpty()) {
				Node[] pathToBeInserted = searchNodePath(insertValue);
				//If path is exists, we can insert
				if(pathToBeInserted.length > 0) {
					//Get node to be inserted
					Node nodeToBeInserted = pathToBeInserted[pathToBeInserted.length - 1];
					//Case 1: Node has space -> Insert
					if(nodeToBeInserted.hasSpace()) {
						nodeToBeInserted.insertInNodeWithSpace(insertValue);
					}
					//Case 2: Node has no space -> 
					else {
						splitNode(insertValue, pathToBeInserted, pathToBeInserted.length - 1, null, null);
					}
				}
			}
			else {
				//Create root node and insert
				root = new Node(order);
				root.setValue(insertValue, 0);
			}
			return true;
		}
		return false;
	}

	/**
	 * @description: Searches for the node, where value o will be inserted
	 * @param o: Integer to be searched
	 * @return: returns a node array containing the search path with all nodes
	 */
	private Node[] searchNodePath(Integer insertValue) {
		Node[] nodeSearchList = new Node[height()];

		Node searchNode = root;
		int searchListPositon = 0;
		//Save first node
		nodeSearchList[searchListPositon] = searchNode;

		//Find leaf where you should insert the value
		while(!searchNode.isLeaf()) {
			int insertPosition = searchNode.findChildPosition(insertValue);
			searchNode = searchNode.getNode(insertPosition);
			searchListPositon++;
			//Save all nodes on this path
			nodeSearchList[searchListPositon] = searchNode;
		}

		return nodeSearchList;
	}

	/**
	 * @description: splits node in three parts; a left part, the mid value, and the right part
	 * @return 
	 */
	private void splitNode(Integer insertValue, Node[] pathToBeInserted, int pathPosition, Node leftPart, Node rightPart) {
		/*Step 1: Find the insertNode and decide if we need a new root */
		Node insertNode;
		boolean needNewRoot = false;

		if(pathPosition >= 0) {
			insertNode = pathToBeInserted[pathPosition];
		}
		else {
			needNewRoot = true;
			insertNode = new Node(order);
			root = insertNode;
		}

		/* Step 2: Make the insertion and recursion */
		//Step 2: Case 1: Node has space and we don't need a new root
		if(insertNode.hasSpace() && !needNewRoot) 
		{
			//Step 2: Case 1: Find insert position
			int insertPosition = 0;
			while(insertPosition < (2 * order) && insertNode.values[insertPosition] != null && insertNode.values[insertPosition] < insertValue) {
				insertPosition++;
			}
			//Step 2: Case 1: Shift values and nodes to the right
			insertNode.shiftValuesToRightAt(insertPosition);
			insertNode.shiftNodesToRightAt(insertPosition + 1);

			//Step 2: Case 1: Insert value and right part of node
			insertNode.setValue(insertValue, insertPosition);
			insertNode.setNode(rightPart, insertPosition + 1);
		}
		//Step 2: Case 2: We need a new root node
		else if(needNewRoot) 
		{
			insertNode.setValue(insertValue, 0);
			insertNode.setNode(leftPart, 0);
			insertNode.setNode(rightPart, 1);
		}
		//Step 2: Case 3: Node has no space and we don't need a new root
		else 
		{
			//Step 2: Case 3: Find insert position
			int insertPosition = 0;
			while(insertPosition < (2 * order) && insertNode.values[insertPosition] < insertValue) {
				insertPosition++;
			}

			//Step 2: Case 3: Shift values and nodes to the right
			insertNode.shiftValuesToRightAt(insertPosition);
			insertNode.shiftNodesToRightAt(insertPosition + 1);

			//Step 2: Case 3: Insert value and right part of node
			insertNode.setValue(insertValue, insertPosition);
			insertNode.setNode(rightPart, insertPosition + 1);

			//Step 2: Case 3: Save mid value and delete in old node
			Integer midValue = insertNode.values[order];
			insertNode.setValue(null, order);

			//Step 2: Case 3: Copy right part of node and delete in old node
			Node recursionRightPart = insertNode.copyStartingAt(order + 1);
			insertNode.removeStartingAt(order + 1);

			//Step 2: Case 3: Open recursion
			splitNode(midValue, pathToBeInserted, pathPosition - 1, insertNode, recursionRightPart);
		}
	}

	/**
	 * @param: filename: name of file
	 * @return: true if file exists and is readable, else false
	 */
	public boolean insert(String filename) {
		//If file is available for us
		if(isFilePresent(filename) && isFileReadable(filename)) {
			Object file = openInputFile(filename);
			//Insert all ints by reading int by int
			while(!isEndOfInputFile(file)) {
				insert((Integer) readInt(file));
			}
			closeInputFile(file);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @param: o: Integer to be searched
	 * @returns: if o exists return true, else false
	 */
	public boolean contains(Integer o) {
		if(!isEmpty()) {
			return containsRecursive(o, root);
		}
		return false;
	}

	/**
	 * @param o: Integer to be searched
	 * @param node: Current node to be searched
	 * @return if there is no childNode true or false, else continue searching recursive in next childNode
	 */
	private boolean containsRecursive(Integer searchValue, Node node) {
		int i = 0;
		//Go through node
		while(node.getValue(i) != null || node.getNode(i) != null) {
			//If value is larger, go to the right
			if(node.getValue(i) != null && node.getValue(i) < searchValue) {
				i++;
			}
			//If path to the child node was found, open recursion
			else if(((node.getValue(i) != null && node.getValue(i) > searchValue)) || (node.getValue(i) == null)) {
				if(node.getNode(i) != null) {
					return containsRecursive(searchValue, node.getNode(i));
				}
				//Returns false if there is no path
				return false;
			}
			else {
				return true;
			}
		}
		//Default return false
		return false;
	}

	/**
	 * @description: Counts the values that are in the btree
	 */
	public int size() {
		Queue<Node> queue = new LinkedList<Node>();
		int valueCounter = 0;
		if(!isEmpty()){
			//Traverse the tree through level order and count all values
			queue.add(root);
			while(!queue.isEmpty()){
				Node currentNode = (Node) queue.poll();
				int i = 0;
				//Adding child nodes to queue
				while(i < ((2 * order) + 1) 
						&& currentNode.getNode(i) != null) {
					queue.add(currentNode.getNode(i));
					i++;
				}
				//Counting of values
				for(Integer a : currentNode.values){
					if(a != null){
						valueCounter++;
					}
				}
			}
		}
		return valueCounter;
	}

	/**
	 * @description: figures out the height of the tree
	 * @return: height of tree
	 */
	public int height() {
		int i = 0;
		if(!isEmpty()) {
			Node save = root;
			boolean pathEnd = false;
			//Go the left part until end and count every childNode
			while(save.getValue(0) != null && !pathEnd) {
				i++;
				if(save.getNode(0) != null) {
					save = save.getNode(0);
				}
				else {
					pathEnd = true;
				}
			}
		}
		return i;
	}

	/**
	 * @description: uses another method getMaxRecursive() and passes the root
	 * @return: if tree is empty null, else Integer
	 */
	public Integer getMax() {
		if(!isEmpty()) {
			return getMaxRecursive(root);
		}
		return null;
	}

	/**
	 * @description: gets the last element or last childNode
	 * @param node: current searching node
	 * @return if node is empty null, else Integer
	 */
	private Integer getMaxRecursive(Node node) {
		int counter = 0;
		boolean elementExists = false;
		//Find last element
		while(node.getValue(counter) != null) {
			elementExists = true;
			counter++;
		}
		//If node is not empty
		if(elementExists) {
			//If there is a child node -> recursion
			if(node.getNode(counter) != null) {
				return getMaxRecursive(node.getNode(counter));
			}
			//If there is no child node -> return last Integer
			else {
				return node.getValue(counter - 1);
			}		
		}
		//Default return null
		return null;
	}

	/**
	 * @description: uses another method getMinRecursive() and passes the root
	 * @return: if tree is empty null, else Integer
	 */
	public Integer getMin() {
		if(!isEmpty()) {
			return getMinRecursive(root);
		}
		return null;
	}

	/**
	 * @description: gets the first element or first childNode
	 * @param node: current searching node
	 * @return if node is empty null, else Integer
	 */
	public Integer getMinRecursive(Node node) {
		//Look at the first value
		if(node.getValue(0) != null) {
			//Searches for available first childNode
			if(node.getNode(0) != null) {
				return getMinRecursive(node.getNode(0));
			}
			//If there is no first childNode
			else {
				return node.getValue(0);
			}
		}
		//Default return null
		return null;
	}

	/**
	 * @description: is the tree empty
	 * @return true if the tree is empty, else false
	 */
	public boolean isEmpty() {
		return root == null || root.getValue(0) == null;
	}

	public void addAll(BTree otherTree) {
        Queue<Node> queue = new LinkedList<Node>();
    	//Initialize queue by adding root
        queue.add(((MyBTree) otherTree).root);
        //Goes through the other Btree in level order and inserts all values
        //For each queue
        while(!queue.isEmpty()){
            Node currentNode = (Node) queue.poll();
            int i = 0;
            //Finds all nodes and adds them to the queue
            while(i < ((2 * order) + 1) 
                    && currentNode.getNode(i) != null) {
                queue.add(currentNode.getNode(i));
                i++;
            }
            //Insert all values from current node
            for(Integer a : currentNode.values){
                if(a != null){
                    insert(a);
                }
            }
        }
	}
	
	/**
	 * @description: prints the btree with inorder
	 */
	public void printInorder() {
		if(!isEmpty()) {
			printInorderRecursive(root);
		}
	}
	
	/**
	 * @description: recursive function of printInorder()
	 */
	private void printInorderRecursive(Node currentNode) {
		//Open recursion with first node
		if(!currentNode.isLeaf()) {
			printInorderRecursive(currentNode.getNode(0));
		}
		
		//Print the node
		currentNode.printNode();
		
		//Open all other recursion for the nodes
		int position = 1;
		while(position < ((2 * order) + 2) && currentNode.getNode(position) != null) {
			printInorderRecursive(currentNode.getNode(position));
			position++;
		}
	}

	/**
	 * @description: prints the btree with postorder
	 */
	public void printPostorder() {
		if(!isEmpty()) {
			printPostorderRecursive(root);
		}
	}
	
	/**
	 * @description: recursive function of printPostorder()
	 */
	private void printPostorderRecursive(Node currentNode) {
		//Open recursion with first node
		if(!currentNode.isLeaf()) {
			printInorderRecursive(currentNode.getNode(0));
		}
		
		//Open recursion for all other nodes
		int position = 1;
		while(position < ((2 * order) + 2) && currentNode.getNode(position) != null) {
			printInorderRecursive(currentNode.getNode(position));
			position++;
		}
		
		//Print the node
		currentNode.printNode();
	}

	/**
	 * @description: prints the btree with preorder
	 */
	public void printPreorder() {
		if(!isEmpty()) {
			printPreorderRecursive(root);
		}
	}
	
	/**
	 * @description: recursive function of printPreorder()
	 */
	private void printPreorderRecursive(Node currentNode) {
		//At first print the node
		currentNode.printNode();
		
		//Open recursion with first node if it is not a leaf
		if(!currentNode.isLeaf()) {
			printInorderRecursive(currentNode.getNode(0));
		}
		
		//Open recursion for all other nodes
		int position = 1;
		while(position < ((2 * order) + 2) && currentNode.getNode(position) != null) {
			printInorderRecursive(currentNode.getNode(position));
			position++;
		}
	}

	/**
	 * @description: prints the btree with level order
	 */
	public void printLevelorder() {
		Queue<Node> queue = new LinkedList<Node>();
		//Initialize queue by adding root
		queue.add(root);
		//For each node
		while(!queue.isEmpty()){
			Node currentNode = (Node) queue.poll();
			int i = 0;
			//Find all nodes and save to queue
			while(i < ((2 * order) + 1) && currentNode.getNode(i) != null) {
				queue.add(currentNode.getNode(i));
				i++;
			}
			//Print currentNode
			currentNode.printNode();
		}
	}

	/**
	 * @description: Creates a deep copy of the b-tree, uses recursion
	 * @returns: BTree
	 */
	public BTree clone() {
		MyBTree cloneTree = new MyBTree(order);
		//Clone only if tree is not empty
		if(!isEmpty()) {
			//Create new root node by cloning the old root
			cloneTree.root = cloneRecursive(this.root);
		}
		return cloneTree;
	}

	/**
	 * @description: copies a node and it's child nodes recursively
	 * @param oldNode: current copied node
	 * @return copied node
	 */
	public Node cloneRecursive(Node oldNode) {
		Node copyNode = new Node(order);
		//Copy all values and open recursion for each childNode
		int counter = 0;
		while(oldNode.getValue(counter) != null) {
			//Set value at position counter
			copyNode.setValue(oldNode.getValue(counter), counter);
			//Copy this node recursively
			if(oldNode.getNode(counter) != null) {
				copyNode.setNode(cloneRecursive(oldNode.getNode(counter)), counter);
			}
			counter++;
		}
		if(oldNode.getNode(counter) != null) {
			//Copies the last node recursively
			if(oldNode.getNode(counter) != null) {
				copyNode.setNode(cloneRecursive(oldNode.getNode(counter)), counter);
			}
		}
		return copyNode;
	}

}
