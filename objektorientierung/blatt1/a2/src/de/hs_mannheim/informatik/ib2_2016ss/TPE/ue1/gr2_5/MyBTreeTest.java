package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue1.gr2_5;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBTreeTest {

	@Test
	public void insertTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 1000) {
			tree.insert(i);
			i++;
		}
	}
	
	@Test
	public void containsTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 1000) {
			tree.insert(i);
			i++;
		}
		i = 0;
		while(i < 1000) {
			assertTrue(tree.contains(i));
			i++;
		}
	}
	
	@Test
	public void sizeTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 1000) {
			tree.insert(i);
			i++;
		}
		assertEquals(1000, tree.size());
	}
	
	@Test
	public void getMaxTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 1000) {
			tree.insert(i);
			i++;
		}
		assertEquals(999, (int) tree.getMax());
	}
	
	@Test
	public void getMinTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 1000) {
			tree.insert(i);
			i++;
		}
		assertEquals(0, (int) tree.getMin());
	}
	
	@Test
	public void isEmptyTest() {
		MyBTree tree = new MyBTree(2);
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void addAllTest() {
		MyBTree tree1 = new MyBTree(2);
		int i = 0;
		while(i < 100) {
			tree1.insert(i);
			i++;
		}
		
		MyBTree tree2 = new MyBTree(2);
		int j = 100;
		while(j < 200) {
			tree2.insert(j);
			j++;
		}
		
		tree1.addAll(tree2);
		
		int k = 0;
		while(k < 200) {
			assertTrue(tree1.contains(k));
			k++;
		}
	}
	
	@Test
	public void cloneTest() {
		MyBTree tree = new MyBTree(2);
		int i = 0;
		while(i < 100) {
			tree.insert(i);
			i++;
		}
		
		MyBTree treeClone = (MyBTree) tree.clone();
		
		i = 0;
		while(i < 100) {
			treeClone.contains(i);
			i++;
		}
	}
	
	@Test
	public void doubleValueTest() {
		MyBTree tree = new MyBTree(2);
		tree.insert(1);
		tree.insert(1);
		assertEquals(1, tree.size());
	}
	
}
