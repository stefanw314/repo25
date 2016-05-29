package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class PlaneTooLowException extends GeneralFlightSimulatorException {
	
	public PlaneTooLowException(int currentHeight) {
		super("Plane is to low, current height: " + currentHeight);
	}

}
