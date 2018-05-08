package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.domainvalue.VehicleModelDO;

@Entity
@Table(name = "vehicle")
public class VehicleDO {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	private String licensePlate;

	@Column(nullable = true)
	private Integer rating;

	@Column(nullable = false)
	private String color;
	
	@Embedded
	private VehicleModelDO model;
	
	@Column(nullable = false)
    private Boolean deleted = false;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus bookingStatus = BookingStatus.UNBOOK;
	
	@Column(nullable = true)
	private Long driverId;
	
	public boolean isUnBooked() {
		return getBookingStatus() == BookingStatus.UNBOOK;
	}
	
	public boolean isDeleted() {
		return getDeleted() == true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
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

	public VehicleModelDO getModel() {
		return model;
	}

	public void setModel(VehicleModelDO model) {
		this.model = model;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return 	"id=" + id 
				+ ",licensePlate=" + licensePlate.toUpperCase()
				+ ",rating=" + rating 
				+ ",color=" + color.toUpperCase() 
				+ ",name=" + model.getName().toUpperCase()
				+ ",seatCount=" + model.getSeatCount() 
				+ ",convertible=" + model.getConvertible()
				+ ",engineType=" + model.getEngineType().name().toUpperCase()
				+ ",manufacturer=" + model.getVehicleManufacturerDO().getManufacturerName().toUpperCase()
				+ ",deleted=" + deleted 
				+ ",bookingStatus=" + bookingStatus
				+ ",driverId=" + driverId;
	}
	
	
	
}
