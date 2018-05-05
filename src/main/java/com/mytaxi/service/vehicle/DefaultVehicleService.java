package com.mytaxi.service.vehicle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.VehicleRepository;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class DefaultVehicleService implements VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Override
	public VehicleDO find(long vehicleId) {
		return findVehicle(vehicleId);
	}

	@Override
	@Transactional
	public VehicleDO create(VehicleDO vehicleDO) {
		return repository.save(vehicleDO);
	}

	@Override
	@Transactional
	public void delete(long vehicleId) {
		VehicleDO vehicleDO = findVehicle(vehicleId);
		vehicleDO.setDeleted(true);
	}

	@Override
	public List<VehicleDO> findAll() {
		return (List<VehicleDO>) repository.findAll();
	}

	private VehicleDO findVehicle(long vehicleId) {
		return repository.findById(vehicleId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + vehicleId));
	}

	@Override
	public void updateBookingStatus(@Valid VehicleDO vehicle, BookingStatus bookingStatus) {
		vehicle.setBookingStatus(bookingStatus);
	}

}
