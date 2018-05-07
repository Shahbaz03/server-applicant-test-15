package com.mytaxi.service.booking;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.BookingException;

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
