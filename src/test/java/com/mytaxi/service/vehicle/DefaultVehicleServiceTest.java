package com.mytaxi.service.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mytaxi.controller.mapper.VehicleMapper;
import com.mytaxi.dataaccessobject.VehicleRepository;
import com.mytaxi.datatransferobject.VehicleDTO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.EntityNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleServiceTest {
	@InjectMocks
	private DefaultVehicleService vehicleService;
	
	@Mock
	private VehicleRepository vehicleRepository;
	
	@Test
	public void testCreate() throws IOException{
		VehicleDO vehicle = getVehicle();
		when(vehicleRepository.save(any(VehicleDO.class))).thenReturn(vehicle);
		vehicleService.create(vehicle);
		assertTrue((Files.exists(Paths.get(vehicle.getId()+".txt"))));
		Files.deleteIfExists(Paths.get(vehicle.getId()+".txt"));
	}
	
	@Test
	public void testFind(){
		VehicleDO vehicle = getVehicle();
		when(vehicleRepository.findById((any(Long.class)))).thenReturn(Optional.of(vehicle));
		
		VehicleDO vehicleDO = vehicleService.find(vehicle.getId());
		assertEquals(vehicleDO.getId(), vehicle.getId());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFind_Wrong_ID(){
		VehicleDO vehicle = getVehicle();
		when(vehicleRepository.findById((any(Long.class)))).thenThrow(EntityNotFoundException.class);
		
		vehicleService.find(vehicle.getId());
	}
	
	private VehicleDO getVehicle(){
		return VehicleMapper.makeVehicleDO(VehicleDTO.newBuilder().setId(123l).setManufacturerName("Honda").setName("Honda BRV")
				.setSeatCount(2).setEngineType(EngineType.ELECTRIC).setColor("White").setConvertible(true)
				.setLicensePlate("WB1006").setRating(3).createVehicleDTO());
	}
	

}
