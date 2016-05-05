package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public class EisKoeln extends Eis {
	
	/**
	 * @description: Privater Konstruktor, um Eis ohne typ zu verhindern
	 */
	protected EisKoeln() {}
	
	/**
	 * @description: Calls the super constructor
	 */
	protected EisKoeln(String name, String art, String behaeltnis, double preis, String[] sorten, String[] extras) {
		super(name,art,behaeltnis,preis,sorten,extras);
	}
	
	/**
	 * @description: Bereitet das Eis vor
	 */
	public void vorbereiten() {
		System.out.println("(KÖLN) Nehme das Behältnis " + behaeltnis);
	}
	
	/**
	 * @description: Füllt das Eis
	 */
	public void fuellen() {
		System.out.println("(KÖLN) Fülle das Behältnis mit " +  toString(sorten) + " nach der Art " + art);
	}
	
	/**
	 * @description: Dekoriert das Eis
	 */
	public void dekorieren() {
		System.out.println("(KÖLN) Dekoriere das Eis mit den Extras " + toString(extras));
	}
}
