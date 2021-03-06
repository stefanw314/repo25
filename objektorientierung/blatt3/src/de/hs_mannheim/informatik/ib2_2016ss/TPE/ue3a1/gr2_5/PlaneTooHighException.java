package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class PlaneTooHighException extends GeneralFlightSimulatorException {
	
	/**
	 * @description pass String to super class method
	 * @param currentHeight
	 */
	public PlaneTooHighException(int currentHeight) {
		super("Plane is to high, current height: " + currentHeight);
	}

}