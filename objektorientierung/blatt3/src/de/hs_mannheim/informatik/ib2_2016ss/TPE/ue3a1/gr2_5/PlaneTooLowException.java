package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class PlaneTooLowException extends GeneralFlightSimulatorException {
	
	/**
	 * @description pass String to super class
	 * @param currentHeight
	 */
	public PlaneTooLowException(int currentHeight) {
		super("Plane is to low, current height: " + currentHeight);
	}

}
