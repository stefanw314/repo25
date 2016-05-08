package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a1.gr2_5;

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
	 * @description: Inserts an Comparable o in the BTree
	 * @returns: true if o was inserted, false if o already exists
	 */
	public boolean insert(Comparable insertValue) {
		if(!contains(insertValue)) {
			if(!isEmpty()) {
				Stack<SearchPathObject> pathToBeInserted = searchNodePath(insertValue, true);
				//If path is exists, we can insert
				if(!pathToBeInserted.empty()) {
					//Get node to be inserted
					Node nodeToBeInserted = pathToBeInserted.peek().node;
					//Case 1: Node has space -> Insert
					if(nodeToBeInserted.hasSpace()) {
						nodeToBeInserted.insertInNodeWithSpace(insertValue);
					}
					//Case 2: Node has no space -> 
					else {
						splitNode(insertValue, pathToBeInserted, null, null);
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

	public class SearchPathObject {
		public SearchPathObject(Node node, int childIndex) {
			this.node = node;
			this.childIndex = childIndex;
		}
		
		public Node node;
		public int childIndex;
	};
	
	/**
	 * @description: Searches for the node, where value o will be inserted
	 * @param o: Comparable to be searched
	 * @param: toLeaf: decides if the algorithm should search until leaf or not
	 * @return: returns a node array containing the search path with all nodes
	 */
	private Stack<SearchPathObject> searchNodePath(Comparable insertValue, boolean toLeaf) {
		Stack<SearchPathObject> nodeSearchList = new Stack<SearchPathObject>();

		Node searchNode = root;
		//Find leaf where you should insert the value		
		do {
			int insertPosition = searchNode.findChildPosition(insertValue);
			nodeSearchList.push(new SearchPathObject(searchNode, insertPosition));
			
			//If the algorithm should not go down to a leaf, check if the value is in the current node
			if(!toLeaf && searchNode.containsValue(insertValue)) {
				searchNode = null;
			}
			//The algorithm should go down to end
			else {
				searchNode = searchNode.getChildNode(insertPosition);
			}
		}
		while(searchNode != null);

		return nodeSearchList;
	}

	/**
	 * @description: splits node in three parts; a left part, the mid value, and the right part
	 * @return 
	 */
	private void splitNode(Comparable insertValue, Stack<SearchPathObject> pathToBeInserted, Node leftPart, Node rightPart) {
		/*Step 1: Find the insertNode and decide if we need a new root */
		Node insertNode;
		boolean needNewRoot = false;

		if(!pathToBeInserted.empty()) {
			insertNode = pathToBeInserted.pop().node;
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
			while(insertPosition < (2 * order) && insertNode.values[insertPosition] != null && insertNode.values[insertPosition].compareTo(insertValue) == -1) {
				insertPosition++;
			}
			//Step 2: Case 1: Shift values and nodes to the right
			insertNode.shiftValuesToRightAt(insertPosition);
			insertNode.shiftNodesToRightAt(insertPosition + 1);

			//Step 2: Case 1: Insert value and right part of node
			insertNode.setValue(insertValue, insertPosition);
			insertNode.setChildNode(rightPart, insertPosition + 1);
		}
		//Step 2: Case 2: We need a new root node
		else if(needNewRoot) 
		{
			insertNode.setValue(insertValue, 0);
			insertNode.setChildNode(leftPart, 0);
			insertNode.setChildNode(rightPart, 1);
		}
		//Step 2: Case 3: Node has no space and we don't need a new root
		else 
		{
			//Step 2: Case 3: Find insert position
			int insertPosition = 0;
			while(insertPosition < (2 * order) && insertNode.values[insertPosition].compareTo(insertValue) == -1) {
				insertPosition++;
			}

			//Step 2: Case 3: Shift values and nodes to the right
			insertNode.shiftValuesToRightAt(insertPosition);
			insertNode.shiftNodesToRightAt(insertPosition + 1);

			//Step 2: Case 3: Insert value and right part of node
			insertNode.setValue(insertValue, insertPosition);
			insertNode.setChildNode(rightPart, insertPosition + 1);

			//Step 2: Case 3: Save mid value and delete in old node
			Comparable midValue = insertNode.values[order];
			insertNode.setValue(null, order);

			//Step 2: Case 3: Copy right part of node and delete in old node
			Node recursionRightPart = insertNode.copyStartingAt(order + 1);
			insertNode.removeStartingAt(order + 1);

			//Step 2: Case 3: Open recursion
			splitNode(midValue, pathToBeInserted, insertNode, recursionRightPart);
		}
	}

	/**
	 * @description: Deletes a value from the tree
	 * @param deleteValue
	 */
	public void delete(Comparable deleteValue) {
		if(contains(deleteValue)) {
			Stack<SearchPathObject> searchPath = searchNodePath(deleteValue, false);
			//Look at the current node
			Node currentDeleteNode = searchPath.peek().node;
			
			if(currentDeleteNode.isLeaf()) {
				//Delete value in the leaf
				deleteRecursive(deleteValue, searchPath);
			}
			else {
				//Find value to replace with the delete value
				Comparable nextElement = currentDeleteNode.getNextBestElement(deleteValue);
				//Replace it
				currentDeleteNode.replaceValue(deleteValue, nextElement);
				//Delete the replaced value in the leaf
				deleteRecursive(nextElement, searchNodePath(nextElement, true));
			}
		}
	}

	
	/**
	 * @description: Deletes a value recursively and calls the recursion for the parent if necessary
	 * @param deleteValue
	 * @param searchPath
	 */
	private void deleteRecursive(Comparable deleteValue, Stack<SearchPathObject> searchPath) {
		Node currentDeleteNode = searchPath.pop().node;
		
		//If it is a recursion, the deleteValue can be null
		if(deleteValue != null) {
			currentDeleteNode.deleteValueFromLeaf(deleteValue);
		}
		
		//If the criteria for the Btree are violated
		if(currentDeleteNode.valuesSize() < order && root != currentDeleteNode) {
			//Decide to take the left or the right node for balancing / merging
			
			boolean takeLeft;
			Node otherNode; 
			
			if(searchPath.peek().childIndex > 0) {
				otherNode = searchPath.peek().node.childNodes[searchPath.peek().childIndex - 1];
				takeLeft = true;

			}
			else {
				otherNode = searchPath.peek().node.childNodes[searchPath.peek().childIndex + 1];
				takeLeft = false;
			}
			
			int valuesSize = otherNode.valuesSize();
			
			Node parentNode = searchPath.peek().node;
			
			//If in the other node are less than order values => merge
			if(valuesSize <= order) {
				if(takeLeft) {
					mergeNodes(otherNode, currentDeleteNode, searchPath, true);
				}
				else {
					mergeNodes(currentDeleteNode, otherNode, searchPath, false);
				}
			}
			//Else just balance
			else {
				if(takeLeft) {
					//Save last element and last node from left node and delete it
					Comparable lastValueLeftNode = otherNode.values[otherNode.getLastValueIndex()];
					otherNode.setValue(null, otherNode.getLastValueIndex());
					
					Node lastNodeLeftNode = null;
					if(otherNode.getLastNodeIndex() > 0 && otherNode.childNodes[otherNode.getLastNodeIndex()] != null) {
						lastNodeLeftNode = otherNode.childNodes[otherNode.getLastNodeIndex()];
						otherNode.setChildNode(null, otherNode.getLastNodeIndex());
					}
					
					//Save element from parent node and delete it
					Comparable valueParentNode = parentNode.values[searchPath.peek().childIndex - 1];
					parentNode.setValue(lastValueLeftNode, searchPath.peek().childIndex - 1);
					
					//Insert in current node
					currentDeleteNode.shiftValuesToRightAt(0);
					currentDeleteNode.values[0] = valueParentNode;
					
					currentDeleteNode.shiftNodesToRightAt(0);
					currentDeleteNode.childNodes[0] = lastNodeLeftNode;
				}
				else {
					//Save first element and first child node from right node and delete it
					Comparable firstValueRightNode = otherNode.values[0];
					Node firstNodeRightNode = otherNode.childNodes[0];
					otherNode.shiftValuesToLeftFrom(1);
					otherNode.shiftNodesToLeftFrom(1);
					
					//Save element from parent node and delete it
					Comparable valueParentNode = parentNode.values[searchPath.peek().childIndex];
					parentNode.setValue(firstValueRightNode, searchPath.peek().childIndex);
					
					//Insert in current node
					currentDeleteNode.values[currentDeleteNode.valuesSize()] = valueParentNode;
					currentDeleteNode.childNodes[currentDeleteNode.childNodesSize()] = firstNodeRightNode;
				}
			}
		}
	}

	
	/**
	 * @description: Merges leftNode and rightNode to leftNode with parentValue
	 * @param leftNode
	 * @param rightNode
	 */
	public void mergeNodes(Node leftNode, Node rightNode, Stack<SearchPathObject> searchStack, boolean mergeWithLeft) {
		SearchPathObject parent = searchStack.peek();
		
		//Get parent node and childIndex
		Node parentNode = parent.node;
		int parentNodeChildIndex = parent.childIndex;
		
		//Decrement pointer in parent, so that left merge and right merge is the same
		if(mergeWithLeft) {
			parentNodeChildIndex--;
		}
		
		//Merge left node with parent node
		leftNode.setValue(parentNode.values[parentNodeChildIndex], leftNode.valuesSize());
		
		//Merge left values with right values
		int leftNodeValuePosition = leftNode.valuesSize();
		int rightNodeValuePosition = 0;
		while(rightNodeValuePosition < rightNode.valuesSize()) {
			leftNode.values[leftNodeValuePosition] = rightNode.values[rightNodeValuePosition];
			rightNodeValuePosition++;
			leftNodeValuePosition++;
		}
	
		//Merge left nodes with right nodes
		int leftNodeNodePosition = leftNode.childNodesSize();
		int rightNodeNodePosition = 0;
		while(rightNodeNodePosition < rightNode.childNodesSize()) {
			leftNode.childNodes[leftNodeNodePosition] = rightNode.childNodes[rightNodeNodePosition];
			rightNodeNodePosition++;
			leftNodeNodePosition++;
		}
		
		//Delete value from parent node and pointer to the right node and shift
		parentNode.setValue(null, parentNodeChildIndex);
		parentNode.setChildNode(null, parentNodeChildIndex + 1);
		parentNode.shiftNodesToLeftFrom(parentNodeChildIndex + 2);
		parentNode.shiftValuesToLeftFrom(parentNodeChildIndex + 1);
		
		//Set the root new or open recursion, if parent starts with null
		if(parentNode.values[0] == null) {
			if(parentNode == root) {
				root = leftNode;
			}
			else {
				deleteRecursive(null, searchStack);
			}
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
				insert((Comparable) readInt(file));
			}
			closeInputFile(file);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @param: o: Comparable to be searched
	 * @returns: if o exists return true, else false
	 */
	public boolean contains(Comparable o) {
		if(!isEmpty()) {
			return containsRecursive(o, root);
		}
		return false;
	}

	/**
	 * @param o: Comparable to be searched
	 * @param node: Current node to be searched
	 * @return if there is no childNode true or false, else continue searching recursive in next childNode
	 */
	private boolean containsRecursive(Comparable searchValue, Node node) {
		int i = 0;
		//Go through node
		while(node.getValue(i) != null || node.getChildNode(i) != null) {
			//If value is larger, go to the right
			if(node.getValue(i) != null && node.getValue(i).compareTo(searchValue) == -1) {
				i++;
			}
			//If path to the child node was found, open recursion
			else if(((node.getValue(i) != null && node.getValue(i).compareTo(searchValue) == 1)) || (node.getValue(i) == null)) {
				if(node.getChildNode(i) != null) {
					return containsRecursive(searchValue, node.getChildNode(i));
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
						&& currentNode.getChildNode(i) != null) {
					queue.add(currentNode.getChildNode(i));
					i++;
				}
				//Counting of values
				for(Comparable a : currentNode.values){
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
				if(save.getChildNode(0) != null) {
					save = save.getChildNode(0);
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
	 * @return: if tree is empty null, else Comparable
	 */
	public Comparable getMax() {
		if(!isEmpty()) {
			return getMaxRecursive(root);
		}
		return null;
	}

	/**
	 * @description: gets the last element or last childNode
	 * @param node: current searching node
	 * @return if node is empty null, else Comparable
	 */
	private Comparable getMaxRecursive(Node node) {
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
			if(node.getChildNode(counter) != null) {
				return getMaxRecursive(node.getChildNode(counter));
			}
			//If there is no child node -> return last Comparable
			else {
				return node.getValue(counter - 1);
			}		
		}
		//Default return null
		return null;
	}

	/**
	 * @description: uses another method getMinRecursive() and passes the root
	 * @return: if tree is empty null, else Comparable
	 */
	public Comparable getMin() {
		if(!isEmpty()) {
			return getMinRecursive(root);
		}
		return null;
	}

	/**
	 * @description: gets the first element or first childNode
	 * @param node: current searching node
	 * @return if node is empty null, else Comparable
	 */
	public Comparable getMinRecursive(Node node) {
		//Look at the first value
		if(node.getValue(0) != null) {
			//Searches for available first childNode
			if(node.getChildNode(0) != null) {
				return getMinRecursive(node.getChildNode(0));
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
                    && currentNode.getChildNode(i) != null) {
                queue.add(currentNode.getChildNode(i));
                i++;
            }
            //Insert all values from current node
            for(Comparable a : currentNode.values){
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
		if(currentNode != null) {
			//Open all other recursion for the nodes
			int position = 0;
			while(position < (2 * order + 1)) {
				printInorderRecursive(currentNode.getChildNode(position));
				if(currentNode.getValue(position) != null) {
					System.out.println(currentNode.getValue(position));
				}
				position++;
			}
			printInorderRecursive(currentNode.getChildNode(position));
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
			printInorderRecursive(currentNode.getChildNode(0));
		}
		
		//Open recursion for all other nodes
		int position = 1;
		while(position < ((2 * order) + 2) && currentNode.getChildNode(position) != null) {
			printInorderRecursive(currentNode.getChildNode(position));
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
		//Open recursion with first node
		if(currentNode != null) {
			//Open all other recursion for the nodes
			int position = 0;
			while(position < (2 * order + 1)) {
				if(currentNode.getValue(position) != null) {
					System.out.println(currentNode.getValue(position));
				}
				printInorderRecursive(currentNode.getChildNode(position));
				position++;
			}
			printInorderRecursive(currentNode.getChildNode(position));
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
			while(i < ((2 * order) + 1) && currentNode.getChildNode(i) != null) {
				queue.add(currentNode.getChildNode(i));
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
			if(oldNode.getChildNode(counter) != null) {
				copyNode.setChildNode(cloneRecursive(oldNode.getChildNode(counter)), counter);
			}
			counter++;
		}
		if(oldNode.getChildNode(counter) != null) {
			//Copies the last node recursively
			copyNode.setChildNode(cloneRecursive(oldNode.getChildNode(counter)), counter);
		}
		return copyNode;
	}

}
