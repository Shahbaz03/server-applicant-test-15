package com.mytaxi.constants;

/**
 * Swagger Constants for the entire application
 * 
 * @author Shahbaz.Alam
 *
 */
public class SwaggerConstants {
	
	public static final String BOOKING_CONTROLLER = "Booking Controller";
	public static final String BOOKING_CONTROLLER_DESC = "A dedicated resource for all types of book/unbook requests. Currently it "
			+ "allows drivers to book/unbook vehicle, but later will be extended to handle book requests from customers as well."
			+ "It will also be used by internal audits on booking history etc.";
	
	public static final String DRIVER_CONTROLLER = "Driver Controller";
	public static final String DRIVER_CONTROLLER_DESC = "A dedicated resource for all driver operations";
	
	public static final String HOME_CONTROLLER = "Home Controller";
	public static final String HOME_CONTROLLER_DESC = "Swagger Home Page";
	
	public static final String SEARCH_CONTROLLER = "Search Controller";
	public static final String SEARCH_CONTROLLER_DESC = "A dedicated resource for all Search. This can be used by drivers"
			+ " to search for any vehicle using an attribute like 'Black BMW'. It can also be used by internal employees"
			+ " to search for drivers and vehicles using vehicle attributes.";
	
	public static final String VEHICLE_CONTROLLER = "Vehicle Controller";
	public static final String VEHICLE_CONTROLLER_DESC = "A dedicated resource for all vehicle operations. Have kept the name"
			+ " as Vehicle to keep it general and not being specific to only cars.";

}
