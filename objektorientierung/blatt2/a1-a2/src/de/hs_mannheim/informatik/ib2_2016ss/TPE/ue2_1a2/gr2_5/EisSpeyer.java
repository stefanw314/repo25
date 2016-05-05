package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public class EisSpeyer extends Eis {
	
	/**
	 * @description: Privater Konstruktor, um Eis ohne typ zu verhindern
	 */
	protected EisSpeyer() {}
	
	/**
	 * @description: Calls the super constructor
	 */
	protected EisSpeyer(String name, String art, String behaeltnis, double preis, String[] sorten, String[] extras) {
		super(name,art,behaeltnis,preis,sorten,extras);
	}
	
	/**
	 * @description: Bereitet das Eis vor
	 */
	public void vorbereiten() {
		System.out.println("(Speyer) Nehme das Behältnis " + behaeltnis);
	}
	
	/**
	 * @description: Füllt das Eis
	 */
	public void fuellen() {
		System.out.println("(Speyer) Fülle das Behältnis mit " +  toString(sorten) + " nach der Art " + art);
	}
	
	/**
	 * @description: Dekoriert das Eis
	 */
	public void dekorieren() {
		System.out.println("(Speyer) Dekoriere das Eis mit den Extras " + toString(extras));
	}
}
