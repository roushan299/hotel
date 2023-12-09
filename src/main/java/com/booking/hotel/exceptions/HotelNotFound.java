package com.booking.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFound extends RuntimeException{

    public HotelNotFound() {
        super();
    }

    public HotelNotFound(String message) {
        super(message);
    }

    public HotelNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelNotFound(Throwable cause) {
        super(cause);
    }

    public HotelNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
