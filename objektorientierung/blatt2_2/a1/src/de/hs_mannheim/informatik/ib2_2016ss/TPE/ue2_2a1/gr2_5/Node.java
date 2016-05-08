package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a1.gr2_5;

public class Node {

	protected Comparable[] values;
	protected Node[] childNodes;
	protected int order;
	
	/**
	 * @param m: order of the node for the Btree
	 */
	public Node(int m) {
		//Add one more value and node to the array, for additional space for splitting
		this.values = new Comparable[2*m + 1];
		this.childNodes = new Node[2*m + 2];
		this.order = m;
	}

	/** 
	 * @description: prints the values of the node
	 */
	public void printNode() {
		//Goes through all values and prints them
		int i = 0;
		while(i < values.length - 1) {
			/*
			if(childNodes[i] != null) {
				System.out.print("(childNode)");
			}
			else {
				System.out.print("(null)");
			}*/
			
			if(values[i] != null) {
				System.out.print("" + values[i]);
			}
			//Set delimeter if it is not the last value
			if(values[i + 1] != null && values[i] != null && i != values.length - 2) {
				System.out.print(" | ");
			}
			i++;
		}
		/*if(childNodes[i] != null) {
			System.out.print("(childNode)");
		}*/
		System.out.println();
	}
	
	/**
	 * @description: Gets the value on a position
	 * @param position
	 * @return an Comparable value
	 */
	public Comparable getValue(int position) {
		return values[position];
	}

	/**
	 * @description: Sets a value on a position in the array
	 * @param value to be inserted
	 * @param position where to insert
	 */
	public void setValue(Comparable value, int position) {
		values[position] = value;
	}

	/**
	 * @description: Gets the node on a position
	 * @param position
	 * @return a child node
	 */
	public Node getChildNode(int position) {
		return childNodes[position];
	}

	/**
	 * 
	 * @param node: the node to be set
	 * @param position: at what position should the node be set
	 */
	public void setChildNode(Node node, int position) {
		childNodes[position] = node;
	}
	
	/**
	 * @description: Returns true if it is a leaf, else false
	 */
	public boolean isLeaf() {
		//If there is no child node, it is a leaf
		return childNodes[0] == null;
	}
	
	/**
	 * @description: finds the position where the search path should go down
	 * @param o: the value to be inserted
	 * @return the int position
	 */
	public int findChildPosition(Comparable o) {
		int nodePosition = 0;
		
		//Goes through all values until found
		while((nodePosition) < (2 * order) && values[nodePosition] != null && values[nodePosition].compareTo(o) == -1) {
			nodePosition++;
		}
		
		return nodePosition;
	}
	
	/**
	 * @description: Checks for free space in the node
	 * @return true if space is available, else false
	 */
	public boolean hasSpace() {
		int valuesInNode = 0;
		//Goes through all values
		while(valuesInNode < values.length && values[valuesInNode] != null) {
			valuesInNode++;
		}
		//If there is space, it returns true
		return valuesInNode < (2 * order);
	}
	
	/**
	 * @description: Inserts the value o to the right position and shifts all other values up by one position
	 * @param o: value to be inserted
	 */
	public void insertInNodeWithSpace(Comparable insertValue) {
		int insertPosition = 0;
		//Find insert position
		while(insertPosition < (2 * order) && values[insertPosition] != null && values[insertPosition].compareTo(insertValue) == -1) {
			insertPosition++;
		}
		//Shift values
		shiftValuesToRightAt(insertPosition);
		//Insert value
		setValue(insertValue, insertPosition);
	}
	
	/**
	 * @description: Copies the node values and child nodes starting at position
	 * @param from: starting at int value
	 * @return new copied part node
	 */
	public Node copyStartingAt(int from) {
		Node copyNode = new Node(order);
		int i = 0;
		//Goes through all values starting at from and copies value and child node
		while(from < ((2 * order) + 1)) {
			copyNode.setValue(values[from], i);
			copyNode.setChildNode(childNodes[from], i);
			from++;
			i++;
		}
		copyNode.setChildNode(childNodes[from], i);
		return copyNode;
	}
	
	/**
	 * @description: removes values and nodes from this node starting at value
	 * @param from: starting at int value
	 */
	public void removeStartingAt(int from) {
		//Goes through all values starting at from
		while(from < ((2 * order) + 1)) {
			setValue(null, from);
			setChildNode(null, from);
			from++;
		}
		setChildNode(null, from);
	}
	
