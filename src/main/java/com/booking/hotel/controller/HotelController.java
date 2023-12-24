package com.booking.hotel.controller;

import com.booking.hotel.dto.HotelRequest;
import com.booking.hotel.exceptions.BadRequestException;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/create")
    public void createHotel(@Valid @RequestBody HotelRequest hotel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new BadRequestException("Request Not Valid");
        }
        hotelService.createHotel(hotel);
    }

    @GetMapping("/id/{id}")
    public Hotel getHotelById(@PathVariable("id") Long id){
       Hotel hotel =  hotelService.getHotelById(id);
       return hotel;
    }

    @GetMapping("/getAll")
    public List<Hotel> getAllHotels(){
        List<Hotel> hotelList = hotelService.getAllHotels();
        return hotelList;
    }

    @DeleteMapping("/remove/id/{id}")
    public void deleteHotelById(@PathVariable("id") Long id){
        this.hotelService.deleteHotelById(id);
    }

    @PutMapping("/update")
    public void updateHotel(@RequestBody Hotel hotel){
        this.hotelService.updateHotel(hotel);
    }

}
