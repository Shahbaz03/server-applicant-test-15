package com.mytaxi.service.booking;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.BookingException;

/**
 * The functional interface is primarily
 * used for validating all the booking/unbooking
 * requests.
 * It checks all the preset conditions before
 * allowing a driver to book/unbook a vehicle
 * 
 * @author Shahbaz.Alam
 *
 * @param <T>
 */
@FunctionalInterface
public interface ValidateBooking<T> {

	void validate(T t) throws BookingException;

	ValidateBooking<DriverDO> isDriverNotOnline = (DriverDO driver) -> {
		if (!driver.isOnline())
			throw new BookingException("Driver is not online");
	};

	ValidateBooking<DriverDO> isVehicleAllocated = (DriverDO driver) -> {
		if (driver.hasVechicleAllocated())
			throw new BookingException("Driver already has a vehicle");
	};

	ValidateBooking<DriverDO> isVehicleNotAllocated = (DriverDO driver) -> {
		if (!driver.hasVechicleAllocated())
			throw new BookingException("Driver does not have a vehicle allocated");
	};
}
