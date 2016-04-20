package bTree;

import java.io.FileOutputStream;
import java.io.*;

public class BTest {

	public static void main(String[] args) {
		// create new tree
		BTree tree = new BTree(2);
//		BTree tree2 = new BTree(1);
		// insert some values
		tree.insert(5);
		tree.insert(2);
		tree.insert(3);
		tree.insert(8); 
		tree.insert(4);
		tree.insert(7);
		tree.insert(10); 
		tree.insert(30);
		tree.insert(20); 
		tree.insert(4);
		tree.insert(7); 
		tree.insert(9); 
		tree.insert(40);
		tree.insert(60);
		tree.insert(50);
		tree.insert(25);
		tree.insert(39);
		tree.insert(35); 
		tree.insert(70); 
		tree.insert(6); 
		tree.insert(70); 
		tree.insert(33); 

//		tree2.insert(10);
//		tree2.insert(30);
//		tree2.insert(20);
//		tree.insert(4);
//		tree2.insert(7);
		
//		tree.inOrder();
//		tree.preOrder();
//		tree.postOrder();
//		System.out.println("Anzahl der Objekte im Baum = "+tree.size());
//		System.out.println("7 in Baum? "+tree.contains(7));
//		System.out.println("Höhe "+tree.height());
//		System.out.println("Kleinstes Element "+tree.getMin());
//		System.out.println("Größtes Element "+tree.getMax());
		
//		tree.build();  // read Integers from file
		
		tree.levelOrder();
		
//		tree.addAll(tree2);
		
//		tree.levelOrder();
//		
//		BTree ntree = null;
//		
//		try {
//		    ntree = (BTree)tree.clone();
//		} catch (CloneNotSupportedException e){}
//		
//		ntree.levelOrder();
//		
//		ntree.insert(7);
//		tree.insert(4);
//		
//		ntree.levelOrder();
//		tree.levelOrder();
//
//		System.out.println("now delete");
//		
//		tree.delete(1);
//		tree.delete(4);
//		tree.delete(7);
//		tree.delete(3);
		
		try {
			ObjectOutputStream oos =	 new ObjectOutputStream (new FileOutputStream("btree.dat"));
			oos.writeObject(tree);   // ganze
			oos.close();	
			
		} catch (IOException e) { e.printStackTrace();}
		
		System.out.println("delete: "+5); tree.delete(5);		
		tree.levelOrder();

		System.out.println("delete: "+4); tree.delete(4);
		tree.levelOrder();
		
		System.out.println("delete: "+7); tree.delete(7);		
		tree.levelOrder();
		
		System.out.println("delete: "+2); tree.delete(2);		
		tree.levelOrder();
		
		System.out.println("delete: "+40); tree.delete(40);		
		tree.levelOrder();
		
		
		BTree newTree = null;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("btree.dat"));

			newTree = (BTree) ois.readObject(); // ganze Liste einlesen
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		newTree.levelOrder();
	}

}
