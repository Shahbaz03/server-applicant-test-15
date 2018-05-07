package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.VehicleMapper;
import com.mytaxi.datatransferobject.VehicleDTO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.vehicle.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/vehicle")
@Api(tags = {"tag1"})
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/{vehicleID}")
	public VehicleDTO getCar(@Valid @PathVariable long vehicleId) {
		return VehicleMapper.makeVehicleDTO(vehicleService.find(vehicleId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VehicleDO createCar(@Valid @RequestBody VehicleDTO vehicleDTO) throws ConstraintsViolationException {
		return vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO));
	}

	@DeleteMapping("/{vehicleID}")
	public void deleteCar(@Valid @PathVariable long vehicleId) {
		vehicleService.delete(vehicleId);
	}

	@PutMapping("/{vehicleID}")
	public void updateBookingStatus(@Valid @PathVariable long vehicleId, @RequestParam BookingStatus bookingStatus) {
		vehicleService.updateBookingStatus(vehicleService.find(vehicleId), bookingStatus);
	}

	@GetMapping
	@ApiOperation(value="It finds the vehicles")
	public List<VehicleDTO> findVehicles(@RequestParam(required = false) boolean showDeleted) {
		return VehicleMapper.makeVehicleDTOList(vehicleService.findAll(), showDeleted);
	}

}
