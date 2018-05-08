package com.mytaxi.domainvalue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class VehicleModelDO {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer seatCount = 4;

	@Column(nullable = false)
	private Boolean convertible = false;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EngineType engineType = EngineType.DIESEL;

	@Embedded
	private VehicleManufacturerDO vehicleManufacturerDO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public Boolean getConvertible() {
		return convertible;
	}

	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public VehicleManufacturerDO getVehicleManufacturerDO() {
		return vehicleManufacturerDO;
	}

	public void setVehicleManufacturerDO(VehicleManufacturerDO vehicleManufacturerDO) {
		this.vehicleManufacturerDO = vehicleManufacturerDO;
	}

}
