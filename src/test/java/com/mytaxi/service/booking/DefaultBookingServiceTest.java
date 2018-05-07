package com.mytaxi.service.booking;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.VehicleDO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.BookingException;
import com.mytaxi.service.driver.DefaultDriverService;
import com.mytaxi.service.vehicle.DefaultVehicleService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBookingServiceTest {
	@InjectMocks
	private DefaultBookingService bookingService;
	
	@Mock
	private DefaultVehicleService vehicleService;
	@Mock
	private DefaultDriverService driverService;
	
	@Test
	public void testBookVehicle(){
		BookingDTO dto = getBookingDTO();
		VehicleDO vehicle = getVehicle();
		vehicle.setBookingStatus(BookingStatus.UNBOOK);
		
		DriverDO driver = getDriver();
		driver.setOnlineStatus(OnlineStatus.ONLINE);
		
		when(driverService.find(any(Long.class))).thenReturn(driver);
		when(vehicleService.find(any(Long.class))).thenReturn(vehicle);
		
		bookingService.bookVehicle(dto);
	}
	
	@Test
	public void testBookVehicle_OfflineDriver(){
		BookingDTO dto = getBookingDTO();
		
		DriverDO driver = getDriver();
		driver.setOnlineStatus(OnlineStatus.OFFLINE);
		
		when(driverService.find(any(Long.class))).thenReturn(driver);
		try{
			bookingService.bookVehicle(dto);
		}catch(BookingException ex){
			assertEquals("Driver is not online", ex.getMessage());
		}
	}
	
	@Test
	public void testBookVehicle_DriverHasVehicle(){
		BookingDTO dto = getBookingDTO();
		
		DriverDO driver = getDriver();
		driver.setOnlineStatus(OnlineStatus.ONLINE);
		driver.setVehicleId(12l);
		
		when(driverService.find(any(Long.class))).thenReturn(driver);
		try{
			bookingService.bookVehicle(dto);
		}catch(BookingException ex){
			assertEquals("Driver already has a vehicle", ex.getMessage());
		}
	}
	
	@Test
	public void testBookVehicle_VehicleAlreadyBooked(){
		BookingDTO dto = getBookingDTO();
		
		DriverDO driver = getDriver();
		driver.setOnlineStatus(OnlineStatus.ONLINE);
		
		VehicleDO vehicle = getVehicle();
		vehicle.setBookingStatus(BookingStatus.BOOK);
		
		when(driverService.find(any(Long.class))).thenReturn(driver);
		when(vehicleService.find(any(Long.class))).thenReturn(vehicle);
		
		try{
			bookingService.bookVehicle(dto);
		}catch(BookingException ex){
			assertEquals("Vehicle Already book", ex.getMessage());
		}
	}
	
	private BookingDTO getBookingDTO(){
		BookingDTO dto = new BookingDTO();
		dto.setDriverID(12l);
		dto.setVehicleID(12345l);
		return dto;
	}
	
	private DriverDO getDriver(){
		DriverDO driver = new DriverDO("username", "password");
		return driver;
	}
	
	private VehicleDO getVehicle(){
		return new VehicleDO();
	}

}
