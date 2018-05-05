package com.mytaxi.service.driver;

import static com.mytaxi.domainvalue.BookingStatus.BOOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.exception.BookingException;
import com.mytaxi.service.vehicle.VehicleService;

@Service
public class BookingService {

	private VehicleService vehicleService;
	private DriverService driverService;

	@Autowired
	public BookingService(VehicleService vehicleService, DriverService driverService) {
		this.vehicleService = vehicleService;
		this.driverService = driverService;
	}

	@Transactional
	public void bookVehicle(BookingDTO bookingDTO) {

		DriverDO driver = driverService.find(bookingDTO.getDriverID());

		if (!driver.isOnline())
			throw new BookingException("Driver is not online");

		VehicleDO vehicle = vehicleService.find(bookingDTO.getVehicleID());

		if (!vehicle.isUnBooked())
			throw new BookingException("Vehicle Already book");

		vehicleService.updateBookingStatus(vehicle, BOOK);
		driverService.assignVehicle(driver,vehicle.getId());
	}

	// assuming admin can also unbook without driver being online
	// unBookVehicle(){
	// if(vehicleIsBooked()){
	// unBookVehicle()
	// }
	// }

}
