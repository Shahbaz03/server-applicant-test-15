package com.mytaxi.service.booking;

import static com.mytaxi.domainvalue.BookingStatus.BOOK;
import static com.mytaxi.service.booking.ValidateBooking.isDriverNotOnline;
import static com.mytaxi.service.booking.ValidateBooking.isVehicleAllocated;
import static com.mytaxi.service.booking.ValidateBooking.isVehicleNotAllocated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.exception.BookingException;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.vehicle.VehicleService;

@Service
public class DefaultBookingService {
	private VehicleService vehicleService;
	private DriverService driverService;

	@Autowired
	public DefaultBookingService(VehicleService vehicleService, DriverService driverService) {
		this.vehicleService = vehicleService;
		this.driverService = driverService;
	}

	@Transactional
	public void bookVehicle(BookingDTO bookingDTO) {
		DriverDO driver = driverService.find(bookingDTO.getDriverID());
		validateIfDriverCanBook(driver);

		VehicleDO vehicle = vehicleService.find(bookingDTO.getVehicleID());
		validateIfVehicleCanBeBooked(vehicle);

		bookVehicle(driver, vehicle);
		vehicleService.updateFlatFile(vehicle);	
	}

	@Transactional
	public void unBookVehicle(Long driverId) {
		DriverDO driver = driverService.find(driverId);
		validateIfDriveCanUnBook(driver);

		VehicleDO vehicle = vehicleService.find(driver.getVehicleId());

		unBookVehicle(driver, vehicle);
		vehicleService.updateFlatFile(vehicle);
	}

	private void validateIfDriveCanUnBook(DriverDO driver) {
		isDriverNotOnline.validate(driver);
		isVehicleNotAllocated.validate(driver);
	}

	private void validateIfDriverCanBook(DriverDO driver) {
		isDriverNotOnline.validate(driver);
		isVehicleAllocated.validate(driver);
	}

	private void unBookVehicle(DriverDO driver, VehicleDO vehicle) {
		driver.setVehicleId(null);
		vehicle.setDriverId(null);
		vehicle.setBookingStatus(BookingStatus.UNBOOK);
	}

	private void bookVehicle(DriverDO driver, VehicleDO vehicle) {
		vehicle.setBookingStatus(BOOK);
		vehicle.setDriverId(driver.getId());
		driver.setVehicleId(vehicle.getId());
	}

	private void validateIfVehicleCanBeBooked(VehicleDO vehicle) {
		if (!vehicle.isUnBooked())
			throw new BookingException("Vehicle Already book");
	}


}
