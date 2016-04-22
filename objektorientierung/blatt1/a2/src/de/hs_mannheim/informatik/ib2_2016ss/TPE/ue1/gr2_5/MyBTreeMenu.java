package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue1.gr2_5;

import static gdi.MakeItSimple.*;

public class MyBTreeMenu {

		static BTree la[] = new BTree[3];  // array with space for 3 btrees

		public static void main(String[] args) {

			for (int i = 0; i < la.length; i++){
				la[i] = new MyBTree(2); //create objects
			}

			int wl = 0;

			while (true) {
				println("------------------- Menü -------------------");
				println("1: Print Inorder");  
				println("2: Print Postorder");
				println("3: Print Preorder");
				println("4: Print Levelorder");
				println("5: insert (Integer)");
				println("6: insert from file");
				println("7: size ()");
				println("8: height()");
				println("9: contains(element)");
				println("10: getMin()");
				println("11: getMax()");
				println("12: isempty()");
				println("13: addAll(BTree)");
				println("14: clone");
				println("30: change working list");  
				println("99: terminate");

				int selection = readInt();

				switch (selection) {
				case 1:
					la[wl].printInorder();
					break;
				case 2:
					la[wl].printPostorder();
					break;
				case 3:
					la[wl].printPreorder();
					break;
				case 4:
					la[wl].printLevelorder();
					break;
				case 5:
					println("value to add= ");
					int z = readInt();
					la[wl].insert((Integer) z);
					break;
				case 6:
					String filename = "filename";
					la[wl].insert(filename);
					break;
				case 7:
					println("size of BTree: " + la[wl].size());
					break;
				case 8:
					println("height of BTree: " + la[wl].height());
					break;
				case 9:
					println("value to search?");
					int contains = readInt();
					if(la[wl].contains((Integer) contains)){
						println("value found");
					}
					else{
						println("value not found");
					}
					break;
				case 10:
					println("Minimum: " + la[wl].getMin());
					break;
				case 11:
					println("Maximum: " + la[wl].getMax());
					break;
				case 12:
					if(la[wl].isEmpty()){
						println("BTree is empty");
					}
					else{
						println("BTree is not empty");
					}
					break;		
				case 13:
					println("Which BTree to add to? (current BTree: " + wl + ")");
					int list = readInt();
					if(list >=0 && list <= (la[wl].size()-1)){
						la[wl].addAll(la[list]);
						println("Added BTree " + list + " to " + wl);
						break;
					}
					else{
						println("BTree does not exist!");
					}
					break;
				case 14:
					println("In which BTree to clone? (current BTree: " + wl + ")");
					int list2 = readInt();
					if(list2 >=0 && list2 <= (la[wl].size()-1)){
						la[list2] = la[wl].clone();
						println("Cloned " + wl + " to " + list2);
						break;
					}
					else{
						println("BTree does not exist!");
					}
					break;
				case 30:
					println("Welche List wollen Sie verwenden? Aktuelle Liste: " + wl);
					int test = readInt();
					if(test >=0 && test <= la.length-1){
						wl = test;
					}
					else{
						println("Diese Liste existiert nicht");
					}
					println("neues wl: " + wl);
					break;
				case 99:
					println("terminate...");
					return;
				default:
					break;
				};

				readLine();
			}

		}
	}

