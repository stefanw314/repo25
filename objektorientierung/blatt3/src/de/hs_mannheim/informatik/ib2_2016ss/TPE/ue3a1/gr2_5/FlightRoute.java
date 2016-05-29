package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a1.gr2_5;

import java.util.LinkedList;
import java.util.Queue;

public class FlightRoute {
	
	private Queue<Integer> flightStack = new LinkedList<Integer>();
	private int flightLength;
	private int minHeightCity;
	private int maxHeight;

	/**
	 * @description: Constructor for FlightRoute
	 * @param altitudeChange: contains change for each km and for flight length calculation
	 * @param minHeightCity: minimum height in city in m
	 * @param maxHeight: max height in m
	 * @throws SimulatorConfigurationException, if configuration is not valid
	 */
	public FlightRoute(int[] altitudeChange, int minHeightCity, int maxHeight) throws SimulatorConfigurationException {
		//FlightLength / Altitude change array should not be zero
		//MinHeightCity must be less than 200m because the plane can't ascend so fast (100m in 1km)
		//MaxHeight must be larger than minHeightCity
		if(altitudeChange.length == 0 || minHeightCity > 200 || maxHeight <= minHeightCity) {
			throw new SimulatorConfigurationException();
		}
		else{
			for(int change: altitudeChange){
				//Change should not be larger than 100
				if(Math.abs(change) > 100) {
					throw new SimulatorConfigurationException();
				}
				this.flightStack.add(change);
			}
			this.flightLength = altitudeChange.length;
			this.minHeightCity = minHeightCity;
			this.maxHeight = maxHeight;
		}
	}
	
	/**
	 * @return true if flight route is finished, else false
	 */
	public boolean isFinished() {
		return flightStack.isEmpty();
	}
	
	/**
	 * @return next altitude change
	 */
	public int getNextKilometer() {
		return flightStack.poll();
	}
	
	/**
	 * @return flight length
	 */
	public int getFlightLength() {
		return flightLength;
	}
	
	/**
	 * @return min height in city
	 */
	public int getMinHeightCity() {
		return minHeightCity;
	}
	
	/**
	 * @return max height
	 */
	public int getMaxHeight() {
		return maxHeight;
	}
	
}
