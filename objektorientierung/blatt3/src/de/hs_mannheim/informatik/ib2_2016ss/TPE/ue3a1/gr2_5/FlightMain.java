package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class FlightMain {
	
	public static void main(String[] args) throws GeneralFlightSimulatorException, SimulatorConfigurationException {		 //Cahnge dis shit
		PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{90, 100, 100, -100}, 150, 900));
		plane.closeDoors();

		while(!plane.getFlightRoute().isFinished()) {
			plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
		}
		
		plane.stop();
		plane.openDoors();
	}
	
}
