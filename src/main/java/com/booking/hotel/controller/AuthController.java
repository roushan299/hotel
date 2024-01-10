package com.booking.hotel.controller;

import com.booking.hotel.dto.JwtRequest;
import com.booking.hotel.dto.JwtResponse;
import com.booking.hotel.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        JwtResponse jwtResponse = authService.login(jwtRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
