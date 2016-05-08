package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a3.gr2_5;

public class ElectricCar extends Car implements Electric{
	private int voltage;
	
	public ElectricCar(String brand, int constructionYear, String price, String voltage, int id){
		super(brand, constructionYear, price, id);
		if(voltage =="High Voltage"){
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
	 * @description: Returns the object string
	 */
	public String toString(){
		String carInfo = super.toString();
		carInfo += " Voltage: " + voltage;
		return carInfo;
	}

}
