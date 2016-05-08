package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a3.gr2_5;

public class HybridCar extends Car implements Electric, Gasoline{
	private int emissionTier;
	private int voltage;
	
	public HybridCar(String brand, int constructionYear, String price, int emissionTier, String voltage, int id){
		super(brand, constructionYear, price, id);
		this.emissionTier = emissionTier;
		if(voltage == "High Voltage"){
			this.voltage = HIGH_VOLTAGE;
		}
		else{
			this.voltage = LOW_VOLTAGE;
		}
	}

	/**
	 * @description: returns the voltage value
	 */
	public int getVoltage() {
		return voltage;
	}
	
	/**
	 * @description: returns the emission tier
	 */
	public int getEmissionTier() {
		return emissionTier;
	}
	
	/**
	 * @description: Returns the object string
	 */
	public String toString(){
		String carInfo = super.toString();
		carInfo += " Emission Tier: " + emissionTier + " Voltage: " + voltage;
		return carInfo;
	}

}
