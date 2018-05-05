package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Booking cannot be completed")
public class BookingException extends RuntimeException
{
    static final long serialVersionUID = -3387516993334229948L;


    public BookingException(String message)
    {
        super(message);
    }

}
