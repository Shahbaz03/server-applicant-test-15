package com.mytaxi.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mytaxi.datatransferobject.VehicleDTO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainobject.VehicleManufacturerDO;
import com.mytaxi.domainobject.VehicleModelDO;

public class VehicleMapper {

	public static List<VehicleDTO> makeVehicleDTOList(List<VehicleDO> vehicles, boolean showDeleted) {
		return showDeleted ? 
				vehicles.stream().map(VehicleMapper::makeVehicleDTO).collect(Collectors.toList()) :
					vehicles.stream().filter(vehicle -> !vehicle.isDeleted()).map(VehicleMapper::makeVehicleDTO).collect(Collectors.toList());
	}

	public static VehicleDO makeVehicleDO(@Valid VehicleDTO vehicleDTO) {
		VehicleManufacturerDO vehicleManufacturer = new VehicleManufacturerDO();
		vehicleManufacturer.setManufacturerName(vehicleDTO.getManufacturerName());
		
		VehicleModelDO vehicleModel = new VehicleModelDO();
		vehicleModel.setConvertible(vehicleDTO.getConvertible());
		vehicleModel.setEngineType(vehicleDTO.getEngineType());
		vehicleModel.setName(vehicleDTO.getName());
		vehicleModel.setSeatCount(vehicleDTO.getSeatCount());
		vehicleModel.setVehicleManufacturerDO(vehicleManufacturer);
		
		VehicleDO vehicle = new VehicleDO();
		vehicle.setColor(vehicleDTO.getColor());
		vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
		vehicle.setModel(vehicleModel);
		
		return vehicle;
	}

	public static VehicleDTO makeVehicleDTO(VehicleDO vehicleDO) {
		VehicleDTO.VehicleDTOBuilder vehicleDTOBuilder = VehicleDTO.newBuilder()
				.setName(vehicleDO.getModel().getName())
				.setManufacturerName(vehicleDO.getModel().getVehicleManufacturerDO().getManufacturerName())
				.setColor(vehicleDO.getColor())
				.setConvertible(vehicleDO.getModel().getConvertible())
				.setEngineType(vehicleDO.getModel().getEngineType())
				.setId(vehicleDO.getId())
				.setLicensePlate(vehicleDO.getLicensePlate())
				.setRating(vehicleDO.getRating())
				.setSeatCount(vehicleDO.getModel().getSeatCount());
				
		return vehicleDTOBuilder.createVehicleDTO();
	}

}
