package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

public class PlaneImplementation implements Plane {
	
	private FlightRoute flightRoute;
	
	private int currentHeight = 0;
	private int kilometerCounter = 0;
	private boolean doorOpen = true;
	private boolean isFlying = false;
	
	/**
	 * @description: Constructor needs a flight route
	 * @param flightRoute
	 * @throws SimulatorConfigurationException
	 */
	public PlaneImplementation(FlightRoute flightRoute) throws SimulatorConfigurationException {
		if(flightRoute != null) {
			this.flightRoute = flightRoute;
		}
		else {
			throw new SimulatorConfigurationException();
		}
	}
	
	/**
	 * @return the flight route
	 */
	public FlightRoute getFlightRoute() {
		return flightRoute;
	}
	/**
	 * @description: Opens the doors of the plane
	 * @throws: GeneralFlightSimulatorException if plane is in the air or flies
	 * 
	 */
	public void openDoors() throws GeneralFlightSimulatorException {
		if(!isFlying && currentHeight == 0 && !doorOpen) {
			System.out.println("Open door!");
			doorOpen = true;
		}
		else {
			throw new GeneralFlightException();
		}
	}
	
	/**
	 * @description: Closes the door of the plane
	 */
	public void closeDoors() {
		if(currentHeight == 0 && doorOpen) {
			System.out.println("Close door!");
			doorOpen = false;
		}
		else {
			System.out.println("Door is already closed!");
		}
	}

	/**
	 * @description: Stops the plane if height is zero
	 * @throws GeneralFlightSimulatorException if height is not zero or is already stopped
	 */
	public void stop() throws GeneralFlightSimulatorException {
		if(isFlying && currentHeight == 0) {
			System.out.println("Plane stopped!");
			isFlying = false;
		}
		else {
			throw new GeneralFlightException();
		}
	}

	/**
	 * @description: Flies the next kilometer
	 * @throws: GeneralFlightSimulatorException if something will go wrong
	 */
	public void flyNextKilometer(int additionalHeight) throws GeneralFlightSimulatorException {
		//Set current temp height
		int tempCurrentHeight = currentHeight + additionalHeight;
		
		//If the plane is in the city
		if(flightRoute.getFlightLength() > 4 && kilometerCounter > 2 && (flightRoute.getFlightLength() - 2) < kilometerCounter) {
			//If minimum height is not allowed in the city
			if(flightRoute.getMinHeightCity() < tempCurrentHeight) {
				throw new PlaneTooLowException(currentHeight);
			}
		}
		
		//If height is to large
		if(flightRoute.getMaxHeight() < tempCurrentHeight) {
			throw new PlaneTooHighException(currentHeight);
		}
		
		//If height is negative (chrash boom!)
		if(tempCurrentHeight < 0) {
			throw new PlaneTooLowException(currentHeight);
		}
		
		//If everything is fine, change the height
		System.out.println("Fly kilometer and change altitude: " + additionalHeight);
		System.out.println("New height: " + tempCurrentHeight);
		
		currentHeight = tempCurrentHeight;
		
		isFlying = true;	
		kilometerCounter++;

	}

}
