package bTree;

import java.io.Serializable;

/**
 * 
 * @author Wolfgang Schramm
 * @date 20.04.2016
 * @version 2.0
 * 
 */

// all methods are visible only in package bTree

public class BNode implements Cloneable, Serializable {
	
	// node types
	
	public static final int LEAF_PAGE = 0;    // --> node type
	public static final int NODE_PAGE = 1;    // --> node type
	
	BNode[] children;
	Integer [] keys;
	
	BNode parent;
	
	int maxKeysP1;   // max number of keys in node plus 1
	                 // when reached --> node bursts --> split node
	
	int nodeType; // type of the current node
	
	/**
	 * constuctor
	 * @param degree --> degree of B-Tree (and B-Node)
	 * @param nodeType --> leaf (0) oder inner node (1)
	 */
	
	BNode(int degree, int nodeType) {
	}
	
	/**
	 * @return type of actual node
	 */
	
	int getNodeType() {
	}
	
	/**
	 * @return true if there no keys on node 
	 */
	
	boolean isEmpty() {
	}
	
	/**
	 * @return degree of node
	 */
	
	private int getdegree () {
	}
	
	
	/**
	 * @return parent node
	 */
		
	BNode getParent () {
	}
	
	BNode setParent (BNode p) {
	}

	/**
	 * @return true, if this is leaf, false otherwise
	 */
	
	boolean isLeaf () {
		return this.nodeType == LEAF_PAGE; 
	}

	/**
	 * makes a deep clone of actual node
	 * @return cloned node
	 */
			
	@Override
	public Object clone () throws CloneNotSupportedException {
	}
	
	
	/**
	 * splits the this BNode an
	 * @param obj = key to insert
	 * @returns a new BNode with the second part of the this node
	 */	
	
	BNode splitBNode () {
		
	}

	
	/**
	 * 
	 * right shift keys on node from i on
	 * it is assumed that there is enough space on leaf
	 * that means that there is min. one free entry on the right
	 * @param i = position to insert new key
	 * 
	 */
	
	public void shiftKeysOnLeaf (int i) {
	}
	
	
	/**
	 * 
	 * finds position on node to insert obj
	 * @param obj = key to insert
	 * @return insertion position or -1, when obj is already in node
	 * it is guaranteed that there exists a position
	 * 
	 */
	
	int searchPosOnNode (Integer obj) {
	}
	
	/**
	 * 
	 * finds position on node to insert obj
	 * @param obj = key to delete
	 * @return insertion position
	 * it is guaranteed that obj is on node
	 * 
	 */
	
	public int searchObjPosOnNode (Integer obj) {
	}
	
    
	/**
	 * 
	 * @return index (position) of biggest element on node
	 * 
	 */
	
	int getIndexOfBiggestObjOnNode () {
	}
	
	
	/**
	 * 
	 * @return true, when node gets in deficit
	 * 
	 */
	
	boolean isInDeficit () {
	}
	
	Integer getKey (int i) {
	}
	
	void setKey (int i, Object obj) {
	}
	
	BNode getChild (int i) {
	}
	
	/**
	 * 
	 * @param i = position of child
	 * @param n = reference to child
	 * also sets the parent reference of child node
	 */
	
	 void setChild (int i, BNode n) {   
	}
	
	/**
	 * decides weather Node is overfilled or not
	 * @returns true if node bursts
	 */
	
	boolean isFull() {
	}
	
	/**
	 * 
	 * @return true if node bursts while insertion process
	 * @param obj --> key to insert
	 * @param lnode --> left sibling
	 * @param rnode --> right sibling
	 * 
	 */
	
	boolean insertIntoNode (Integer obj, BNode lnode, BNode rnode) {
	}
	
	/**
	 * 
	 * @return true if (any) left sibling exists
	 *         false otherwise
	 * 
	 */
	
	boolean lSibExists () {
	}
	
	/**
	 * 
	 * @return true if (any) right sibling exists
	 *         false otherwise
	 * 
	 */
	
	boolean rSibExists () {
	}
	
	/**
	 * 
	 * @return left sibling of this-BNode
	 * precond: left sibling exists
	 * 
	 */
	
	BNode getLSib () {
	}
	
	
	/**
	 * 
	 * @return right sibling of this-BNode
	 * precond: right sibling exists
	 * 
	 */
	
	BNode getRSib () {
	}
	
	/**
	 * @return true, if node can yield an object for balancing
	 */
	
	boolean isBigEnough() {
	}
	
	/**
	 * @return position of embedded element when join with left sibling
	 * @param child node
	 */
	
	int getPosOfEmbeddedElementL (BNode child) {
	}
	
	/**
	 * @return position of embedded element when join with right sibling
	 * @param child node
	 */
	
	int getPosOfEmbeddedElementR (BNode child) {
	}
	
	
	/**
	 * @return position of rightmost element
	 * precond: there is at least 1 free space!!!!
	 */
	
	int getPosOfRightmostElement () {
	}
	
	/**
	 * shifts right all elements in a node
	 * precond: it is guaranteed that there is at least one space at the right
	 */
	
	void shiftRight () {
	}
	
	/**
	 * left shift of all elements on a node right from pos
	 * precond: the node is a leaf
	 */
	
	void shiftLeft (int pos) {
	}
	
	
	/**
	 * @return all keys on a node
	 */
	
	@Override
	public String toString () {
	
	}
}