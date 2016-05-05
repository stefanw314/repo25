package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_1a2.gr2_5;

public abstract class Eis {
	
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
	protected Eis(String name, String art, String behaeltnis, double preis, String[] sorten, String[] extras) {
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
	public abstract void vorbereiten();
	
	/**
	 * @description: Füllt das Eis
	 */
	public abstract void fuellen();
	
	/**
	 * @description: Dekoriert das Eis
	 */
	public abstract void dekorieren();
	
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
