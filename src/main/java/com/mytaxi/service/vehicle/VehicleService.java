package com.mytaxi.service.vehicle;

import java.util.List;

import javax.validation.Valid;

import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;

/**
 * The vehicle service interface
 * 
 * @author Shahbaz.Alam
 *
 */
public interface VehicleService {

	VehicleDO find(@Valid long carId);

	VehicleDO create(VehicleDO carDO);

	void delete(@Valid long carId);

	List<VehicleDO> findAll();

	void updateBookingStatus(@Valid VehicleDO vehicleId, BookingStatus bookingStatus);
	
	void createFlatFile(VehicleDO vehicleDO);
	
	void updateFlatFile(VehicleDO vehicleDO);

}
