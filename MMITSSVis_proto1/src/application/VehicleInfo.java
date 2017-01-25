/* Author: 	Sam Wareing
 * File: 	VehicleInfo.java
 * Description: 
 * 		This class contains the basic data for every vehicle 
 * 
 * 
 */

package application;

import java.awt.Point;

public class VehicleInfo {
	
	private double speed; 	
	private Point gps;		
	private int tempID;
	
	
	public VehicleInfo(){
		speed = 0.0;
		tempID = 0;
		gps = new Point();
	}
	
	public VehicleInfo(int tempID){
		speed = 0.0;
		gps = new Point();
		this.tempID = tempID;
	}
	
	/* speed get/set */ 
	public double getSpeed(){ return speed; }
	public void setSpeed(double s){ speed = s;}
	
	/* tempID get/set */
	public int getTempID(){ return tempID; }
	public void setTempID(int t){ tempID = t; }
	
	/* X & Y location on map */
	public Point getGPS(){ return gps; }
	public void setGPS(Point p){ gps = p; }
	
	


}
