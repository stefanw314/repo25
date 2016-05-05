package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public abstract class Eisdiele {

	public Eis passendesEis;
	
	@SuppressWarnings("unused")
	private Eisdiele() {
		
	}
	
	public Eisdiele(Eis eis) {
		passendesEis = eis;
	}
	
	
	
	/**
	 * @description Erstellt ein Eis indem es die statische Methode von Eis aufruft
	 * @param typ: Eisname
	 * @return Eis Objekt oder null
	 */
	public abstract Eis erstelleEis(String typ);
	
	/**
	 * @description: Bestellvorgang des Eises
	 * @param typ: Eisname
	 */
	public void bestellen(String typ) {
		begruessen();
		Eis eis = erstelleEis(typ);
		
		if (eis != null) {
			eis.vorbereiten();
			eis.fuellen();
			eis.dekorieren();
			kassieren(eis.preis);
			verabschieden(eis.name);
		} 
		else {
			entschuldigen(typ);
		}
	}

	/**
	 * @description: Begrüßt den Kunden
	 */
	public abstract void begruessen();
	
	/**
	 * @description: Kassiert das Geld
	 * @param preis: Übergebener Preis
	 */
	public abstract void kassieren(double preis);
	
	/**
	 * @description: Verabschieded sich vom Kunden
	 * @param name
	 */
	public abstract void verabschieden(String name);
	
	/**
	 * @description: Entschuldigt sich, dass es das Eis mit dem Namen typ nicht gibt
	 * @param typ: Eisname
	 */
	public abstract void entschuldigen(String typ);
	
}
