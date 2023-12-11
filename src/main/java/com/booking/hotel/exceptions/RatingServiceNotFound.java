package com.booking.hotel.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RatingServiceNotFound extends HttpClientErrorException {
    public RatingServiceNotFound(HttpStatusCode statusCode) {
        super(statusCode);
    }

    public RatingServiceNotFound(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public RatingServiceNotFound(HttpStatusCode statusCode, String statusText, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, body, responseCharset);
    }

    public RatingServiceNotFound(HttpStatusCode statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, headers, body, responseCharset);
    }

    public RatingServiceNotFound(String message, HttpStatusCode statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(message, statusCode, statusText, headers, body, responseCharset);
    }
}
