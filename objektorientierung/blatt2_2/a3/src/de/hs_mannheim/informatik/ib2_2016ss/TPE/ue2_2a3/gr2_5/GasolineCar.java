package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a3.gr2_5;

public class GasolineCar extends Car implements Gasoline{
	private int emissionTier;
	
	public GasolineCar(String brand, int constructionYear, String price, int emissionTier, int id){
		super(brand, constructionYear, price, id);
	    this.emissionTier = emissionTier;
	}
	
	/**
	 * @description: Returns the emission tier
	 */
	public int getEmissionTier() {
		return emissionTier;
	}
	
	/**
	 * @description: Returns the object string
	 */
	public String toString(){
		String carInfo = super.toString();
		carInfo += " Emission Tier : " + emissionTier;
		return carInfo;
	}

}
