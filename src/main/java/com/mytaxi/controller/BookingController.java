package com.mytaxi.controller;

import static com.mytaxi.constants.SwaggerConstants.BOOKING_CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.service.booking.BookingService;

import io.swagger.annotations.Api;

/**
 * The controller serves all the booking 
 * requests. 
 * The idea of having a separate controller
 * for book/unbook requests is to have a dedicated
 * resource for all booking transactions. 
 * At present, a driver can select/deselect
 * a vehicle using this. But going forward
 * this controller can also be used by the customers
 * to book their requests. It will only require us 
 * to extend the existing controller to accept requests
 * from the customer.
 * Also, we will be needing a booking history to be mantained
 * such as, which vehicle was with which driver for what period
 * of time. 
 * All this, and many more can be achieved easily with this
 * separate dedicated controller.
 * 
 * 
 * @author Shahbaz.Alam
 *
 */
@RestController
@Api(tags = {BOOKING_CONTROLLER})
@RequestMapping("v1/book")
public class BookingController {

	private BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void book(@RequestBody BookingDTO bookingDTO) {
		bookingService.bookVehicle(bookingDTO);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void unBook(@RequestBody BookingDTO bookingDTO) {

	}

}
