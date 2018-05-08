package com.mytaxi.service.vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.VehicleRepository;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * The default vehicle service implementation.
 * It implements all the CRUD methods for a vehicle.
 * 
 * @author Shahbaz.Alam
 *
 */
@Service
public class DefaultVehicleService implements VehicleService {
	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultVehicleService.class);

	@Autowired
	private VehicleRepository repository;

	
	@Override
	public VehicleDO find(long vehicleId) {
		return findVehicle(vehicleId);
	}

	/**
	 * creates the vehicle
	 */
	@Override
	@Transactional
	public VehicleDO create(VehicleDO vehicleDO) {
		VehicleDO vehicle = repository.save(vehicleDO);
		createFlatFile(vehicleDO);
		return vehicle;
	}
	
	/**
	 * The method is used to create flat files with the vehicle
	 * data. It saves the vehicle's data in comma separated value
	 * structure. This is being done to enable faster searches
	 * based on vehicle attributes. 
	 * Currently it writes the file to the project directory
	 * but the idea is to store these files in amazon s3 or
	 * amazon ebs volume and run apache solr for file indexing.
	 * This complete method can be taken out and run as a separate
	 * service.
	 * 
	 */
	public void createFlatFile(VehicleDO vehicleDO) {
		try {
			Files.write(Paths.get(vehicleDO.getId()+".txt"), vehicleDO.toString().getBytes());
		} catch (IOException e) {
			LOG.error("Failed to write into file for vehicleId=" + vehicleDO.getId());
		}
	}
	
	@Override
	public void updateFlatFile(VehicleDO vehicleDO) {
		createFlatFile(vehicleDO);	
	}

	@Override
	@Transactional
	public void delete(long vehicleId) {
		VehicleDO vehicleDO = findVehicle(vehicleId);
		vehicleDO.setDeleted(true);
		updateFlatFile(vehicleDO);
	}

	@Override
	public List<VehicleDO> findAll() {
		return (List<VehicleDO>) repository.findAll();
	}
	
	@Override
	public void updateBookingStatus(VehicleDO vehicle, BookingStatus bookingStatus) {
		vehicle.setBookingStatus(bookingStatus);
		updateFlatFile(vehicle);
	}

	private VehicleDO findVehicle(long vehicleId) {
		return repository.findById(vehicleId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + vehicleId));
	}

}
