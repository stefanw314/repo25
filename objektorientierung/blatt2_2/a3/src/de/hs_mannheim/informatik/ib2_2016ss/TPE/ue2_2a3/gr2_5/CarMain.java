package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a3.gr2_5;

import de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a1.gr2_5.MyBTree;

public class CarMain {
	
	public static void main (String[]args){
		Car gasoline1 = new GasolineCar("BMW" , 2012, "25.000", 2, 1);
		Car gasoline2 = new GasolineCar("Audi" , 2011, "22.000", 3, 2);
		Car electric = new ElectricCar("Tesla", 2015, "50.000", "High Voltage", 3);
		Car hybrid = new HybridCar("VW", 2014, "30.000", 1, "Low Voltage", 4);
		
		/*System.out.println(gasoline1.toString());
		System.out.println(gasoline2.toString());
		System.out.println(electric.toString());
		System.out.println(hybrid.toString());
		*/
		
		MyBTree carTree = new MyBTree(1);
		carTree.insert(gasoline1);
		carTree.insert(gasoline2);
		carTree.insert(electric);
		carTree.insert(hybrid);
		
		carTree.printLevelorder();
		
	}

}
