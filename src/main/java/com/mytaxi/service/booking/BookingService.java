package com.mytaxi.service.booking;

import com.mytaxi.datatransferobject.BookingDTO;

public interface BookingService {
	
	void bookVehicle(BookingDTO bookingDTO);
	
	void unBookVehicle(Long driverId);
	
}
