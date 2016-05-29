package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

public class FlightMainTest {

	private static int countTests = 0;
	private static int successfulTests = 0;
	private static int countPlaneTooLowException = 0;
	private static int countPlaneTooHighException = 0;
	private static int countGeneralFlightException = 0;
	private static int countGeneralFlightSimulatorException = 0;
	private static int countSimulatorConfigurationException = 0;
	
	@Test
	public void test1() {
		countTests++;
		try {
			PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{100, 60, -60, -100}, 150, 200));
			plane.closeDoors();

			while(!plane.getFlightRoute().isFinished()) {
				plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
			}
			
			plane.stop();
			plane.openDoors();
			
			successfulTests++;
		}
		catch(PlaneTooLowException e) {
			countPlaneTooLowException++;
		}
		catch(PlaneTooHighException e) {
			countPlaneTooHighException++;
		}
		catch(GeneralFlightException e) {
			countGeneralFlightException++;
		}
		catch(GeneralFlightSimulatorException e) {
			countGeneralFlightSimulatorException++;
		}
		catch(SimulatorConfigurationException e) {
			countSimulatorConfigurationException++;
		}
	}
	
	@Test
	public void test2() {
		countTests++;
		try {
			PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{100, 100, 100, -100}, 150, 200));
			plane.closeDoors();

			while(!plane.getFlightRoute().isFinished()) {
				plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
			}
			
			plane.stop();
			plane.openDoors();
			
			successfulTests++;
		}
		catch(PlaneTooLowException e) {
			countPlaneTooLowException++;
		}
		catch(PlaneTooHighException e) {
			countPlaneTooHighException++;
		}
		catch(GeneralFlightException e) {
			countGeneralFlightException++;
		}
		catch(GeneralFlightSimulatorException e) {
			countGeneralFlightSimulatorException++;
		}
		catch(SimulatorConfigurationException e) {
			countSimulatorConfigurationException++;
		}
	}
	
	@Test
	public void test3() {
		countTests++;
		try {
			PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{-100, 100, 100, -100}, 150, 200));
			plane.closeDoors();

			while(!plane.getFlightRoute().isFinished()) {
				plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
			}
			
			plane.stop();
			plane.openDoors();
			
			successfulTests++;
		}
		catch(PlaneTooLowException e) {
			countPlaneTooLowException++;
		}
		catch(PlaneTooHighException e) {
			countPlaneTooHighException++;
		}
		catch(GeneralFlightException e) {
			countGeneralFlightException++;
		}
		catch(GeneralFlightSimulatorException e) {
			countGeneralFlightSimulatorException++;
		}
		catch(SimulatorConfigurationException e) {
			countSimulatorConfigurationException++;
		}
	}
	
	@Test
	public void test4() {
		countTests++;
		try {
			PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{190, 100, 100, -100}, 150, 200));
			plane.closeDoors();

			while(!plane.getFlightRoute().isFinished()) {
				plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
			}
			
			plane.stop();
			plane.openDoors();
			
			successfulTests++;
		}
		catch(PlaneTooLowException e) {
			countPlaneTooLowException++;
		}
		catch(PlaneTooHighException e) {
			countPlaneTooHighException++;
		}
		catch(GeneralFlightException e) {
			countGeneralFlightException++;
		}
		catch(GeneralFlightSimulatorException e) {
			countGeneralFlightSimulatorException++;
		}
		catch(SimulatorConfigurationException e) {
			countSimulatorConfigurationException++;
		}
	}
	
	@Test
	public void test5() {
		countTests++;
		try {
			PlaneImplementation plane = new PlaneImplementation(new FlightRoute(new int[]{90, 100, 100, -100}, 150, 900));
			plane.closeDoors();

			while(!plane.getFlightRoute().isFinished()) {
				plane.flyNextKilometer(plane.getFlightRoute().getNextKilometer());
			}
			
			plane.stop();
			plane.openDoors();
			
			successfulTests++;
		}
		catch(PlaneTooLowException e) {
			countPlaneTooLowException++;
		}
		catch(PlaneTooHighException e) {
			countPlaneTooHighException++;
		}
		catch(GeneralFlightException e) {
			countGeneralFlightException++;
		}
		catch(GeneralFlightSimulatorException e) {
			countGeneralFlightSimulatorException++;
		}
		catch(SimulatorConfigurationException e) {
			countSimulatorConfigurationException++;
		}
	}
	
	@AfterClass
	public static void after() {
		System.out.println("Ausgeführte Tests:" + countTests);
		System.out.println("Erfolgreiche Tests:" + successfulTests);
		System.out.println("Anzahl PlaneTooLowException:" + countPlaneTooLowException);
		System.out.println("Anzahl PlaneTooHighException:" + countPlaneTooHighException);
		System.out.println("Anzahl GeneralFlightException:" + countGeneralFlightException);
		System.out.println("Anzahl GeneralFlightSimulatorException:" + countGeneralFlightSimulatorException);
		System.out.println("Anzahl SimulatorConfigurationException:" + countSimulatorConfigurationException);
	}

}
