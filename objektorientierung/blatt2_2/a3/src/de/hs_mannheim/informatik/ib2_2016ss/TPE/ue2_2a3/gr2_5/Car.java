package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a3.gr2_5;

public abstract class Car implements Comparable<Car>{
	private int id;
	private String brand; 
	private int constructionYear;
	private String price;
	
	public Car(String brand, int constructionYear, String price, int id){
		this.brand = brand;
		this.constructionYear = constructionYear;
		this.price = price;
		this.id = id;
	}
	
	/**
	 * @description: Compares a car to another car by id
	 */
	public int compareTo (Car car){	
		if(this.id > car.id) {
			return 1;
		}
		else if(this.id < car.id) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @description: Car object to string conversion
	 */
	public String toString(){
		String carInfo ="";
		carInfo+= "Brand: " + brand + " Year of Construction:  " + constructionYear + " Current Price:  " + price;
		return carInfo;
	}
}
