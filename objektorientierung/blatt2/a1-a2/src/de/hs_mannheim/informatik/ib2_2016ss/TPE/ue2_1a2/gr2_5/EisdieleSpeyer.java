package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public class EisdieleSpeyer extends Eisdiele {
	
	public EisdieleSpeyer(Eis eis) {
		super(eis);
	}

	/**
	 * @description erstellt ein Eis indem es die statische Methode von Eis aufruft
	 * @param typ: Eisname
	 * @return Eis Objekt oder null
	 */
	public Eis erstelleEis(String typ) {
		for (Eis.Eissorten c : Eis.Eissorten.values()) {
	        if (c.name().equals(typ)) {
	        	String art = "";
	        	String behaeltnis = "";
	        	double preis = 0.0d;
	        	String[] sorten = new String[]{};
	        	String[] extras = new String[]{};

	    		//Erstelle je nach Eissorte ein anderes Eis
	    		if(typ == Eis.Eissorten.Spaghettieis.name()) {
	    			art = "Spaghettis";
	    			behaeltnis = "Glas";
	    			preis = 7.00;
	    			sorten = new String[]{"Vanille"};
	    			extras = new String[]{"Erdbeersoße", "Streusel", "Sahne", "Keks"};
	    		}
	    		else if(typ == Eis.Eissorten.Banansplit.name()) {
	    			art = "Bälle";
	    			behaeltnis = "Glas";
	    			preis = 7.50;
	    			sorten = new String[]{"Vanille"};
	    			extras = new String[]{"Bananen", "Schokosträusel"};
	    		}
	    		else if(typ == Eis.Eissorten.Nussbecher.name()) {
	    			art = "Bälle";
	    			behaeltnis = "Glas";
	    			preis = 7.50;
	    			sorten = new String[]{"Schokolade", "Nuss", "Vanille", "Erdbeere"};
	    			extras = new String[]{"Schokosträusel"};
	    		}
	        	return new EisSpeyer(typ, art, behaeltnis, preis, sorten, extras);
	        }
	    }
		return null;
	}
	
	
	/**
	 * @description: Begrüßt den Kunden
	 */
	public void begruessen() {
		System.out.println("(Speyer) Guten Tag! Willkommen bei unserer Eisdiele.");
	}
	
	/**
	 * @description: Kassiert das Geld
	 * @param preis: Übergebener Preis
	 */
	public void kassieren(double preis) {
		System.out.println("(Speyer) Das wären dann " + preis + " Euro");
	}
	
	/**
	 * @description: Verabschieded sich vom Kunden
	 * @param name
	 */
	public void verabschieden(String name) {
		System.out.println("(Speyer) Hier ist Ihr " + name + ". Guten Appetit!");
	}
	
	/**
	 * @description: Entschuldigt sich, dass es das Eis mit dem Namen typ nicht gibt
	 * @param typ: Eisname
	 */
	public void entschuldigen(String typ) {
		System.out.println("(Speyer) Entschuldige, aber wir haben kein " + typ);
	}
}