	/**
	 * @description: shifts all values to the right to position
	 * @param valuePosition: shift until this position
	 */
	public void shiftValuesToRightAt(int valuePosition) {
		//Shift values from left to right
        for( int index = values.length-2; index >= valuePosition ; index-- ) {
        	values[index+1] = values[index];
        }
		
        //Reset valuePosition
        values[valuePosition] = null;
	}
	
	/**
	 * @description: shifts all values to the left to position
	 * @param valuePosition: shift until this position
	 */
	public void shiftValuesToLeftFrom(int valuePosition) {
		//Shift values from right to left
		int index;
        for(index = valuePosition; index < 2 * order ; index++ ) {
        	values[index-1] = values[index];
        }
		
        //Reset valuePosition
       values[index - 1] = null;
	}
	
	/**
	 * @description: shifts all nodes to the right to position
	 * @param valuePosition: shift until this position
	 */
	public void shiftNodesToRightAt(int nodePosition) {
		//Shift nodes from right to right
        for( int index =childNodes.length-2; index >= nodePosition ; index-- ) {
        	childNodes[index+1] = childNodes[index];
        }
		
        //Reset nodePosition
        childNodes[nodePosition] = null;
	}
	
	/**
	 * @description: shifts all nodes to the right to position
	 * @param valuePosition: shift until this position
	 */
	public void shiftNodesToLeftFrom(int nodePosition) {
		//Shift nodes from right to right
		int index;
        for(index =nodePosition; index < 2 * order + 1 ; index++ ) {
        	childNodes[index-1] = childNodes[index];
        }
		
        //Reset nodePosition
        childNodes[index - 1] = null;
	}

	/**
	 * @description: counts the values in the node
	 * @return size of the node
	 */
	public int valuesSize() {
		int i = 0;
		while(values[i] != null) {
			i++;
		}
		return i;
	}
	
	/**
	 * @description: counts the child nodes in the node
	 * @return size of the node
	 */
	public int childNodesSize() {
		int i = 0;
		while(childNodes[i] != null) {
			i++;
		}
		return i;
	}
	
	/**
	 * @description: Deletes a value from the node
	 */
	public void deleteValueFromLeaf(Comparable toBeDeleted) {
		int currentPosition = 0;
		boolean found = false;
		
		//Find the delete position
		while(currentPosition < 2 * order && !found) {
			if(values[currentPosition] != null && values[currentPosition].equals(toBeDeleted)) {
				found = true;
			}
			else {
				currentPosition++;
			}
		}
		
		//Delete the value at the position
		values[currentPosition] = null;
		
		//Move all other values to the left		
		shiftValuesToLeftFrom(currentPosition + 1);
	}

	/**
	 * @description: Returns the last index of existing value
	 * @return index
	 */
	public int getLastValueIndex() {
		int i = 0;
		while(values[i] != null) {
			i++;
		}
		return i - 1;
	}
	
	/**
	 * @description: Returns the last index of existing value
	 * @return index
	 */
	public int getLastNodeIndex() {
		int i = 0;
		while(childNodes[i] != null) {
			i++;
		}
		return i - 1;
	}

	/**
	 * @description: Replaces a value with another value
	 * @param deleteValue
	 * @param nextElement
	 */
	public void replaceValue(Comparable deleteValue, Comparable nextElement) {
		for(int i = 0; i < 2 * order; i++) {
			if(values[i] != null && values[i].equals(deleteValue)) {
				values[i] = nextElement;
			}
		}
	}

	/**
	 * @description: Gets the leftest element from the right subtree or the rightest element from the left subtree
	 * @return the element
	 */
	public Comparable getNextBestElement(Comparable value) {
		int nodePosition = 0;
		
		//Goes through all values until found
		while(nodePosition < 2 * order && !values[nodePosition].equals(value)) {
			nodePosition++;
		}
		
		Node leftChildNode = childNodes[nodePosition];
		
		while(!leftChildNode.isLeaf()) {
			leftChildNode = leftChildNode.childNodes[leftChildNode.getLastNodeIndex()];
		}
		
		return leftChildNode.values[leftChildNode.getLastValueIndex()];
	}

	/**
	 * @description: Checks if a value is in the node
	 * @param containsValue
	 * @return true if it is in the node
	 */
	public boolean containsValue(Comparable containsValue) {
		int i = 0;
		//Go through the node and return true if it is in the node
		while(i < values.length) {
			if(values[i] != null && values[i].equals(containsValue)) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	
}