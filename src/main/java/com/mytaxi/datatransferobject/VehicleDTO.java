package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {
	@JsonIgnore
    private Long id;

    @NotNull(message = "LicensePlate can not be null!")
    private String licensePlate;

    private Integer rating;
    
    @NotNull(message = "Vehicle Color can not be null!")
	private String color;
	
    @NotNull(message = "Vehicle Name can not be null!")
	private String name;
	
    @NotNull(message = "Vehicle Seat Count can not be null!")
	private Integer seatCount;

    @NotNull(message = "Specify whether the Vehicle is Convertible or Not")
	private Boolean convertible;

    @NotNull(message = "Specify the Vehicle Engine Type")
	private EngineType engineType;
	
    @NotNull(message = "Specify the Vehicle Manufacturer Name")
	private String manufacturerName;
    
    private VehicleDTO(){
    	
    }
    
    private VehicleDTO(Long id, String licensePlate, Integer rating, String color, String name, 
    		Integer seatCount, Boolean convertible, EngineType engineType, String manufacturerName){
    	this.id = id;
    	this.licensePlate = licensePlate;
    	this.rating = rating;
    	this.color = color;
    	this.name = name;
    	this.seatCount = seatCount;
    	this.convertible = convertible;
    	this.engineType = engineType;
    	this.manufacturerName = manufacturerName;
    }
    
    public static VehicleDTOBuilder newBuilder(){
    	return new VehicleDTOBuilder();
    }
    
    public static class VehicleDTOBuilder {
        private Long id;
        private String licensePlate;
        private Integer rating;
    	private String color;
    	private String name;
    	private Integer seatCount;
    	private Boolean convertible;
    	private EngineType engineType;
    	private String manufacturerName;
    	
		public VehicleDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public VehicleDTOBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public VehicleDTOBuilder setRating(Integer rating) {
			this.rating = rating;
			return this;
		}

		public VehicleDTOBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public VehicleDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public VehicleDTOBuilder setSeatCount(Integer seatCount) {
			this.seatCount = seatCount;
			return this;
		}

		public VehicleDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}

		public VehicleDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}

		public VehicleDTOBuilder setManufacturerName(String manufacturerName) {
			this.manufacturerName = manufacturerName;
			return this;
		}

		public VehicleDTO createVehicleDTO(){
	        return new VehicleDTO(id, licensePlate, rating, color, name, 
	        		seatCount, convertible, engineType, manufacturerName);
	    }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

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

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
   
}
