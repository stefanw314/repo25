package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public class EisdieleMain {
	
	public static void main(String[] args) {
		System.out.println("Bestelle ein Spaghettieis in MA: ");
		EisdieleMannheim eisdieleTest1 = new EisdieleMannheim(new EisMannheim());
		eisdieleTest1.bestellen("Spaghettieis");
		System.out.println("_________________________");
		
		System.out.println("Bestelle ein Spaghettieis in Köln: ");
		EisdieleKoeln eisdieleTest2 = new EisdieleKoeln(new EisKoeln());
		eisdieleTest2.bestellen("Spaghettieis");
		System.out.println("_________________________");
		
		System.out.println("Bestelle ein Spaghettieis in Speyer: ");
		EisdieleSpeyer eisdieleTest3 = new EisdieleSpeyer(new EisSpeyer());
		eisdieleTest3.bestellen("Nussbecher");
		System.out.println("_________________________");
	}
	
}
