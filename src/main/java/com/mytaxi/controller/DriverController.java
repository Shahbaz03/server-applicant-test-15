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

import static com.mytaxi.constants.SwaggerConstants.DRIVER_CONTROLLER;
import com.google.common.collect.Lists;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.driver.DriverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@Api(tags = {DRIVER_CONTROLLER})
@RequestMapping("v1/drivers")
public class DriverController {

	private final DriverService driverService;

	@Autowired
	public DriverController(final DriverService driverService) {
		this.driverService = driverService;
	}

	@GetMapping("/{driverId}")
	public DriverDTO getDriver(@Valid @PathVariable long driverId) {
		return DriverMapper.makeDriverDTO(driverService.find(driverId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
		return DriverMapper.makeDriverDTO(driverService.create(DriverMapper.makeDriverDO(driverDTO)));
	}

	@DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) {
        driverService.delete(driverId);
    }

	@PutMapping("/{driverId}")
	public void updateLocation(@Valid @PathVariable long driverId, @RequestParam double longitude,
			@RequestParam double latitude) {
		driverService.updateLocation(driverId, longitude, latitude);
	}

	/**
	 * This is a generic method to find drivers.
	 * It uses the query params to filter the list of drivers.
	 * 
	 * @param onlineStatus
	 * @param username
	 * @param showDeleted
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Find Drivers. Find All, Find By Username, By Online Status.")
	public List<DriverDTO> findDrivers(
			@RequestParam(required = false) OnlineStatus onlineStatus, 
			@RequestParam(required = false) String username,
			@RequestParam(required = false) boolean showDeleted) {
		if(username != null)
			return Lists.newArrayList(DriverMapper.makeDriverDTO(driverService.find(username)));
		
		if(onlineStatus != null)
			return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus), showDeleted);
		
		return DriverMapper.makeDriverDTOList(driverService.findAll(), showDeleted);
	}
	
}
