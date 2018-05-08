package com.mytaxi.service.booking;

import static com.mytaxi.domainvalue.BookingStatus.BOOK;
import static com.mytaxi.domainvalue.BookingStatus.UNBOOK;
import static com.mytaxi.service.booking.ValidateBooking.isDriverNotOnline;
import static com.mytaxi.service.booking.ValidateBooking.isVehicleAllocated;
import static com.mytaxi.service.booking.ValidateBooking.isVehicleNotAllocated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.exception.BookingException;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.vehicle.VehicleService;

/**
 * The default booking service implementation.
 * The service actually interacts with the
 * vehicle and driver service to make necessary 
 * updates and changes.
 * Having a separate Booking service
 * enables us to do a lot of things independently
 * which are precise for Booking a vehicle. Currently
 * it only implements a couple of methods to book and unbook
 * a vehicle. But the service is intended to implement
 * other methods such as booking history , auditing etc.
 * 
 * @author Shahbaz.Alam
 *
 */
@Service
public class DefaultBookingService implements BookingService {
	private VehicleService vehicleService;
	private DriverService driverService;

	@Autowired
	public DefaultBookingService(VehicleService vehicleService, DriverService driverService) {
		this.vehicleService = vehicleService;
		this.driverService = driverService;
	}

	/**
	 * The bookVehicle method is used to map an unbooked
	 * vehicle to an online driver. It uses the driverId 
	 * and vehicleId to validate the driver and vehicle's 
	 * eligibility for booking. 
	 * Once the validation is successful it maps the vehicle
	 * to the driver.
	 * It throws a booking exception if the validation fails.
	 */
	@Override
	@Transactional
	public void bookVehicle(BookingDTO bookingDTO) {
		DriverDO driver = driverService.find(bookingDTO.getDriverID());
		validateIfDriverCanBook(driver);

		VehicleDO vehicle = vehicleService.find(bookingDTO.getVehicleID());
		validateIfVehicleCanBeBooked(vehicle);

		bookVehicle(driver, vehicle);
		vehicleService.updateFlatFile(vehicle);	
	}

	/**
	 * The unbookVehicle method is used to remove the mapping
	 * of the vehicle and the driver. Once the driver deselcts the
	 * vehicle the method is invoked to remove the mapping and
	 * make the vehicle available for other drivers to book.
	 */
	@Override
	@Transactional
	public void unBookVehicle(Long driverId) {
		DriverDO driver = driverService.find(driverId);
		validateIfDriveCanUnBook(driver);

		VehicleDO vehicle = vehicleService.find(driver.getVehicleId());

		unBookVehicle(driver, vehicle);
		vehicleService.updateFlatFile(vehicle);
	}

	/**
	 * Validate method to check if driver
	 * can deselect a vehicle. It uses
	 * a functional interface for validation
	 * 
	 * @param driver
	 */
	private void validateIfDriveCanUnBook(DriverDO driver) {
		isDriverNotOnline.validate(driver);
		isVehicleNotAllocated.validate(driver);
	}

	/**
	 * Validate method to check if driver
	 * can select a vehicle. It uses a functional
	 * interface for the validation.
	 * 
	 * @param driver
	 */
	private void validateIfDriverCanBook(DriverDO driver) {
		isDriverNotOnline.validate(driver);
		isVehicleAllocated.validate(driver);
	}

	private void unBookVehicle(DriverDO driver, VehicleDO vehicle) {
		driver.setVehicleId(null);
		vehicle.setDriverId(null);
		vehicle.setBookingStatus(UNBOOK);
	}

	private void bookVehicle(DriverDO driver, VehicleDO vehicle) {
		vehicle.setBookingStatus(BOOK);
		vehicle.setDriverId(driver.getId());
		driver.setVehicleId(vehicle.getId());
	}

	/**
	 * Validate method to check if the vehicle is available
	 * for booking by any online driver. It throws a
	 * booking exception if the vehicle is already booked
	 * by any other driver.
	 * 
	 * @param vehicle
	 */
	private void validateIfVehicleCanBeBooked(VehicleDO vehicle) {
		if (!vehicle.isUnBooked())
			throw new BookingException("Vehicle Already book");
	}


}
