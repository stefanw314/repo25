package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue1.gr2_5;

public class Node {

	protected Integer[] values;
	protected Node[] childNodes;
	protected int order;
	
	/**
	 * @param m: order of the node for the Btree
	 */
	public Node(int m) {
		//Add one more value and node to the array, for additional space for splitting
		this.values = new Integer[2*m + 1];
		this.childNodes = new Node[2*m + 2];
		this.order = m;
	}

	/** 
	 * @description: prints the values of the node
	 */
	public void printNode() {
		//Goes through all values and prints them
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] != null) {
				System.out.print("" + values[i]);
			}
			//Set delimeter if it is not the last value
			if(values[i + 1] != null && values[i] != null && i != values.length - 2) {
				System.out.print(" | ");
			}
		}
		System.out.println();
	}
	
	/**
	 * @description: Gets the value on a position
	 * @param position
	 * @return an Integer value
	 */
	public Integer getValue(int position) {
		return values[position];
	}

	/**
	 * @description: Sets a value on a position in the array
	 * @param value to be inserted
	 * @param position where to insert
	 */
	public void setValue(Integer value, int position) {
		values[position] = value;
	}

	/**
	 * @description: Gets the node on a position
	 * @param position
	 * @return a child node
	 */
	public Node getNode(int position) {
		return childNodes[position];
	}

	/**
	 * 
	 * @param node: the node to be set
	 * @param position: at what position should the node be set
	 */
	public void setNode(Node node, int position) {
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
	public int findChildPosition(Integer o) {
		int nodePosition = 0;
		
		//Goes through all values until found
		while((nodePosition) < (2 * order) && values[nodePosition] != null && values[nodePosition] < o) {
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
	public void insertInNodeWithSpace(Integer insertValue) {
		int insertPosition = 0;
		//Find insert position
		while(insertPosition < (2 * order) && values[insertPosition] != null && values[insertPosition] < insertValue) {
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
			copyNode.setNode(childNodes[from], i);
			from++;
			i++;
		}
		copyNode.setNode(childNodes[from], i);
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
			setNode(null, from);
			from++;
		}
		setNode(null, from);
	}
	
	/**
	 * @description: shifts all values to the right to position
	 * @param valuePosition: shift until this position
	 */
	public void shiftValuesToRightAt(int valuePosition) {
		//Shift values from right to right
        for( int index = values.length-2; index >= valuePosition ; index-- ) {
        	values[index+1] = values[index];
        }
		
        //Reset valuePosition
        values[valuePosition] = null;
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
	
	
}