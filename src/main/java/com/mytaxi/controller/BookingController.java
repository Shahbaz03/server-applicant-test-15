package com.mytaxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.BookingDTO;
import com.mytaxi.service.driver.BookingService;

@RestController
@RequestMapping("v1/book")
public class BookingController {

	private BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		super();
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
