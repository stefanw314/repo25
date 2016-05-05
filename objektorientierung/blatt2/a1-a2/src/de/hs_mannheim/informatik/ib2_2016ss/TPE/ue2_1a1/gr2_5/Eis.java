package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a1.gr2_5;

public class Eis {
	
	public enum Eissorten {
		Spaghettieis, Banansplit, Nussbecher
	}
	
	public String name;
	public String behaeltnis;
	public String art;
	
	public double preis;
	
	public String[] sorten;
	public String[] extras;
	
	/**
	 * @description: Privater Konstruktor, um Eis ohne typ zu verhindern
	 */
	protected Eis() {}
	
	/**
	 * @description: Konstruktor, der von erstelleEis(typ) aufgerufen wird, falls das Eis existiert
	 * @param typ: Eisname
	 */
	public Eis(String name, String art, String behaeltnis, double preis, String[] sorten, String[] extras) {
		this.name = name;
		this.art = art;
		this.behaeltnis = behaeltnis;
		this.preis = preis;
		this.sorten = sorten;
		this.extras = extras;
	}
	
	/**
	 * @description: Bereitet das Eis vor
	 */
	public void vorbereiten() {
		System.out.println("Nehme das Behältnis " + behaeltnis);
	}
	
	/**
	 * @description: Füllt das Eis
	 */
	public void fuellen() {
		System.out.println("Fülle das Behältnis mit " +  toString(sorten) + " nach der Art " + art);
	}
	
	/**
	 * @description: Dekoriert das Eis
	 */
	public void dekorieren() {
		System.out.println("Dekoriere das Eis mit den Extras " + toString(extras));
	}
	
	/**
	 * @description Erstellt einen kommagetrennten String aus einem String Array
	 * @param input array
	 * @return kommggetrennter String
	 */
	public String toString(String[] input) {
		String output = "";
		//Iteriere über den gesamten Array
		for(int i = 0; i < input.length; i++) {
			output += input[i];
			if(i + 1 < input.length) {
				output += ", ";
			}
		}
		return output;
	}
}
