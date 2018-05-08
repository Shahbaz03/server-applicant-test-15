package com.mytaxi.service.booking;

import com.mytaxi.datatransferobject.BookingDTO;

/**
 * The Booking service interface. 
 * 
 * @author Shahbaz.Alam
 *
 */
public interface BookingService {
	
	void bookVehicle(BookingDTO bookingDTO);
	
	void unBookVehicle(Long driverId);
	
}
