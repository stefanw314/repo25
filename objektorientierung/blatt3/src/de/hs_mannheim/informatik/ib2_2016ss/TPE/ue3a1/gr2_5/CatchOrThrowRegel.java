package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class CatchOrThrowRegel {
	public static void main(String[] args) {
		//Catch in parent class
		try {
			vorbereiten();
		}
		catch(Exception e) {
			System.out.println("Exception handling in parent method");
		}
		
		//Will be catched in method, no need for try-catch block
		vorbereiten2();
	}
	
	/**
	 * @description: Example for throwing exception
	 * @throws Exception
	 */
	public static void vorbereiten() throws Exception {
		System.out.println("Prepare");
		throw new Exception();
	}
	
	/**
	 * @description: Example for catching and not throwing
	 */
	public static void vorbereiten2() {
		System.out.println("Prepare");
		try {
			//Lets assume it fails and throws an exception
			throw new Exception();
		}
		catch(Exception e) {
			System.out.println("Exception handling in method");
		}
	}
	
}
