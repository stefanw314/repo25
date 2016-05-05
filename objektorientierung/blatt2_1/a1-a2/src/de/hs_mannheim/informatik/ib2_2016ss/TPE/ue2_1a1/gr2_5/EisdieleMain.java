package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a1.gr2_5;

public class EisdieleMain {
	
	public static void main(String[] args) {
		System.out.println("Bestelle ein Spaghettieis: ");
		Eisdiele eisdieleTest1 = new Eisdiele();
		eisdieleTest1.bestellen("Spaghettieis");
		System.out.println("_________________________");
		
		System.out.println("Bestelle ein Banansplit: ");
		Eisdiele eisdieleTest2 = new Eisdiele();
		eisdieleTest2.bestellen("Banansplit");
		System.out.println("_________________________");
		
		System.out.println("Bestelle einen Nussbecher: ");
		Eisdiele eisdieleTest3 = new Eisdiele();
		eisdieleTest3.bestellen("Nussbecher");
		System.out.println("_________________________");
		
		System.out.println("Bestelle einen Krokantbecher: ");
		Eisdiele eisdieleTest4 = new Eisdiele();
		eisdieleTest4.bestellen("Krokantbecher");
		System.out.println("_________________________");
	}
	
}
