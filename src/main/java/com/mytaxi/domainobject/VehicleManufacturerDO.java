package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VehicleManufacturerDO {

	@Column(nullable = false)
	private String manufacturerName;

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

}
