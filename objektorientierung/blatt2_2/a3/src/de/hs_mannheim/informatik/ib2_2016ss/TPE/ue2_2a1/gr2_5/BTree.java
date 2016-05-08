package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a1.gr2_5;

public interface BTree {
	boolean insert (Comparable o);
    boolean insert (String filename);
    boolean contains(Comparable o);
    
    int size();
    int height();
    
    Comparable getMax();
    Comparable getMin();
    
    boolean isEmpty();
    
    void addAll(BTree otherTree);
    void printInorder();
    void printPostorder();
    void printPreorder();
    void printLevelorder();
    
    BTree clone();
}
