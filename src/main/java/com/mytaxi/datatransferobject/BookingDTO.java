package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

public class BookingDTO {

	@NotNull(message = "Driver Id cannot be null")
	private Long driverID;

	@NotNull(message = "Vehicle Id cannot be null")
	private Long vehicleID;

	public Long getDriverID() {
		return driverID;
	}

	public void setDriverID(Long driverID) {
		this.driverID = driverID;
	}

	public Long getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(Long vehicleID) {
		this.vehicleID = vehicleID;
	}

}
